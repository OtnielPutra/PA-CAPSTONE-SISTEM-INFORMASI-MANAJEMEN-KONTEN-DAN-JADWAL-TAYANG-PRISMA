package view;

import controller.JadwalController;
import model.JadwalTayang;

import javax.swing.*;

/**
 * Dialog tambah/ubah "Jadwal Tayang" - JDialog MANDIRI hasil GUI Builder (pasangan file JadwalDialog.form).
 *
 * Dipakai oleh JadwalTayangPanel:
 *   JadwalDialog d = new JadwalDialog(owner, data);   // data == null berarti tambah baru
 *   if (d.tampilkan()) { ... }
 *
 * Mode Design NetBeans memakai konstruktor (Frame, boolean) di bawah.
 */
public class JadwalDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final JadwalController controller = new JadwalController();
    private final boolean baru;
    private final JadwalTayang j;
    private boolean tersimpan;

    /** Konstruktor bawaan NetBeans - dipakai untuk merender Design view. */
    public JadwalDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.baru = true;
        this.j = new JadwalTayang();
        initComponents();
        setTitle("Tambah Jadwal");
        lblKepala.setText("Tambah Jadwal");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Konstruktor aplikasi: data == null berarti menambah data baru. */
    public JadwalDialog(java.awt.Window owner, JadwalTayang data) {
        super(owner, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.baru = data == null;
        this.j = baru ? new JadwalTayang() : data;
        initComponents();
        setTitle(baru ? "Tambah Jadwal" : "Ubah Jadwal");
        lblKepala.setText(baru ? "Tambah Jadwal" : "Ubah Jadwal");
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
        pasangWaktu(sprMulai);
        pasangWaktu(sprSelesai);
    }

    private void isiComboProgram() {
        java.util.List<model.ProgramTV> daftar = new controller.ProgramTVController().tampilSemua();
        cmbProgram.removeAllItems();
                for (model.ProgramTV item : daftar) {
            cmbProgram.addItem(item);
        }
        cmbProgram.setRenderer(new javax.swing.DefaultListCellRenderer() {
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
        for (String item : JadwalTayang.DAFTAR_STATUS) {
            cmbStatus.addItem(item);
        }
    }

    private void isiCombo() {
        isiComboProgram();
        isiComboKanal();
    }

    /** Mengisi seluruh kolom dari objek data. */
    private void isiDariData() {
        pilihProgram(cmbProgram, j.getIdProgram());
        pilihKanal(cmbKanal, j.getIdKanal());
        isiTanggal(sprTanggal, j.getTanggalTayang() == null ? java.time.LocalDate.now() : j.getTanggalTayang());
        isiWaktu(sprMulai, j.getWaktuMulai() == null ? java.time.LocalTime.of(8, 0) : j.getWaktuMulai());
        isiWaktu(sprSelesai, j.getWaktuSelesai() == null ? java.time.LocalTime.of(9, 0) : j.getWaktuSelesai());
        cmbStatus.setSelectedItem(j.getStatusJadwal() == null ? "Terjadwal" : j.getStatusJadwal());
    }

    /** @return null bila valid, atau pesan galat. */
    private String validasi() {
        if (cmbProgram.getSelectedItem() == null) { return "Program TV wajib dipilih."; }
        if (cmbKanal.getSelectedItem() == null) { return "Kanal wajib dipilih."; }
        if (!ambilWaktu(sprSelesai).isAfter(ambilWaktu(sprMulai))) { return "Waktu Selesai harus setelah Waktu Mulai."; }
        return null;
    }


    private void simpan() {
        String galat = validasi();
        if (galat != null) {
            tampilGalat(galat);
            return;
        }
        model.ProgramTV progJadwal = (model.ProgramTV) cmbProgram.getSelectedItem();
        model.Kanal knJadwal = (model.Kanal) cmbKanal.getSelectedItem();
        j.setIdProgram(progJadwal.getIdProgram());
        j.setIdKanal(knJadwal.getIdKanal());
        j.setTanggalTayang(ambilTanggal(sprTanggal));
        j.setWaktuMulai(ambilWaktu(sprMulai));
        j.setWaktuSelesai(ambilWaktu(sprSelesai));
        j.setStatusJadwal((String) cmbStatus.getSelectedItem());
        boolean ok = baru ? controller.tambah(j) : controller.ubah(j);
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
        lblProgram = new javax.swing.JLabel();
        lblKanal = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblMulai = new javax.swing.JLabel();
        lblSelesai = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        cmbProgram = new javax.swing.JComboBox();
        cmbKanal = new javax.swing.JComboBox();
        sprTanggal = new javax.swing.JSpinner();
        sprMulai = new javax.swing.JSpinner();
        sprSelesai = new javax.swing.JSpinner();
        cmbStatus = new javax.swing.JComboBox();
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
        lblKepala.setText("Tambah Jadwal");
        pnlJudul.add(lblKepala);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Lengkapi kolom bertanda * lalu tekan Simpan.");
        pnlJudul.add(lblSub);

        getContentPane().add(pnlJudul, java.awt.BorderLayout.NORTH);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 12, 22));
        pnlForm.setLayout(new java.awt.GridLayout(0, 2, 12, 6));

        lblProgram.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblProgram.setForeground(new java.awt.Color(15, 23, 42));
        lblProgram.setText("Program TV *");
        pnlForm.add(lblProgram);

        cmbProgram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbProgram);

        lblKanal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblKanal.setForeground(new java.awt.Color(15, 23, 42));
        lblKanal.setText("Kanal *");
        pnlForm.add(lblKanal);

        cmbKanal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbKanal);

        lblTanggal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(15, 23, 42));
        lblTanggal.setText("Tanggal Tayang *");
        pnlForm.add(lblTanggal);

        sprTanggal.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprTanggal);

        lblMulai.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblMulai.setForeground(new java.awt.Color(15, 23, 42));
        lblMulai.setText("Waktu Mulai *");
        pnlForm.add(lblMulai);

        sprMulai.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprMulai);

        lblSelesai.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblSelesai.setForeground(new java.awt.Color(15, 23, 42));
        lblSelesai.setText("Waktu Selesai *");
        pnlForm.add(lblSelesai);

        sprSelesai.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprSelesai);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(15, 23, 42));
        lblStatus.setText("Status Jadwal *");
        pnlForm.add(lblStatus);

        cmbStatus.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbStatus);

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
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblKanal;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblMulai;
    private javax.swing.JLabel lblSelesai;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JComboBox cmbKanal;
    private javax.swing.JComboBox cmbProgram;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JSpinner sprMulai;
    private javax.swing.JSpinner sprSelesai;
    private javax.swing.JSpinner sprTanggal;
    // End of variables declaration//GEN-END:variables
}
