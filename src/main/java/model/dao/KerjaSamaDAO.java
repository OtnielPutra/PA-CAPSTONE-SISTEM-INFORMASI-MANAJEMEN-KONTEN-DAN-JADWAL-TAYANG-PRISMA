package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.KerjaSama;

/** Akses data tabel `kerja_sama` (digabung dengan nama mitra, program, dan konten untuk tampilan). */
public class KerjaSamaDAO extends BaseDAO<KerjaSama> {

    private static final String SQL_PILIH = """
            SELECT ks.id_kerja_sama, ks.judul_kerja_sama, ks.jenis_kerja_sama,
                   ks.tanggal_mulai, ks.tanggal_akhir, ks.status_kerja_sama, ks.nilai_kontrak,
                   ks.admin_id_pengguna, ks.program_tv_id_program, ks.konten_id_konten, ks.mitra_id_mitra,
                   m.nama_mitra, pr.nama_program, kt.judul_konten
            FROM kerja_sama ks
            JOIN mitra m ON m.id_mitra = ks.mitra_id_mitra
            LEFT JOIN program_tv pr ON pr.id_program = ks.program_tv_id_program
            LEFT JOIN konten kt ON kt.id_konten = ks.konten_id_konten
            """;

    private static final String URUTAN = " ORDER BY ks.id_kerja_sama";

    @Override
    protected KerjaSama buatObjek(ResultSet rs) throws SQLException {
        KerjaSama k = new KerjaSama();
        k.setIdKerjaSama(rs.getInt("id_kerja_sama"));
        k.setJudulKerjaSama(rs.getString("judul_kerja_sama"));
        k.setJenisKerjaSama(rs.getString("jenis_kerja_sama"));
        k.setTanggalMulai(rs.getObject("tanggal_mulai", LocalDate.class));
        k.setTanggalAkhir(rs.getObject("tanggal_akhir", LocalDate.class));
        k.setStatusKerjaSama(rs.getString("status_kerja_sama"));
        k.setNilaiKontrak(rs.getBigDecimal("nilai_kontrak"));
        k.setIdAdmin(rs.getInt("admin_id_pengguna"));
        k.setIdProgram(rs.getObject("program_tv_id_program", Integer.class));
        k.setIdKonten(rs.getObject("konten_id_konten", Integer.class));
        k.setIdMitra(rs.getInt("mitra_id_mitra"));
        k.setNamaMitra(rs.getString("nama_mitra"));
        k.setNamaProgram(rs.getString("nama_program"));
        k.setJudulKonten(rs.getString("judul_konten"));
        return k;
    }

    @Override
    public List<KerjaSama> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<KerjaSama> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE ks.judul_kerja_sama LIKE ? OR ks.jenis_kerja_sama LIKE ? OR ks.status_kerja_sama LIKE ?"
                + " OR m.nama_mitra LIKE ? OR pr.nama_program LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 5));
    }

    @Override
    public KerjaSama getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE ks.id_kerja_sama = ?", id);
    }

    @Override
    public boolean tambah(KerjaSama k) throws SQLException {
        int id = sisipkan("""
                INSERT INTO kerja_sama (judul_kerja_sama, jenis_kerja_sama, tanggal_mulai, tanggal_akhir,
                                        status_kerja_sama, nilai_kontrak, admin_id_pengguna,
                                        program_tv_id_program, konten_id_konten, mitra_id_mitra)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """,
                rapikan(k.getJudulKerjaSama()), rapikan(k.getJenisKerjaSama()), k.getTanggalMulai(),
                k.getTanggalAkhir(), k.getStatusKerjaSama(), k.getNilaiKontrak(), k.getIdAdmin(),
                k.getIdProgram(), k.getIdKonten(), k.getIdMitra());
        k.setIdKerjaSama(id);
        return id > 0;
    }

    @Override
    public boolean ubah(KerjaSama k) throws SQLException {
        return jalankan("""
                UPDATE kerja_sama
                SET judul_kerja_sama = ?, jenis_kerja_sama = ?, tanggal_mulai = ?, tanggal_akhir = ?,
                    status_kerja_sama = ?, nilai_kontrak = ?, program_tv_id_program = ?,
                    konten_id_konten = ?, mitra_id_mitra = ?
                WHERE id_kerja_sama = ?
                """,
                rapikan(k.getJudulKerjaSama()), rapikan(k.getJenisKerjaSama()), k.getTanggalMulai(),
                k.getTanggalAkhir(), k.getStatusKerjaSama(), k.getNilaiKontrak(), k.getIdProgram(),
                k.getIdKonten(), k.getIdMitra(), k.getIdKerjaSama()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM kerja_sama WHERE id_kerja_sama = ?", id) > 0;
    }
}
