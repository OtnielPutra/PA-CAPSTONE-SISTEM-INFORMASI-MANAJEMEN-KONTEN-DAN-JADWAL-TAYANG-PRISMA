package view;

import controller.JadwalController;
import model.JadwalTayang;

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
 * Halaman CRUD "Jadwal Tayang" - JPanel MANDIRI (pure Java Swing).
 *
 * Berbeda dari versi lama, class ini TIDAK mewarisi class GUI apa pun.
 * Seluruh kerangka tampilan (judul, tombol tambah, pencarian, aksi, tabel)
 * berada di form NetBeans milik class ini sendiri: buka file ini lalu klik
 * tab "Design" untuk mengedit tampilan.
 *
 * Tambah/ubah data dibuka lewat JadwalDialog (JDialog mandiri).
 */
public class JadwalPanel extends javax.swing.JPanel implements view.component.Refreshable {

    private static final long serialVersionUID = 1L;

    private static final String[] KOLOM = {"ID", "Program TV", "Kanal", "Tanggal", "Waktu", "Petugas", "Status"};
    private static final int[] LEBAR_KOLOM = {45, 220, 130, 120, 130, 160, 110};
    private static final int KOLOM_STATUS = 6;

    private static final java.time.format.DateTimeFormatter TGL = java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy", new java.util.Locale("id", "ID"));
    private static final java.time.format.DateTimeFormatter JAM = java.time.format.DateTimeFormatter.ofPattern("HH:mm");

    private final JadwalController controller = new JadwalController();
    private final boolean editable;
    private DefaultTableModel tblModel;
    private List<JadwalTayang> dataBaris = new ArrayList<>();

    /** Konstruktor kosong - dipakai NetBeans untuk merender Design view. */
    public JadwalPanel() {
        this(true);
    }

    /** @param editable false untuk mode lihat-saja (tombol aksi disembunyikan). */
    public JadwalPanel(boolean editable) {
        this.editable = editable;
        initComponents();
        initTabel();
    }

    /** Memuat ulang data dari database ke tabel. */
    public void refresh() {
        muatUlang();
    }

    private void initTabel() {
        tblModel = new DefaultTableModel(KOLOM, 0) {
            private static final long serialVersionUID = 2L;

            @Override
            public boolean isCellEditable(int baris, int kolom) {
                return false;
            }
        };
        tblData.setModel(tblModel);
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

        sprCariTanggal.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        sprCariTanggal.setEditor(new javax.swing.JSpinner.DateEditor(sprCariTanggal, "yyyy-MM-dd"));
        sprCariMulai.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        sprCariMulai.setEditor(new javax.swing.JSpinner.DateEditor(sprCariMulai, "HH:mm"));
        sprCariSampai.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        sprCariSampai.setEditor(new javax.swing.JSpinner.DateEditor(sprCariSampai, "HH:mm"));
        btnAdd.setVisible(editable);
        btnUbah.setVisible(editable);
        btnHapus.setVisible(editable);
        muatUlang();
    }

    private void muatUlang() {
        List<JadwalTayang> data = controller.cariJadwal(filterTanggal(), filterWaktu(chkMulai, sprCariMulai), filterWaktu(chkSampai, sprCariSampai));
        dataBaris = data;
        tblModel.setRowCount(0);
        for (JadwalTayang j : data) {
            tblModel.addRow(new Object[] {nilaiSel(j, 0), nilaiSel(j, 1), nilaiSel(j, 2), nilaiSel(j, 3), nilaiSel(j, 4), nilaiSel(j, 5), nilaiSel(j, 6)});
        }
        lblCount.setText(data.size() + " data");
        if (data.isEmpty() && !controller.getPesan().isEmpty()) {
            lblCount.setText("Gagal memuat data: " + controller.getPesan());
        }
    }

