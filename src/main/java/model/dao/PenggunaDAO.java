package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Admin;
import model.Pengguna;
import model.PetugasJadwal;
import model.PetugasKonten;

/**
 * Akses data akun. Satu akun tersimpan di tabel `pengguna` ditambah satu baris
 * di tabel peran (admin / petugas_jadwal / petugas_konten). Objek yang
 * dikembalikan adalah subclass Pengguna sesuai perannya (polymorphism).
 */
public class PenggunaDAO extends BaseDAO<Pengguna> {

    private static final String SQL_PILIH = """
            SELECT p.id_pengguna, p.nama_lengkap, p.username, p.password, p.status_akun,
                   a.tanggal_penugasan AS tgl_admin,
                   pj.cakupan_jadwal, pj.tanggal_penugasan AS tgl_jadwal,
                   pk.spesialisasi_konten, pk.tanggal_penugasan AS tgl_konten
            FROM pengguna p
            LEFT JOIN admin a ON a.id_pengguna = p.id_pengguna
            LEFT JOIN petugas_jadwal pj ON pj.id_pengguna = p.id_pengguna
            LEFT JOIN petugas_konten pk ON pk.id_pengguna = p.id_pengguna
            """;

    private static final String URUTAN = " ORDER BY p.id_pengguna";

    @Override
    protected Pengguna buatObjek(ResultSet rs) throws SQLException {
        LocalDate tglAdmin = rs.getObject("tgl_admin", LocalDate.class);
        LocalDate tglJadwal = rs.getObject("tgl_jadwal", LocalDate.class);
        LocalDate tglKonten = rs.getObject("tgl_konten", LocalDate.class);

        Pengguna pengguna;
        if (tglAdmin != null) {
            pengguna = new Admin();
            pengguna.setTanggalPenugasan(tglAdmin);
        } else if (tglJadwal != null) {
            PetugasJadwal petugas = new PetugasJadwal();
            petugas.setCakupanJadwal(rs.getString("cakupan_jadwal"));
            petugas.setTanggalPenugasan(tglJadwal);
            pengguna = petugas;
        } else if (tglKonten != null) {
            PetugasKonten petugas = new PetugasKonten();
            petugas.setSpesialisasiKonten(rs.getString("spesialisasi_konten"));
            petugas.setTanggalPenugasan(tglKonten);
            pengguna = petugas;
        } else {
            return null; // akun tanpa peran dilewati
        }

        pengguna.setIdPengguna(rs.getInt("id_pengguna"));
        pengguna.setNamaLengkap(rs.getString("nama_lengkap"));
        pengguna.setUsername(rs.getString("username"));
        pengguna.setPassword(rs.getString("password"));
        pengguna.setStatusAkun(rs.getString("status_akun"));
        return pengguna;
    }

    /**
     * Verifikasi login. Mengembalikan null jika username tidak terdaftar,
     * akun nonaktif, atau password salah (password dibandingkan persis, huruf besar/kecil dibedakan).
     */
    public Pengguna login(String username, String password) throws SQLException {
        Pengguna pengguna = ambilSatu(SQL_PILIH
                + " WHERE p.username = ? AND p.status_akun = 'aktif'", username);
        if (pengguna != null && pengguna.getPassword() != null
                && pengguna.getPassword().equals(password)) {
            return pengguna;
        }
        return null;
    }

