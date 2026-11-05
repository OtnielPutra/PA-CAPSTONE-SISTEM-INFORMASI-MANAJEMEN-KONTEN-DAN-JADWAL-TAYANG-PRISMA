package view;

import controller.PublikasiController;
import model.PublikasiKonten;

import javax.swing.*;

/**
 * Dialog tambah/ubah "Publikasi Konten" - JDialog MANDIRI hasil GUI Builder (pasangan file PublikasiDialog.form).
 *
 * Dipakai oleh PublikasiKontenPanel:
 *   PublikasiDialog d = new PublikasiDialog(owner, data);   // data == null berarti tambah baru
 *   if (d.tampilkan()) { ... }
 *
 * Mode Design NetBeans memakai konstruktor (Frame, boolean) di bawah.
 */
public class PublikasiDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final PublikasiController controller = new PublikasiController();
    private final boolean baru;
    private final PublikasiKonten p;
    private boolean tersimpan;

    /** Konstruktor bawaan NetBeans - dipakai untuk merender Design view. */
    public PublikasiDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.baru = true;
        this.p = new PublikasiKonten();
        initComponents();
        setTitle("Tambah Publikasi");
        lblKepala.setText("Tambah Publikasi");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Konstruktor aplikasi: data == null berarti menambah data baru. */
    public PublikasiDialog(java.awt.Window owner, PublikasiKonten data) {
        super(owner, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.baru = data == null;
        this.p = baru ? new PublikasiKonten() : data;
        initComponents();
        setTitle(baru ? "Tambah Publikasi" : "Ubah Publikasi");
        lblKepala.setText(baru ? "Tambah Publikasi" : "Ubah Publikasi");
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
        pasangWaktuPenuh(sprTanggal);
    }

    private void isiComboKonten() {
        java.util.List<model.Konten> daftar = new controller.KontenController().tampilSemua();
        cmbKonten.removeAllItems();
                for (model.Konten item : daftar) {
            cmbKonten.addItem(item);
        }
        cmbKonten.setRenderer(new javax.swing.DefaultListCellRenderer() {
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

    private void isiComboKanal() {
        java.util.List<model.Kanal> daftar = new controller.KanalController().tampilSemua();
        cmbKanal.removeAllItems();
                for (model.Kanal item : daftar) {
            cmbKanal.addItem(item);
        }
        cmbKanal.setRenderer(new javax.swing.DefaultListCellRenderer() {
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

    private void pilihKanal(javax.swing.JComboBox cmb, Integer id) {
        if (id == null) {
            cmb.setSelectedItem(null);
            return;
        }
        for (int i = 0; i < cmb.getItemCount(); i++) {
            Object o = cmb.getItemAt(i);
            if (o instanceof model.Kanal && ((model.Kanal) o).getIdKanal() == id) {
                cmb.setSelectedItem(o);
                return;
            }
        }
        cmb.setSelectedItem(null);
    }

    /** Mengisi combo pilihan tetap (status/jenis/kategori/role). */
    private void isiComboStatis() {
        cmbStatus.removeAllItems();
        for (String item : PublikasiKonten.DAFTAR_STATUS) {
            cmbStatus.addItem(item);
        }
    }

    private void isiCombo() {
        isiComboKonten();
        isiComboKanal();
    }

    /** Mengisi seluruh kolom dari objek data. */
    private void isiDariData() {
        pilihKonten(cmbKonten, p.getIdKonten());
        pilihKanal(cmbKanal, p.getIdKanal());
        isiWaktuPenuh(sprTanggal, p.getTanggalPublikasi() == null ? java.time.LocalDateTime.now() : p.getTanggalPublikasi());
        cmbStatus.setSelectedItem(p.getStatusPublikasi() == null ? "Direncanakan" : p.getStatusPublikasi());
        txtLink.setText(p.getLinkPublikasi() == null ? "" : p.getLinkPublikasi());
    }

    /** @return null bila valid, atau pesan galat. */
    private String validasi() {
        if (cmbKonten.getSelectedItem() == null) { return "Konten wajib dipilih."; }
        if (cmbKanal.getSelectedItem() == null) { return "Kanal Publikasi wajib dipilih."; }
        return null;
    }


    private void simpan() {
        String galat = validasi();
        if (galat != null) {
            tampilGalat(galat);
            return;
        }
        model.Konten ktPub = (model.Konten) cmbKonten.getSelectedItem();
        model.Kanal knPub = (model.Kanal) cmbKanal.getSelectedItem();
        p.setIdKonten(ktPub.getIdKonten());
        p.setIdKanal(knPub.getIdKanal());
        p.setTanggalPublikasi(ambilWaktuPenuh(sprTanggal));
        p.setStatusPublikasi((String) cmbStatus.getSelectedItem());
        p.setLinkPublikasi(txtLink.getText().trim());
        boolean ok = baru ? controller.tambah(p) : controller.ubah(p);
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
        lblKonten = new javax.swing.JLabel();
        lblKanal = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblLink = new javax.swing.JLabel();
        cmbKonten = new javax.swing.JComboBox();
        cmbKanal = new javax.swing.JComboBox();
        sprTanggal = new javax.swing.JSpinner();
        cmbStatus = new javax.swing.JComboBox();
        txtLink = new javax.swing.JTextField();
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
        lblKepala.setText("Tambah Publikasi");
        pnlJudul.add(lblKepala);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Lengkapi kolom bertanda * lalu tekan Simpan.");
        pnlJudul.add(lblSub);

        getContentPane().add(pnlJudul, java.awt.BorderLayout.NORTH);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 12, 22));
        pnlForm.setLayout(new java.awt.GridLayout(0, 2, 12, 6));

        lblKonten.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblKonten.setForeground(new java.awt.Color(15, 23, 42));
        lblKonten.setText("Konten *");
        pnlForm.add(lblKonten);

        cmbKonten.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbKonten);

        lblKanal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblKanal.setForeground(new java.awt.Color(15, 23, 42));
        lblKanal.setText("Kanal Publikasi *");
        pnlForm.add(lblKanal);

        cmbKanal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbKanal);

        lblTanggal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(15, 23, 42));
        lblTanggal.setText("Tanggal Publikasi *");
        pnlForm.add(lblTanggal);

        sprTanggal.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprTanggal);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(15, 23, 42));
        lblStatus.setText("Status Publikasi *");
        pnlForm.add(lblStatus);

        cmbStatus.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbStatus);

        lblLink.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblLink.setForeground(new java.awt.Color(15, 23, 42));
        lblLink.setText("Link Publikasi");
        pnlForm.add(lblLink);

        txtLink.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtLink.setColumns(16);
        txtLink.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtLink);

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
    private javax.swing.JLabel lblKonten;
    private javax.swing.JLabel lblKanal;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblLink;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JComboBox cmbKanal;
    private javax.swing.JComboBox cmbKonten;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JSpinner sprTanggal;
    private javax.swing.JTextField txtLink;
    // End of variables declaration//GEN-END:variables
}
