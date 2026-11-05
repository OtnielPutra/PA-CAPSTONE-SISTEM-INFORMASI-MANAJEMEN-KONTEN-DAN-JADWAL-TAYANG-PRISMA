package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Kanal;
import model.Konten;
import model.Mitra;
import model.ProgramTV;
import model.dao.KanalDAO;
import model.dao.KontenDAO;
import model.dao.MitraDAO;
import model.dao.ProgramTVDAO;

/**
 * Menyediakan daftar pilihan untuk JComboBox di form
 * (program, kanal, mitra, dan konten). Objek yang dikembalikan menampilkan
 * namanya sendiri lewat toString(), jadi cukup dimasukkan ke combo box.
 */
public class PilihanController {

    private final ProgramTVDAO programDAO = new ProgramTVDAO();
    private final KanalDAO kanalDAO = new KanalDAO();
    private final MitraDAO mitraDAO = new MitraDAO();
    private final KontenDAO kontenDAO = new KontenDAO();
    private String pesan = "";

    /** Program TV yang berstatus aktif. */
    public List<ProgramTV> getProgramAktif() {
        List<ProgramTV> hasil = new ArrayList<>();
        try {
            for (ProgramTV program : programDAO.getSemua()) {
                if ("aktif".equals(program.getStatusProgram())) {
                    hasil.add(program);
                }
            }
        } catch (SQLException e) {
            pesan = BaseController.pesanKesalahan(e);
        }
        return hasil;
    }

    /** Kanal yang berstatus aktif. */
    public List<Kanal> getKanalAktif() {
        List<Kanal> hasil = new ArrayList<>();
        try {
            for (Kanal kanal : kanalDAO.getSemua()) {
                if ("aktif".equals(kanal.getStatusKanal())) {
                    hasil.add(kanal);
                }
            }
        } catch (SQLException e) {
            pesan = BaseController.pesanKesalahan(e);
        }
        return hasil;
    }

    /** Mitra yang berstatus aktif. */
    public List<Mitra> getMitraAktif() {
        List<Mitra> hasil = new ArrayList<>();
        try {
            for (Mitra mitra : mitraDAO.getSemua()) {
                if ("aktif".equals(mitra.getStatusMitra())) {
                    hasil.add(mitra);
                }
            }
        } catch (SQLException e) {
            pesan = BaseController.pesanKesalahan(e);
        }
        return hasil;
    }

    /** Semua konten. */
    public List<Konten> getKonten() {
        try {
            return kontenDAO.getSemua();
        } catch (SQLException e) {
            pesan = BaseController.pesanKesalahan(e);
            return new ArrayList<>();
        }
    }

    public String getPesan() {
        return pesan;
    }
}
