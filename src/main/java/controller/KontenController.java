package controller;

import java.time.LocalDate;
import model.Konten;
import model.Pengguna;
import model.dao.KontenDAO;

/** Menu Konten (Petugas Konten). */
public class KontenController extends BaseController<Konten> {

    public KontenController() {
        super(new KontenDAO(), "Konten");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_PETUGAS_KONTEN;
    }

    @Override
    protected String validasi(Konten k) {
        return pertama(
                cekTeks("Judul konten", k.getJudulKonten(), 150),
                cekPilihan("Jenis konten", k.getJenisKonten(), Konten.DAFTAR_JENIS),
                cekTeks("Kategori", k.getKategori(), 50),
                cekTeks("Ringkasan konten", k.getRingkasanKonten(), 255),
                cekPilihan("Status konten", k.getStatusKonten(), Konten.DAFTAR_STATUS));
    }

    /** Tanggal pencatatan dan petugas diisi otomatis (hari ini dan akun yang sedang login). */
    @Override
    public boolean tambah(Konten konten) {
        if (konten != null) {
            if (konten.getTanggalPencatatan() == null) {
                konten.setTanggalPencatatan(LocalDate.now());
            }
            if (konten.getIdPetugasKonten() <= 0) {
                konten.setIdPetugasKonten(idPenggunaAktif());
            }
        }
        return super.tambah(konten);
    }
}
