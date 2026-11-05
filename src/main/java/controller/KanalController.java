package controller;

import model.Kanal;
import model.Pengguna;
import model.dao.KanalDAO;

/** Menu Kanal (Admin). */
public class KanalController extends BaseController<Kanal> {

    public KanalController() {
        super(new KanalDAO(), "Kanal");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_ADMIN;
    }

    @Override
    protected String validasi(Kanal k) {
        return pertama(
                cekTeks("Nama kanal", k.getNamaKanal(), 100),
                cekPilihan("Jenis kanal", k.getJenisKanal(), Kanal.DAFTAR_JENIS),
                cekTeks("Platform", k.getPlatform(), 50),
                cekPilihan("Status kanal", k.getStatusKanal(), Kanal.DAFTAR_STATUS));
    }
}
