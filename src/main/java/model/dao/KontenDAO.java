package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Konten;

/** Akses data tabel `konten` (digabung dengan nama program dan nama petugas untuk tampilan). */
public class KontenDAO extends BaseDAO<Konten> {

    private static final String SQL_PILIH = """
            SELECT k.id_konten, k.judul_konten, k.jenis_konten, k.kategori, k.ringkasan_konten,
                   k.tanggal_pencatatan, k.status_konten, k.program_tv_id_program,
                   k.petugas_konten_id_pengguna, pr.nama_program, pg.nama_lengkap
            FROM konten k
            LEFT JOIN program_tv pr ON pr.id_program = k.program_tv_id_program
            JOIN pengguna pg ON pg.id_pengguna = k.petugas_konten_id_pengguna
            """;

    private static final String URUTAN = " ORDER BY k.id_konten";

    @Override
    protected Konten buatObjek(ResultSet rs) throws SQLException {
        Konten k = new Konten();
        k.setIdKonten(rs.getInt("id_konten"));
        k.setJudulKonten(rs.getString("judul_konten"));
        k.setJenisKonten(rs.getString("jenis_konten"));
        k.setKategori(rs.getString("kategori"));
        k.setRingkasanKonten(rs.getString("ringkasan_konten"));
        k.setTanggalPencatatan(rs.getObject("tanggal_pencatatan", LocalDate.class));
        k.setStatusKonten(rs.getString("status_konten"));
        k.setIdProgram(rs.getObject("program_tv_id_program", Integer.class));
        k.setIdPetugasKonten(rs.getInt("petugas_konten_id_pengguna"));
        k.setNamaProgram(rs.getString("nama_program"));
        k.setNamaPetugas(rs.getString("nama_lengkap"));
        return k;
    }

    @Override
    public List<Konten> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<Konten> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE k.judul_konten LIKE ? OR k.jenis_konten LIKE ? OR k.kategori LIKE ?"
                + " OR k.status_konten LIKE ? OR pr.nama_program LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 5));
    }

    @Override
    public Konten getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE k.id_konten = ?", id);
    }

    @Override
    public boolean tambah(Konten k) throws SQLException {
        int id = sisipkan("""
                INSERT INTO konten (judul_konten, jenis_konten, kategori, ringkasan_konten, tanggal_pencatatan,
                                    status_konten, program_tv_id_program, petugas_konten_id_pengguna)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """,
                rapikan(k.getJudulKonten()), k.getJenisKonten(), rapikan(k.getKategori()),
                rapikan(k.getRingkasanKonten()), k.getTanggalPencatatan(), k.getStatusKonten(),
                k.getIdProgram(), k.getIdPetugasKonten());
        k.setIdKonten(id);
        return id > 0;
    }

    @Override
    public boolean ubah(Konten k) throws SQLException {
        return jalankan("""
                UPDATE konten
                SET judul_konten = ?, jenis_konten = ?, kategori = ?, ringkasan_konten = ?,
                    status_konten = ?, program_tv_id_program = ?
                WHERE id_konten = ?
                """,
                rapikan(k.getJudulKonten()), k.getJenisKonten(), rapikan(k.getKategori()),
                rapikan(k.getRingkasanKonten()), k.getStatusKonten(), k.getIdProgram(),
                k.getIdKonten()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM konten WHERE id_konten = ?", id) > 0;
    }
}
