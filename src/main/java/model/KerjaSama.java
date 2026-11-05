package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Kerja sama dengan mitra, sesuai tabel `kerja_sama`.
 */
public class KerjaSama {

    public static final List<String> DAFTAR_STATUS =
            List.of("Direncanakan", "Berjalan", "Selesai", "Dibatalkan");

    private int idKerjaSama; // id_kerja_sama
    private String judulKerjaSama; // judul_kerja_sama
    private String jenisKerjaSama; // jenis_kerja_sama
    private LocalDate tanggalMulai; // tanggal_mulai
    private LocalDate tanggalAkhir; // tanggal_akhir
    private String statusKerjaSama; // status_kerja_sama (enum)
    private BigDecimal nilaiKontrak; // nilai_kontrak decimal(15,2)
    private int idAdmin; // admin_id_pengguna
    private Integer idProgram; // program_tv_id_program (boleh null)
    private Integer idKonten; // konten_id_konten (boleh null)
    private int idMitra; // mitra_id_mitra
    private String namaMitra; // hanya untuk tampilan (hasil JOIN)
    private String namaProgram; // hanya untuk tampilan (hasil JOIN)
    private String judulKonten; // hanya untuk tampilan (hasil JOIN)

    public int getIdKerjaSama() {
        return idKerjaSama;
    }

    public void setIdKerjaSama(int idKerjaSama) {
        this.idKerjaSama = idKerjaSama;
    }

    public String getJudulKerjaSama() {
        return judulKerjaSama;
    }

    public void setJudulKerjaSama(String judulKerjaSama) {
        this.judulKerjaSama = judulKerjaSama;
    }

    public String getJenisKerjaSama() {
        return jenisKerjaSama;
    }

    public void setJenisKerjaSama(String jenisKerjaSama) {
        this.jenisKerjaSama = jenisKerjaSama;
    }

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(LocalDate tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public LocalDate getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(LocalDate tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getStatusKerjaSama() {
        return statusKerjaSama;
    }

    public void setStatusKerjaSama(String statusKerjaSama) {
        this.statusKerjaSama = statusKerjaSama;
    }

    public BigDecimal getNilaiKontrak() {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(BigDecimal nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(Integer idProgram) {
        this.idProgram = idProgram;
    }

    public Integer getIdKonten() {
        return idKonten;
    }

    public void setIdKonten(Integer idKonten) {
        this.idKonten = idKonten;
    }

    public int getIdMitra() {
        return idMitra;
    }

    public void setIdMitra(int idMitra) {
        this.idMitra = idMitra;
    }

    public String getNamaMitra() {
        return namaMitra;
    }

    public void setNamaMitra(String namaMitra) {
        this.namaMitra = namaMitra;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getJudulKonten() {
        return judulKonten;
    }

    public void setJudulKonten(String judulKonten) {
        this.judulKonten = judulKonten;
    }
}
