package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import model.PublikasiKonten;

/** Akses data tabel `publikasi_konten` (digabung dengan judul konten dan nama kanal untuk tampilan). */
public class PublikasiKontenDAO extends BaseDAO<PublikasiKonten> {

    private static final String SQL_PILIH = """
            SELECT p.id_publikasi, p.tanggal_publikasi, p.status_publikasi, p.link_publikasi,
                   p.konten_id_konten, p.kanal_id_kanal, p.petugas_konten_id_pengguna,
                   k.judul_konten, c.nama_kanal
            FROM publikasi_konten p
            JOIN konten k ON k.id_konten = p.konten_id_konten
            JOIN kanal c ON c.id_kanal = p.kanal_id_kanal
            """;

    private static final String URUTAN = " ORDER BY p.id_publikasi";

    @Override
    protected PublikasiKonten buatObjek(ResultSet rs) throws SQLException {
        PublikasiKonten p = new PublikasiKonten();
        p.setIdPublikasi(rs.getInt("id_publikasi"));
        p.setTanggalPublikasi(rs.getObject("tanggal_publikasi", LocalDateTime.class));
        p.setStatusPublikasi(rs.getString("status_publikasi"));
        p.setLinkPublikasi(rs.getString("link_publikasi"));
        p.setIdKonten(rs.getInt("konten_id_konten"));
        p.setIdKanal(rs.getInt("kanal_id_kanal"));
        p.setIdPetugasKonten(rs.getInt("petugas_konten_id_pengguna"));
        p.setJudulKonten(rs.getString("judul_konten"));
        p.setNamaKanal(rs.getString("nama_kanal"));
        return p;
    }

    @Override
    public List<PublikasiKonten> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<PublikasiKonten> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE k.judul_konten LIKE ? OR c.nama_kanal LIKE ? OR p.status_publikasi LIKE ?"
                + " OR p.link_publikasi LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 4));
    }

    @Override
    public PublikasiKonten getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE p.id_publikasi = ?", id);
    }

    @Override
    public boolean tambah(PublikasiKonten p) throws SQLException {
        int id = sisipkan("""
                INSERT INTO publikasi_konten (tanggal_publikasi, status_publikasi, link_publikasi,
                                              konten_id_konten, kanal_id_kanal, petugas_konten_id_pengguna)
                VALUES (?, ?, ?, ?, ?, ?)
                """,
                p.getTanggalPublikasi(), p.getStatusPublikasi(), kosongJadiNull(p.getLinkPublikasi()),
                p.getIdKonten(), p.getIdKanal(), p.getIdPetugasKonten());
        p.setIdPublikasi(id);
        return id > 0;
    }

    @Override
    public boolean ubah(PublikasiKonten p) throws SQLException {
        return jalankan("""
                UPDATE publikasi_konten
                SET tanggal_publikasi = ?, status_publikasi = ?, link_publikasi = ?,
                    konten_id_konten = ?, kanal_id_kanal = ?
                WHERE id_publikasi = ?
                """,
                p.getTanggalPublikasi(), p.getStatusPublikasi(), kosongJadiNull(p.getLinkPublikasi()),
                p.getIdKonten(), p.getIdKanal(), p.getIdPublikasi()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM publikasi_konten WHERE id_publikasi = ?", id) > 0;
    }
}