    private Object nilaiSel(JadwalTayang j, int c) {
        switch (c) {
            case 0: return String.valueOf(j.getIdJadwal());
            case 1: return j.getNamaProgram() == null || j.getNamaProgram().trim().isEmpty() ? "-" : j.getNamaProgram();
            case 2: return j.getNamaKanal() == null || j.getNamaKanal().trim().isEmpty() ? "-" : j.getNamaKanal();
            case 3: return j.getTanggalTayang() == null ? "-" : TGL.format(j.getTanggalTayang());
            case 4: return (j.getWaktuMulai() == null ? "-" : JAM.format(j.getWaktuMulai())) + " - " + (j.getWaktuSelesai() == null ? "-" : JAM.format(j.getWaktuSelesai()));
            case 5: return j.getNamaPetugas() == null || j.getNamaPetugas().trim().isEmpty() ? "-" : j.getNamaPetugas();
            case 6: return j.getStatusJadwal();
            default: return "";
        }
    }

    private JadwalTayang itemTerpilih() {
        int view = tblData.getSelectedRow();
        if (view < 0) {
            return null;
        }
        return dataBaris.get(tblData.convertRowIndexToModel(view));
    }

    private void tambahData() {
        JadwalDialog d = new JadwalDialog(owner(), null);
        if (d.tampilkan()) {
            muatUlang();
            info("Data berhasil disimpan.");
        }
    }

    private void ubahTerpilih() {
        JadwalTayang terpilih = itemTerpilih();
        if (terpilih == null) {
            info("Pilih satu baris data terlebih dahulu.");
            return;
        }
        JadwalDialog d = new JadwalDialog(owner(), terpilih);
        if (d.tampilkan()) {
            muatUlang();
            info("Data berhasil disimpan.");
        }
    }

    private void hapusTerpilih() {
        JadwalTayang terpilih = itemTerpilih();
        if (terpilih == null) {
            info("Pilih satu baris data terlebih dahulu.");
            return;
        }
        int ok = javax.swing.JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?",
                "Konfirmasi Hapus", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
        if (ok != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }
        if (controller.hapus(terpilih.getIdJadwal())) {
            muatUlang();
            info("Data berhasil dihapus.");
        } else {
            galat(controller.getPesan());
        }
    }

    /** Aksi tambahan: membatalkan jadwal terpilih (status menjadi Dibatalkan). */
    private void nonaktifkanTerpilih() {
        JadwalTayang terpilih = itemTerpilih();
        if (terpilih == null) {
            info("Pilih satu jadwal terlebih dahulu.");
            return;
        }
        int ok = javax.swing.JOptionPane.showConfirmDialog(this, "Nonaktifkan (batalkan) jadwal ini?",
                "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
        if (ok != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }
        if (controller.nonaktifkan(terpilih.getIdJadwal())) {
            muatUlang();
            info("Nonaktif jadwal berhasil dilakukan.");
        } else {
            galat(controller.getPesan());
        }
    }

    private void resetPencarian() {
        chkTanggal.setSelected(false);
        sprCariTanggal.setEnabled(false);
        chkMulai.setSelected(false);
        sprCariMulai.setEnabled(false);
        chkSampai.setSelected(false);
        sprCariSampai.setEnabled(false);
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

    private java.time.LocalDate filterTanggal() {
        return chkTanggal.isSelected()
                ? ((java.util.Date) sprCariTanggal.getValue()).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalDate() : null;
    }

    private java.time.LocalTime filterWaktu(javax.swing.JCheckBox chk, javax.swing.JSpinner spr) {
        return chk.isSelected()
                ? ((java.util.Date) spr.getValue()).toInstant()
                        .atZone(java.time.ZoneId.systemDefault()).toLocalTime().withSecond(0).withNano(0) : null;
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

        pnlHead = new javax.swing.JPanel();
        pnlHeadText = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblSubtitle = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        pnlCard = new javax.swing.JPanel();
        pnlToolbar = new javax.swing.JPanel();
        pnlSearch = new javax.swing.JPanel();
        chkTanggal = new javax.swing.JCheckBox();
        sprCariTanggal = new javax.swing.JSpinner();
        chkMulai = new javax.swing.JCheckBox();
        sprCariMulai = new javax.swing.JSpinner();
        chkSampai = new javax.swing.JCheckBox();
        sprCariSampai = new javax.swing.JSpinner();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pnlActions = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        btnNonaktifkan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        scrTable = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        lblCount = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new java.awt.BorderLayout());

        pnlHeadText.setOpaque(false);
        pnlHeadText.setLayout(new javax.swing.BoxLayout(pnlHeadText, javax.swing.BoxLayout.Y_AXIS));

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTitle.setText("Jadwal Tayang");
        pnlHeadText.add(lblTitle);
        pnlHeadText.add(filler1);

        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Atur tanggal dan waktu penayangan program.");
        pnlHeadText.add(lblSubtitle);

        pnlHead.add(pnlHeadText, java.awt.BorderLayout.WEST);

        btnAdd.setBackground(new java.awt.Color(37, 99, 235));
        btnAdd.setFocusPainted(false);
        btnAdd.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("+ Tambah Jadwal");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        pnlHead.add(btnAdd, java.awt.BorderLayout.EAST);

        add(pnlHead, java.awt.BorderLayout.NORTH);

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 12, 16));
        pnlCard.setLayout(new java.awt.BorderLayout());

