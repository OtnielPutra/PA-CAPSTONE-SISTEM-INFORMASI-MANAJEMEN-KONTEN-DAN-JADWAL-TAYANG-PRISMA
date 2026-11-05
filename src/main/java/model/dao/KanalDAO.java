package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Kanal;

/** Akses data tabel `kanal`. */
public class KanalDAO extends BaseDAO<Kanal> {

    private static final String SQL_PILIH = """
            SELECT id_kanal, nama_kanal, jenis_kanal, platform, status_kanal
            FROM kanal
            """;

    private static final String URUTAN = " ORDER BY id_kanal";

    @Override
    protected Kanal buatObjek(ResultSet rs) throws SQLException {
        Kanal kanal = new Kanal();
        kanal.setIdKanal(rs.getInt("id_kanal"));
        kanal.setNamaKanal(rs.getString("nama_kanal"));
        kanal.setJenisKanal(rs.getString("jenis_kanal"));
        kanal.setPlatform(rs.getString("platform"));
        kanal.setStatusKanal(rs.getString("status_kanal"));
        return kanal;
    }

    @Override
    public List<Kanal> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<Kanal> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE nama_kanal LIKE ? OR jenis_kanal LIKE ? OR platform LIKE ? OR status_kanal LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 4));
    }

    @Override
    public Kanal getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE id_kanal = ?", id);
    }

    @Override
    public boolean tambah(Kanal kanal) throws SQLException {
        int id = sisipkan(
                "INSERT INTO kanal (nama_kanal, jenis_kanal, platform, status_kanal) VALUES (?, ?, ?, ?)",
                rapikan(kanal.getNamaKanal()), kanal.getJenisKanal(),
                rapikan(kanal.getPlatform()), kanal.getStatusKanal());
        kanal.setIdKanal(id);
        return id > 0;
    }

    @Override
    public boolean ubah(Kanal kanal) throws SQLException {
        return jalankan(
                "UPDATE kanal SET nama_kanal = ?, jenis_kanal = ?, platform = ?, status_kanal = ? WHERE id_kanal = ?",
                rapikan(kanal.getNamaKanal()), kanal.getJenisKanal(),
                rapikan(kanal.getPlatform()), kanal.getStatusKanal(), kanal.getIdKanal()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM kanal WHERE id_kanal = ?", id) > 0;
    }
}
