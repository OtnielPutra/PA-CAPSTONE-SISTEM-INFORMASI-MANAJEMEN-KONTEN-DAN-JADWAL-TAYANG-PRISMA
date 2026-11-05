package view;

import controller.DashboardController;
import model.JadwalTayang;
import model.Pengguna;
import view.component.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Dashboard: kartu statistik + jadwal tayang terbaru + relasi mitra.
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file DashboardPanel.form - buka file ini lalu klik tab "Design" untuk
 * mengatur tampilan secara drag-and-drop. Jangan mengedit kode di antara
 * tanda //GEN-BEGIN dan //GEN-END; perubahan tampilan dilakukan lewat Design.
 *
 * Kerangka tampilan (header, 4 kartu statistik, 2 kartu tabel) kini dikelola
 * GUI Builder. Bagian dinamis (nama pengguna, angka statistik, isi tabel
 * DataTable dengan pewarna status) diisi lewat kode di luar blok yang dijaga.
 */
public class DashboardPanel extends JPanel implements Refreshable {
    private static final long serialVersionUID = 1L;

    private final DashboardController controller = new DashboardController();

    private final EntityTableModel<JadwalTayang> jadwalModel = new EntityTableModel<JadwalTayang>(
            new String[] {"Program", "Kanal", "Tanggal", "Waktu", "Status"},
            (j, c) -> {
                switch (c) {
                    case 0: return Fmt.dash(j.getNamaProgram());
                    case 1: return Fmt.dash(j.getNamaKanal());
                    case 2: return Fmt.date(j.getTanggalTayang());
                    case 3: return Fmt.time(j.getWaktuMulai()) + " - " + Fmt.time(j.getWaktuSelesai());
                    default: return j.getStatusJadwal();
                }
            });

    private final EntityTableModel<Object[]> mitraModel = new EntityTableModel<Object[]>(
            new String[] {"Mitra", "Jumlah Kerja Sama"},
            (row, c) -> c == 0 ? String.valueOf(row[0]) : row[1] + " kerja sama");

    public DashboardPanel(Pengguna user) {
        initComponents();
        lblH1.setText("Selamat datang, " + user.getNamaLengkap());
        lblSub.setText("Anda masuk sebagai " + user.getRole()
                + ". Ringkasan pengelolaan konten dan jadwal tayang.");
        initTables();
        refresh();
    }

    /** Memasang DataTable asli (dengan pewarna kolom status) ke scroll pane hasil desain. */
    private void initTables() {
        DataTable jadwalTable = new DataTable(jadwalModel);
        jadwalTable.setStatusColumn(4);
        scrJadwal.setViewportView(jadwalTable);
        scrJadwal.getViewport().setBackground(Color.WHITE);

        DataTable mitraTable = new DataTable(mitraModel);
        scrMitra.setViewportView(mitraTable);
        scrMitra.getViewport().setBackground(Color.WHITE);

        scrBody.getViewport().setOpaque(false);
        scrBody.getVerticalScrollBar().setUnitIncrement(16);
    }

