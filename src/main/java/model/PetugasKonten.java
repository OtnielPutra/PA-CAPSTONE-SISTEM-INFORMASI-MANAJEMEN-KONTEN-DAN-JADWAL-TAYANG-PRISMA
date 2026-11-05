package model;

/**
 * Akun Petugas Konten, sesuai tabel `petugas_konten`.
 */
public class PetugasKonten extends Pengguna {

    private String spesialisasiKonten; // spesialisasi_konten

    public String getSpesialisasiKonten() {
        return spesialisasiKonten;
    }

    public void setSpesialisasiKonten(String spesialisasiKonten) {
        this.spesialisasiKonten = spesialisasiKonten;
    }

    @Override
    public String getRole() {
        return ROLE_PETUGAS_KONTEN;
    }

    @Override
    public String getKeterangan() {
        return spesialisasiKonten;
    }
}
