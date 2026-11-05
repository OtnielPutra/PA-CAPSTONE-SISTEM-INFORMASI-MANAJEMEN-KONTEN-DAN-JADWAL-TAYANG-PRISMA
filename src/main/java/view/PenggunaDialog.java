package view;

import controller.AkunController;
import model.Pengguna;

import javax.swing.*;

/**
 * Dialog tambah/ubah "Akun Pengguna" - JDialog MANDIRI hasil GUI Builder (pasangan file PenggunaDialog.form).
 *
 * Dipakai oleh PenggunaPanel:
 *   PenggunaDialog d = new PenggunaDialog(owner, data);   // data == null berarti tambah baru
 *   if (d.tampilkan()) { ... }
 *
 * Mode Design NetBeans memakai konstruktor (Frame, boolean) di bawah.
 */
public class PenggunaDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    private final AkunController controller = new AkunController();
    private final boolean baru;
    private final Pengguna k;
    private boolean tersimpan;

    /** Konstruktor bawaan NetBeans - dipakai untuk merender Design view. */
    public PenggunaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.baru = true;
        this.k = new model.PetugasKonten();
        initComponents();
        setTitle("Tambah Akun");
        lblKepala.setText("Tambah Akun");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
    }

    /** Konstruktor aplikasi: data == null berarti menambah data baru. */
    public PenggunaDialog(java.awt.Window owner, Pengguna data) {
        super(owner, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        this.baru = data == null;
        this.k = baru ? new model.PetugasKonten() : data;
        initComponents();
        setTitle(baru ? "Tambah Akun" : "Ubah Akun");
        lblKepala.setText(baru ? "Tambah Akun" : "Ubah Akun");
        pasangSpinner();
        isiComboStatis();
        isiCombo();
        isiDariData();
        cmbRole.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinkronLabelDetail();
            }
        });

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

    /** Mengisi combo pilihan tetap (status/jenis/kategori/role). */
    private void isiComboStatis() {
        cmbRole.removeAllItems();
        for (String item : Pengguna.DAFTAR_ROLE) {
            cmbRole.addItem(item);
        }
        cmbStatus.removeAllItems();
        for (String item : Pengguna.DAFTAR_STATUS) {
            cmbStatus.addItem(item);
        }
    }

    private void isiCombo() {

    }

    /** Mengisi seluruh kolom dari objek data. */
    private void isiDariData() {
        txtNama.setText(k.getNamaLengkap() == null ? "" : k.getNamaLengkap());
        txtUsername.setText(k.getUsername() == null ? "" : k.getUsername());
        txtPassword.setText(k.getPassword() == null ? "" : k.getPassword());
        cmbRole.setSelectedItem(k.getRole() == null ? Pengguna.ROLE_PETUGAS_KONTEN : k.getRole());
        txtDetail.setText(k.getKeterangan() == null || "-".equals(k.getKeterangan()) ? "" : k.getKeterangan());
        isiTanggal(sprTanggal, k.getTanggalPenugasan() == null ? java.time.LocalDate.now() : k.getTanggalPenugasan());
        cmbStatus.setSelectedItem(k.getStatusAkun() == null ? "aktif" : k.getStatusAkun());
        sinkronLabelDetail();
    }

    /** @return null bila valid, atau pesan galat. */
    private String validasi() {
        if (txtNama.getText().trim().isEmpty()) { return "Nama Lengkap wajib diisi."; }
        if (txtUsername.getText().trim().isEmpty()) { return "Username wajib diisi."; }
        if (txtPassword.getPassword().length == 0) { return "Password wajib diisi."; }
        if (!Pengguna.ROLE_ADMIN.equals(cmbRole.getSelectedItem()) && txtDetail.getText().trim().isEmpty()) { return "Detail penugasan wajib diisi untuk petugas."; }
        return null;
    }


    private void simpan() {
        String galat = validasi();
        if (galat != null) {
            tampilGalat(galat);
            return;
        }
        // Pengguna bersifat abstract: objek dibuat oleh AkunController sesuai peran yang dipilih.
        String peran = (String) cmbRole.getSelectedItem();
        Pengguna p = controller.buatPengguna(peran, txtNama.getText().trim(), txtUsername.getText().trim(),
                new String(txtPassword.getPassword()), (String) cmbStatus.getSelectedItem(),
                txtDetail.getText().trim());
        if (p == null) {
            tampilGalat("Peran tidak valid.");
            return;
        }
        p.setTanggalPenugasan(ambilTanggal(sprTanggal));
        if (!baru) {
            p.setIdPengguna(k.getIdPengguna());
        }
        boolean ok = baru ? controller.tambah(p) : controller.ubah(p);
        if (!ok) {
            tampilGalat(controller.getPesan());
            return;
        }

        tersimpan = true;
        dispose();
    }

    private String labelDetail(String peran) {
        if (Pengguna.ROLE_PETUGAS_JADWAL.equals(peran)) {
            return "Cakupan Jadwal";
        }
        if (Pengguna.ROLE_PETUGAS_KONTEN.equals(peran)) {
            return "Spesialisasi Konten";
        }
        return "Detail Penugasan";
    }

    private void sinkronLabelDetail() {
        String r = (String) cmbRole.getSelectedItem();
        boolean admin = Pengguna.ROLE_ADMIN.equals(r);
        txtDetail.setEnabled(!admin);
        lblDetail.setText(admin ? "Detail Penugasan" : labelDetail(r) + " *");
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
        lblNama = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblDetail = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        cmbRole = new javax.swing.JComboBox();
        txtDetail = new javax.swing.JTextField();
        sprTanggal = new javax.swing.JSpinner();
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
        lblKepala.setText("Tambah Akun");
        pnlJudul.add(lblKepala);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Lengkapi kolom bertanda * lalu tekan Simpan.");
        pnlJudul.add(lblSub);

        getContentPane().add(pnlJudul, java.awt.BorderLayout.NORTH);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 22, 12, 22));
        pnlForm.setLayout(new java.awt.GridLayout(0, 2, 12, 6));

        lblNama.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblNama.setForeground(new java.awt.Color(15, 23, 42));
        lblNama.setText("Nama Lengkap *");
        pnlForm.add(lblNama);

        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtNama.setColumns(16);
        txtNama.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtNama);

        lblUsername.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(15, 23, 42));
        lblUsername.setText("Username *");
        pnlForm.add(lblUsername);

        txtUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtUsername.setColumns(16);
        txtUsername.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtUsername);

        lblPassword.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(15, 23, 42));
        lblPassword.setText("Password *");
        pnlForm.add(lblPassword);

        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtPassword);

        lblRole.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblRole.setForeground(new java.awt.Color(15, 23, 42));
        lblRole.setText("Peran *");
        pnlForm.add(lblRole);

        cmbRole.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(cmbRole);

        lblDetail.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblDetail.setForeground(new java.awt.Color(15, 23, 42));
        lblDetail.setText("Detail Penugasan");
        pnlForm.add(lblDetail);

        txtDetail.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtDetail.setColumns(16);
        txtDetail.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        pnlForm.add(txtDetail);

        lblTanggal.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblTanggal.setForeground(new java.awt.Color(15, 23, 42));
        lblTanggal.setText("Tanggal Penugasan *");
        pnlForm.add(lblTanggal);

        sprTanggal.setPreferredSize(new java.awt.Dimension(170, 32));
        pnlForm.add(sprTanggal);

        lblStatus.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(15, 23, 42));
        lblStatus.setText("Status Akun *");
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
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblDetail;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblPesan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlJudul;
    private javax.swing.JPanel pnlTombol;
    private javax.swing.JComboBox cmbRole;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JSpinner sprTanggal;
    private javax.swing.JTextField txtDetail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