    @Override
    public void refresh() {
        DashboardController.Stats s = controller.ringkasan();
        lblNilaiKonten.setText(String.valueOf(s.konten));
        lblNilaiProgram.setText(String.valueOf(s.program));
        lblNilaiJadwal.setText(String.valueOf(s.jadwal));
        lblNilaiKerjaSama.setText(String.valueOf(s.kerjaSama));
        List<JadwalTayang> jadwal = controller.jadwalTerbaru(5);
        jadwalModel.setRows(jadwal);
        mitraModel.setRows(controller.kerjaSamaPerMitra(6));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblH1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblSub = new javax.swing.JLabel();
        scrBody = new javax.swing.JScrollPane();
        pnlBody = new javax.swing.JPanel();
        pnlStats = new javax.swing.JPanel();
        cardKonten = new view.component.RoundedPanel();
        lblKonten = new javax.swing.JLabel();
        lblNilaiKonten = new javax.swing.JLabel();
        cardProgram = new view.component.RoundedPanel();
        lblProgram = new javax.swing.JLabel();
        lblNilaiProgram = new javax.swing.JLabel();
        cardJadwalStat = new view.component.RoundedPanel();
        lblJadwalStat = new javax.swing.JLabel();
        lblNilaiJadwal = new javax.swing.JLabel();
        cardKerjaSama = new view.component.RoundedPanel();
        lblKerjaSama = new javax.swing.JLabel();
        lblNilaiKerjaSama = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18));
        cardJadwal = new view.component.RoundedPanel();
        pnlJadwalHead = new javax.swing.JPanel();
        lblJadwalJudul = new javax.swing.JLabel();
        scrJadwal = new javax.swing.JScrollPane();
        tblJadwal = new javax.swing.JTable();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18));
        cardMitra = new view.component.RoundedPanel();
        pnlMitraHead = new javax.swing.JPanel();
        lblMitraJudul = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4), new java.awt.Dimension(0, 4));
        lblMitraCatatan = new javax.swing.JLabel();
        scrMitra = new javax.swing.JScrollPane();
        tblMitra = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new javax.swing.BoxLayout(pnlHead, javax.swing.BoxLayout.Y_AXIS));

        lblH1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblH1.setForeground(new java.awt.Color(15, 23, 42));
        lblH1.setText("Selamat datang");
        pnlHead.add(lblH1);
        pnlHead.add(filler1);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Anda mas sebagai pengguna. Ringkasan pengelolaan konten dan jadwal tayang.");
        pnlHead.add(lblSub);

        add(pnlHead, java.awt.BorderLayout.NORTH);

        scrBody.setBorder(null);
        scrBody.setOpaque(false);

        pnlBody.setOpaque(false);
        pnlBody.setLayout(new javax.swing.BoxLayout(pnlBody, javax.swing.BoxLayout.Y_AXIS));

        pnlStats.setAlignmentX(0.0F);
        pnlStats.setOpaque(false);
        pnlStats.setLayout(new java.awt.GridLayout(1, 4, 15, 0));

        cardKonten.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardKonten.setLayout(new java.awt.BorderLayout());

        lblKonten.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblKonten.setForeground(new java.awt.Color(100, 116, 139));
        lblKonten.setText("Total Konten");
        cardKonten.add(lblKonten, java.awt.BorderLayout.NORTH);

        lblNilaiKonten.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiKonten.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiKonten.setText("0");
        cardKonten.add(lblNilaiKonten, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardKonten);

        cardProgram.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardProgram.setLayout(new java.awt.BorderLayout());

        lblProgram.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblProgram.setForeground(new java.awt.Color(100, 116, 139));
        lblProgram.setText("Program TV");
        cardProgram.add(lblProgram, java.awt.BorderLayout.NORTH);

        lblNilaiProgram.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiProgram.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiProgram.setText("0");
        cardProgram.add(lblNilaiProgram, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardProgram);

        cardJadwalStat.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardJadwalStat.setLayout(new java.awt.BorderLayout());

        lblJadwalStat.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblJadwalStat.setForeground(new java.awt.Color(100, 116, 139));
        lblJadwalStat.setText("Jadwal Tayang");
        cardJadwalStat.add(lblJadwalStat, java.awt.BorderLayout.NORTH);

        lblNilaiJadwal.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiJadwal.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiJadwal.setText("0");
        cardJadwalStat.add(lblNilaiJadwal, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardJadwalStat);

        cardKerjaSama.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 20, 18, 20));
        cardKerjaSama.setLayout(new java.awt.BorderLayout());

        lblKerjaSama.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblKerjaSama.setForeground(new java.awt.Color(100, 116, 139));
        lblKerjaSama.setText("Kerja Sama");
        cardKerjaSama.add(lblKerjaSama, java.awt.BorderLayout.NORTH);

        lblNilaiKerjaSama.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblNilaiKerjaSama.setForeground(new java.awt.Color(15, 23, 42));
        lblNilaiKerjaSama.setText("0");
        cardKerjaSama.add(lblNilaiKerjaSama, java.awt.BorderLayout.CENTER);

        pnlStats.add(cardKerjaSama);

        pnlBody.add(pnlStats);
        pnlBody.add(filler2);

        cardJadwal.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        cardJadwal.setAlignmentX(0.0F);
        cardJadwal.setLayout(new java.awt.BorderLayout());

        pnlJadwalHead.setOpaque(false);
        pnlJadwalHead.setLayout(new javax.swing.BoxLayout(pnlJadwalHead, javax.swing.BoxLayout.Y_AXIS));

        lblJadwalJudul.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblJadwalJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblJadwalJudul.setText("Jadwal Tayang Terbaru");
        pnlJadwalHead.add(lblJadwalJudul);

        cardJadwal.add(pnlJadwalHead, java.awt.BorderLayout.NORTH);

        scrJadwal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        scrJadwal.setViewportView(tblJadwal);

        cardJadwal.add(scrJadwal, java.awt.BorderLayout.CENTER);

        pnlBody.add(cardJadwal);
        pnlBody.add(filler3);

        cardMitra.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        cardMitra.setAlignmentX(0.0F);
        cardMitra.setLayout(new java.awt.BorderLayout());

        pnlMitraHead.setOpaque(false);
        pnlMitraHead.setLayout(new javax.swing.BoxLayout(pnlMitraHead, javax.swing.BoxLayout.Y_AXIS));

        lblMitraJudul.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblMitraJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblMitraJudul.setText("Relasi Mitra dan Kerja Sama");
        pnlMitraHead.add(lblMitraJudul);
        pnlMitraHead.add(filler4);

        lblMitraCatatan.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMitraCatatan.setForeground(new java.awt.Color(100, 116, 139));
        lblMitraCatatan.setText("Setiap kerja sama mengacu pada satu mitra terdaftar.");
        pnlMitraHead.add(lblMitraCatatan);

        cardMitra.add(pnlMitraHead, java.awt.BorderLayout.NORTH);

        scrMitra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)));
        scrMitra.setViewportView(tblMitra);

        cardMitra.add(scrMitra, java.awt.BorderLayout.CENTER);

        pnlBody.add(cardMitra);

        scrBody.setViewportView(pnlBody);

        add(scrBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.RoundedPanel cardJadwal;
    private view.component.RoundedPanel cardJadwalStat;
    private view.component.RoundedPanel cardKerjaSama;
    private view.component.RoundedPanel cardKonten;
    private view.component.RoundedPanel cardMitra;
    private view.component.RoundedPanel cardProgram;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JLabel lblH1;
    private javax.swing.JLabel lblJadwalJudul;
    private javax.swing.JLabel lblJadwalStat;
    private javax.swing.JLabel lblKerjaSama;
    private javax.swing.JLabel lblKonten;
    private javax.swing.JLabel lblMitraCatatan;
    private javax.swing.JLabel lblMitraJudul;
    private javax.swing.JLabel lblNilaiJadwal;
    private javax.swing.JLabel lblNilaiKerjaSama;
    private javax.swing.JLabel lblNilaiKonten;
    private javax.swing.JLabel lblNilaiProgram;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPanel pnlJadwalHead;
    private javax.swing.JPanel pnlMitraHead;
    private javax.swing.JPanel pnlStats;
    private javax.swing.JScrollPane scrBody;
    private javax.swing.JScrollPane scrJadwal;
    private javax.swing.JScrollPane scrMitra;
    private javax.swing.JTable tblJadwal;
    private javax.swing.JTable tblMitra;
    // End of variables declaration//GEN-END:variables
}
