package view;

import model.Pengguna;

import java.util.ArrayList;
import java.util.List;

/** Daftar menu aplikasi dan peran yang boleh mengaksesnya. */
public enum Menu {
    DASHBOARD("Dashboard", false),
    KONTEN("Manajemen Konten", false),
    PROGRAM("Program TV", false),
    JADWAL("Jadwal Tayang", false),
    PUBLIKASI("Publikasi Konten", false),
    LAMPIRAN("Lampiran Konten", false),
    MONITOR("Monitor Data", false),
    KANAL("Data Kanal", true),
    MITRA("Data Mitra", true),
    KERJASAMA("Kerja Sama", true),
    AKUN("Akun Pengguna", true);

    private final String label;
    private final boolean pengaturanSistem;

    Menu(String label, boolean pengaturanSistem) {
        this.label = label;
        this.pengaturanSistem = pengaturanSistem;
    }

    public String getLabel() {
        return label;
    }

    public boolean isPengaturanSistem() {
        return pengaturanSistem;
    }

    /** Menu yang tampil untuk peran tertentu, sesuai hak akses controller. */
    public static List<Menu> untukPeran(String peran) {
        List<Menu> hasil = new ArrayList<>();
        for (Menu m : values()) {
            if (bolehDiakses(m, peran)) {
                hasil.add(m);
            }
        }
        return hasil;
    }

    private static boolean bolehDiakses(Menu m, String peran) {
        if (Pengguna.ROLE_ADMIN.equals(peran)) {
            return m == DASHBOARD || m == PROGRAM || m == MONITOR
                    || m == KANAL || m == MITRA || m == KERJASAMA || m == AKUN;
        }
        if (Pengguna.ROLE_PETUGAS_KONTEN.equals(peran)) {
            return m == DASHBOARD || m == KONTEN || m == PROGRAM
                    || m == PUBLIKASI || m == LAMPIRAN || m == MONITOR;
        }
        if (Pengguna.ROLE_PETUGAS_JADWAL.equals(peran)) {
            return m == DASHBOARD || m == JADWAL || m == PROGRAM || m == MONITOR;
        }
        return false;
    }
}
