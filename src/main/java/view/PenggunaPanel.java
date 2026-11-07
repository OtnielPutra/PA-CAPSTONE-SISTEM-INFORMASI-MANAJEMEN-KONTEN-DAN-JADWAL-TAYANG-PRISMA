package view;

import controller.AkunController;
import model.Pengguna;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Halaman CRUD "Akun Pengguna" - JPanel MANDIRI (pure Java Swing).
 *
 * Berbeda dari versi lama, class ini TIDAK mewarisi class GUI apa pun.
 * Seluruh kerangka tampilan (judul, tombol tambah, pencarian, aksi, tabel)
 * berada di form NetBeans milik class ini sendiri: buka file ini lalu klik
 * tab "Design" untuk mengedit tampilan.
 *
 * Tambah/ubah data dibuka lewat PenggunaDialog (JDialog mandiri).
  *
 * PERBARUAN (mainframe ada di dalam panel):
 * Kerangka halaman kini milik panel ini sendiri: pnlMenu (menu sidebar
 * PRISMA), pnlTopbar (breadcrumb, judul halaman, avatar, Log Out), dan
 * lblFooter - semuanya komponen form panel ini, jadi teksnya bisa
 * langsung diedit di tab Design panel ini. MainFrame hanya jendela
 * host tanpa kerangka, sehingga menu membentang penuh di sisi kiri.
 * Sebagai halaman biasa kerangka ini SELALU tampil - juga pada mode
 * lihat-saja (petugas konten / petugas jadwal yang hanya mencari data) -
 * supaya menu navigasi tetap ada di sisi kiri layar. Kerangka baru
 * disembunyikan saat panel dipasang di dalam tab MonitorPanel, yaitu
 * lewat konstruktor (false, false), agar tidak tampil ganda.
*/
public class PenggunaPanel extends javax.swing.JPanel implements view.component.Refreshable, HalamanUtama {

    private static final long serialVersionUID = 1L;

    private static final String[] KOLOM = {"ID", "Nama Lengkap", "Username", "Peran", "Detail Penugasan", "Tgl Penugasan", "Status"};
    private static final int[] LEBAR_KOLOM = {45, 180, 140, 130, 220, 120, 90};
    private static final int KOLOM_STATUS = 6;

    private static final java.time.format.DateTimeFormatter TGL = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy", new java.util.Locale("id", "ID"));

    private final AkunController controller = new AkunController();
    private final boolean editable;
    private DefaultTableModel tblModel;
    private List<Pengguna> dataBaris = new ArrayList<>();

    /** Konstruktor kosong - dipakai NetBeans untuk merender Design view. */
    public PenggunaPanel() {
        this(true, true);
    }

    /**
     * Konstruktor halaman (dipakai PanelFactory). Kerangka mainframe
     * (menu, topbar, footer) SELALU tampil - juga untuk mode lihat-saja
     * (petugas konten / petugas jadwal yang hanya mencari data), supaya
     * menu navigasi tetap ada di sisi kiri layar.
     *
     * @param editable false untuk mode lihat-saja: tombol Tambah/Ubah/Hapus
     *                 disembunyikan dan klik ganda tabel dinonaktifkan.
     */
    public PenggunaPanel(boolean editable) {
        this(editable, true);
    }

    /**
     * @param editable          false untuk mode lihat-saja (tombol aksi disembunyikan).
     * @param tampilkanKerangka false HANYA saat panel dipasang di dalam tab
     *                          MonitorPanel: kerangka (menu, topbar, footer)
     *                          disembunyikan agar tidak tampil ganda.
     */
    public PenggunaPanel(boolean editable, boolean tampilkanKerangka) {
        this.editable = editable;
        initComponents();
        if (!tampilkanKerangka) {
            // Pemakaian di dalam tab MonitorPanel: kerangka menu, topbar,
            // dan footer disembunyikan agar tidak tampil ganda.
            pnlMenu.setVisible(false);
            pnlTopbar.setVisible(false);
            lblFooter.setVisible(false);
            // Tanpa padding kiri pnlPage: isi tab tidak menambah +28 ganda
            // di dalam halaman Monitor Data (yang sudah memberi +28 miliknya).
            pnlPage.setBorder(null);
        }
        initTabel();
    }

    /** Memuat ulang data dari database ke tabel. */
    public void refresh() {
        muatUlang();
    }

