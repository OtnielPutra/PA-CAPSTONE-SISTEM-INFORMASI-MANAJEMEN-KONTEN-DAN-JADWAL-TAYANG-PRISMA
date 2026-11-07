package view;

import controller.SesiLogin;
import model.Pengguna;
import view.component.Refreshable;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/**
 * Jendela host PRISMA (Java Swing murni, JFrame).
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file MainFrame.form. Buka file ini lalu klik tab "Design" untuk
 * mengedit tampilan.
 *
 * Sejak kerangka halaman dipindahkan ke dalam panel, MainFrame TIDAK lagi
 * memegang komponen tampilan apa pun: tanpa menu, tanpa topbar, tanpa
 * footer. Isinya hanya pnlContent - wadah penuh TANPA padding tempat panel
 * halaman dipasang. Karena itu panel halaman membentang dari tepi kiri
 * jendela, sehingga menu sidebar milik panel memenuhi sisi kiri layar.
 *
 * Kerangka (menu, topbar, footer) ada di TIAP panel halaman sebagai
 * komponen form milik panel itu sendiri (pnlMenu, pnlTopbar, lblFooter) -
 * teksnya bisa diedit langsung di tab Design panel masing-masing.
 *
 * Yang diatur lewat kode (di luar blok yang dijaga): penerusan identitas
 * dan hak akses peran ke kerangka panel (siapkanKerangka), serta pemasangan
 * halaman dari PanelFactory.
 *
 * Prinsip "design = runtime": komponen yang terlihat di Design view adalah
 * komponen yang benar-benar dipakai saat aplikasi berjalan.
 */
public class MainFrame extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private Pengguna user;
    private final Map<Menu, JPanel> pages = new EnumMap<>(Menu.class);

    /** Konstruktor kosong - hanya dipakai NetBeans untuk merender Design view. */
    public MainFrame() {
        initComponents();
    }

    /** Konstruktor runtime: menyiapkan halaman awal sesuai pengguna yang login. */
    public MainFrame(Pengguna user) {
        initComponents();
        this.user = user;
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pasUkuranLayar();
        initUserData(user);
    }

    private void pasUkuranLayar() {
        java.awt.GraphicsConfiguration gc = getGraphicsConfiguration();
        java.awt.Rectangle layar = gc.getBounds();
        java.awt.Insets taskbar = java.awt.Toolkit.getDefaultToolkit().getScreenInsets(gc);
        setBounds(layar.x + taskbar.left,
                  layar.y + taskbar.top,
                  layar.width - taskbar.left - taskbar.right,
                  layar.height - taskbar.top - taskbar.bottom);
    }

    private void initUserData(Pengguna pengguna) {
        navigate(Menu.DASHBOARD);
    }

    /** Berpindah halaman. Halaman dibuat saat pertama dibuka; data dimuat ulang setiap kali dibuka. */
    public void navigate(Menu menu) {
        JPanel page = pages.get(menu);
        if (page == null) {
            try {
                page = PanelFactory.create(menu, user);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membuka menu: " + ex.getMessage(),
                        "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }
            pages.put(menu, page);
            siapkanKerangka(page);
        } else if (page instanceof Refreshable) {
            ((Refreshable) page).refresh();
        }
        pnlContent.removeAll();
        pnlContent.add(page, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }

    /** Meneruskan identitas dan hak akses peran ke kerangka milik panel halaman yang baru dibuat. */
    private void siapkanKerangka(JPanel page) {
        if (user == null || !(page instanceof HalamanUtama)) {
            return;
        }
        HalamanUtama halaman = (HalamanUtama) page;
        halaman.pasangIdentitas(user);
        halaman.terapkanHakAkses(user.getRole());
    }

    /** Keluar dari akun ini - dipanggil tombol Log Out pada topbar tiap panel. */
    public void logout() {
        int ok = JOptionPane.showConfirmDialog(this, "Yakin ingin keluar dari akun ini?", "Log Out",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ok != JOptionPane.YES_OPTION) {
            return;
        }
        SesiLogin.akhiri();
        dispose();
        new LoginFrame().setVisible(true);
    }

    private void confirmExit() {
        int ok = JOptionPane.showConfirmDialog(this, "Tutup aplikasi PRISMA?", "Keluar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PRISMA - Sistem Informasi Manajemen Konten");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlContent.setBackground(new java.awt.Color(243, 246, 251));
        pnlContent.setLayout(new java.awt.BorderLayout());

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmExit();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlContent;
    // End of variables declaration//GEN-END:variables
}
