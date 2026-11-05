package model;

import java.util.List;

/**
 * Kanal publikasi / siaran, sesuai tabel `kanal`.
 */
public class Kanal {

    public static final List<String> DAFTAR_JENIS =
            List.of("Televisi", "Website", "YouTube", "Instagram", "TikTok");
    public static final List<String> DAFTAR_STATUS = List.of("aktif", "nonaktif");

    private int idKanal; // id_kanal
    private String namaKanal; // nama_kanal
    private String jenisKanal; // jenis_kanal (enum)
    private String platform; // platform
    private String statusKanal = "aktif"; // status_kanal: aktif / nonaktif

    public int getIdKanal() {
        return idKanal;
    }

    public void setIdKanal(int idKanal) {
        this.idKanal = idKanal;
    }

    public String getNamaKanal() {
        return namaKanal;
    }

    public void setNamaKanal(String namaKanal) {
        this.namaKanal = namaKanal;
    }

    public String getJenisKanal() {
        return jenisKanal;
    }

    public void setJenisKanal(String jenisKanal) {
        this.jenisKanal = jenisKanal;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getStatusKanal() {
        return statusKanal;
    }

    public void setStatusKanal(String statusKanal) {
        this.statusKanal = statusKanal;
    }

    @Override
    public String toString() {
        return namaKanal;
    }
}
