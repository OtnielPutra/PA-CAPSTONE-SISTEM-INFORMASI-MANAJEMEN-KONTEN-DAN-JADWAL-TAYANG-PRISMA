package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import model.LampiranKonten;

/** Akses data tabel `lampiran_konten` (digabung dengan judul konten untuk tampilan). */
public class LampiranKontenDAO extends BaseDAO<LampiranKonten> {

    private static final String SQL_PILIH = """
            SELECT l.id_lampiran, l.nama_file, l.lokasi_file, l.tanggal_unggah,
                   l.konten_id_konten, l.petugas_konten_id_pengguna, k.judul_konten
            FROM lampiran_konten l
            JOIN konten k ON k.id_konten = l.konten_id_konten
            """;

    private static final String URUTAN = " ORDER BY l.id_lampiran";

    @Override
    protected LampiranKonten buatObjek(ResultSet rs) throws SQLException {
        LampiranKonten l = new LampiranKonten();
        l.setIdLampiran(rs.getInt("id_lampiran"));
        l.setNamaFile(rs.getString("nama_file"));
        l.setLokasiFile(rs.getString("lokasi_file"));
        l.setTanggalUnggah(rs.getObject("tanggal_unggah", LocalDateTime.class));
        l.setIdKonten(rs.getInt("konten_id_konten"));
        l.setIdPetugasKonten(rs.getInt("petugas_konten_id_pengguna"));
        l.setJudulKonten(rs.getString("judul_konten"));
        return l;
    }

    @Override
    public List<LampiranKonten> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    /** Semua lampiran milik satu konten. */
    public List<LampiranKonten> getByKonten(int idKonten) throws SQLException {
        return ambilDaftar(SQL_PILIH + " WHERE l.konten_id_konten = ?" + URUTAN, idKonten);
    }

    @Override
    public List<LampiranKonten> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE l.nama_file LIKE ? OR k.judul_konten LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 2));
    }

    @Override
    public LampiranKonten getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE l.id_lampiran = ?", id);
    }

    @Override
    public boolean tambah(LampiranKonten l) throws SQLException {
        int id = sisipkan("""
                INSERT INTO lampiran_konten (nama_file, lokasi_file, tanggal_unggah,
                                             konten_id_konten, petugas_konten_id_pengguna)
                VALUES (?, ?, ?, ?, ?)
                """,
                rapikan(l.getNamaFile()), rapikan(l.getLokasiFile()), l.getTanggalUnggah(),
                l.getIdKonten(), l.getIdPetugasKonten());
        l.setIdLampiran(id);
        return id > 0;
    }

    @Override
    public boolean ubah(LampiranKonten l) throws SQLException {
        return jalankan("""
                UPDATE lampiran_konten
                SET nama_file = ?, lokasi_file = ?, tanggal_unggah = ?, konten_id_konten = ?
                WHERE id_lampiran = ?
                """,
                rapikan(l.getNamaFile()), rapikan(l.getLokasiFile()), l.getTanggalUnggah(),
                l.getIdKonten(), l.getIdLampiran()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM lampiran_konten WHERE id_lampiran = ?", id) > 0;
    }
}
