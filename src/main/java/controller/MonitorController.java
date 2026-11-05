package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.JadwalTayang;
import model.KerjaSama;
import model.Konten;
import model.Mitra;
import model.ProgramTV;
import model.dao.JadwalTayangDAO;
import model.dao.KerjaSamaDAO;
import model.dao.KontenDAO;
import model.dao.MitraDAO;
import model.dao.ProgramTVDAO;

/**
 * Menu Monitor Data: hanya menampilkan data (baca saja) untuk semua peran.
 */
public class MonitorController {

    /** Pengambil data yang boleh melempar SQLException. */
    @FunctionalInterface
    private interface Pengambil<T> {
        List<T> ambil() throws SQLException;
    }

    private final ProgramTVDAO programDAO = new ProgramTVDAO();
    private final KontenDAO kontenDAO = new KontenDAO();
    private final JadwalTayangDAO jadwalDAO = new JadwalTayangDAO();
    private final KerjaSamaDAO kerjaSamaDAO = new KerjaSamaDAO();
    private final MitraDAO mitraDAO = new MitraDAO();
    private String pesan = "";

    public List<ProgramTV> tampilProgram() {
        return baca(programDAO::getSemua);
    }

    public List<Konten> tampilKonten() {
        return baca(kontenDAO::getSemua);
    }

    public List<JadwalTayang> tampilJadwal() {
        return baca(jadwalDAO::getSemua);
    }

    public List<KerjaSama> tampilKerjaSama() {
        return baca(kerjaSamaDAO::getSemua);
    }

    public List<Mitra> tampilMitra() {
        return baca(mitraDAO::getSemua);
    }

    public String getPesan() {
        return pesan;
    }

    private <T> List<T> baca(Pengambil<T> pengambil) {
        try {
            return pengambil.ambil();
        } catch (SQLException e) {
            pesan = BaseController.pesanKesalahan(e);
            return new ArrayList<>();
        }
    }
}
