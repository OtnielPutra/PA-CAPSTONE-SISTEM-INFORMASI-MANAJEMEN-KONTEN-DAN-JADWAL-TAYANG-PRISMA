package model;

import java.util.List;

/**
 * Mitra kerja sama, sesuai tabel `mitra`.
 */
public class Mitra {

    public static final List<String> DAFTAR_STATUS = List.of("aktif", "nonaktif");

    private int idMitra; // id_mitra
    private String namaMitra; // nama_mitra
    private String jenisMitra; // jenis_mitra
    private String email; // email
    private String alamat; // alamat
    private String statusMitra = "aktif"; // status_mitra: aktif / nonaktif

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

    public String getJenisMitra() {
        return jenisMitra;
    }

    public void setJenisMitra(String jenisMitra) {
        this.jenisMitra = jenisMitra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatusMitra() {
        return statusMitra;
    }

    public void setStatusMitra(String statusMitra) {
        this.statusMitra = statusMitra;
    }

    @Override
    public String toString() {
        return namaMitra;
    }
}
