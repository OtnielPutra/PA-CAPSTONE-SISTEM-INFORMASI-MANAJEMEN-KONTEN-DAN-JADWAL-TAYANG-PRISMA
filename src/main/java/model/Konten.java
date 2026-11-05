package model;

import java.time.LocalDate;
import java.util.List;

/**
 * Konten redaksi, sesuai tabel `konten`.
 */
public class Konten {

    public static final List<String> DAFTAR_JENIS =
            List.of("Berita", "Artikel", "Video", "Infografik", "Wawancara");
    public static final List<String> DAFTAR_STATUS =
            List.of("Dalam Proses", "Siap Tayang", "Sudah Tayang", "Diarsipkan");

    private int idKonten; // id_konten
    private String judulKonten; // judul_konten
    private String jenisKonten; // jenis_konten (enum)
    private String kategori; // kategori
    private String ringkasanKonten; // ringkasan_konten
    private LocalDate tanggalPencatatan; // tanggal_pencatatan (diisi otomatis saat tambah)
    private String statusKonten = "Dalam Proses"; // status_konten (enum)
    private Integer idProgram; // program_tv_id_program (boleh null)
    private int idPetugasKonten; // petugas_konten_id_pengguna
    private String namaProgram; // hanya untuk tampilan (hasil JOIN)
    private String namaPetugas; // hanya untuk tampilan (hasil JOIN)

    public int getIdKonten() {
        return idKonten;
    }

    public void setIdKonten(int idKonten) {
        this.idKonten = idKonten;
    }

    public String getJudulKonten() {
        return judulKonten;
    }

    public void setJudulKonten(String judulKonten) {
        this.judulKonten = judulKonten;
    }

    public String getJenisKonten() {
        return jenisKonten;
    }

    public void setJenisKonten(String jenisKonten) {
        this.jenisKonten = jenisKonten;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getRingkasanKonten() {
        return ringkasanKonten;
    }

    public void setRingkasanKonten(String ringkasanKonten) {
        this.ringkasanKonten = ringkasanKonten;
    }

    public LocalDate getTanggalPencatatan() {
        return tanggalPencatatan;
    }

    public void setTanggalPencatatan(LocalDate tanggalPencatatan) {
        this.tanggalPencatatan = tanggalPencatatan;
    }

    public String getStatusKonten() {
        return statusKonten;
    }

    public void setStatusKonten(String statusKonten) {
        this.statusKonten = statusKonten;
    }

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public int getIdPetugasKonten() {
        return idPetugasKonten;
    }

    public void setIdPetugasKonten(int idPetugasKonten) {
        this.idPetugasKonten = idPetugasKonten;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }

    @Override
    public String toString() {
        return judulKonten;
    }
}
