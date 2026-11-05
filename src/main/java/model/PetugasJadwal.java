package model;

/**
 * Akun Petugas Jadwal, sesuai tabel `petugas_jadwal`.
 */
public class PetugasJadwal extends Pengguna {

    private String cakupanJadwal; // cakupan_jadwal

    public String getCakupanJadwal() {
        return cakupanJadwal;
    }

    public void setCakupanJadwal(String cakupanJadwal) {
        this.cakupanJadwal = cakupanJadwal;
    }

    @Override
    public String getRole() {
        return ROLE_PETUGAS_JADWAL;
    }

    @Override
    public String getKeterangan() {
        return cakupanJadwal;
    }
}
