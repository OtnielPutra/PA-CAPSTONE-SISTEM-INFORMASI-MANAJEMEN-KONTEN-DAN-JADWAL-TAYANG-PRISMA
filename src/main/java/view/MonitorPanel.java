package view;

import view.component.Refreshable;

import javax.swing.*;
import java.awt.*;

/**
 * Monitor Data: menampilkan seluruh data utama secara read-only dalam tab.
 *
 * CATATAN PENTING:
 * File ini adalah Java Form NetBeans (GUI Builder / Matisse) dan berpasangan
 * dengan file MonitorPanel.form - buka file ini lalu klik tab "Design" untuk
 * mengatur header dan tab secara drag-and-drop. Jangan mengedit kode di antara
 * tanda //GEN-BEGIN dan //GEN-END.
 *
 * Kerangka (header dan JTabbedPane dengan 5 tab) dikelola GUI Builder. Isi
 * tiap tab (halaman read-only dari PanelFactory) dipasang saat runtime.
 */
public class MonitorPanel extends JPanel implements Refreshable {
    private static final long serialVersionUID = 1L;

    private final JPanel[] pages;

    public MonitorPanel() {
        initComponents();
        pages = new JPanel[] {new ProgramTvPanel(false), new KontenPanel(false), new JadwalPanel(false),
                new KerjaSamaPanel(false), new MitraPanel(false)};
        JPanel[] wraps = {wrapProgram, wrapKonten, wrapJadwal, wrapKerjaSama, wrapMitra};
        for (int i = 0; i < pages.length; i++) {
            wraps[i].add(pages[i], BorderLayout.CENTER);
        }
    }

    @Override
    public void refresh() {
        for (JPanel p : pages) {
            ((Refreshable) p).refresh();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6));
        lblSub = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        wrapProgram = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        wrapKonten = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        wrapJadwal = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        wrapKerjaSama = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        wrapMitra = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new javax.swing.BoxLayout(pnlHead, javax.swing.BoxLayout.Y_AXIS));

        lblJudul.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 23, 42));
        lblJudul.setText("Monitor Data");
        pnlHead.add(lblJudul);
        pnlHead.add(filler1);

        lblSub.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Pantau seluruh data utama sistem");
        pnlHead.add(lblSub);

        add(pnlHead, java.awt.BorderLayout.NORTH);

        tabs.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N

        wrapProgram.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapProgram.setOpaque(false);
        wrapProgram.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        wrapProgram.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tabs.addTab("Program TV", wrapProgram);

        wrapKonten.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapKonten.setOpaque(false);
        wrapKonten.setLayout(new java.awt.BorderLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        wrapKonten.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        tabs.addTab("Konten", wrapKonten);

        wrapJadwal.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapJadwal.setOpaque(false);
        wrapJadwal.setLayout(new java.awt.BorderLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        wrapJadwal.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        tabs.addTab("Jadwal Tayang", wrapJadwal);

        wrapKerjaSama.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapKerjaSama.setOpaque(false);
        wrapKerjaSama.setLayout(new java.awt.BorderLayout());

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        wrapKerjaSama.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        tabs.addTab("Kerja Sama", wrapKerjaSama);

        wrapMitra.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 0, 0));
        wrapMitra.setOpaque(false);
        wrapMitra.setLayout(new java.awt.BorderLayout());

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        wrapMitra.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        tabs.addTab("Mitra", wrapMitra);

        add(tabs, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JPanel wrapJadwal;
    private javax.swing.JPanel wrapKerjaSama;
    private javax.swing.JPanel wrapKonten;
    private javax.swing.JPanel wrapMitra;
    private javax.swing.JPanel wrapProgram;
    // End of variables declaration//GEN-END:variables
}
