package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class induk (abstract) untuk semua DAO. Berisi kode JDBC yang berulang
 * (membuka koneksi, mengisi parameter, membaca hasil) agar tiap DAO hanya
 * menulis query dan cara membentuk objek dari satu baris hasil.
 *
 * @param <T> jenis entitas yang diolah
 */
public abstract class BaseDAO<T> implements DAO<T> {

    /** Mengubah satu baris ResultSet menjadi objek. Boleh mengembalikan null untuk melewati baris. */
    protected abstract T buatObjek(ResultSet rs) throws SQLException;

    protected List<T> ambilDaftar(String sql, Object... parameter) throws SQLException {
        List<T> hasil = new ArrayList<>();
        try (Connection koneksi = Koneksi.getKoneksi();
             PreparedStatement ps = koneksi.prepareStatement(sql)) {
            isiParameter(ps, parameter);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    T objek = buatObjek(rs);
                    if (objek != null) {
                        hasil.add(objek);
                    }
                }
            }
        }
        return hasil;
    }

    protected T ambilSatu(String sql, Object... parameter) throws SQLException {
        List<T> hasil = ambilDaftar(sql, parameter);
        return hasil.isEmpty() ? null : hasil.get(0);
    }

    /** Menjalankan query COUNT dan mengembalikan angka pertama. */
    protected int hitung(String sql, Object... parameter) throws SQLException {
        try (Connection koneksi = Koneksi.getKoneksi();
             PreparedStatement ps = koneksi.prepareStatement(sql)) {
            isiParameter(ps, parameter);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    /** INSERT / UPDATE / DELETE. Mengembalikan jumlah baris yang terpengaruh. */
    protected int jalankan(String sql, Object... parameter) throws SQLException {
        try (Connection koneksi = Koneksi.getKoneksi()) {
            return jalankan(koneksi, sql, parameter);
        }
    }

    /** Versi untuk transaksi: memakai koneksi yang sudah dibuka pemanggil. */
    protected int jalankan(Connection koneksi, String sql, Object... parameter) throws SQLException {
        try (PreparedStatement ps = koneksi.prepareStatement(sql)) {
            isiParameter(ps, parameter);
            return ps.executeUpdate();
        }
    }

    /** INSERT dan mengembalikan id hasil auto increment (0 jika tidak ada). */
    protected int sisipkan(String sql, Object... parameter) throws SQLException {
        try (Connection koneksi = Koneksi.getKoneksi()) {
            return sisipkan(koneksi, sql, parameter);
        }
    }

    protected int sisipkan(Connection koneksi, String sql, Object... parameter) throws SQLException {
        try (PreparedStatement ps = koneksi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            isiParameter(ps, parameter);
            ps.executeUpdate();
            try (ResultSet kunci = ps.getGeneratedKeys()) {
                return kunci.next() ? kunci.getInt(1) : 0;
            }
        }
    }

    private static void isiParameter(PreparedStatement ps, Object[] parameter) throws SQLException {
        for (int i = 0; i < parameter.length; i++) {
            ps.setObject(i + 1, parameter[i]);
        }
    }

    /** Membungkus kata kunci untuk operator LIKE. */
    protected static String kataKunci(String teks) {
        return "%" + (teks == null ? "" : teks.trim()) + "%";
    }

    /** Membuat array berisi nilai yang sama sebanyak jumlah (untuk LIKE di banyak kolom). */
    protected static Object[] ulang(Object nilai, int jumlah) {
        Object[] hasil = new Object[jumlah];
        for (int i = 0; i < jumlah; i++) {
            hasil[i] = nilai;
        }
        return hasil;
    }

    protected static String rapikan(String teks) {
        return teks == null ? null : teks.trim();
    }

    /** Teks kosong disimpan sebagai NULL di database. */
    protected static String kosongJadiNull(String teks) {
        return (teks == null || teks.isBlank()) ? null : teks.trim();
    }
}
