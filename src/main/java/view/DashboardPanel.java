package view;

import controller.DashboardController;
import model.JadwalTayang;
import model.Pengguna;
import view.component.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Dashboard: kartu statistik + jadwal tayang terbaru + relasi mitra.
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file DashboardPanel.form - buka file ini lalu klik tab "Design" untuk
 * mengatur tampilan secara drag-and-drop. Jangan mengedit kode di antara
 * tanda //GEN-BEGIN dan //GEN-END; perubahan tampilan dilakukan lewat Design.
 *
 * Kerangka tampilan (header, 4 kartu statistik, 2 kartu tabel beserta kolomnya)
 * kini dikelola GUI Builder: tabel tblJadwal dan tblMitra milik form dipakai
 * LANGSUNG saat runtime (tidak digantikan tabel lain). Bagian dinamis (nama
 * pengguna, angka statistik, isi baris tabel) diisi lewat kode di luar blok
 * yang dijaga.
  *
 * PERBARUAN (mainframe ada di dalam panel):
 * Kerangka halaman kini milik panel ini sendiri: pnlMenu (menu sidebar
 * PRISMA), pnlTopbar (breadcrumb, judul halaman, avatar, Log Out), dan
 * lblFooter - semuanya komponen form panel ini, jadi teksnya bisa
 * langsung diedit di tab Design panel ini. MainFrame hanya jendela
 * host tanpa kerangka, sehingga menu membentang penuh di sisi kiri.
*/
public class DashboardPanel extends JPanel implements Refreshable, HalamanUtama {
    private static final long serialVersionUID = 1L;

    private final DashboardController controller = new DashboardController();

    public DashboardPanel(Pengguna user) {
        initComponents();
        lblH1.setText("Selamat datang, " + user.getNamaLengkap());
        lblSub.setText("Anda masuk sebagai " + user.getRole()
                + ". Ringkasan pengelolaan konten dan jadwal tayang.");
        initTables();
        refresh();
    }

    /** Memberi gaya DataTable pada tabel milik form - tabel TIDAK digantikan. */
    private void initTables() {
        DataTable.gayakan(tblJadwal, 4); // kolom ke-5 (Status) diberi warna
        scrJadwal.getViewport().setBackground(Color.WHITE);

        DataTable.gayakan(tblMitra, -1);
        scrMitra.getViewport().setBackground(Color.WHITE);

        scrBody.getViewport().setOpaque(false);
        scrBody.getVerticalScrollBar().setUnitIncrement(16);
    }