    @Override
    public List<Pengguna> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<Pengguna> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE p.nama_lengkap LIKE ? OR p.username LIKE ? OR p.status_akun LIKE ?"
                + " OR pj.cakupan_jadwal LIKE ? OR pk.spesialisasi_konten LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 5));
    }

    @Override
    public Pengguna getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE p.id_pengguna = ?", id);
    }

    @Override
    public boolean tambah(Pengguna pengguna) throws SQLException {
        try (Connection koneksi = Koneksi.getKoneksi()) {
            koneksi.setAutoCommit(false);
            try {
                int id = sisipkan(koneksi,
                        "INSERT INTO pengguna (nama_lengkap, username, password, status_akun) VALUES (?, ?, ?, ?)",
                        rapikan(pengguna.getNamaLengkap()), rapikan(pengguna.getUsername()),
                        pengguna.getPassword(), pengguna.getStatusAkun());
                pengguna.setIdPengguna(id);
                simpanPeran(koneksi, pengguna);
                koneksi.commit();
                return true;
            } catch (SQLException e) {
                koneksi.rollback();
                throw e;
            }
        }
    }

    @Override
    public boolean ubah(Pengguna pengguna) throws SQLException {
        Pengguna lama = getById(pengguna.getIdPengguna());
        if (lama == null) {
            return false;
        }
        if (pengguna.getTanggalPenugasan() == null) {
            pengguna.setTanggalPenugasan(lama.getTanggalPenugasan());
        }

        try (Connection koneksi = Koneksi.getKoneksi()) {
            koneksi.setAutoCommit(false);
            try {
                jalankan(koneksi,
                        "UPDATE pengguna SET nama_lengkap = ?, username = ?, password = ?, status_akun = ? WHERE id_pengguna = ?",
                        rapikan(pengguna.getNamaLengkap()), rapikan(pengguna.getUsername()),
                        pengguna.getPassword(), pengguna.getStatusAkun(), pengguna.getIdPengguna());

                if (lama.getRole().equals(pengguna.getRole())) {
                    ubahPeran(koneksi, pengguna);
                } else {
                    // Peran diganti: hapus baris peran lama, buat baris peran baru
                    hapusPeran(koneksi, lama.getIdPengguna());
                    simpanPeran(koneksi, pengguna);
                }
                koneksi.commit();
                return true;
            } catch (SQLException e) {
                koneksi.rollback();
                throw e;
            }
        }
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        try (Connection koneksi = Koneksi.getKoneksi()) {
            koneksi.setAutoCommit(false);
            try {
                hapusPeran(koneksi, id);
                int jumlah = jalankan(koneksi, "DELETE FROM pengguna WHERE id_pengguna = ?", id);
                koneksi.commit();
                return jumlah > 0;
            } catch (SQLException e) {
                koneksi.rollback();
                throw e;
            }
        }
    }

    // ---------- bagian khusus peran ----------

    private void simpanPeran(Connection koneksi, Pengguna pengguna) throws SQLException {
        if (pengguna instanceof Admin) {
            jalankan(koneksi, "INSERT INTO admin (id_pengguna, tanggal_penugasan) VALUES (?, ?)",
                    pengguna.getIdPengguna(), pengguna.getTanggalPenugasan());
        } else if (pengguna instanceof PetugasJadwal petugas) {
            jalankan(koneksi, "INSERT INTO petugas_jadwal (id_pengguna, cakupan_jadwal, tanggal_penugasan) VALUES (?, ?, ?)",
                    pengguna.getIdPengguna(), rapikan(petugas.getCakupanJadwal()), pengguna.getTanggalPenugasan());
        } else if (pengguna instanceof PetugasKonten petugas) {
            jalankan(koneksi, "INSERT INTO petugas_konten (id_pengguna, spesialisasi_konten, tanggal_penugasan) VALUES (?, ?, ?)",
                    pengguna.getIdPengguna(), rapikan(petugas.getSpesialisasiKonten()), pengguna.getTanggalPenugasan());
        }
    }

    private void ubahPeran(Connection koneksi, Pengguna pengguna) throws SQLException {
        if (pengguna instanceof Admin) {
            jalankan(koneksi, "UPDATE admin SET tanggal_penugasan = ? WHERE id_pengguna = ?",
                    pengguna.getTanggalPenugasan(), pengguna.getIdPengguna());
        } else if (pengguna instanceof PetugasJadwal petugas) {
            jalankan(koneksi, "UPDATE petugas_jadwal SET cakupan_jadwal = ?, tanggal_penugasan = ? WHERE id_pengguna = ?",
                    rapikan(petugas.getCakupanJadwal()), pengguna.getTanggalPenugasan(), pengguna.getIdPengguna());
        } else if (pengguna instanceof PetugasKonten petugas) {
            jalankan(koneksi, "UPDATE petugas_konten SET spesialisasi_konten = ?, tanggal_penugasan = ? WHERE id_pengguna = ?",
                    rapikan(petugas.getSpesialisasiKonten()), pengguna.getTanggalPenugasan(), pengguna.getIdPengguna());
        }
    }

    private void hapusPeran(Connection koneksi, int idPengguna) throws SQLException {
        jalankan(koneksi, "DELETE FROM admin WHERE id_pengguna = ?", idPengguna);
        jalankan(koneksi, "DELETE FROM petugas_jadwal WHERE id_pengguna = ?", idPengguna);
        jalankan(koneksi, "DELETE FROM petugas_konten WHERE id_pengguna = ?", idPengguna);
    }
}
