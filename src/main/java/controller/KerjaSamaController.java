package controller;

import java.math.BigDecimal;
import model.KerjaSama;
import model.Pengguna;
import model.dao.KerjaSamaDAO;

/** Menu Kerja Sama (Admin). */
public class KerjaSamaController extends BaseController<KerjaSama> {

    // nilai_kontrak adalah decimal(15,2): maksimal 13 angka di depan koma
    private static final BigDecimal BATAS_NILAI = new BigDecimal("10000000000000");

    public KerjaSamaController() {
        super(new KerjaSamaDAO(), "Kerja Sama");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_ADMIN;
    }

    @Override
    protected String validasi(KerjaSama k) {
        String kesalahan = pertama(
                cekTeks("Judul kerja sama", k.getJudulKerjaSama(), 150),
                cekTeks("Jenis kerja sama", k.getJenisKerjaSama(), 100),
                cekWajib("Tanggal mulai", k.getTanggalMulai()),
                cekWajib("Tanggal akhir", k.getTanggalAkhir()),
                cekPilihan("Status kerja sama", k.getStatusKerjaSama(), KerjaSama.DAFTAR_STATUS),
                cekWajib("Nilai kontrak", k.getNilaiKontrak()),
                k.getIdMitra() <= 0 ? "Mitra harus dipilih" : null);
        if (kesalahan != null) {
            return kesalahan;
        }
        if (k.getTanggalAkhir().isBefore(k.getTanggalMulai())) {
            return "Tanggal akhir tidak boleh sebelum tanggal mulai";
        }
        BigDecimal nilai = k.getNilaiKontrak();
        if (nilai.signum() < 0) {
            return "Nilai kontrak tidak boleh negatif";
        }
        if (nilai.compareTo(BATAS_NILAI) >= 0) {
            return "Nilai kontrak terlalu besar";
        }
        if (nilai.stripTrailingZeros().scale() > 2) {
            return "Nilai kontrak maksimal 2 angka di belakang koma";
        }
        return null;
    }

    /** Admin pembuat diambil dari akun yang sedang login. */
    @Override
    public boolean tambah(KerjaSama kerjaSama) {
        if (kerjaSama != null && kerjaSama.getIdAdmin() <= 0) {
            kerjaSama.setIdAdmin(idPenggunaAktif());
        }
        return super.tambah(kerjaSama);
    }
}
