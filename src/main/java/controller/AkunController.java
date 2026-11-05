package controller;

import java.time.LocalDate;
import model.Admin;
import model.Pengguna;
import model.PetugasJadwal;
import model.PetugasKonten;
import model.dao.PenggunaDAO;

/** Menu Akun (Admin): kelola akun Admin, Petugas Jadwal, dan Petugas Konten. */
public class AkunController extends BaseController<Pengguna> {

    public AkunController() {
        super(new PenggunaDAO(), "Akun");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_ADMIN;
    }

    /**
     * Membuat objek akun sesuai peran yang dipilih di combo box.
     * keterangan = cakupan jadwal (Petugas Jadwal) atau spesialisasi konten (Petugas Konten);
     * diabaikan untuk Admin. Mengembalikan null jika peran tidak dikenal.
     */
    public Pengguna buatPengguna(String peran, String namaLengkap, String username,
                                 String password, String statusAkun, String keterangan) {
        Pengguna pengguna;
        if (Pengguna.ROLE_ADMIN.equals(peran)) {
            pengguna = new Admin();
        } else if (Pengguna.ROLE_PETUGAS_JADWAL.equals(peran)) {
            PetugasJadwal petugas = new PetugasJadwal();
            petugas.setCakupanJadwal(keterangan);
            pengguna = petugas;
        } else if (Pengguna.ROLE_PETUGAS_KONTEN.equals(peran)) {
            PetugasKonten petugas = new PetugasKonten();
            petugas.setSpesialisasiKonten(keterangan);
            pengguna = petugas;
        } else {
            return null;
        }
        pengguna.setNamaLengkap(namaLengkap);
        pengguna.setUsername(username);
        pengguna.setPassword(password);
        pengguna.setStatusAkun(statusAkun);
        return pengguna;
    }

    @Override
    protected String validasi(Pengguna p) {
        String kesalahan = pertama(
                cekTeks("Nama petugas", p.getNamaLengkap(), 100),
                cekTeks("Username", p.getUsername(), 50),
                cekTeks("Password", p.getPassword(), 30),
                cekPilihan("Status akun", p.getStatusAkun(), Pengguna.DAFTAR_STATUS));
        if (kesalahan != null) {
            return kesalahan;
        }
        if (p.getUsername().trim().contains(" ")) {
            return "Username tidak boleh mengandung spasi";
        }
        if (p instanceof PetugasJadwal petugas) {
            return cekTeks("Cakupan jadwal", petugas.getCakupanJadwal(), 100);
        }
        if (p instanceof PetugasKonten petugas) {
            return cekTeks("Spesialisasi konten", petugas.getSpesialisasiKonten(), 100);
        }
        return null;
    }

    @Override
    public boolean tambah(Pengguna pengguna) {
        if (pengguna != null && pengguna.getTanggalPenugasan() == null) {
            pengguna.setTanggalPenugasan(LocalDate.now());
        }
        return super.tambah(pengguna);
    }

    @Override
    public boolean hapus(int id) {
        if (id == idPenggunaAktif()) {
            setPesan("Akun yang sedang digunakan tidak dapat dihapus");
            return false;
        }
        return super.hapus(id);
    }
}
