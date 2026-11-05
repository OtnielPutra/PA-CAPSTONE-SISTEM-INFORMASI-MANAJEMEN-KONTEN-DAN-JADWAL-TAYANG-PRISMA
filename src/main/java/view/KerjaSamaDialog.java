package view;

import controller.KerjaSamaController;
import model.KerjaSama;

import javax.swing.*;

/**
 * Dialog tambah/ubah "Kerja Sama" - JDialog MANDIRI hasil GUI Builder (pasangan file KerjaSamaDialog.form).
 *
 * Dipakai oleh KerjaSamaPanel:
 *   KerjaSamaDialog d = new KerjaSamaDialog(owner, data);   // data == null berarti tambah baru
 *   if (d.tampilkan()) { ... }
 *
 * Mode Design NetBeans memakai konstruktor (Frame, boolean) di bawah.
 */
public class KerjaSamaDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final KerjaSamaController controller = new KerjaSamaController();
    private final boolean baru;
    private final KerjaSama k;
    private boolean tersimpan;

    /** Konstruktor bawaan NetBeans - dipakai untuk merender Design view. */
    public KerjaSamaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.baru = true;
        this.k = new KerjaSama();
        initComponents();
        setTitle("Tambah Kerja Sama");
        lblKepala.setText("Tambah Kerja Sama");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Konstruktor aplikasi: data == null berarti menambah data baru. */
    public KerjaSamaDialog(java.awt.Window owner, KerjaSama data) {
        super(owner, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.baru = data == null;
        this.k = baru ? new KerjaSama() : data;
        initComponents();
        setTitle(baru ? "Tambah Kerja Sama" : "Ubah Kerja Sama");
        lblKepala.setText(baru ? "Tambah Kerja Sama" : "Ubah Kerja Sama");
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
        pasangTanggal(sprMulai);
        pasangTanggal(sprAkhir);
    }

    private void isiComboMitra() {
        java.util.List<model.Mitra> daftar = new controller.MitraController().tampilSemua();
        cmbMitra.removeAllItems();
                for (model.Mitra item : daftar) {
            cmbMitra.addItem(item);
        }
        cmbMitra.setRenderer(new javax.swing.DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, Object nilai,
                    int indeks, boolean dipilih, boolean fokus) {
                super.getListCellRendererComponent(list, nilai, indeks, dipilih, fokus);
                setText(nilai == null ? "-" : nilai.toString());
                return this;
            }
        });
    }

    private void pilihMitra(javax.swing.JComboBox cmb, Integer id) {
        if (id == null) {
            cmb.setSelectedItem(null);
            return;
        }
        for (int i = 0; i < cmb.getItemCount(); i++) {
            Object o = cmb.getItemAt(i);
            if (o instanceof model.Mitra && ((model.Mitra) o).getIdMitra() == id) {
                cmb.setSelectedItem(o);
                return;
            }
        }
        cmb.setSelectedItem(null);
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

    private void isiComboKonten() {
        java.util.List<model.Konten> daftar = new controller.KontenController().tampilSemua();
        cmbKonten.removeAllItems();
        cmbKonten.addItem(null);
        for (model.Konten item : daftar) {
            cmbKonten.addItem(item);
        }
        cmbKonten.setRenderer(new javax.swing.DefaultListCellRenderer() {
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

    private void pilihKonten(javax.swing.JComboBox cmb, Integer id) {
        if (id == null) {
            cmb.setSelectedItem(null);
            return;
        }
        for (int i = 0; i < cmb.getItemCount(); i++) {
            Object o = cmb.getItemAt(i);
            if (o instanceof model.Konten && ((model.Konten) o).getIdKonten() == id) {
                cmb.setSelectedItem(o);
                return;
            }
        }
        cmb.setSelectedItem(null);
    }

    /** Mengisi combo pilihan tetap (status/jenis/kategori/role). */
    private void isiComboStatis() {
        cmbStatus.removeAllItems();
        for (String item : KerjaSama.DAFTAR_STATUS) {
            cmbStatus.addItem(item);
        }
    }

    private void isiCombo() {
        isiComboMitra();
        isiComboProgram();
        isiComboKonten();
    }

    /** Mengisi seluruh kolom dari objek data. */
    private void isiDariData() {
        txtJudul.setText(k.getJudulKerjaSama() == null ? "" : k.getJudulKerjaSama());
        txtJenis.setText(k.getJenisKerjaSama() == null ? "" : k.getJenisKerjaSama());
        pilihMitra(cmbMitra, k.getIdMitra());
        isiTanggal(sprMulai, k.getTanggalMulai() == null ? java.time.LocalDate.now() : k.getTanggalMulai());
        isiTanggal(sprAkhir, k.getTanggalAkhir() == null ? java.time.LocalDate.now().plusMonths(1) : k.getTanggalAkhir());
        cmbStatus.setSelectedItem(k.getStatusKerjaSama() == null ? "Direncanakan" : k.getStatusKerjaSama());
        txtNilai.setText(k.getNilaiKontrak() == null ? "" : k.getNilaiKontrak().toPlainString());
        pilihProgram(cmbProgram, k.getIdProgram());
        pilihKonten(cmbKonten, k.getIdKonten());
    }

    /** @return null bila valid, atau pesan galat. */
    private String validasi() {
        if (txtJudul.getText().trim().isEmpty()) { return "Judul Kerja Sama wajib diisi."; }
        if (txtJenis.getText().trim().isEmpty()) { return "Jenis Kerja Sama wajib diisi."; }
        if (cmbMitra.getSelectedItem() == null) { return "Mitra Terkait wajib dipilih."; }
        if (ambilTanggal(sprAkhir).isBefore(ambilTanggal(sprMulai))) { return "Tanggal Akhir tidak boleh sebelum Tanggal Mulai."; }
        return null;
    }


    private void simpan() {
        String galat = validasi();
        if (galat != null) {
            tampilGalat(galat);
            return;
        }
        java.math.BigDecimal nominal;
        String teksNilai = txtNilai.getText().trim();
        try {
            if (teksNilai.isEmpty()) {
                nominal = null;
            } else if (teksNilai.contains(",")) {
                nominal = new java.math.BigDecimal(teksNilai.replace(".", "").replace(" ", "").replace(",", "."));
            } else {
                nominal = new java.math.BigDecimal(teksNilai.replace(" ", ""));
            }
        } catch (NumberFormatException exNilai) {
            tampilGalat("Nilai kontrak harus berupa angka.");
            return;
        }
        k.setJudulKerjaSama(txtJudul.getText().trim());
        k.setJenisKerjaSama(txtJenis.getText().trim());
        model.Mitra mtKs = (model.Mitra) cmbMitra.getSelectedItem();
        k.setIdMitra(mtKs.getIdMitra());
        k.setTanggalMulai(ambilTanggal(sprMulai));
        k.setTanggalAkhir(ambilTanggal(sprAkhir));
        k.setStatusKerjaSama((String) cmbStatus.getSelectedItem());
        k.setNilaiKontrak(nominal);
        model.ProgramTV prKs = (model.ProgramTV) cmbProgram.getSelectedItem();
        k.setIdProgram(prKs == null ? null : prKs.getIdProgram());
        model.Konten ktKs = (model.Konten) cmbKonten.getSelectedItem();
        k.setIdKonten(ktKs == null ? null : ktKs.getIdKonten());
        boolean ok = baru ? controller.tambah(k) : controller.ubah(k);
        if (!ok) {
            tampilGalat(controller.getPesan());
            return;
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
        lblMitra = new javax.swing.JLabel();
        lblMulai = new javax.swing.JLabel();
        lblAkhir = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblNilai = new javax.swing.JLabel();
        lblProgram = new javax.swing.JLabel();
        lblKonten = new javax.swing.JLabel();
        txtJudul = new javax.swing.JTextField();
        txtJenis = new javax.swing.JTextField();
        cmbMitra = new javax.swing.JComboBox();
        sprMulai = new javax.swing.JSpinner();
        sprAkhir = new javax.swing.JSpinner();
        cmbStatus = new javax.swing.JComboBox();
        txtNilai = new javax.swing.JTextField();
        cmbProgram = new javax.swing.JComboBox();
        cmbKonten = new javax.swing.JComboBox();
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
        lblKepala.setText("Tambah Kerja Sama");
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
        lblJudul.setText("Judul Kerja Sama *");
        pnlForm.add(lblJudul);

        txtJudul.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtJudul.setColumns(16);
        txtJudul.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtJudul);

        lblJenis.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(15, 23, 42));
        lblJenis.setText("Jenis Kerja Sama *");
        pnlForm.add(lblJenis);

        txtJenis.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtJenis.setColumns(16);
        txtJenis.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtJenis);

        lblMitra.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblMitra.setForeground(new java.awt.Color(15, 23, 42));
        lblMitra.setText("Mitra Terkait *");
        pnlForm.add(lblMitra);

        cmbMitra.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbMitra);

        lblMulai.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblMulai.setForeground(new java.awt.Color(15, 23, 42));
        lblMulai.setText("Tanggal Mulai *");
        pnlForm.add(lblMulai);

        sprMulai.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprMulai);

        lblAkhir.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblAkhir.setForeground(new java.awt.Color(15, 23, 42));
        lblAkhir.setText("Tanggal Akhir *");
        pnlForm.add(lblAkhir);

        sprAkhir.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprAkhir);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(15, 23, 42));
        lblStatus.setText("Status Kerja Sama *");
        pnlForm.add(lblStatus);

        cmbStatus.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbStatus);

        lblNilai.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblNilai.setForeground(new java.awt.Color(15, 23, 42));
        lblNilai.setText("Nilai Kontrak (Rp)");
        pnlForm.add(lblNilai);

        txtNilai.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtNilai.setColumns(16);
        txtNilai.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtNilai);

        lblProgram.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblProgram.setForeground(new java.awt.Color(15, 23, 42));
        lblProgram.setText("Program TV Terkait");
        pnlForm.add(lblProgram);

        cmbProgram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbProgram);

        lblKonten.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblKonten.setForeground(new java.awt.Color(15, 23, 42));
        lblKonten.setText("Konten Terkait");
        pnlForm.add(lblKonten);

        cmbKonten.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbKonten);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel lblKepala;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblMitra;
    private javax.swing.JLabel lblMulai;
    private javax.swing.JLabel lblAkhir;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblNilai;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblKonten;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JComboBox cmbKonten;
    private javax.swing.JComboBox cmbMitra;
    private javax.swing.JComboBox cmbProgram;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JSpinner sprAkhir;
    private javax.swing.JSpinner sprMulai;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtNilai;
    // End of variables declaration//GEN-END:variables
}
