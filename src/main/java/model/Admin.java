package model;

/**
 * Akun Admin, sesuai tabel `admin`.
 */
public class Admin extends Pengguna {


    @Override
    public String getRole() {
        return ROLE_ADMIN;
    }

    @Override
    public String getKeterangan() {
        return "-";
    }
}
