package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.JadwalTayang;
import model.Pengguna;
import model.dao.JadwalTayangDAO;

/** Menu Jadwal (Petugas Jadwal): tambah, cari, ubah, dan nonaktifkan jadwal tayang. */
public class JadwalController extends BaseController<JadwalTayang> {

    private final JadwalTayangDAO jadwalDAO;

    public JadwalController() {
        this(new JadwalTayangDAO());
    }

    private JadwalController(JadwalTayangDAO dao) {
        super(dao, "Jadwal");
        this.jadwalDAO = dao;
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_PETUGAS_JADWAL;
    }

    @Override
    protected String validasi(JadwalTayang j) {
        String kesalahan = pertama(
                cekWajib("Tanggal tayang", j.getTanggalTayang()),
                cekWajib("Waktu mulai", j.getWaktuMulai()),
                cekWajib("Waktu selesai", j.getWaktuSelesai()),
                j.getIdProgram() <= 0 ? "Program TV harus dipilih" : null,
                j.getIdKanal() <= 0 ? "Kanal harus dipilih" : null,
                cekPilihan("Status jadwal", j.getStatusJadwal(), JadwalTayang.DAFTAR_STATUS));
        if (kesalahan != null) {
            return kesalahan;
        }
        if (!j.getWaktuSelesai().isAfter(j.getWaktuMulai())) {
            return "Waktu selesai harus setelah waktu mulai";
        }
        if (!"Dibatalkan".equals(j.getStatusJadwal())) {
            try {
                if (jadwalDAO.adaBentrok(j)) {
                    return "Jadwal bentrok dengan jadwal lain pada kanal dan tanggal yang sama";
                }
            } catch (SQLException e) {
                return pesanKesalahan(e);
            }
        }
        return null;
    }

    /** Petugas diambil dari akun yang sedang login. */
    @Override
    public boolean tambah(JadwalTayang jadwal) {
        if (jadwal != null && jadwal.getIdPetugasJadwal() <= 0) {
            jadwal.setIdPetugasJadwal(idPenggunaAktif());
        }
        return super.tambah(jadwal);
    }

    /** Pencarian berdasarkan tanggal, waktu mulai, dan waktu selesai. Kriteria boleh dikosongkan (null). */
    public List<JadwalTayang> cariJadwal(LocalDate tanggal, LocalTime mulai, LocalTime selesai) {
        if (tanggal == null && mulai == null && selesai == null) {
            return tampilSemua();
        }
        try {
            return jadwalDAO.cari(tanggal, mulai, selesai);
        } catch (SQLException e) {
            setPesan(pesanKesalahan(e));
            return new ArrayList<>();
        }
    }

    /** Menonaktifkan jadwal: statusnya menjadi Dibatalkan (data tidak dihapus). */
    public boolean nonaktifkan(int idJadwal) {
        if (!Pengguna.ROLE_PETUGAS_JADWAL.equals(SesiLogin.getRole())) {
            setPesan("Anda tidak memiliki akses untuk mengelola data " + getNamaData());
            return false;
        }
        try {
            boolean berhasil = jadwalDAO.ubahStatus(idJadwal, "Dibatalkan");
            setPesan(berhasil ? "Nonaktif jadwal berhasil dilakukan" : "Data Jadwal tidak ditemukan");
            return berhasil;
        } catch (SQLException e) {
            setPesan(pesanKesalahan(e));
            return false;
        }
    }
}
