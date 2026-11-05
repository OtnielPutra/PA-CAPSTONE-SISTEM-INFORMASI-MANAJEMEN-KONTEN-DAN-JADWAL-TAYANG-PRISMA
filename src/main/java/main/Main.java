package main;

import view.LoginFrame;

/** Titik awal program: mengatur look and feel lalu membuka jendela login. */
public class Main {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println("Gagal mengatur look and feel: " + ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
