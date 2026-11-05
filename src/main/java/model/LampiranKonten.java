package model;

import java.time.LocalDateTime;

/**
 * Lampiran file milik konten, sesuai tabel `lampiran_konten`.
 */
public class LampiranKonten {

    private int idLampiran; // id_lampiran
    private String namaFile; // nama_file
    private String lokasiFile; // lokasi_file
    private LocalDateTime tanggalUnggah; // tanggal_unggah (diisi otomatis)
    private int idKonten; // konten_id_konten
    private int idPetugasKonten; // petugas_konten_id_pengguna
    private String judulKonten; // hanya untuk tampilan (hasil JOIN)

    public int getIdLampiran() {
        return idLampiran;
    }

    public void setIdLampiran(int idLampiran) {
        this.idLampiran = idLampiran;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getLokasiFile() {
        return lokasiFile;
    }

    public void setLokasiFile(String lokasiFile) {
        this.lokasiFile = lokasiFile;
    }

    public LocalDateTime getTanggalUnggah() {
        return tanggalUnggah;
    }

    public void setTanggalUnggah(LocalDateTime tanggalUnggah) {
        this.tanggalUnggah = tanggalUnggah;
    }

    public int getIdKonten() {
        return idKonten;
    }

    public void setIdKonten(int idKonten) {
        this.idKonten = idKonten;
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
}
