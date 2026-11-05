package controller;

import model.Pengguna;
import model.ProgramTV;
import model.dao.ProgramTVDAO;

/** Menu Program TV (Admin). */
public class ProgramTVController extends BaseController<ProgramTV> {

    public ProgramTVController() {
        super(new ProgramTVDAO(), "Program TV");
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_ADMIN;
    }

    @Override
    protected String validasi(ProgramTV p) {
        return pertama(
                cekTeks("Nama program", p.getNamaProgram(), 100),
                cekPilihan("Kategori program", p.getKategoriProgram(), ProgramTV.DAFTAR_KATEGORI),
                cekTeksOpsional("URL program", p.getUrlProgram(), 200),
                cekPilihan("Status program", p.getStatusProgram(), ProgramTV.DAFTAR_STATUS));
    }

    /** Admin pembuat diambil dari akun yang sedang login. */
    @Override
    public boolean tambah(ProgramTV program) {
        if (program != null && program.getIdAdmin() <= 0) {
            program.setIdAdmin(idPenggunaAktif());
        }
        return super.tambah(program);
    }
}
