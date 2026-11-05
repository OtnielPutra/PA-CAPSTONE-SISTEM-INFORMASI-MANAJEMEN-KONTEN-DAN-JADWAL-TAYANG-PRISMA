package view.component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Format tampilan tanggal, waktu, dan rupiah (bahasa Indonesia). */
public final class Fmt {
    private static final Locale ID = Locale.forLanguageTag("id-ID");
    private static final DateTimeFormatter TANGGAL = DateTimeFormatter.ofPattern("dd MMM yyyy", ID);
    private static final DateTimeFormatter TANGGAL_WAKTU = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", ID);
    private static final DateTimeFormatter WAKTU = DateTimeFormatter.ofPattern("HH:mm");
    private static final DecimalFormat RUPIAH = new DecimalFormat("#,##0", new DecimalFormatSymbols(ID));

    private Fmt() { }

    public static String date(LocalDate d) { return d == null ? "-" : TANGGAL.format(d); }
    public static String dateTime(LocalDateTime d) { return d == null ? "-" : TANGGAL_WAKTU.format(d); }
    public static String time(LocalTime t) { return t == null ? "-" : WAKTU.format(t); }
    public static String dash(String s) { return s == null || s.isBlank() ? "-" : s; }
    public static String rupiah(BigDecimal n) { return n == null ? "-" : "Rp " + RUPIAH.format(n); }
}
