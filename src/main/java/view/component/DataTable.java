package view.component;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Tabel bergaya modern: baris berselang-seling, lebar kolom, dan kolom status berwarna.
 *
 * Gaya yang sama bisa diterapkan ke tabel milik form NetBeans lewat
 * {@link #gayakan(JTable, int)} - sehingga tabel yang dikelola GUI Builder
 * dipakai langsung saat runtime tanpa digantikan tabel lain.
 */
public class DataTable extends JTable {
    private static final long serialVersionUID = 1L;
    private final Renderer perender = new Renderer(-1);

    public DataTable(TableModel model) {
        super(model);
        gayakan(this, -1);
        setDefaultRenderer(Object.class, perender);
    }

    public void setStatusColumn(int column) {
        perender.kolomStatus = column;
    }

    public void setColumnWidths(int[] lebar) {
        for (int i = 0; i < lebar.length && i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setPreferredWidth(lebar[i]);
        }
    }

    /**
     * Menerapkan gaya DataTable (tinggi baris, belang baris, kepala tabel, dan
     * pewarna kolom status) ke JTable apa pun - misalnya tabel yang dideklarasikan
     * di form NetBeans. Kolom status = -1 berarti tidak ada kolom yang diwarnai.
     */
    public static void gayakan(JTable tabel, int kolomStatus) {
        tabel.setRowHeight(36);
        tabel.setShowVerticalLines(false);
        tabel.setShowHorizontalLines(true);
        tabel.setGridColor(Theme.LINE);
        tabel.setIntercellSpacing(new Dimension(0, 1));
        tabel.setSelectionBackground(new Color(0xDB, 0xEA, 0xFE));
        tabel.setSelectionForeground(Theme.TEXT);
        tabel.setFillsViewportHeight(true);
        tabel.setFont(Theme.font(Font.PLAIN, 13));
        JTableHeader h = tabel.getTableHeader();
        h.setFont(Theme.font(Font.BOLD, 12));
        h.setForeground(Theme.MUTED);
        h.setBackground(new Color(0xF8, 0xFA, 0xFC));
        h.setReorderingAllowed(false);
        tabel.setDefaultRenderer(Object.class, new Renderer(kolomStatus));
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

    public static class Renderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;
        int kolomStatus;

        Renderer(int kolomStatus) {
            this.kolomStatus = kolomStatus;
        }

        @Override
        public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int row, int col) {
            super.getTableCellRendererComponent(t, v == null ? "-" : v, sel, foc, row, col);
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
            setFont(Theme.font(Font.PLAIN, 13));
            if (!sel) {
                setBackground(row % 2 == 0 ? Color.WHITE : new Color(0xFA, 0xFB, 0xFD));
                setForeground(Theme.TEXT);
            }
            if (col == kolomStatus && v != null) {
                setFont(Theme.font(Font.BOLD, 12));
                setForeground(warnaStatus(v.toString()));
            }
            return this;
        }
    }
}
