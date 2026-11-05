package view;

import model.Pengguna;

import javax.swing.*;

/** Membuat halaman sesuai menu. Program TV hanya bisa diubah oleh Admin. */
public final class PanelFactory {
    private PanelFactory() { }

    public static JPanel create(Menu menu, Pengguna user) {
        switch (menu) {
            case DASHBOARD: return new DashboardPanel(user);
            case KONTEN: return new KontenPanel(true);
            case PROGRAM: return new ProgramTvPanel(Pengguna.ROLE_ADMIN.equals(user.getRole()));
            case JADWAL: return new JadwalPanel(true);
            case PUBLIKASI: return new PublikasiPanel(true);
            case LAMPIRAN: return new LampiranPanel(true);
            case MONITOR: return new MonitorPanel();
            case KANAL: return new KanalPanel(true);
            case MITRA: return new MitraPanel(true);
            case KERJASAMA: return new KerjaSamaPanel(true);
            case AKUN: return new PenggunaPanel(true);
            default: throw new IllegalArgumentException("Menu tidak dikenal: " + menu);
        }
    }
}
