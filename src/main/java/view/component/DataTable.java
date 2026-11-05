package view.component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

/** Tabel bergaya modern: baris berselang-seling, lebar kolom, dan kolom status berwarna. */
public class DataTable extends JTable {
    private static final long serialVersionUID = 1L;
    private int statusColumn = -1;

    public DataTable(TableModel model) {
        super(model);
        setRowHeight(36);
        setShowVerticalLines(false);
        setShowHorizontalLines(true);
        setGridColor(Theme.LINE);
        setIntercellSpacing(new Dimension(0, 1));
        setSelectionBackground(new Color(0xDB, 0xEA, 0xFE));
        setSelectionForeground(Theme.TEXT);
        setFillsViewportHeight(true);
        setFont(Theme.font(Font.PLAIN, 13));
        JTableHeader h = getTableHeader();
        h.setFont(Theme.font(Font.BOLD, 12));
        h.setForeground(Theme.MUTED);
        h.setBackground(new Color(0xF8, 0xFA, 0xFC));
        h.setReorderingAllowed(false);
        setDefaultRenderer(Object.class, new Renderer());
    }

    public void setStatusColumn(int column) {
        this.statusColumn = column;
    }

    public void setColumnWidths(int[] lebar) {
        for (int i = 0; i < lebar.length && i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setPreferredWidth(lebar[i]);
        }
    }

    static Color warnaStatus(String s) {
        switch (s) {
            case "aktif":
            case "Berjalan":
            case "Terbit":
            case "Sudah Tayang":
                return Theme.SUCCESS;
            case "Siap Tayang":
            case "Terjadwal":
                return Theme.BLUE;
            case "Direncanakan":
            case "Dalam Proses":
                return Theme.WARNING;
            case "Dibatalkan":
            case "nonaktif":
                return Theme.DANGER;
            default:
                return Theme.MUTED;
        }
    }

    private class Renderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int row, int col) {
            super.getTableCellRendererComponent(t, v == null ? "-" : v, sel, foc, row, col);
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
            setFont(Theme.font(Font.PLAIN, 13));
            if (!sel) {
                setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xFA, 0xFB, 0xFD));
                setForeground(Theme.TEXT);
            }
            if (col == statusColumn && v != null) {
                setFont(Theme.font(Font.BOLD, 12));
                setForeground(warnaStatus(v.toString()));
            }
            return this;
        }
    }
}
