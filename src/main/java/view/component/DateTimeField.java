package view.component;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Pemilih tanggal / waktu berbasis JSpinner. Pada mode "bisaKosong" ada kotak centang:
 * filter baru aktif bila dicentang, sehingga pencarian bisa dikosongkan.
 */
public class DateTimeField extends JPanel {
    private static final long serialVersionUID = 1L;

    public enum Mode { DATE, TIME, DATETIME }

    private final boolean bisaKosong;
    private final JSpinner spinner;
    private final JCheckBox aktif;

    public DateTimeField(Mode mode, boolean bisaKosong) {
        super(new FlowLayout(FlowLayout.LEFT, 6, 0));
        this.bisaKosong = bisaKosong;
        setOpaque(false);

        int kolomWaktu = mode == Mode.DATE ? Calendar.DAY_OF_MONTH : Calendar.MINUTE;
        spinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, kolomWaktu));
        String pola = mode == Mode.DATE ? "yyyy-MM-dd" : mode == Mode.TIME ? "HH:mm" : "yyyy-MM-dd HH:mm";
        spinner.setEditor(new JSpinner.DateEditor(spinner, pola));
        Theme.styleInput(((JSpinner.DefaultEditor) spinner.getEditor()).getTextField());
        spinner.setPreferredSize(new Dimension(mode == Mode.DATETIME ? 170 : 120, 34));

        if (bisaKosong) {
            JCheckBox cb = new JCheckBox();
            cb.setOpaque(false);
            cb.setToolTipText("Aktifkan filter ini");
            cb.addActionListener(e -> spinner.setEnabled(cb.isSelected()));
            aktif = cb;
            add(cb);
        } else {
            aktif = null;
        }
        add(spinner);
        spinner.setEnabled(!bisaKosong);
    }

    private boolean nilaiAktif() {
        return aktif == null || aktif.isSelected();
    }

    private LocalDateTime nilai() {
        Date d = (Date) spinner.getValue();
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private void tetapkan(LocalDateTime v) {
        spinner.setValue(Date.from(v.atZone(ZoneId.systemDefault()).toInstant()));
    }

    private void setAktif(boolean on) {
        if (aktif != null) {
            aktif.setSelected(on);
        }
        spinner.setEnabled(aktif == null || on);
    }

    public LocalDate getDate() {
        return nilaiAktif() ? nilai().toLocalDate() : null;
    }

    public LocalTime getTime() {
        return nilaiAktif() ? nilai().toLocalTime().withSecond(0).withNano(0) : null;
    }

    public LocalDateTime getDateTime() {
        return nilaiAktif() ? nilai().withSecond(0).withNano(0) : null;
    }

    public void setDate(LocalDate d) {
        if (d == null) {
            setAktif(false);
            if (!bisaKosong) {
                tetapkan(LocalDateTime.now());
            }
            return;
        }
        tetapkan(d.atStartOfDay());
        setAktif(true);
    }

    public void setTime(LocalTime t) {
        if (t == null) {
            setAktif(false);
            return;
        }
        tetapkan(LocalDate.now().atTime(t));
        setAktif(true);
    }

    public void setDateTime(LocalDateTime dt) {
        if (dt == null) {
            setAktif(false);
            if (!bisaKosong) {
                tetapkan(LocalDateTime.now());
            }
            return;
        }
        tetapkan(dt);
        setAktif(true);
    }

    /** Mengosongkan (true) atau mengaktifkan (false) filter. Tidak berpengaruh bila tidak bisaKosong. */
    public void setEmptyValue(boolean kosong) {
        if (bisaKosong) {
            setAktif(!kosong);
        }
    }
}
