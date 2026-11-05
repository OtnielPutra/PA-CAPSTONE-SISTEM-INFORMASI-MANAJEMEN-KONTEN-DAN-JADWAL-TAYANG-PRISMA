package view;

import controller.LampiranController;
import model.LampiranKonten;

import javax.swing.*;

/**
 * Dialog tambah/ubah "Lampiran Konten" - JDialog MANDIRI hasil GUI Builder (pasangan file LampiranDialog.form).
 *
 * Dipakai oleh LampiranKontenPanel:
 *   LampiranDialog d = new LampiranDialog(owner, data);   // data == null berarti tambah baru
 *   if (d.tampilkan()) { ... }
 *
 * Mode Design NetBeans memakai konstruktor (Frame, boolean) di bawah.
 */
public class LampiranDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final LampiranController controller = new LampiranController();
    private final boolean baru;
    private final LampiranKonten l;
    private boolean tersimpan;
    private java.io.File berkasTerpilih;

    /** Konstruktor bawaan NetBeans - dipakai untuk merender Design view. */
    public LampiranDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.baru = true;
        this.l = new LampiranKonten();
        initComponents();
        setTitle("Tambah Lampiran");
        lblKepala.setText("Tambah Lampiran");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Konstruktor aplikasi: data == null berarti menambah data baru. */
    public LampiranDialog(java.awt.Window owner, LampiranKonten data) {
        super(owner, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.baru = data == null;
        this.l = baru ? new LampiranKonten() : data;
        initComponents();
        setTitle(baru ? "Tambah Lampiran" : "Ubah Lampiran");
        lblKepala.setText(baru ? "Tambah Lampiran" : "Ubah Lampiran");
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

    /** Mengisi combo pilihan tetap (status/jenis/kategori/role). */
    private void isiComboStatis() {

    }

    private void isiCombo() {
        isiComboKonten();
    }

    /** Mengisi seluruh kolom dari objek data. */
    private void isiDariData() {
        pilihKonten(cmbKonten, l.getIdKonten());
        txtFile.setText(baru ? "(belum ada file dipilih)" : l.getNamaFile());
    }

    /** @return null bila valid, atau pesan galat. */
    private String validasi() {
        if (cmbKonten.getSelectedItem() == null) { return "Konten wajib dipilih."; }
        return null;
    }


    private void simpan() {
        String galat = validasi();
        if (galat != null) {
            tampilGalat(galat);
            return;
        }
                model.Konten ktLamp = (model.Konten) cmbKonten.getSelectedItem();
        l.setIdKonten(ktLamp.getIdKonten());
        if (baru) {
            if (berkasTerpilih == null) {
                tampilGalat("File lampiran harus dipilih.");
                return;
            }
            if (!controller.unggah(berkasTerpilih, l.getIdKonten())) {
                tampilGalat(controller.getPesan());
                return;
            }
        } else {
            if (!controller.ubah(l)) {
                tampilGalat(controller.getPesan());
                return;
            }
            if (berkasTerpilih != null && !controller.gantiFile(l.getIdLampiran(), berkasTerpilih)) {
                tampilGalat(controller.getPesan());
                return;
            }
        }
        tersimpan = true;
        dispose();
    }

    private void tampilGalat(String pesan) {
        lblPesan.setText(pesan);
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
        lblBerkas = new javax.swing.JLabel();
        cmbKonten = new javax.swing.JComboBox();
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
        lblKepala.setText("Tambah Lampiran");
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

        lblBerkas.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblBerkas.setForeground(new java.awt.Color(15, 23, 42));
        lblBerkas.setText("File");
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
    private javax.swing.JLabel lblKonten;
    private javax.swing.JLabel lblBerkas;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JButton btnPilih;
    private javax.swing.JComboBox cmbKonten;
    private javax.swing.JPanel pnlBerkas;
    private javax.swing.JTextField txtFile;
    // End of variables declaration//GEN-END:variables
}
