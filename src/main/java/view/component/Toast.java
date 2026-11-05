package view.component;

import javax.swing.*;
import java.awt.*;

/** Pesan informasi standar (tanpa animasi). */
public final class Toast {
    private Toast() { }

    public static void show(Component anchor, String pesan) {
        JOptionPane.showMessageDialog(anchor, pesan, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
}
