package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Jadwal tayang program, sesuai tabel `jadwal_tayang`.
 */
public class JadwalTayang {

    public static final List<String> DAFTAR_STATUS =
            List.of("Terjadwal", "Selesai", "Dibatalkan");

    private int idJadwal; // id_jadwal
    private LocalDate tanggalTayang; // tanggal_tayang
    private LocalTime waktuMulai; // waktu_mulai
    private LocalTime waktuSelesai; // waktu_selesai
    private String statusJadwal = "Terjadwal"; // status_jadwal (enum)
    private int idPetugasJadwal; // petugas_jadwal_id_pengguna
    private int idProgram; // program_tv_id_program
    private int idKanal; // kanal_id_kanal
    private String namaProgram; // hanya untuk tampilan (hasil JOIN)
    private String namaKanal; // hanya untuk tampilan (hasil JOIN)
    private String namaPetugas; // hanya untuk tampilan (hasil JOIN)

    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public LocalDate getTanggalTayang() {
        return tanggalTayang;
    }

    public void setTanggalTayang(LocalDate tanggalTayang) {
        this.tanggalTayang = tanggalTayang;
    }

    public LocalTime getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(LocalTime waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public LocalTime getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(LocalTime waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public String getStatusJadwal() {
        return statusJadwal;
    }

    public void setStatusJadwal(String statusJadwal) {
        this.statusJadwal = statusJadwal;
    }

    public int getIdPetugasJadwal() {
        return idPetugasJadwal;
    }

    public void setIdPetugasJadwal(int idPetugasJadwal) {
        this.idPetugasJadwal = idPetugasJadwal;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public int getIdKanal() {
        return idKanal;
    }

    public void setIdKanal(int idKanal) {
        this.idKanal = idKanal;
    }

    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getNamaKanal() {
        return namaKanal;
    }

    public void setNamaKanal(String namaKanal) {
        this.namaKanal = namaKanal;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public void setNamaPetugas(String namaPetugas) {
        this.namaPetugas = namaPetugas;
    }
}
