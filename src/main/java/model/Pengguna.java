package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Class induk (abstract) untuk semua akun, sesuai tabel `pengguna`.
 * Admin, PetugasJadwal, dan PetugasKonten mewarisi class ini.
 */
public abstract class Pengguna {

    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_PETUGAS_JADWAL = "Petugas Jadwal";
    public static final String ROLE_PETUGAS_KONTEN = "Petugas Konten";

    public static final List<String> DAFTAR_ROLE =
            List.of(ROLE_ADMIN, ROLE_PETUGAS_JADWAL, ROLE_PETUGAS_KONTEN);
    public static final List<String> DAFTAR_STATUS = List.of("aktif", "nonaktif");

    // Abstract method: wajib di-override oleh setiap subclass (polymorphism)
    public abstract String getRole();

    // Informasi khusus tiap peran (cakupan jadwal / spesialisasi konten)
    public abstract String getKeterangan();

    private int idPengguna; // id_pengguna
    private String namaLengkap; // nama_lengkap
    private String username; // username
    private String password; // password
    private String statusAkun = "aktif"; // status_akun: aktif / nonaktif
    private LocalDate tanggalPenugasan; // tanggal_penugasan (ada di tabel admin / petugas_jadwal / petugas_konten)

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatusAkun() {
        return statusAkun;
    }

    public void setStatusAkun(String statusAkun) {
        this.statusAkun = statusAkun;
    }

    public LocalDate getTanggalPenugasan() {
        return tanggalPenugasan;
    }

    public void setTanggalPenugasan(LocalDate tanggalPenugasan) {
        this.tanggalPenugasan = tanggalPenugasan;
    }

    @Override
    public String toString() {
        return namaLengkap + " (" + getRole() + ")";
    }
}
