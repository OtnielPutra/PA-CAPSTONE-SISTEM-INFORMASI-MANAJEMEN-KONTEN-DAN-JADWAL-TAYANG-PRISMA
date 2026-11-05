package controller;

import model.JadwalTayang;
import model.KerjaSama;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Data ringkasan untuk halaman Dashboard. Memakai MonitorController agar tidak ada query ganda. */
public class DashboardController {

    /** Jumlah data per entitas. */
    public static final class Stats {
        public final int konten;
        public final int program;
        public final int jadwal;
        public final int kerjaSama;

        public Stats(int konten, int program, int jadwal, int kerjaSama) {
            this.konten = konten;
            this.program = program;
            this.jadwal = jadwal;
            this.kerjaSama = kerjaSama;
        }
    }

    private final MonitorController monitor = new MonitorController();

    public Stats ringkasan() {
        return new Stats(
                monitor.tampilKonten().size(),
                monitor.tampilProgram().size(),
                monitor.tampilJadwal().size(),
                monitor.tampilKerjaSama().size());
    }

    /** Jadwal tayang yang paling baru dibuat (id terbesar lebih dulu). */
    public List<JadwalTayang> jadwalTerbaru(int jumlah) {
        List<JadwalTayang> semua = new ArrayList<>(monitor.tampilJadwal());
        semua.sort(Comparator.comparingInt(JadwalTayang::getIdJadwal).reversed());
        return new ArrayList<>(semua.subList(0, Math.min(jumlah, semua.size())));
    }

    /** Pasangan {nama mitra, jumlah kerja sama}, diurutkan dari yang terbanyak. */
    public List<Object[]> kerjaSamaPerMitra(int jumlah) {
        Map<String, Integer> hitung = new LinkedHashMap<>();
        for (KerjaSama k : monitor.tampilKerjaSama()) {
            hitung.merge(k.getNamaMitra(), 1, Integer::sum);
        }
        List<Object[]> hasil = new ArrayList<>();
        for (Map.Entry<String, Integer> e : hitung.entrySet()) {
            hasil.add(new Object[] {e.getKey(), e.getValue()});
        }
        hasil.sort((a, b) -> Integer.compare((Integer) b[1], (Integer) a[1]));
        return new ArrayList<>(hasil.subList(0, Math.min(jumlah, hasil.size())));
    }

    public String getPesan() {
        return monitor.getPesan();
    }
}
