package view;

import controller.SesiLogin;
import model.Pengguna;
import view.component.Refreshable;
import view.component.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Jendela utama PRISMA (Java Swing murni, JFrame).
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file MainFrame.form. Jangan mengedit kode di antara tanda
 * //GEN-BEGIN dan //GEN-END - perubahan tampilan dilakukan lewat tab "Design".
 *
 * Kerangka tampilan (sidebar, topbar, area konten, footer) dikelola oleh
 * GUI Builder. Bagian dinamis (tombol menu sesuai peran, nama pengguna,
 * dan halaman aktif) diisi lewat kode di luar blok yang dijaga.
 */
public class MainFrame extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private Pengguna user;
    private final Map<Menu, JPanel> pages = new EnumMap<>(Menu.class);
    private final Map<Menu, JButton> navButtons = new EnumMap<>(Menu.class);

    /** Konstruktor kosong - hanya dipakai NetBeans untuk merender Design view. */
    public MainFrame() {
        initComponents();
        
    }

    /** Konstruktor runtime: menyusun menu navigasi sesuai peran pengguna. */
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
        String nama = pengguna.getNamaLengkap() == null ? "" : pengguna.getNamaLengkap().trim();
        lblAvatar.setText(nama.isEmpty() ? "?" : nama.substring(0, 1).toUpperCase());
        lblUserName.setText(nama);
        lblUserRole.setText(pengguna.getRole());

        List<Menu> diizinkan = Menu.untukPeran(pengguna.getRole());
        tambahGrupNav("MENU UTAMA", false, diizinkan);
        tambahGrupNav("PENGATURAN SISTEM", true, diizinkan);

        navigate(Menu.DASHBOARD);
    }

    /** Menambahkan grup tombol menu ke sidebar (dipanggil saat konstruksi runtime). */
    private void tambahGrupNav(String judul, boolean sistem, List<Menu> diizinkan) {
        boolean adaHeader = false;
        for (Menu m : diizinkan) {
            if (m.isPengaturanSistem() != sistem) {
                continue;
            }
            if (!adaHeader) {
                JLabel l = Theme.label(judul, Font.PLAIN, 11, new Color(0x94, 0xA3, 0xB8));
                l.setAlignmentX(LEFT_ALIGNMENT);
                l.setBorder(BorderFactory.createEmptyBorder(14, 12, 8, 0));
                pnlNav.add(l);
                adaHeader = true;
            }
            JButton b = new JButton(m.getLabel());
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setFont(Theme.font(Font.PLAIN, 14));
            b.setAlignmentX(LEFT_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            b.setBackground(Theme.NAVY);
            b.setForeground(new Color(0xCB, 0xD5, 0xE1));
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.addActionListener(e -> navigate(m));
            navButtons.put(m, b);
            pnlNav.add(b);
            pnlNav.add(Box.createVerticalStrut(3));
        }
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
        } else if (page instanceof Refreshable) {
            ((Refreshable) page).refresh();
        }
        pnlContent.removeAll();
        pnlContent.add(page, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();

        lblPageTitle.setText(menu.getLabel());
        lblBreadcrumb.setText("PRISMA / " + menu.getLabel());
        for (Map.Entry<Menu, JButton> e : navButtons.entrySet()) {
            boolean aktif = e.getKey() == menu;
            e.getValue().setBackground(aktif ? new Color(0x1E40AF) : Theme.NAVY);
            e.getValue().setForeground(aktif ? Color.WHITE : new Color(0xCB, 0xD5, 0xE1));
        }
    }

    private void logout() {
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

        pnlSidebar = new javax.swing.JPanel();
        lblBrand = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 22), new java.awt.Dimension(0, 22), new java.awt.Dimension(0, 22));
        pnlNav = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnlMain = new javax.swing.JPanel();
        pnlTopbar = new javax.swing.JPanel();
        pnlTopLeft = new javax.swing.JPanel();
        lblBreadcrumb = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4));
        lblPageTitle = new javax.swing.JLabel();
        pnlTopRight = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        pnlWho = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblUserRole = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        lblFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PRISMA - Sistem Informasi Manajemen Konten");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlSidebar.setBackground(new java.awt.Color(11, 31, 58));
        pnlSidebar.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 14, 14, 14));
        pnlSidebar.setLayout(new javax.swing.BoxLayout(pnlSidebar, javax.swing.BoxLayout.Y_AXIS));

        lblBrand.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setText("PRISMA");
        pnlSidebar.add(lblBrand);

        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblTagline.setForeground(new java.awt.Color(191, 219, 254));
        lblTagline.setText("CONTENT & BROADCAST");
        pnlSidebar.add(lblTagline);
        pnlSidebar.add(filler1);

        pnlNav.setAlignmentX(0.0F);
        pnlNav.setBackground(new java.awt.Color(11, 31, 58));
        pnlNav.setLayout(new javax.swing.BoxLayout(pnlNav, javax.swing.BoxLayout.Y_AXIS));
        pnlSidebar.add(pnlNav);
        pnlSidebar.add(filler2);

        getContentPane().add(pnlSidebar, java.awt.BorderLayout.WEST);

        pnlMain.setBackground(new java.awt.Color(243, 246, 251));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlTopbar.setBackground(new java.awt.Color(255, 255, 255));
        pnlTopbar.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(14, 28, 14, 24)));
        pnlTopbar.setLayout(new java.awt.BorderLayout());

        pnlTopLeft.setOpaque(false);
        pnlTopLeft.setLayout(new javax.swing.BoxLayout(pnlTopLeft, javax.swing.BoxLayout.Y_AXIS));

        lblBreadcrumb.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblBreadcrumb.setForeground(new java.awt.Color(100, 116, 139));
        lblBreadcrumb.setText(" ");
        pnlTopLeft.add(lblBreadcrumb);
        pnlTopLeft.add(filler3);

        lblPageTitle.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblPageTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblPageTitle.setText(" ");
        pnlTopLeft.add(lblPageTitle);

        pnlTopbar.add(pnlTopLeft, java.awt.BorderLayout.WEST);

        pnlTopRight.setOpaque(false);
        pnlTopRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblAvatar.setBackground(new java.awt.Color(37, 99, 235));
        lblAvatar.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblAvatar.setForeground(new java.awt.Color(255, 255, 255));
        lblAvatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvatar.setText("?");
        lblAvatar.setOpaque(true);
        pnlTopRight.add(lblAvatar);

        pnlWho.setOpaque(false);
        pnlWho.setLayout(new javax.swing.BoxLayout(pnlWho, javax.swing.BoxLayout.Y_AXIS));

        lblUserName.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(15, 23, 42));
        lblUserName.setText(" ");
        pnlWho.add(lblUserName);

        lblUserRole.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblUserRole.setForeground(new java.awt.Color(100, 116, 139));
        lblUserRole.setText(" ");
        pnlWho.add(lblUserRole);

        pnlTopRight.add(pnlWho);

        btnLogout.setBackground(new java.awt.Color(254, 226, 226));
        btnLogout.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(220, 38, 38));
        btnLogout.setText("Log Out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        pnlTopRight.add(btnLogout);

        pnlTopbar.add(pnlTopRight, java.awt.BorderLayout.EAST);

        pnlMain.add(pnlTopbar, java.awt.BorderLayout.NORTH);

        pnlContent.setBackground(new java.awt.Color(243, 246, 251));
        pnlContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 26, 10, 26));
        pnlContent.setLayout(new java.awt.BorderLayout());
        pnlMain.add(pnlContent, java.awt.BorderLayout.CENTER);

        lblFooter.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFooter.setForeground(new java.awt.Color(100, 116, 139));
        lblFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFooter.setText("PRISMA · Sistem informasi manajemen konten");
        lblFooter.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 10, 0));
        pnlMain.add(lblFooter, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmExit();
    }//GEN-LAST:event_formWindowClosing

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        logout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblBreadcrumb;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblPageTitle;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNav;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTopLeft;
    private javax.swing.JPanel pnlTopRight;
    private javax.swing.JPanel pnlTopbar;
    private javax.swing.JPanel pnlWho;
    // End of variables declaration//GEN-END:variables
}