    @Override
    public void refresh() {
        DashboardController.Stats s = controller.ringkasan();
        lblNilaiKonten.setText(String.valueOf(s.konten));
        lblNilaiProgram.setText(String.valueOf(s.program));
        lblNilaiJadwal.setText(String.valueOf(s.jadwal));
        lblNilaiKerjaSama.setText(String.valueOf(s.kerjaSama));

        // Baris diisi ke model milik form; judul/kolom tetap dari Design view.
        List<JadwalTayang> jadwal = controller.jadwalTerbaru(5);
        DefaultTableModel mk = (DefaultTableModel) tblJadwal.getModel();
        mk.setRowCount(0);
        for (JadwalTayang j : jadwal) {
            mk.addRow(new Object[] {
                Fmt.dash(j.getNamaProgram()),
                Fmt.dash(j.getNamaKanal()),
                Fmt.date(j.getTanggalTayang()),
                Fmt.time(j.getWaktuMulai()) + " - " + Fmt.time(j.getWaktuSelesai()),
                j.getStatusJadwal()
            });
        }

        DefaultTableModel mm = (DefaultTableModel) tblMitra.getModel();
        mm.setRowCount(0);
        for (Object[] baris : controller.kerjaSamaPerMitra(6)) {
            mm.addRow(new Object[] {String.valueOf(baris[0]), baris[1] + " kerja sama"});
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
        lblH1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblSub = new javax.swing.JLabel();
        scrBody = new javax.swing.JScrollPane();
        pnlBody = new javax.swing.JPanel();
        pnlStats = new javax.swing.JPanel();
        cardKonten = new view.component.RoundedPanel();
        lblKonten = new javax.swing.JLabel();
        lblNilaiKonten = new javax.swing.JLabel();
        cardProgram = new view.component.RoundedPanel();
        lblProgram = new javax.swing.JLabel();
        lblNilaiProgram = new javax.swing.JLabel();
        cardJadwalStat = new view.component.RoundedPanel();
        lblJadwalStat = new javax.swing.JLabel();
        lblNilaiJadwal = new javax.swing.JLabel();
        cardKerjaSama = new view.component.RoundedPanel();
        lblKerjaSama = new javax.swing.JLabel();
        lblNilaiKerjaSama = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18));
        cardJadwal = new view.component.RoundedPanel();
        pnlJadwalHead = new javax.swing.JPanel();
        lblJadwalJudul = new javax.swing.JLabel();
        scrJadwal = new javax.swing.JScrollPane();
        tblJadwal = new javax.swing.JTable();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18));
        cardMitra = new view.component.RoundedPanel();
        pnlMitraHead = new javax.swing.JPanel();
        lblMitraJudul = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4));
        lblMitraCatatan = new javax.swing.JLabel();
        scrMitra = new javax.swing.JScrollPane();
        tblMitra = new javax.swing.JTable();
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
        btnMenuDashboard.setBackground(new java.awt.Color(30, 64, 175));
        btnMenuDashboard.setBorderPainted(false);
        btnMenuDashboard.setFocusPainted(false);
        btnMenuDashboard.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuDashboard.setForeground(new java.awt.Color(255, 255, 255));
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
        btnMenuMonitor.setBackground(new java.awt.Color(11, 31, 58));
        btnMenuMonitor.setBorderPainted(false);
        btnMenuMonitor.setFocusPainted(false);
        btnMenuMonitor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuMonitor.setForeground(new java.awt.Color(203, 213, 225));
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
        lblBreadcrumb.setText("PRISMA / Dashboard");
        pnlTopLeft.add(lblBreadcrumb);
        pnlTopLeft.add(fillerTop1);

        lblPageTitle.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblPageTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblPageTitle.setText("Dashboard");
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

        lblH1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblH1.setForeground(new java.awt.Color(15, 23, 42));
        lblH1.setText("Selamat datang");
        pnlHead.add(lblH1);
        pnlHead.add(filler1);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Anda mas sebagai pengguna. Ringkasan pengelolaan konten dan jadwal tayang.");
        pnlHead.add(lblSub);

        pnlPage.add(pnlHead, java.awt.BorderLayout.NORTH);

        scrBody.setBorder(null);
        scrBody.setOpaque(false);

        pnlBody.setOpaque(false);
        pnlBody.setLayout(new javax.swing.BoxLayout(pnlBody, javax.swing.BoxLayout.Y_AXIS));

        pnlStats.setAlignmentX(0.0F);
        pnlStats.setOpaque(false);
        pnlStats.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        cardKonten.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardKonten.setLayout(new java.awt.BorderLayout());

        lblKonten.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblKonten.setForeground(new java.awt.Color(100, 116, 139));
        lblKonten.setText("Total Konten");
        cardKonten.add(lblKonten, java.awt.BorderLayout.NORTH);

        lblNilaiKonten.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiKonten.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiKonten.setText("0");
        cardKonten.add(lblNilaiKonten, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardKonten);

        cardProgram.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardProgram.setLayout(new java.awt.BorderLayout());

        lblProgram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblProgram.setForeground(new java.awt.Color(100, 116, 139));
        lblProgram.setText("Program TV");
        cardProgram.add(lblProgram, java.awt.BorderLayout.NORTH);

        lblNilaiProgram.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiProgram.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiProgram.setText("0");
        cardProgram.add(lblNilaiProgram, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardProgram);

        cardJadwalStat.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardJadwalStat.setLayout(new java.awt.BorderLayout());

        lblJadwalStat.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblJadwalStat.setForeground(new java.awt.Color(100, 116, 139));
        lblJadwalStat.setText("Jadwal Tayang");
        cardJadwalStat.add(lblJadwalStat, java.awt.BorderLayout.NORTH);

        lblNilaiJadwal.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiJadwal.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiJadwal.setText("0");
        cardJadwalStat.add(lblNilaiJadwal, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardJadwalStat);

        cardKerjaSama.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardKerjaSama.setLayout(new java.awt.BorderLayout());

        lblKerjaSama.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblKerjaSama.setForeground(new java.awt.Color(100, 116, 139));
        lblKerjaSama.setText("Kerja Sama");
        cardKerjaSama.add(lblKerjaSama, java.awt.BorderLayout.NORTH);

        lblNilaiKerjaSama.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiKerjaSama.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiKerjaSama.setText("0");
        cardKerjaSama.add(lblNilaiKerjaSama, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardKerjaSama);

        pnlBody.add(pnlStats);
        pnlBody.add(filler2);

        cardJadwal.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        cardJadwal.setAlignmentX(0.0F);
        cardJadwal.setLayout(new java.awt.BorderLayout());

        pnlJadwalHead.setOpaque(false);
        pnlJadwalHead.setLayout(new javax.swing.BoxLayout(pnlJadwalHead, javax.swing.BoxLayout.Y_AXIS));

        lblJadwalJudul.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblJadwalJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblJadwalJudul.setText("Jadwal Tayang Terbaru");
        pnlJadwalHead.add(lblJadwalJudul);

        cardJadwal.add(pnlJadwalHead, java.awt.BorderLayout.NORTH);

        scrJadwal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));

        tblJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Program", "Kanal", "Tanggal", "Waktu", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrJadwal.setViewportView(tblJadwal);

        cardJadwal.add(scrJadwal, java.awt.BorderLayout.CENTER);

        pnlBody.add(cardJadwal);
        pnlBody.add(filler3);

        cardMitra.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        cardMitra.setAlignmentX(0.0F);
        cardMitra.setLayout(new java.awt.BorderLayout());

        pnlMitraHead.setOpaque(false);
        pnlMitraHead.setLayout(new javax.swing.BoxLayout(pnlMitraHead, javax.swing.BoxLayout.Y_AXIS));

        lblMitraJudul.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblMitraJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblMitraJudul.setText("Relasi Mitra dan Kerja Sama");
        pnlMitraHead.add(lblMitraJudul);
        pnlMitraHead.add(filler4);

        lblMitraCatatan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMitraCatatan.setForeground(new java.awt.Color(100, 116, 139));
        lblMitraCatatan.setText("Setiap kerja sama mengacu pada satu mitra terdaftar.");
        pnlMitraHead.add(lblMitraCatatan);

        cardMitra.add(pnlMitraHead, java.awt.BorderLayout.NORTH);

        scrMitra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));

        tblMitra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mitra", "Jumlah Kerja Sama"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrMitra.setViewportView(tblMitra);

        cardMitra.add(scrMitra, java.awt.BorderLayout.CENTER);

        pnlBody.add(cardMitra);

        scrBody.setViewportView(pnlBody);

        pnlPage.add(scrBody, java.awt.BorderLayout.CENTER);

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
    private view.component.RoundedPanel cardJadwal;
    private view.component.RoundedPanel cardJadwalStat;
    private view.component.RoundedPanel cardKerjaSama;
    private view.component.RoundedPanel cardKonten;
    private view.component.RoundedPanel cardMitra;
    private view.component.RoundedPanel cardProgram;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler fillerMenu1;
    private javax.swing.Box.Filler fillerMenu2;
    private javax.swing.Box.Filler fillerTop1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblBreadcrumb;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblGrupSistem;
    private javax.swing.JLabel lblGrupUtama;
    private javax.swing.JLabel lblH1;
    private javax.swing.JLabel lblJadwalJudul;
    private javax.swing.JLabel lblJadwalStat;
    private javax.swing.JLabel lblKerjaSama;
    private javax.swing.JLabel lblKonten;
    private javax.swing.JLabel lblMitraCatatan;
    private javax.swing.JLabel lblMitraJudul;
    private javax.swing.JLabel lblNilaiJadwal;
    private javax.swing.JLabel lblNilaiKerjaSama;
    private javax.swing.JLabel lblNilaiKonten;
    private javax.swing.JLabel lblNilaiProgram;
    private javax.swing.JLabel lblPageTitle;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlJadwalHead;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMitraHead;
    private javax.swing.JPanel pnlPage;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlStats;
    private javax.swing.JPanel pnlTopLeft;
    private javax.swing.JPanel pnlTopRight;
    private javax.swing.JPanel pnlTopbar;
    private javax.swing.JPanel pnlWho;
    private javax.swing.JScrollPane scrBody;
    private javax.swing.JScrollPane scrJadwal;
    private javax.swing.JScrollPane scrMitra;
    private javax.swing.JTable tblJadwal;
    private javax.swing.JTable tblMitra;
    // End of variables declaration//GEN-END:variables
}
