package controller;

import model.Pengguna;
import model.PublikasiKonten;
import model.dao.PublikasiKontenDAO;

/** Menu Publikasi Konten (Petugas Konten). */
public class PublikasiController extends BaseController<PublikasiKonten> {

    public PublikasiController() {
        super(new PublikasiKontenDAO(), "Publikasi Konten");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_PETUGAS_KONTEN;
    }

    @Override
    protected String validasi(PublikasiKonten p) {
        String kesalahan = pertama(
                cekWajib("Tanggal publikasi", p.getTanggalPublikasi()),
                cekPilihan("Status publikasi", p.getStatusPublikasi(), PublikasiKonten.DAFTAR_STATUS),
                cekTeksOpsional("Link publikasi", p.getLinkPublikasi(), 255),
                p.getIdKonten() <= 0 ? "Konten harus dipilih" : null,
                p.getIdKanal() <= 0 ? "Kanal harus dipilih" : null);
        if (kesalahan != null) {
            return kesalahan;
        }
        boolean linkKosong = p.getLinkPublikasi() == null || p.getLinkPublikasi().isBlank();
        if ("Terbit".equals(p.getStatusPublikasi()) && linkKosong) {
            return "Link publikasi wajib diisi untuk status Terbit";
        }
        return null;
    }

    /** Petugas diambil dari akun yang sedang login. */
    @Override
    public boolean tambah(PublikasiKonten publikasi) {
        if (publikasi != null && publikasi.getIdPetugasKonten() <= 0) {
            publikasi.setIdPetugasKonten(idPenggunaAktif());
        }
        return super.tambah(publikasi);
    }
}
