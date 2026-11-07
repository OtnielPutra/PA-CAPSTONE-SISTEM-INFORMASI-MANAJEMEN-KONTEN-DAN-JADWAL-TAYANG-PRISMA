package view;

import view.component.Refreshable;

import java.util.List;
import javax.swing.*;
import java.awt.*;

/**
 * Monitor Data: menampilkan seluruh data utama secara read-only dalam tab.
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file MonitorPanel.form - buka file ini lalu klik tab "Design" untuk
 * mengatur header dan tab secara drag-and-drop. Jangan mengedit kode di antara
 * tanda //GEN-BEGIN dan //GEN-END.
 *
 * Kerangka (header dan JTabbedPane dengan 5 tab) dikelola GUI Builder. Isi
 * tiap tab (halaman read-only dari PanelFactory) dipasang saat runtime.
  *
 * PERBARUAN (mainframe ada di dalam panel):
 * Kerangka halaman kini milik panel ini sendiri: pnlMenu (menu sidebar
 * PRISMA), pnlTopbar (breadcrumb, judul halaman, avatar, Log Out), dan
 * lblFooter - semuanya komponen form panel ini, jadi teksnya bisa
 * langsung diedit di tab Design panel ini. MainFrame hanya jendela
 * host tanpa kerangka, sehingga menu membentang penuh di sisi kiri.
*/
public class MonitorPanel extends JPanel implements Refreshable, HalamanUtama {
    private static final long serialVersionUID = 1L;

    private final JPanel[] pages;

    public MonitorPanel() {
        initComponents();
        // (false, false) = tab MonitorPanel: kerangka tiap panel disembunyikan
        // agar tidak tampil ganda. Halaman biasa tetap memakai konstruktor
        // satu-argumen yang kerangkanya SELALU tampil (lihat panel masing-masing).
        pages = new JPanel[] {new ProgramTvPanel(false, false), new KontenPanel(false, false),
                new JadwalPanel(false, false), new KerjaSamaPanel(false, false),
                new MitraPanel(false, false)};
        JPanel[] wraps = {wrapProgram, wrapKonten, wrapJadwal, wrapKerjaSama, wrapMitra};
        for (int i = 0; i < pages.length; i++) {
            wraps[i].add(pages[i], BorderLayout.CENTER);
        }
    }

