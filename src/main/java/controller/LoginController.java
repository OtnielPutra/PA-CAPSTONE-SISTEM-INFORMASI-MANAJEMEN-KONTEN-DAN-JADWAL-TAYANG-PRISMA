package controller;

import java.sql.SQLException;
import model.Pengguna;
import model.dao.PenggunaDAO;

/**
 * Mengurus proses login: view memanggil class ini, class ini memanggil PenggunaDAO.
 * Setelah login(), baca getPesan() untuk tulisan merah di form.
 */
public class LoginController {

    private final PenggunaDAO penggunaDAO = new PenggunaDAO();
    private String pesan = "";

    /**
     * Memverifikasi akun.
     * Berhasil: sesi dimulai dan objek Pengguna (Admin / PetugasJadwal / PetugasKonten) dikembalikan.
     * Gagal: mengembalikan null dan getPesan() berisi alasannya.
     */
    public Pengguna login(String username, String password) {
        pesan = "";
        if (username == null || username.isBlank() || password == null || password.isEmpty()) {
            pesan = "Username atau password Anda salah";
            return null;
        }
        try {
            Pengguna pengguna = penggunaDAO.login(username.trim(), password);
            if (pengguna == null) {
                pesan = "Username atau password Anda salah";
                return null;
            }
            SesiLogin.mulai(pengguna);
            return pengguna;
        } catch (SQLException e) {
            pesan = BaseController.pesanKesalahan(e);
            return null;
        }
    }

    public void logout() {
        SesiLogin.akhiri();
    }

    public String getPesan() {
        return pesan;
    }
}
