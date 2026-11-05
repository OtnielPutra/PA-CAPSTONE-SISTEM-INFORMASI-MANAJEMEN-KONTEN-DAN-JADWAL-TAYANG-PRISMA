package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.JadwalTayang;

/** Akses data tabel `jadwal_tayang` (digabung dengan nama program, kanal, dan petugas untuk tampilan). */
public class JadwalTayangDAO extends BaseDAO<JadwalTayang> {

    private static final String SQL_PILIH = """
            SELECT j.id_jadwal, j.tanggal_tayang, j.waktu_mulai, j.waktu_selesai, j.status_jadwal,
                   j.petugas_jadwal_id_pengguna, j.program_tv_id_program, j.kanal_id_kanal,
                   pr.nama_program, c.nama_kanal, pg.nama_lengkap
            FROM jadwal_tayang j
            JOIN program_tv pr ON pr.id_program = j.program_tv_id_program
            JOIN kanal c ON c.id_kanal = j.kanal_id_kanal
            JOIN pengguna pg ON pg.id_pengguna = j.petugas_jadwal_id_pengguna
            """;

    private static final String URUTAN = " ORDER BY j.tanggal_tayang, j.waktu_mulai, j.id_jadwal";

    @Override
    protected JadwalTayang buatObjek(ResultSet rs) throws SQLException {
        JadwalTayang j = new JadwalTayang();
        j.setIdJadwal(rs.getInt("id_jadwal"));
        j.setTanggalTayang(rs.getObject("tanggal_tayang", LocalDate.class));
        j.setWaktuMulai(rs.getObject("waktu_mulai", LocalTime.class));
        j.setWaktuSelesai(rs.getObject("waktu_selesai", LocalTime.class));
        j.setStatusJadwal(rs.getString("status_jadwal"));
        j.setIdPetugasJadwal(rs.getInt("petugas_jadwal_id_pengguna"));
        j.setIdProgram(rs.getInt("program_tv_id_program"));
        j.setIdKanal(rs.getInt("kanal_id_kanal"));
        j.setNamaProgram(rs.getString("nama_program"));
        j.setNamaKanal(rs.getString("nama_kanal"));
        j.setNamaPetugas(rs.getString("nama_lengkap"));
        return j;
    }

    @Override
    public List<JadwalTayang> getSemua() throws SQLException {
        return ambilDaftar(SQL_PILIH + URUTAN);
    }

    @Override
    public List<JadwalTayang> cari(String kataKunci) throws SQLException {
        return ambilDaftar(SQL_PILIH
                + " WHERE pr.nama_program LIKE ? OR c.nama_kanal LIKE ? OR j.status_jadwal LIKE ?" + URUTAN,
                ulang(kataKunci(kataKunci), 3));
    }

    /**
     * Pencarian sesuai flowchart: ketiga kriteria boleh dikosongkan (null).
     * Tanggal dicocokkan persis, jadwal harus mulai pada/setelah waktu mulai
     * dan selesai pada/sebelum waktu selesai.
     */
    public List<JadwalTayang> cari(LocalDate tanggal, LocalTime mulai, LocalTime selesai) throws SQLException {
        StringBuilder sql = new StringBuilder(SQL_PILIH).append(" WHERE 1 = 1");
        List<Object> parameter = new ArrayList<>();
        if (tanggal != null) {
            sql.append(" AND j.tanggal_tayang = ?");
            parameter.add(tanggal);
        }
        if (mulai != null) {
            sql.append(" AND j.waktu_mulai >= ?");
            parameter.add(mulai);
        }
        if (selesai != null) {
            sql.append(" AND j.waktu_selesai <= ?");
            parameter.add(selesai);
        }
        sql.append(URUTAN);
        return ambilDaftar(sql.toString(), parameter.toArray());
    }

    @Override
    public JadwalTayang getById(int id) throws SQLException {
        return ambilSatu(SQL_PILIH + " WHERE j.id_jadwal = ?", id);
    }

    /**
     * Apakah jadwal ini bertabrakan dengan jadwal lain (kanal dan tanggal sama, waktu beririsan)?
     * Jadwal berstatus Dibatalkan dan jadwal itu sendiri diabaikan.
     * Jadwal yang selesai tepat saat jadwal lain mulai tidak dianggap bentrok.
     */
    public boolean adaBentrok(JadwalTayang j) throws SQLException {
        int jumlah = hitung("""
                SELECT COUNT(*) FROM jadwal_tayang
                WHERE kanal_id_kanal = ? AND tanggal_tayang = ? AND status_jadwal <> 'Dibatalkan'
                  AND id_jadwal <> ? AND waktu_mulai < ? AND waktu_selesai > ?
                """,
                j.getIdKanal(), j.getTanggalTayang(), j.getIdJadwal(), j.getWaktuSelesai(), j.getWaktuMulai());
        return jumlah > 0;
    }

    /** Dipakai untuk menonaktifkan jadwal (status menjadi Dibatalkan). */
    public boolean ubahStatus(int id, String status) throws SQLException {
        return jalankan("UPDATE jadwal_tayang SET status_jadwal = ? WHERE id_jadwal = ?", status, id) > 0;
    }

    @Override
    public boolean tambah(JadwalTayang j) throws SQLException {
        int id = sisipkan("""
                INSERT INTO jadwal_tayang (tanggal_tayang, waktu_mulai, waktu_selesai, status_jadwal,
                                           petugas_jadwal_id_pengguna, program_tv_id_program, kanal_id_kanal)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """,
                j.getTanggalTayang(), j.getWaktuMulai(), j.getWaktuSelesai(), j.getStatusJadwal(),
                j.getIdPetugasJadwal(), j.getIdProgram(), j.getIdKanal());
        j.setIdJadwal(id);
        return id > 0;
    }

    @Override
    public boolean ubah(JadwalTayang j) throws SQLException {
        return jalankan("""
                UPDATE jadwal_tayang
                SET tanggal_tayang = ?, waktu_mulai = ?, waktu_selesai = ?, status_jadwal = ?,
                    program_tv_id_program = ?, kanal_id_kanal = ?
                WHERE id_jadwal = ?
                """,
                j.getTanggalTayang(), j.getWaktuMulai(), j.getWaktuSelesai(), j.getStatusJadwal(),
                j.getIdProgram(), j.getIdKanal(), j.getIdJadwal()) > 0;
    }

    @Override
    public boolean hapus(int id) throws SQLException {
        return jalankan("DELETE FROM jadwal_tayang WHERE id_jadwal = ?", id) > 0;
    }
}
