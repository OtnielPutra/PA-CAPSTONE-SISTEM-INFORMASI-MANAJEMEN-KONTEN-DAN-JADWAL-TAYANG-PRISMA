package view;

import controller.KontenController;
import model.Konten;

import javax.swing.*;

/**
 * Dialog tambah/ubah "Manajemen Konten" - JDialog MANDIRI hasil GUI Builder (pasangan file KontenDialog.form).
 *
 * Dipakai oleh KontenPanel:
 *   KontenDialog d = new KontenDialog(owner, data);   // data == null berarti tambah baru
 *   if (d.tampilkan()) { ... }
 *
 * Mode Design NetBeans memakai konstruktor (Frame, boolean) di bawah.
 */
public class KontenDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final KontenController controller = new KontenController();
    private final controller.LampiranController lampiranController = new controller.LampiranController();
    private final boolean baru;
    private final Konten k;
    private boolean tersimpan;
    private java.io.File berkasTerpilih;

    /** Konstruktor bawaan NetBeans - dipakai untuk merender Design view. */
    public KontenDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.baru = true;
        this.k = new Konten();
        initComponents();
        setTitle("Tambah Konten");
        lblKepala.setText("Tambah Konten");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Konstruktor aplikasi: data == null berarti menambah data baru. */
    public KontenDialog(java.awt.Window owner, Konten data) {
        super(owner, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.baru = data == null;
        this.k = baru ? new Konten() : data;
        initComponents();
        setTitle(baru ? "Tambah Konten" : "Ubah Konten");
        lblKepala.setText(baru ? "Tambah Konten" : "Ubah Konten");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Menampilkan dialog modal. @return true bila data berhasil disimpan. */
    public boolean tampilkan() {
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        return tersimpan;
    }

    public boolean isTersimpan() {
        return tersimpan;
    }

    /** Mengatur model & format seluruh spinner tanggal/waktu. */
    private void pasangSpinner() {
        pasangTanggal(sprTanggal);
    }

    private void isiComboProgram() {
        java.util.List<model.ProgramTV> daftar = new controller.ProgramTVController().tampilSemua();
        cmbProgram.removeAllItems();
        cmbProgram.addItem(null);
        for (model.ProgramTV item : daftar) {
            cmbProgram.addItem(item);
        }
        cmbProgram.setRenderer(new javax.swing.DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object nilai,
                    int indeks, boolean dipilih, boolean fokus) {
                super.getListCellRendererComponent(list, nilai, indeks, dipilih, fokus);
                setText(nilai == null ? "-- Tidak ada --" : nilai.toString());
                return this;
            }
        });
    }

    private void pilihProgram(javax.swing.JComboBox cmb, Integer id) {
        if (id == null) {
            cmb.setSelectedItem(null);
            return;
        }
        for (int i = 0; i < cmb.getItemCount(); i++) {
            Object o = cmb.getItemAt(i);
            if (o instanceof model.ProgramTV && ((model.ProgramTV) o).getIdProgram() == id) {
                cmb.setSelectedItem(o);
                return;
            }
        }
        cmb.setSelectedItem(null);
    }

    /** Mengisi combo pilihan tetap (status/jenis/kategori/role). */
    private void isiComboStatis() {
        cmbJenis.removeAllItems();
        for (String item : Konten.DAFTAR_JENIS) {
            cmbJenis.addItem(item);
        }
        cmbStatus.removeAllItems();
        for (String item : Konten.DAFTAR_STATUS) {
            cmbStatus.addItem(item);
        }
    }

    private void isiCombo() {
        isiComboProgram();
    }

    /** Mengisi seluruh kolom dari objek data. */
    private void isiDariData() {
        txtJudul.setText(k.getJudulKonten() == null ? "" : k.getJudulKonten());
        cmbJenis.setSelectedItem(k.getJenisKonten());
        txtKategori.setText(k.getKategori() == null ? "" : k.getKategori());
        txtRingkasan.setText(k.getRingkasanKonten() == null ? "" : k.getRingkasanKonten());
        isiTanggal(sprTanggal, k.getTanggalPencatatan() == null ? java.time.LocalDate.now() : k.getTanggalPencatatan());
        pilihProgram(cmbProgram, k.getIdProgram());
        cmbStatus.setSelectedItem(k.getStatusKonten() == null ? "Dalam Proses" : k.getStatusKonten());
        txtFile.setText("(file baru, bila ada)");
    }

    /** @return null bila valid, atau pesan galat. */
    private String validasi() {
        if (txtJudul.getText().trim().isEmpty()) { return "Judul Konten wajib diisi."; }
        if (txtKategori.getText().trim().isEmpty()) { return "Kategori wajib diisi."; }
        if (txtRingkasan.getText().trim().isEmpty()) { return "Ringkasan Konten wajib diisi."; }
        return null;
    }


    private void simpan() {
        String galat = validasi();
        if (galat != null) {
            tampilGalat(galat);
            return;
        }
        k.setJudulKonten(txtJudul.getText().trim());
        k.setJenisKonten((String) cmbJenis.getSelectedItem());
        k.setKategori(txtKategori.getText().trim());
        k.setRingkasanKonten(txtRingkasan.getText().trim());
        k.setTanggalPencatatan(ambilTanggal(sprTanggal));
        model.ProgramTV terpilih = (model.ProgramTV) cmbProgram.getSelectedItem();
        k.setIdProgram(terpilih == null ? null : terpilih.getIdProgram());
        k.setStatusKonten((String) cmbStatus.getSelectedItem());
        boolean ok = baru ? controller.tambah(k) : controller.ubah(k);
        if (!ok) {
            tampilGalat(controller.getPesan());
            return;
        }
        if (berkasTerpilih != null && !lampiranController.unggah(berkasTerpilih, k.getIdKonten())) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Konten tersimpan, tetapi lampiran gagal diunggah: " + lampiranController.getPesan(),
                    "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
        }

        tersimpan = true;
        dispose();
    }

    private void tampilGalat(String pesan) {
        lblPesan.setText(pesan);
    }

    private void pasangTanggal(javax.swing.JSpinner s) {
        s.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        s.setEditor(new javax.swing.JSpinner.DateEditor(s, "yyyy-MM-dd"));
    }

    private void isiTanggal(javax.swing.JSpinner s, java.time.LocalDate t) {
        s.setValue(java.util.Date.from(t.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
    }

    private java.time.LocalDate ambilTanggal(javax.swing.JSpinner s) {
        return ((java.util.Date) s.getValue()).toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    private void pasangWaktu(javax.swing.JSpinner s) {
        s.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        s.setEditor(new javax.swing.JSpinner.DateEditor(s, "HH:mm"));
    }

    private void isiWaktu(javax.swing.JSpinner s, java.time.LocalTime t) {
        s.setValue(java.util.Date.from(java.time.LocalDate.now().atTime(t)
                .atZone(java.time.ZoneId.systemDefault()).toInstant()));
    }

    private java.time.LocalTime ambilWaktu(javax.swing.JSpinner s) {
        return ((java.util.Date) s.getValue()).toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalTime().withSecond(0).withNano(0);
    }

    private void pasangWaktuPenuh(javax.swing.JSpinner s) {
        s.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));
        s.setEditor(new javax.swing.JSpinner.DateEditor(s, "yyyy-MM-dd HH:mm"));
    }

    private void isiWaktuPenuh(javax.swing.JSpinner s, java.time.LocalDateTime t) {
        s.setValue(java.util.Date.from(t.atZone(java.time.ZoneId.systemDefault()).toInstant()));
    }

    private java.time.LocalDateTime ambilWaktuPenuh(javax.swing.JSpinner s) {
        return ((java.util.Date) s.getValue()).toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDateTime().withSecond(0).withNano(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlJudul = new javax.swing.JPanel();
        lblKepala = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblJenis = new javax.swing.JLabel();
        lblKategori = new javax.swing.JLabel();
        lblRingkasan = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblProgram = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblBerkas = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        cmbJenis = new javax.swing.JComboBox();
        txtKategori = new javax.swing.JTextField();
        scr_txtRingkasan = new javax.swing.JScrollPane();
        txtRingkasan = new javax.swing.JTextArea();
        sprTanggal = new javax.swing.JSpinner();
        cmbProgram = new javax.swing.JComboBox();
        cmbStatus = new javax.swing.JComboBox();
        pnlBerkas = new javax.swing.JPanel();
        txtFile = new javax.swing.JTextField();
        btnPilih = new javax.swing.JButton();
        pnlBottom = new javax.swing.JPanel();
        lblPesan = new javax.swing.JLabel();
        pnlTombol = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlJudul.setOpaque(false);
        pnlJudul.setLayout(new javax.swing.BoxLayout(pnlJudul, javax.swing.BoxLayout.Y_AXIS));

        lblKepala.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblKepala.setForeground(new java.awt.Color(15, 23, 42));
        lblKepala.setText("Tambah Konten");
        pnlJudul.add(lblKepala);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Lengkapi kolom bertanda * lalu tekan Simpan.");
        pnlJudul.add(lblSub);

        getContentPane().add(pnlJudul, java.awt.BorderLayout.NORTH);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 12, 22));
        pnlForm.setLayout(new java.awt.GridLayout(0, 2, 12, 6));

        lblJudul.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblJudul.setText("Judul Konten *");
        pnlForm.add(lblJudul);

        txtJudul.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtJudul.setColumns(16);
        txtJudul.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtJudul);

        lblJenis.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(15, 23, 42));
        lblJenis.setText("Jenis Konten *");
        pnlForm.add(lblJenis);

        cmbJenis.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbJenis);

        lblKategori.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblKategori.setForeground(new java.awt.Color(15, 23, 42));
        lblKategori.setText("Kategori *");
        pnlForm.add(lblKategori);

        txtKategori.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtKategori.setColumns(16);
        txtKategori.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtKategori);

        lblRingkasan.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblRingkasan.setForeground(new java.awt.Color(15, 23, 42));
        lblRingkasan.setText("Ringkasan Konten *");
        pnlForm.add(lblRingkasan);

        txtRingkasan.setColumns(20);
        txtRingkasan.setLineWrap(true);
        txtRingkasan.setRows(3);
        txtRingkasan.setWrapStyleWord(true);
        txtRingkasan.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        scr_txtRingkasan.setViewportView(txtRingkasan);

        pnlForm.add(scr_txtRingkasan);

        lblTanggal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(15, 23, 42));
        lblTanggal.setText("Tanggal Pencatatan *");
        pnlForm.add(lblTanggal);

        sprTanggal.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprTanggal);

        lblProgram.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblProgram.setForeground(new java.awt.Color(15, 23, 42));
        lblProgram.setText("Program Terkait");
        pnlForm.add(lblProgram);

        cmbProgram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbProgram);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(15, 23, 42));
        lblStatus.setText("Status Konten *");
        pnlForm.add(lblStatus);

        cmbStatus.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbStatus);

        lblBerkas.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblBerkas.setForeground(new java.awt.Color(15, 23, 42));
        lblBerkas.setText("Tambah Lampiran");
        pnlForm.add(lblBerkas);

        pnlBerkas.setOpaque(false);
        pnlBerkas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));

        txtFile.setEditable(false);
        txtFile.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtFile.setColumns(14);
        txtFile.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlBerkas.add(txtFile);

        btnPilih.setFocusPainted(false);
        btnPilih.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnPilih.setText("Pilih File...");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });
        pnlBerkas.add(btnPilih);

        pnlForm.add(pnlBerkas);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pnlBottom.setOpaque(false);
        pnlBottom.setLayout(new java.awt.BorderLayout(8, 0));

        lblPesan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblPesan.setForeground(new java.awt.Color(220, 38, 38));
        lblPesan.setText(" ");
        pnlBottom.add(lblPesan, java.awt.BorderLayout.CENTER);

        pnlTombol.setOpaque(false);
        pnlTombol.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnBatal.setFocusPainted(false);
        btnBatal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        pnlTombol.add(btnBatal);

        btnSimpan.setBackground(new java.awt.Color(37, 99, 235));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlTombol.add(btnSimpan);

        pnlBottom.add(pnlTombol, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlBottom, java.awt.BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnSimpan);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed
private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            berkasTerpilih = fc.getSelectedFile();
            txtFile.setText(berkasTerpilih.getName());
        }
}//GEN-LAST:event_btnPilihActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel lblKepala;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblKategori;
    private javax.swing.JLabel lblRingkasan;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblBerkas;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JButton btnPilih;
    private javax.swing.JComboBox cmbJenis;
    private javax.swing.JComboBox cmbProgram;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JPanel pnlBerkas;
    private javax.swing.JScrollPane scr_txtRingkasan;
    private javax.swing.JSpinner sprTanggal;
    private javax.swing.JTextArea txtRingkasan;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtKategori;
    // End of variables declaration//GEN-END:variables
}
