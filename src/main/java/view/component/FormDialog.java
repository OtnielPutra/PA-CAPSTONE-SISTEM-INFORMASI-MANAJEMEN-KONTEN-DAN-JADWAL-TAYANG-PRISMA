package view.component;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Dialog form tambah/ubah. Isi form ditambahkan satu per satu, lalu setSaveAction
 * menentukan apa yang terjadi saat Simpan ditekan. Aksi mengembalikan null bila
 * berhasil, atau pesan galat (dialog tetap terbuka).
 */
public class FormDialog {

    @FunctionalInterface
    public interface Simpan {
        String jalankan();
    }

    private final Window owner;
    private final JDialog dialog;
    private final JPanel form = new JPanel(new GridBagLayout());
    private final JLabel pesan = Theme.label(" ", Font.PLAIN, 12, Theme.DANGER);
    private int baris;
    private Simpan aksi;
    private boolean tersimpan;

    public FormDialog(Window owner, String judul) {
        this.owner = owner;
        dialog = new JDialog(owner, judul, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel isi = new JPanel(new BorderLayout(0, 14));
        isi.setBackground(Color.WHITE);
        isi.setBorder(BorderFactory.createEmptyBorder(22, 26, 18, 26));
        isi.add(Theme.label(judul, Font.BOLD, 18, Theme.TEXT), BorderLayout.NORTH);
        form.setOpaque(false);
        isi.add(form, BorderLayout.CENTER);

        JPanel bawah = new JPanel(new BorderLayout());
        bawah.setOpaque(false);
        bawah.add(pesan, BorderLayout.CENTER);
        JPanel tombol = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        tombol.setOpaque(false);
        AnimatedButton batal = new AnimatedButton("Batal", AnimatedButton.Kind.SECONDARY);
        AnimatedButton simpan = new AnimatedButton("Simpan", AnimatedButton.Kind.PRIMARY);
        batal.addActionListener(e -> dialog.dispose());
        simpan.addActionListener(e -> doSimpan());
        tombol.add(batal);
        tombol.add(simpan);
        bawah.add(tombol, BorderLayout.EAST);
        isi.add(bawah, BorderLayout.SOUTH);

        dialog.setContentPane(isi);
        dialog.getRootPane().setDefaultButton(simpan);
    }

    public JDialog getDialog() {
        return dialog;
    }

    public JLabel addRow(String label, JComponent field) {
        JLabel l = Theme.label(label, Font.BOLD, 13, Theme.TEXT);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = baris;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(8, 0, 8, 16);
        form.add(l, c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = baris;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(4, 0, 4, 0);
        form.add(field, c);
        baris++;
        return l;
    }

    public JTextField addText(String label, String nilai) {
        JTextField f = new JTextField(nilai == null ? "" : nilai, 26);
        Theme.styleInput(f);
        addRow(label, f);
        return f;
    }

    public JPasswordField addPassword(String label, String nilai) {
        JPasswordField f = new JPasswordField(nilai == null ? "" : nilai, 26);
        Theme.styleInput(f);
        addRow(label, f);
        return f;
    }

    public JTextArea addTextArea(String label, String nilai, int rows) {
        JTextArea a = new JTextArea(nilai == null ? "" : nilai, rows, 26);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setFont(Theme.font(Font.PLAIN, 13));
        a.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        JScrollPane sp = new JScrollPane(a);
        sp.setBorder(BorderFactory.createLineBorder(Theme.LINE));
        addRow(label, sp);
        return a;
    }

    public JComboBox<String> addCombo(String label, List<String> pilihan, String terpilih) {
        JComboBox<String> c = new JComboBox<>(pilihan.toArray(new String[0]));
        if (terpilih != null) {
            c.setSelectedItem(terpilih);
        }
        Theme.styleInput(c);
        addRow(label, c);
        return c;
    }

    /**
     * Combo berisi entitas. Pemilihan dicocokkan lewat id (idTerpilih, 0 = belum ada).
     * labelKosong != null menambahkan pilihan kosong (untuk relasi opsional).
     */
    public <E> JComboBox<E> addEntityCombo(String label, List<E> pilihan, ToIntFunction<E> idOf,
                                           int idTerpilih, String labelKosong) {
        JComboBox<E> c = new JComboBox<>();
        if (labelKosong != null) {
            c.addItem(null);
        }
        for (E e : pilihan) {
            c.addItem(e);
        }
        if (idTerpilih > 0) {
            for (E e : pilihan) {
                if (idOf.applyAsInt(e) == idTerpilih) {
                    c.setSelectedItem(e);
                    break;
                }
            }
        }
        c.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean sel, boolean foc) {
                super.getListCellRendererComponent(list, value, index, sel, foc);
                setText(value == null ? labelKosong : value.toString());
                return this;
            }
        });
        Theme.styleInput(c);
        addRow(label, c);
        return c;
    }

    public DateTimeField addDateTime(String label, DateTimeField field) {
        addRow(label, field);
        return field;
    }

    public void setSaveAction(Simpan aksi) {
        this.aksi = aksi;
    }

    /** Menampilkan dialog (modal). @return true bila data berhasil disimpan. */
    public boolean showDialog() {
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        dialog.setVisible(true);
        return tersimpan;
    }

    private void doSimpan() {
        String galat;
        try {
            galat = aksi == null ? null : aksi.jalankan();
        } catch (RuntimeException ex) {
            galat = ex.getMessage() == null ? "Terjadi kesalahan" : ex.getMessage();
        }
        if (galat != null) {
            pesan.setText(galat);
            return;
        }
        tersimpan = true;
        dialog.dispose();
    }
}
