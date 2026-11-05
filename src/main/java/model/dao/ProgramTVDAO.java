package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.ProgramTV;

/** Akses data tabel `program_tv`. */
public class ProgramTVDAO extends BaseDAO<ProgramTV> {

    private static final String SQL_PILIH = """
            SELECT id_program, nama_program, kategori_program, url_program,
                   status_program, admin_id_pengguna
            FROM program_tv
            """;

    private static final String URUTAN = " ORDER BY id_program";

    @Override
    protected ProgramTV buatObjek(ResultSet rs) throws SQLException {
        ProgramTV program = new ProgramTV();
        program.setIdProgram(rs.getInt("id_program"));
        program.setNamaProgram(rs.getString("nama_program"));
        program.setKategoriProgram(rs.getString("kategori_program"));
        program.setUrlProgram(rs.getString("url_program"));
        program.setStatusProgram(rs.getString("status_program"));
        program.setIdAdmin(rs.getInt("admin_id_pengguna"));
        return program;
    }

    @Override
    public List<ProgramTV> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<ProgramTV> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE nama_program LIKE ? OR kategori_program LIKE ? OR status_program LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 3));
    }

    @Override
    public ProgramTV getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE id_program = ?", id);
    }

    @Override
    public boolean tambah(ProgramTV program) throws SQLException {
        int id = sisipkan("""
                INSERT INTO program_tv (nama_program, kategori_program, url_program, status_program, admin_id_pengguna)
                VALUES (?, ?, ?, ?, ?)
                """,
                rapikan(program.getNamaProgram()), program.getKategoriProgram(),
                kosongJadiNull(program.getUrlProgram()), program.getStatusProgram(), program.getIdAdmin());
        program.setIdProgram(id);
        return id > 0;
    }

    @Override
    public boolean ubah(ProgramTV program) throws SQLException {
        return jalankan("""
                UPDATE program_tv
                SET nama_program = ?, kategori_program = ?, url_program = ?, status_program = ?
                WHERE id_program = ?
                """,
                rapikan(program.getNamaProgram()), program.getKategoriProgram(),
                kosongJadiNull(program.getUrlProgram()), program.getStatusProgram(),
                program.getIdProgram()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM program_tv WHERE id_program = ?", id) > 0;
    }
}
