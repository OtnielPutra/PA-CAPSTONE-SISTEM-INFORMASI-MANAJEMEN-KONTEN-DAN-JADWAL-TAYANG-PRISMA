package view;

import model.Pengguna;

/**
 * Kontrak panel halaman utama PRISMA.
 *
 * Sejak kerangka "mainframe" (menu sidebar, topbar, footer) dipindahkan ke
 * dalam masing-masing panel halaman, setiap panel membawa komponennya
 * sendiri. Interface ini dipakai MainFrame untuk mengatur panel yang baru
 * dibuka tanpa mengenal class panelnya satu per satu.
 */
public interface HalamanUtama {

    /** Menyembunyikan tombol menu yang tidak boleh diakses peran pengguna. */
    void terapkanHakAkses(String peran);

    /** Menampilkan inisial, nama, dan peran pengguna yang login pada topbar panel. */
    void pasangIdentitas(Pengguna pengguna);
}
