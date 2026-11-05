package controller;

import java.util.regex.Pattern;
import model.Mitra;
import model.Pengguna;
import model.dao.MitraDAO;

/** Menu Mitra (Admin). */
public class MitraController extends BaseController<Mitra> {

    private static final Pattern POLA_EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    public MitraController() {
        super(new MitraDAO(), "Mitra");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_ADMIN;
    }

    @Override
    protected String validasi(Mitra m) {
        String kesalahan = pertama(
                cekTeks("Nama mitra", m.getNamaMitra(), 100),
                cekTeks("Jenis mitra", m.getJenisMitra(), 50),
                cekTeks("Email", m.getEmail(), 100),
                cekTeks("Alamat", m.getAlamat(), 200),
                cekPilihan("Status mitra", m.getStatusMitra(), Mitra.DAFTAR_STATUS));
        if (kesalahan != null) {
            return kesalahan;
        }
        if (!POLA_EMAIL.matcher(m.getEmail().trim()).matches()) {
            return "Format email tidak valid";
        }
        return null;
    }
}
