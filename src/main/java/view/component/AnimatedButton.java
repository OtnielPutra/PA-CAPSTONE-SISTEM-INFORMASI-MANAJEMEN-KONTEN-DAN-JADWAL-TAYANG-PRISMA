package view.component;

import javax.swing.*;
import java.awt.*;

/** Tombol Swing biasa dengan warna tetap (tanpa animasi). */
public class AnimatedButton extends JButton {
    private static final long serialVersionUID = 1L;

    public enum Kind { PRIMARY, SECONDARY, DANGER, SIDEBAR }

    private final Kind kind;

    public AnimatedButton(String text, Kind kind) {
        super(text);
        this.kind = kind;
        setFocusPainted(false);
        setFont(Theme.font(Font.BOLD, 13));
        setMargin(new java.awt.Insets(8, 16, 8, 16));
        switch (kind) {
            case PRIMARY:
                setBackground(Theme.BLUE);
                setForeground(Color.WHITE);
                break;
            case DANGER:
                setBackground(new Color(0xFE, 0xE2, 0xE2));
                setForeground(Theme.DANGER);
                break;
            case SIDEBAR:
                setHorizontalAlignment(SwingConstants.LEFT);
                setFont(Theme.font(Font.PLAIN, 14));
                setBackground(Theme.NAVY);
                setForeground(new Color(0xCB, 0xD5, 0xE1));
                setBorderPainted(false);
                break;
            default:
                setBackground(Color.WHITE);
                setForeground(Theme.TEXT);
                break;
        }
    }

    public void setActive(boolean active) {
        if (kind == Kind.SIDEBAR) {
            setBackground(active ? new Color(0x1E, 0x40, 0xAF) : Theme.NAVY);
            setForeground(active ? Color.WHITE : new Color(0xCB, 0xD5, 0xE1));
        }
    }
}
