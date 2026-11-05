package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Mitra;

/** Akses data tabel `mitra`. */
public class MitraDAO extends BaseDAO<Mitra> {

    private static final String SQL_PILIH = """
            SELECT id_mitra, nama_mitra, jenis_mitra, email, alamat, status_mitra
            FROM mitra
            """;

    private static final String URUTAN = " ORDER BY id_mitra";

    @Override
    protected Mitra buatObjek(ResultSet rs) throws SQLException {
        Mitra mitra = new Mitra();
        mitra.setIdMitra(rs.getInt("id_mitra"));
        mitra.setNamaMitra(rs.getString("nama_mitra"));
        mitra.setJenisMitra(rs.getString("jenis_mitra"));
        mitra.setEmail(rs.getString("email"));
        mitra.setAlamat(rs.getString("alamat"));
        mitra.setStatusMitra(rs.getString("status_mitra"));
        return mitra;
    }

    @Override
    public List<Mitra> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<Mitra> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE nama_mitra LIKE ? OR jenis_mitra LIKE ? OR email LIKE ? OR alamat LIKE ? OR status_mitra LIKE ?"
                + URUTAN, ulang(kataKunci(kataKunci), 5));
    }

    @Override
    public Mitra getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE id_mitra = ?", id);
    }

    @Override
    public boolean tambah(Mitra mitra) throws SQLException {
        int id = sisipkan("""
                INSERT INTO mitra (nama_mitra, jenis_mitra, email, alamat, status_mitra)
                VALUES (?, ?, ?, ?, ?)
                """,
                rapikan(mitra.getNamaMitra()), rapikan(mitra.getJenisMitra()), rapikan(mitra.getEmail()),
                rapikan(mitra.getAlamat()), mitra.getStatusMitra());
        mitra.setIdMitra(id);
        return id > 0;
    }

    @Override
    public boolean ubah(Mitra mitra) throws SQLException {
        return jalankan("""
                UPDATE mitra
                SET nama_mitra = ?, jenis_mitra = ?, email = ?, alamat = ?, status_mitra = ?
                WHERE id_mitra = ?
                """,
                rapikan(mitra.getNamaMitra()), rapikan(mitra.getJenisMitra()), rapikan(mitra.getEmail()),
                rapikan(mitra.getAlamat()), mitra.getStatusMitra(), mitra.getIdMitra()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM mitra WHERE id_mitra = ?", id) > 0;
    }
}