        pnlToolbar.setOpaque(false);
        pnlToolbar.setLayout(new java.awt.BorderLayout());

        pnlSearch.setOpaque(false);
        pnlSearch.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 0));


        chkTanggal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        chkTanggal.setText("Tanggal");
        chkTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTanggalActionPerformed(evt);
            }
        });
        pnlSearch.add(chkTanggal);

        sprCariTanggal.setPreferredSize(new java.awt.Dimension(120, 34));
        pnlSearch.add(sprCariTanggal);


        chkMulai.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        chkMulai.setText("Mulai");
        chkMulai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMulaiActionPerformed(evt);
            }
        });
        pnlSearch.add(chkMulai);

        sprCariMulai.setPreferredSize(new java.awt.Dimension(120, 34));
        pnlSearch.add(sprCariMulai);


        chkSampai.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        chkSampai.setText("Selesai");
        chkSampai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSampaiActionPerformed(evt);
            }
        });
        pnlSearch.add(chkSampai);

        sprCariSampai.setPreferredSize(new java.awt.Dimension(120, 34));
        pnlSearch.add(sprCariSampai);


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


        btnNonaktifkan.setFocusPainted(false);
        btnNonaktifkan.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnNonaktifkan.setText("Nonaktifkan");
        btnNonaktifkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNonaktifkanActionPerformed(evt);
            }
        });
        pnlActions.add(btnNonaktifkan);

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
        scrTable.setViewportView(tblData);

        pnlCard.add(scrTable, java.awt.BorderLayout.CENTER);

        lblCount.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblCount.setForeground(new java.awt.Color(100, 116, 139));
        lblCount.setText(" ");
        pnlCard.add(lblCount, java.awt.BorderLayout.SOUTH);

        add(pnlCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnNonaktifkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNonaktifkanActionPerformed
        nonaktifkanTerpilih();
    }//GEN-LAST:event_btnNonaktifkanActionPerformed

    private void chkTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTanggalActionPerformed
        sprCariTanggal.setEnabled(chkTanggal.isSelected());
    }//GEN-LAST:event_chkTanggalActionPerformed

    private void chkMulaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMulaiActionPerformed
        sprCariMulai.setEnabled(chkMulai.isSelected());
    }//GEN-LAST:event_chkMulaiActionPerformed

    private void chkSampaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSampaiActionPerformed
        sprCariSampai.setEnabled(chkSampai.isSelected());
    }//GEN-LAST:event_chkSampaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnNonaktifkan;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUbah;
    private javax.swing.JCheckBox chkMulai;
    private javax.swing.JCheckBox chkSampai;
    private javax.swing.JCheckBox chkTanggal;
    private javax.swing.JSpinner sprCariMulai;
    private javax.swing.JSpinner sprCariSampai;
    private javax.swing.JSpinner sprCariTanggal;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlCard;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlHeadText;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JPanel pnlToolbar;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
