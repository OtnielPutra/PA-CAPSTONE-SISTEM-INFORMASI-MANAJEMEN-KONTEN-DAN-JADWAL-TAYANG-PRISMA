package model;

import java.util.List;

/**
 * Program televisi, sesuai tabel `program_tv`.
 */
public class ProgramTV {

    public static final List<String> DAFTAR_KATEGORI = List.of(
            "Berita", "Berita Daerah", "Infotainment", "Talkshow", "Kuliner",
            "Dokumenter", "Olahraga", "Teknologi", "Edukasi", "Musik", "Film",
            "Drama", "Kartun", "Kartun Edukasi", "Hiburan Digital", "Feature");
    public static final List<String> DAFTAR_STATUS = List.of("aktif", "nonaktif");

    private int idProgram; // id_program
    private String namaProgram; // nama_program
    private String kategoriProgram; // kategori_program (enum)
    private String urlProgram; // url_program (boleh kosong)
    private String statusProgram = "aktif"; // status_program: aktif / nonaktif
    private int idAdmin; // admin_id_pengguna (admin pembuat)

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getKategoriProgram() {
        return kategoriProgram;
    }

    public void setKategoriProgram(String kategoriProgram) {
        this.kategoriProgram = kategoriProgram;
    }

    public String getUrlProgram() {
        return urlProgram;
    }

    public void setUrlProgram(String urlProgram) {
        this.urlProgram = urlProgram;
    }

    public String getStatusProgram() {
        return statusProgram;
    }

    public void setStatusProgram(String statusProgram) {
        this.statusProgram = statusProgram;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    // Dipakai JComboBox agar yang tampil adalah nama program
    @Override
    public String toString() {
        return namaProgram;
    }
}
