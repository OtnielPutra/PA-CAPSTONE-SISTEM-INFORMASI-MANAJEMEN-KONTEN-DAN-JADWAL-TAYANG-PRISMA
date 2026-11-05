package view;

import controller.LoginController;
import model.Pengguna;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/**
 * Form login PRISMA (Java Swing murni, JFrame).
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file LoginFrame.form. Jangan mengedit kode di antara tanda
 * //GEN-BEGIN dan //GEN-END secara manual - semua perubahan tampilan
 * dilakukan lewat tab "Design" di Apache NetBeans.
 */
public class LoginFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    /** Controller autentikasi untuk proses login. */
    private final LoginController auth = new LoginController();

    /** Karakter echo default JPasswordField, diisi di konstruktor. */
    private char echoDefault;

    /**
     * Membuat form login baru.
     */
    public LoginFrame() {
        initComponents();
        setResizable(false);
        echoDefault = txtPass.getEchoChar();
        getRootPane().setDefaultButton(btnLogin);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBrand = new javax.swing.JPanel();
        lblPrisma = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblTagline = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30));
        lblDesc1 = new javax.swing.JLabel();
        lblDesc2 = new javax.swing.JLabel();
        pnlForm = new javax.swing.JPanel();
        lblWelcome = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4));
        lblSubtitle = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 22), new java.awt.Dimension(0, 22), new java.awt.Dimension(0, 22));
        lblUsername = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        txtUser = new javax.swing.JTextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 14), new java.awt.Dimension(0, 14), new java.awt.Dimension(0, 14));
        lblPassword = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        txtPass = new javax.swing.JPasswordField();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        chkShow = new javax.swing.JCheckBox();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8));
        lblError = new javax.swing.JLabel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8), new java.awt.Dimension(0, 8));
        btnLogin = new javax.swing.JButton();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10));
        btnKeluar = new javax.swing.JButton();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRISMA - Login");

        pnlBrand.setBackground(new java.awt.Color(30, 58, 138));
        pnlBrand.setBorder(javax.swing.BorderFactory.createEmptyBorder(170, 44, 30, 44));
        pnlBrand.setLayout(new javax.swing.BoxLayout(pnlBrand, javax.swing.BoxLayout.Y_AXIS));

        lblPrisma.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        lblPrisma.setForeground(new java.awt.Color(255, 255, 255));
        lblPrisma.setText("PRISMA");
        pnlBrand.add(lblPrisma);
        pnlBrand.add(filler1);

        lblTagline.setForeground(new java.awt.Color(191, 219, 254));
        lblTagline.setText("CONTENT & BROADCAST");
        pnlBrand.add(lblTagline);
        pnlBrand.add(filler2);

        lblDesc1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDesc1.setForeground(new java.awt.Color(255, 255, 255));
        lblDesc1.setText("Sistem informasi manajemen konten,");
        pnlBrand.add(lblDesc1);

        lblDesc2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDesc2.setForeground(new java.awt.Color(255, 255, 255));
        lblDesc2.setText("program, jadwal tayang, dan kerja sama.");
        pnlBrand.add(lblDesc2);

        getContentPane().add(pnlBrand, java.awt.BorderLayout.WEST);

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 50, 30, 50));
        pnlForm.setLayout(new javax.swing.BoxLayout(pnlForm, javax.swing.BoxLayout.Y_AXIS));

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblWelcome.setForeground(new java.awt.Color(31, 41, 55));
        lblWelcome.setText("Selamat Datang");
        pnlForm.add(lblWelcome);
        pnlForm.add(filler3);

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Masuk untuk mengelola konten dan jadwal tayang.");
        pnlForm.add(lblSubtitle);
        pnlForm.add(filler4);

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(31, 41, 55));
        lblUsername.setText("Username");
        pnlForm.add(lblUsername);
        pnlForm.add(filler5);

        txtUser.setAlignmentX(0.0F);
        txtUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlForm.add(txtUser);
        pnlForm.add(filler6);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(31, 41, 55));
        lblPassword.setText("Password");
        pnlForm.add(lblPassword);
        pnlForm.add(filler7);

        txtPass.setAlignmentX(0.0F);
        txtPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));
        pnlForm.add(txtPass);
        pnlForm.add(filler8);

        chkShow.setBackground(new java.awt.Color(255, 255, 255));
        chkShow.setText("Tampilkan password");
        chkShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowActionPerformed(evt);
            }
        });
        pnlForm.add(chkShow);
        pnlForm.add(filler9);

        lblError.setForeground(new java.awt.Color(220, 38, 38));
        lblError.setText(" ");
        pnlForm.add(lblError);
        pnlForm.add(filler10);

        btnLogin.setBackground(new java.awt.Color(37, 99, 235));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        pnlForm.add(btnLogin);
        pnlForm.add(filler11);

        btnKeluar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        pnlForm.add(btnKeluar);
        pnlForm.add(filler12);

        getContentPane().add(pnlForm, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        // Tampilkan / sembunyikan karakter password.
        txtPass.setEchoChar(chkShow.isSelected() ? (char) 0 : echoDefault);
    }//GEN-LAST:event_chkShowActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        final String user = txtUser.getText();
        final String pass = new String(txtPass.getPassword());
        lblError.setText(" ");
        btnLogin.setEnabled(false);
        btnLogin.setText("Memproses...");

        new SwingWorker<Pengguna, Void>() {
            @Override
            protected Pengguna doInBackground() {
                return auth.login(user, pass);
            }

            @Override
            protected void done() {
                btnLogin.setEnabled(true);
                btnLogin.setText("Login");
                try {
                    Pengguna p = get();
                    if (p != null) {
                        new MainFrame(p).setVisible(true);
                        dispose();
                        return;
                    }
                    lblError.setText(auth.getPesan());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    lblError.setText("Proses login dibatalkan");
                } catch (ExecutionException ex) {
                    lblError.setText("Terjadi kesalahan: " + ex.getCause());
                }
                txtPass.setText("");
            }
        }.execute();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnKeluarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chkShow;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel lblDesc1;
    private javax.swing.JLabel lblDesc2;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPrisma;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTagline;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnlBrand;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
