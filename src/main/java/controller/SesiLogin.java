package controller;

import model.Pengguna;

/**
 * Menyimpan siapa yang sedang login selama program berjalan.
 * Dipakai form lain untuk mengetahui role (mis. tombol keluar di Monitor Data)
 * dan id petugas/admin saat menyimpan data.
 */
public class SesiLogin {

    private static Pengguna penggunaAktif;

    // Class ini tidak perlu dibuat objeknya
    private SesiLogin() {
    }

    public static void mulai(Pengguna pengguna) {
        penggunaAktif = pengguna;
    }

    public static void akhiri() {
        penggunaAktif = null;
    }

    public static boolean sudahLogin() {
        return penggunaAktif != null;
    }

    public static Pengguna getPenggunaAktif() {
        return penggunaAktif;
    }

    public static String getRole() {
        return penggunaAktif == null ? null : penggunaAktif.getRole();
    }
}