    private void initTabel() {
        // Model tabel diambil dari form (GUI Builder): judul/kolom diatur lewat
        // tab Design (Properties -> model), baris diisi dari database di muatUlang().
        // Model TIDAK digantikan lagi, jadi hasil desain ikut tampil saat aplikasi jalan.
        tblModel = (DefaultTableModel) tblData.getModel();
        tblData.setRowHeight(34);
        tblData.setFillsViewportHeight(true);
        tblData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblData.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tblData.setShowVerticalLines(false);
        tblData.setShowHorizontalLines(true);
        tblData.setGridColor(new Color(0xE2, 0xE8, 0xF0));
        tblData.setSelectionBackground(new Color(0xDB, 0xEA, 0xFE));
        tblData.setSelectionForeground(new Color(0x0F, 0x17, 0x2A));
        JTableHeader kepala = tblData.getTableHeader();
        kepala.setFont(new Font("SansSerif", Font.BOLD, 12));
        kepala.setForeground(new Color(0x64, 0x74, 0x8B));
        kepala.setBackground(new Color(0xF8, 0xFA, 0xFC));
        kepala.setReorderingAllowed(false);
        for (int i = 0; i < LEBAR_KOLOM.length && i < tblData.getColumnCount(); i++) {
            tblData.getColumnModel().getColumn(i).setPreferredWidth(LEBAR_KOLOM[i]);
        }
        tblData.setDefaultRenderer(Object.class, new RenderBaris(KOLOM_STATUS));
        tblData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && editable) {
                    ubahTerpilih();
                }
            }
        });
        btnAdd.setVisible(editable);
        btnUbah.setVisible(editable);
        btnHapus.setVisible(editable);
        muatUlang();
    }

    private void muatUlang() {
        List<Pengguna> data = controller.cari(txtSearch.getText().trim());
        dataBaris = data;
        tblModel.setRowCount(0);
        for (Pengguna p : data) {
            tblModel.addRow(new Object[] {nilaiSel(p, 0), nilaiSel(p, 1), nilaiSel(p, 2), nilaiSel(p, 3), nilaiSel(p, 4), nilaiSel(p, 5), nilaiSel(p, 6)});
        }
        lblCount.setText(data.size() + " data");
        if (data.isEmpty() && !controller.getPesan().isEmpty()) {
            lblCount.setText("Gagal memuat data: " + controller.getPesan());
        }
    }

    private Object nilaiSel(Pengguna p, int c) {
        switch (c) {
            case 0: return String.valueOf(p.getIdPengguna());
            case 1: return p.getNamaLengkap();
            case 2: return p.getUsername();
            case 3: return p.getRole();
            case 4: return p.getKeterangan() == null || p.getKeterangan().trim().isEmpty() ? "-" : p.getKeterangan();
            case 5: return p.getTanggalPenugasan() == null ? "-" : TGL.format(p.getTanggalPenugasan());
            case 6: return p.getStatusAkun();
            default: return "";
        }
    }

    private Pengguna itemTerpilih() {
        int view = tblData.getSelectedRow();
        if (view < 0) {
            return null;
        }
        return dataBaris.get(tblData.convertRowIndexToModel(view));
    }

    private void tambahData() {
        PenggunaDialog d = new PenggunaDialog(owner(), null);
        if (d.tampilkan()) {
            muatUlang();
            info("Data berhasil disimpan.");
        }
    }

    private void ubahTerpilih() {
        Pengguna terpilih = itemTerpilih();
        if (terpilih == null) {
            info("Pilih satu baris data terlebih dahulu.");
            return;
        }
        PenggunaDialog d = new PenggunaDialog(owner(), terpilih);
        if (d.tampilkan()) {
            muatUlang();
            info("Data berhasil disimpan.");
        }
    }

    private void hapusTerpilih() {
        Pengguna terpilih = itemTerpilih();
        if (terpilih == null) {
            info("Pilih satu baris data terlebih dahulu.");
            return;
        }
        int ok = javax.swing.JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?",
                "Konfirmasi Hapus", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
        if (ok != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }
        if (controller.hapus(terpilih.getIdPengguna())) {
            muatUlang();
            info("Data berhasil dihapus.");
        } else {
            galat(controller.getPesan());
        }
    }

    private void resetPencarian() {
        txtSearch.setText("");
        muatUlang();
    }

    private java.awt.Window owner() {
        return SwingUtilities.getWindowAncestor(this);
    }

    private void info(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    private void galat(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Kesalahan", JOptionPane.ERROR_MESSAGE);
    }

    /** Pewarna baris tabel: belang putih-abu; kolom status ditebalkan dan diberi warna. */
    private static class RenderBaris extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
        private final int kolomStatus;

        RenderBaris(int kolomStatus) {
            this.kolomStatus = kolomStatus;
        }

        @Override
        public Component getTableCellRendererComponent(JTable t, Object nilai, boolean pilih,
                boolean fokus, int baris, int kolom) {
            super.getTableCellRendererComponent(t, nilai == null ? "-" : nilai, pilih, fokus, baris, kolom);
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
            setFont(new Font("SansSerif", Font.PLAIN, 13));
            if (!pilih) {
                setBackground(baris % 2 == 0 ? Color.WHITE : new Color(0xFA, 0xFB, 0xFD));
                setForeground(new Color(0x0F, 0x17, 0x2A));
            }
            if (kolom == kolomStatus && nilai != null) {
                setFont(new Font("SansSerif", Font.BOLD, 12));
                setForeground(warnaStatus(nilai.toString()));
            }
            return this;
        }
    }

    private static Color warnaStatus(String s) {
        switch (s) {
            case "aktif":
            case "Berjalan":
            case "Terbit":
            case "Sudah Tayang":
                return new Color(0x16, 0xA3, 0x4A);
            case "Siap Tayang":
            case "Terjadwal":
                return new Color(0x25, 0x63, 0xEB);
            case "Direncanakan":
            case "Dalam Proses":
                return new Color(0xD9, 0x77, 0x06);
            case "Dibatalkan":
            case "nonaktif":
                return new Color(0xDC, 0x26, 0x26);
            default:
                return new Color(0x64, 0x74, 0x8B);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
        pnlHeadText = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblSubtitle = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        pnlCard = new javax.swing.JPanel();
        pnlToolbar = new javax.swing.JPanel();
        pnlSearch = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pnlActions = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        scrTable = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lblCount = new javax.swing.JLabel();
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
        btnMenuAkun.setBackground(new java.awt.Color(30, 64, 175));
        btnMenuAkun.setBorderPainted(false);
        btnMenuAkun.setFocusPainted(false);
        btnMenuAkun.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnMenuAkun.setForeground(new java.awt.Color(255, 255, 255));
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
        lblBreadcrumb.setText("PRISMA / Akun Pengguna");
        pnlTopLeft.add(lblBreadcrumb);
        pnlTopLeft.add(fillerTop1);

        lblPageTitle.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        lblPageTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblPageTitle.setText("Akun Pengguna");
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
        pnlHead.setLayout(new java.awt.BorderLayout());

        pnlHeadText.setOpaque(false);
        pnlHeadText.setLayout(new javax.swing.BoxLayout(pnlHeadText, javax.swing.BoxLayout.Y_AXIS));

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTitle.setText("Akun Pengguna");
        pnlHeadText.add(lblTitle);
        pnlHeadText.add(filler1);

        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Data pengguna dan peran dalam sistem.");
        pnlHeadText.add(lblSubtitle);

        pnlHead.add(pnlHeadText, java.awt.BorderLayout.WEST);

        btnAdd.setBackground(new java.awt.Color(37, 99, 235));
        btnAdd.setFocusPainted(false);
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("+ Tambah Akun");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlHead.add(btnAdd, java.awt.BorderLayout.EAST);

        pnlPage.add(pnlHead, java.awt.BorderLayout.NORTH);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 0, 12, 16));
        pnlCard.setLayout(new java.awt.BorderLayout());

        pnlToolbar.setOpaque(false);
        pnlToolbar.setLayout(new java.awt.BorderLayout());

        pnlSearch.setOpaque(false);
        pnlSearch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));


        txtSearch.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtSearch.setColumns(24);
        txtSearch.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(15, 23, 42));
        txtSearch.setToolTipText("Ketik kata kunci lalu tekan Enter");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        pnlSearch.add(txtSearch);

        btnCari.setBackground(new java.awt.Color(37, 99, 235));
        btnCari.setFocusPainted(false);
        btnCari.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        pnlSearch.add(btnCari);

        btnReset.setFocusPainted(false);
        btnReset.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnReset.setForeground(new java.awt.Color(15, 23, 42));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        pnlSearch.add(btnReset);


        pnlToolbar.add(pnlSearch, java.awt.BorderLayout.WEST);

        pnlActions.setOpaque(false);
        pnlActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnUbah.setFocusPainted(false);
        btnUbah.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        pnlActions.add(btnUbah);

        btnHapus.setFocusPainted(false);
        btnHapus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        pnlActions.add(btnHapus);

        pnlToolbar.add(pnlActions, java.awt.BorderLayout.EAST);

        pnlCard.add(pnlToolbar, java.awt.BorderLayout.NORTH);

        scrTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Nama Lengkap", "Username", "Peran", "Detail Penugasan", "Tgl Penugasan", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrTable.setViewportView(tblData);

        pnlCard.add(scrTable, java.awt.BorderLayout.CENTER);

        lblCount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblCount.setForeground(new java.awt.Color(100, 116, 139));
        lblCount.setText(" ");
        pnlCard.add(lblCount, java.awt.BorderLayout.SOUTH);

        pnlPage.add(pnlCard, java.awt.BorderLayout.CENTER);

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

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        tambahData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        muatUlang();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetPencarian();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        ubahTerpilih();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        hapusTerpilih();
    }//GEN-LAST:event_btnHapusActionPerformed
    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        muatUlang();
    }//GEN-LAST:event_txtSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
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
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUbah;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler fillerMenu1;
    private javax.swing.Box.Filler fillerMenu2;
    private javax.swing.Box.Filler fillerTop1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblBreadcrumb;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblGrupSistem;
    private javax.swing.JLabel lblGrupUtama;
    private javax.swing.JLabel lblPageTitle;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblUserRole;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlHeadText;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlPage;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlToolbar;
    private javax.swing.JPanel pnlTopLeft;
    private javax.swing.JPanel pnlTopRight;
    private javax.swing.JPanel pnlTopbar;
    private javax.swing.JPanel pnlWho;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
