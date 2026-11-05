package view;

import controller.BaseController;
import view.component.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

/** Logika umum CRUD; setiap halaman menyediakan komponen form visualnya sendiri. */
public abstract class CrudPanel<T> extends JPanel implements Refreshable {
    private static final long serialVersionUID = 1L;
    private final String judul, subjudul, kata;
    protected final boolean editable;
    private EntityTableModel<T> model;
    protected DataTable table;
    protected JTextField searchField;

    protected CrudPanel() { this("", "", "", false); }
    protected CrudPanel(String judul, String subjudul, String kata, boolean editable) {
        this.judul=judul; this.subjudul=subjudul; this.kata=kata; this.editable=editable;
    }
    protected abstract JLabel titleLabel();
    protected abstract JLabel subtitleLabel();
    protected abstract JButton addButton();
    protected abstract JTextField searchFieldComponent();
    protected abstract JPanel searchPanel();
    protected abstract JPanel actionBar();
    protected abstract JScrollPane tableScrollPane();
    protected abstract JLabel countLabel();
    protected abstract BaseController<T> controller();
    protected abstract String[] columnNames();
    protected abstract Object cellValue(T item, int column);
    protected abstract int idOf(T item);
    protected abstract boolean showForm(T existing);
    protected int statusColumn(){return -1;}
    protected int[] columnWidths(){return null;}
    protected void addExtraActions(JPanel bar){}
    protected JComponent createSearchControls(){return searchFieldComponent();}
    protected List<T> querySearch(){return controller().cari(searchField==null?"":searchField.getText());}
    protected void resetSearch(){if(searchField!=null)searchField.setText("");}
    protected void tambah(){openForm(null);}
    protected void cari(){refresh();}
    protected void reset(){resetSearch();refresh();}

    protected final void init(){
        titleLabel().setText(judul); subtitleLabel().setText(subjudul);
        addButton().setText("+ Tambah "+kata); addButton().setVisible(editable);
        JComponent search=createSearchControls();
        searchField=(search instanceof JTextField)?(JTextField)search:null;
        if(search!=searchFieldComponent()){searchPanel().remove(searchFieldComponent());searchPanel().add(search,0);}
        model=new EntityTableModel<>(columnNames(),this::cellValue); table=new DataTable(model);
        table.setStatusColumn(statusColumn()); if(columnWidths()!=null)table.setColumnWidths(columnWidths());
        if(editable)table.addMouseListener(new MouseAdapter(){public void mouseClicked(MouseEvent e){if(e.getClickCount()==2)editSelected();}});
        tableScrollPane().setViewportView(table);
        if(editable){
            AnimatedButton edit=new AnimatedButton("Ubah",AnimatedButton.Kind.SECONDARY);
            AnimatedButton hapus=new AnimatedButton("Hapus",AnimatedButton.Kind.DANGER);
            edit.addActionListener(e->editSelected()); hapus.addActionListener(e->deleteSelected());
            actionBar().add(edit); addExtraActions(actionBar()); actionBar().add(hapus);
        }
        refresh();
    }
    @Override public void refresh(){List<T> data;try{data=querySearch();}catch(RuntimeException ex){data=Collections.emptyList();}
        model.setRows(data);countLabel().setText(data.size()+" data");}
    protected Window owner(){return SwingUtilities.getWindowAncestor(this);}
    protected T selectedItem(){int v=table.getSelectedRow();return v<0?null:model.getItem(table.convertRowIndexToModel(v));}
    protected void showError(String msg){JOptionPane.showMessageDialog(this,msg,"Kesalahan",JOptionPane.ERROR_MESSAGE);}
    private void openForm(T existing){if(showForm(existing)){refresh();Toast.show(this,"Data berhasil disimpan.");}}
    private void editSelected(){T item=selectedItem();if(item==null){JOptionPane.showMessageDialog(this,"Pilih satu baris data terlebih dahulu.","Informasi",JOptionPane.INFORMATION_MESSAGE);return;}openForm(item);}
    private void deleteSelected(){T item=selectedItem();if(item==null){JOptionPane.showMessageDialog(this,"Pilih satu baris data terlebih dahulu.","Informasi",JOptionPane.INFORMATION_MESSAGE);return;}
        int ok=JOptionPane.showConfirmDialog(this,"Yakin ingin menghapus data ini?","Konfirmasi Hapus",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);if(ok!=JOptionPane.YES_OPTION)return;
        if(controller().hapus(idOf(item))){refresh();Toast.show(this,"Data berhasil dihapus.");}else showError(controller().getPesan());}
}