    @Override
    public void refresh() {
        for (JPanel p : pages) {
            ((Refreshable) p).refresh();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        lblBrand = new javax.swing.JLabel();
        lblTagline = new javax.swing.JLabel();
        fillerMenu1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 22), new java.awt.Dimension(0, 22), new java.awt.Dimension(0, 22));
        lblGrupUtama = new javax.swing.JLabel();
        btnMenuDashboard = new javax.swing.JButton();
        btnMenuKonten = new javax.swing.JButton();
        btnMenuProgram = new javax.swing.JButton();
        btnMenuJadwal = new javax.swing.JButton();
        btnMenuPublikasi = new javax.swing.JButton();
        btnMenuLampiran = new javax.swing.JButton();
        btnMenuMonitor = new javax.swing.JButton();
        btnMenuKanal = new javax.swing.JButton();
        btnMenuMitra = new javax.swing.JButton();
        btnMenuKerjaSama = new javax.swing.JButton();
        btnMenuAkun = new javax.swing.JButton();
        lblGrupSistem = new javax.swing.JLabel();
        fillerMenu2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnlRight = new javax.swing.JPanel();
        pnlTopbar = new javax.swing.JPanel();
        pnlTopLeft = new javax.swing.JPanel();
        lblBreadcrumb = new javax.swing.JLabel();
        fillerTop1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4));
        lblPageTitle = new javax.swing.JLabel();
        pnlTopRight = new javax.swing.JPanel();
        lblAvatar = new javax.swing.JLabel();
        pnlWho = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblUserRole = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        pnlPage = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblSub = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        wrapProgram = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        wrapKonten = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        wrapJadwal = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        wrapKerjaSama = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        wrapMitra = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        lblFooter = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlMenu.setBackground(new java.awt.Color(11, 31, 58));
        pnlMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 14, 14, 14));
        pnlMenu.setLayout(new javax.swing.BoxLayout(pnlMenu, javax.swing.BoxLayout.Y_AXIS));

        lblBrand.setAlignmentX(0.0F);
        lblBrand.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setText("PRISMA");
        pnlMenu.add(lblBrand);

        lblTagline.setAlignmentX(0.0F);
        lblTagline.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblTagline.setForeground(new java.awt.Color(191, 219, 254));
        lblTagline.setText("CONTENT & BROADCAST");
        pnlMenu.add(lblTagline);
        pnlMenu.add(fillerMenu1);

        lblGrupUtama.setAlignmentX(0.0F);
        lblGrupUtama.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 8, 0));
        lblGrupUtama.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblGrupUtama.setForeground(new java.awt.Color(148, 163, 184));
        lblGrupUtama.setText("MENU UTAMA");
        pnlMenu.add(lblGrupUtama);

        btnMenuDashboard.setAlignmentX(0.0F);
        btnMenuDashboard.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuDashboard.setBorderPainted(false);
        btnMenuDashboard.setFocusPainted(false);
        btnMenuDashboard.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuDashboard.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuDashboard.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuDashboard.setText("Dashboard");
        btnMenuDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDashboardActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuDashboard);

        btnMenuKonten.setAlignmentX(0.0F);
        btnMenuKonten.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuKonten.setBorderPainted(false);
        btnMenuKonten.setFocusPainted(false);
        btnMenuKonten.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuKonten.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuKonten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuKonten.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuKonten.setText("Manajemen Konten");
        btnMenuKonten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuKontenActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuKonten);

        btnMenuProgram.setAlignmentX(0.0F);
        btnMenuProgram.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuProgram.setBorderPainted(false);
        btnMenuProgram.setFocusPainted(false);
        btnMenuProgram.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuProgram.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuProgram.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuProgram.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuProgram.setText("Program TV");
        btnMenuProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuProgramActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuProgram);

        btnMenuJadwal.setAlignmentX(0.0F);
        btnMenuJadwal.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuJadwal.setBorderPainted(false);
        btnMenuJadwal.setFocusPainted(false);
        btnMenuJadwal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuJadwal.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuJadwal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuJadwal.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuJadwal.setText("Jadwal Tayang");
        btnMenuJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuJadwalActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuJadwal);

        btnMenuPublikasi.setAlignmentX(0.0F);
        btnMenuPublikasi.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuPublikasi.setBorderPainted(false);
        btnMenuPublikasi.setFocusPainted(false);
        btnMenuPublikasi.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuPublikasi.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuPublikasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuPublikasi.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuPublikasi.setText("Publikasi Konten");
        btnMenuPublikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPublikasiActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuPublikasi);

        btnMenuLampiran.setAlignmentX(0.0F);
        btnMenuLampiran.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuLampiran.setBorderPainted(false);
        btnMenuLampiran.setFocusPainted(false);
        btnMenuLampiran.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuLampiran.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuLampiran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuLampiran.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuLampiran.setText("Lampiran Konten");
        btnMenuLampiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLampiranActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuLampiran);

        btnMenuMonitor.setAlignmentX(0.0F);
        btnMenuMonitor.setBackground(new java.awt.Color(30, 64, 175));
        btnMenuMonitor.setBorderPainted(false);
        btnMenuMonitor.setFocusPainted(false);
        btnMenuMonitor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuMonitor.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuMonitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuMonitor.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuMonitor.setText("Monitor Data");
        btnMenuMonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuMonitorActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuMonitor);

        btnMenuKanal.setAlignmentX(0.0F);
        btnMenuKanal.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuKanal.setBorderPainted(false);
        btnMenuKanal.setFocusPainted(false);
        btnMenuKanal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuKanal.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuKanal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuKanal.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuKanal.setText("Data Kanal");
        btnMenuKanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuKanalActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuKanal);

        btnMenuMitra.setAlignmentX(0.0F);
        btnMenuMitra.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuMitra.setBorderPainted(false);
        btnMenuMitra.setFocusPainted(false);
        btnMenuMitra.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuMitra.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuMitra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuMitra.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuMitra.setText("Data Mitra");
        btnMenuMitra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuMitraActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuMitra);

        btnMenuKerjaSama.setAlignmentX(0.0F);
        btnMenuKerjaSama.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuKerjaSama.setBorderPainted(false);
        btnMenuKerjaSama.setFocusPainted(false);
        btnMenuKerjaSama.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuKerjaSama.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuKerjaSama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuKerjaSama.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuKerjaSama.setText("Kerja Sama");
        btnMenuKerjaSama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuKerjaSamaActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuKerjaSama);

        btnMenuAkun.setAlignmentX(0.0F);
        btnMenuAkun.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuAkun.setBorderPainted(false);
        btnMenuAkun.setFocusPainted(false);
        btnMenuAkun.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuAkun.setForeground(new java.awt.Color(203, 213, 225));
        btnMenuAkun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenuAkun.setMaximumSize(new java.awt.Dimension(32767, 40));
        btnMenuAkun.setText("Akun Pengguna");
        btnMenuAkun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuAkunActionPerformed(evt);
            }
        });
        pnlMenu.add(btnMenuAkun);

        lblGrupSistem.setAlignmentX(0.0F);
        lblGrupSistem.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 8, 0));
        lblGrupSistem.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lblGrupSistem.setForeground(new java.awt.Color(148, 163, 184));
        lblGrupSistem.setText("PENGATURAN SISTEM");
        pnlMenu.add(lblGrupSistem);
        pnlMenu.add(fillerMenu2);

        pnlRight.setBackground(new java.awt.Color(243, 246, 251));
        pnlRight.setLayout(new java.awt.BorderLayout());

        pnlTopbar.setBackground(new java.awt.Color(255, 255, 255));
        pnlTopbar.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(14, 28, 14, 24)));
        pnlTopbar.setLayout(new java.awt.BorderLayout());

        pnlTopLeft.setOpaque(false);
        pnlTopLeft.setLayout(new javax.swing.BoxLayout(pnlTopLeft, javax.swing.BoxLayout.Y_AXIS));

        lblBreadcrumb.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblBreadcrumb.setForeground(new java.awt.Color(100, 116, 139));
        lblBreadcrumb.setText("PRISMA / Monitor Data");
        pnlTopLeft.add(lblBreadcrumb);
        pnlTopLeft.add(fillerTop1);

        lblPageTitle.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblPageTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblPageTitle.setText("Monitor Data");
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

        pnlRight.add(pnlTopbar, java.awt.BorderLayout.NORTH);

        pnlPage.setOpaque(false);
        pnlPage.setLayout(new java.awt.BorderLayout());
        pnlPage.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 28, 0, 0));

        lblFooter.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblFooter.setForeground(new java.awt.Color(100, 116, 139));
        lblFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFooter.setText("PRISMA · Sistem informasi manajemen konten");
        lblFooter.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 10, 0));
        pnlRight.add(lblFooter, java.awt.BorderLayout.SOUTH);

        pnlRight.add(pnlPage, java.awt.BorderLayout.CENTER);


        pnlHead.setOpaque(false);
        pnlHead.setLayout(new javax.swing.BoxLayout(pnlHead, javax.swing.BoxLayout.Y_AXIS));

        lblJudul.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblJudul.setText("Monitor Data");
        pnlHead.add(lblJudul);
        pnlHead.add(filler1);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Pantau seluruh data utama sistem");
        pnlHead.add(lblSub);

        pnlPage.add(pnlHead, java.awt.BorderLayout.NORTH);

        tabs.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N

        wrapProgram.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapProgram.setOpaque(false);
        wrapProgram.setLayout(new java.awt.BorderLayout());

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Nama Program", "Kategori", "URL", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        wrapProgram.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabs.addTab("Program TV", wrapProgram);

        wrapKonten.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapKonten.setOpaque(false);
        wrapKonten.setLayout(new java.awt.BorderLayout());

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Judul Konten", "Jenis", "Kategori", "Program TV", "Tgl Catat", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        wrapKonten.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        tabs.addTab("Konten", wrapKonten);

        wrapJadwal.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapJadwal.setOpaque(false);
        wrapJadwal.setLayout(new java.awt.BorderLayout());

                jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Program TV", "Kanal", "Tanggal", "Waktu", "Petugas", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        wrapJadwal.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        tabs.addTab("Jadwal Tayang", wrapJadwal);

        wrapKerjaSama.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapKerjaSama.setOpaque(false);
        wrapKerjaSama.setLayout(new java.awt.BorderLayout());

                jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Judul Kerja Sama", "Mitra", "Jenis", "Periode", "Nilai Kontrak", "Program TV", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        wrapKerjaSama.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        tabs.addTab("Kerja Sama", wrapKerjaSama);

        wrapMitra.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapMitra.setOpaque(false);
        wrapMitra.setLayout(new java.awt.BorderLayout());

                jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID Mitra", "Nama Mitra", "Jenis", "Email", "Alamat", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        wrapMitra.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        tabs.addTab("Mitra", wrapMitra);

        pnlPage.add(tabs, java.awt.BorderLayout.CENTER);

        add(pnlMenu, java.awt.BorderLayout.WEST);
        add(pnlRight, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    private void btnMenuDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDashboardActionPerformed
        navigate(Menu.DASHBOARD);
    }//GEN-LAST:event_btnMenuDashboardActionPerformed

    private void btnMenuKontenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuKontenActionPerformed
        navigate(Menu.KONTEN);
    }//GEN-LAST:event_btnMenuKontenActionPerformed

    private void btnMenuProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProgramActionPerformed
        navigate(Menu.PROGRAM);
    }//GEN-LAST:event_btnMenuProgramActionPerformed

    private void btnMenuJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuJadwalActionPerformed
        navigate(Menu.JADWAL);
    }//GEN-LAST:event_btnMenuJadwalActionPerformed

    private void btnMenuPublikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPublikasiActionPerformed
        navigate(Menu.PUBLIKASI);
    }//GEN-LAST:event_btnMenuPublikasiActionPerformed

    private void btnMenuLampiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLampiranActionPerformed
        navigate(Menu.LAMPIRAN);
    }//GEN-LAST:event_btnMenuLampiranActionPerformed

    private void btnMenuMonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuMonitorActionPerformed
        navigate(Menu.MONITOR);
    }//GEN-LAST:event_btnMenuMonitorActionPerformed

    private void btnMenuKanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuKanalActionPerformed
        navigate(Menu.KANAL);
    }//GEN-LAST:event_btnMenuKanalActionPerformed

    private void btnMenuMitraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuMitraActionPerformed
        navigate(Menu.MITRA);
    }//GEN-LAST:event_btnMenuMitraActionPerformed

    private void btnMenuKerjaSamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuKerjaSamaActionPerformed
        navigate(Menu.KERJASAMA);
    }//GEN-LAST:event_btnMenuKerjaSamaActionPerformed

    private void btnMenuAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAkunActionPerformed
        navigate(Menu.AKUN);
    }//GEN-LAST:event_btnMenuAkunActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        MainFrame mf = cariMainFrame();
        if (mf != null) {
            mf.logout();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    /** Mencari MainFrame pemilik panel ini (induk terdekat di pohon komponen). */
    private MainFrame cariMainFrame() {
        java.awt.Container p = getParent();
        while (p != null && !(p instanceof MainFrame)) {
            p = p.getParent();
        }
        return (MainFrame) p;
    }

    /** Meneruskan perintah pindah halaman ke MainFrame pemilik panel ini. */
    void navigate(Menu menu) {
        MainFrame mf = cariMainFrame();
        if (mf != null) {
            mf.navigate(menu);
        }
    }

    /** Menyembunyikan tombol menu yang tidak boleh diakses peran pengguna. */
    @Override
    public void terapkanHakAkses(String peran) {
        if (peran == null) {
            return; // tanpa peran (mis. preview) - tampilkan semua
        }
        java.util.List<Menu> diizinkan = Menu.untukPeran(peran);
        btnMenuDashboard.setVisible(diizinkan.contains(Menu.DASHBOARD));
        btnMenuKonten.setVisible(diizinkan.contains(Menu.KONTEN));
        btnMenuProgram.setVisible(diizinkan.contains(Menu.PROGRAM));
        btnMenuJadwal.setVisible(diizinkan.contains(Menu.JADWAL));
        btnMenuPublikasi.setVisible(diizinkan.contains(Menu.PUBLIKASI));
        btnMenuLampiran.setVisible(diizinkan.contains(Menu.LAMPIRAN));
        btnMenuMonitor.setVisible(diizinkan.contains(Menu.MONITOR));
        btnMenuKanal.setVisible(diizinkan.contains(Menu.KANAL));
        btnMenuMitra.setVisible(diizinkan.contains(Menu.MITRA));
        btnMenuKerjaSama.setVisible(diizinkan.contains(Menu.KERJASAMA));
        btnMenuAkun.setVisible(diizinkan.contains(Menu.AKUN));
        lblGrupUtama.setVisible(adaMenuTampak(false, diizinkan));
        lblGrupSistem.setVisible(adaMenuTampak(true, diizinkan));
    }

    private static boolean adaMenuTampak(boolean grupSistem, java.util.List<Menu> diizinkan) {
        for (Menu m : diizinkan) {
            if (m.isPengaturanSistem() == grupSistem) {
                return true;
            }
        }
        return false;
    }

    /** Menampilkan identitas pengguna yang sedang login pada topbar panel ini. */
    @Override
    public void pasangIdentitas(model.Pengguna pengguna) {
        String nama = pengguna.getNamaLengkap() == null ? "" : pengguna.getNamaLengkap().trim();
        lblAvatar.setText(nama.isEmpty() ? "?" : nama.substring(0, 1).toUpperCase());
        lblUserName.setText(nama);
        lblUserRole.setText(pengguna.getRole());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenuAkun;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnMenuJadwal;
    private javax.swing.JButton btnMenuKanal;
    private javax.swing.JButton btnMenuKerjaSama;
    private javax.swing.JButton btnMenuKonten;
    private javax.swing.JButton btnMenuLampiran;
    private javax.swing.JButton btnMenuMitra;
    private javax.swing.JButton btnMenuMonitor;
    private javax.swing.JButton btnMenuProgram;
    private javax.swing.JButton btnMenuPublikasi;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler fillerMenu1;
    private javax.swing.Box.Filler fillerMenu2;
    private javax.swing.Box.Filler fillerTop1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblBreadcrumb;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblGrupSistem;
    private javax.swing.JLabel lblGrupUtama;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPageTitle;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlPage;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlTopLeft;
    private javax.swing.JPanel pnlTopRight;
    private javax.swing.JPanel pnlTopbar;
    private javax.swing.JPanel pnlWho;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JPanel wrapJadwal;
    private javax.swing.JPanel wrapKerjaSama;
    private javax.swing.JPanel wrapKonten;
    private javax.swing.JPanel wrapMitra;
    private javax.swing.JPanel wrapProgram;
    // End of variables declaration//GEN-END:variables
}
