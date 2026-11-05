package view.component;

import javax.swing.*;
import java.awt.*;

/** Palet warna dan font terpusat untuk seluruh tampilan PRISMA. */
public final class Theme {
    public static final Color NAVY = new Color(0x0B, 0x1F, 0x3A);
    public static final Color BLUE = new Color(0x25, 0x63, 0xEB);
    public static final Color BG = new Color(0xF3, 0xF6, 0xFB);
    public static final Color TEXT = new Color(0x0F, 0x17, 0x2A);
    public static final Color MUTED = new Color(0x64, 0x74, 0x8B);
    public static final Color LINE = new Color(0xE2, 0xE8, 0xF0);
    public static final Color DANGER = new Color(0xDC, 0x26, 0x26);
    public static final Color SUCCESS = new Color(0x16, 0xA3, 0x4A);
    public static final Color WARNING = new Color(0xD9, 0x77, 0x06);

    private Theme() { }

    public static Font font(int style, int size) {
        return new Font("SansSerif", style, size);
    }

    public static JLabel label(String text, int style, int size, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(font(style, size));
        l.setForeground(color);
        return l;
    }

    /** Memberi gaya seragam pada kolom isian. */
    public static void styleInput(JComponent c) {
        c.setFont(font(Font.PLAIN, 13));
        c.setForeground(TEXT);
        c.setBackground(Color.WHITE);
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), BorderFactory.createEmptyBorder(6, 10, 6, 10)));
    }
}
