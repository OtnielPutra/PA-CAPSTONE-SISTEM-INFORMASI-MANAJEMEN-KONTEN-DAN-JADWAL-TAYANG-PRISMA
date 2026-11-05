package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Publikasi konten ke sebuah kanal, sesuai tabel `publikasi_konten`.
 */
public class PublikasiKonten {

    public static final List<String> DAFTAR_STATUS =
            List.of("Direncanakan", "Terbit", "Dibatalkan");

    private int idPublikasi; // id_publikasi
    private LocalDateTime tanggalPublikasi; // tanggal_publikasi
    private String statusPublikasi = "Direncanakan"; // status_publikasi (enum)
    private String linkPublikasi; // link_publikasi (boleh kosong)
    private int idKonten; // konten_id_konten
    private int idKanal; // kanal_id_kanal
    private int idPetugasKonten; // petugas_konten_id_pengguna
    private String judulKonten; // hanya untuk tampilan (hasil JOIN)
    private String namaKanal; // hanya untuk tampilan (hasil JOIN)

    public int getIdPublikasi() {
        return idPublikasi;
    }

    public void setIdPublikasi(int idPublikasi) {
        this.idPublikasi = idPublikasi;
    }

    public LocalDateTime getTanggalPublikasi() {
        return tanggalPublikasi;
    }

    public void setTanggalPublikasi(LocalDateTime tanggalPublikasi) {
        this.tanggalPublikasi = tanggalPublikasi;
    }

    public String getStatusPublikasi() {
        return statusPublikasi;
    }

    public void setStatusPublikasi(String statusPublikasi) {
        this.statusPublikasi = statusPublikasi;
    }

    public String getLinkPublikasi() {
        return linkPublikasi;
    }

    public void setLinkPublikasi(String linkPublikasi) {
        this.linkPublikasi = linkPublikasi;
    }

    public int getIdKonten() {
        return idKonten;
    }

    public void setIdKonten(int idKonten) {
        this.idKonten = idKonten;
    }

    public int getIdKanal() {
        return idKanal;
    }

    public void setIdKanal(int idKanal) {
        this.idKanal = idKanal;
    }

    public int getIdPetugasKonten() {
        return idPetugasKonten;
    }

    public void setIdPetugasKonten(int idPetugasKonten) {
        this.idPetugasKonten = idPetugasKonten;
    }

    public String getJudulKonten() {
        return judulKonten;
    }

    public void setJudulKonten(String judulKonten) {
        this.judulKonten = judulKonten;
    }

    public String getNamaKanal() {
        return namaKanal;
    }

    public void setNamaKanal(String namaKanal) {
        this.namaKanal = namaKanal;
    }
}
