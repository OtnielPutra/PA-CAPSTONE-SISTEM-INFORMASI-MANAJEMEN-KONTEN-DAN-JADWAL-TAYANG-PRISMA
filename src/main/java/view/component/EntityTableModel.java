package view.component;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/** Model tabel generik: setiap kolom diisi oleh fungsi Cell. */
public class EntityTableModel<T> extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    @FunctionalInterface
    public interface Cell<T> {
        Object nilai(T item, int kolom);
    }

    private final String[] kolom;
    private final Cell<T> sel;
    private List<T> baris = new ArrayList<>();

    public EntityTableModel(String[] kolom, Cell<T> sel) {
        this.kolom = kolom;
        this.sel = sel;
    }

    public void setRows(List<T> data) {
        baris = new ArrayList<>(data);
        fireTableDataChanged();
    }

    public T getItem(int row) {
        return baris.get(row);
    }

    @Override public int getRowCount() { return baris.size(); }
    @Override public int getColumnCount() { return kolom.length; }
    @Override public String getColumnName(int c) { return kolom[c]; }
    @Override public Object getValueAt(int r, int c) { return sel.nilai(baris.get(r), c); }
    @Override public boolean isCellEditable(int r, int c) { return false; }
    @Override public Class<?> getColumnClass(int c) { return Object.class; }
}
