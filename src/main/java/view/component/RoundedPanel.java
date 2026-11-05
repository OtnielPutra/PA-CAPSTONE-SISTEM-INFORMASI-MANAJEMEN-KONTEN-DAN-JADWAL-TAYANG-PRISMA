package view.component;

import javax.swing.*;
import java.awt.*;

/** Panel kartu putih dengan garis tepi (tanpa sudut membulat kustom). */
public class RoundedPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public RoundedPanel() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Theme.LINE));
    }
}
