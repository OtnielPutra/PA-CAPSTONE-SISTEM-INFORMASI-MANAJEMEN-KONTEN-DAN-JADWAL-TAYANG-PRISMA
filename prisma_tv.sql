-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3308
-- Generation Time: Oct 31, 2026 at 12:49 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prisma`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_pengguna` int NOT NULL,
  `tanggal_penugasan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_pengguna`, `tanggal_penugasan`) VALUES
(1, '2025-01-06');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_tayang`
--

CREATE TABLE `jadwal_tayang` (
  `id_jadwal` int NOT NULL,
  `tanggal_tayang` date NOT NULL,
  `waktu_mulai` time NOT NULL,
  `waktu_selesai` time NOT NULL,
  `status_jadwal` enum('Terjadwal','Selesai','Dibatalkan') NOT NULL DEFAULT 'Terjadwal',
  `petugas_jadwal_id_pengguna` int NOT NULL,
  `program_tv_id_program` int NOT NULL,
  `kanal_id_kanal` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `jadwal_tayang`
--

INSERT INTO `jadwal_tayang` (`id_jadwal`, `tanggal_tayang`, `waktu_mulai`, `waktu_selesai`, `status_jadwal`, `petugas_jadwal_id_pengguna`, `program_tv_id_program`, `kanal_id_kanal`) VALUES
(1, '2026-11-03', '06:00:00', '06:45:00', 'Terjadwal', 2, 1, 1),
(2, '2026-11-03', '06:45:00', '07:00:00', 'Dibatalkan', 2, 5, 1),
(3, '2026-11-03', '07:00:00', '08:00:00', 'Terjadwal', 2, 1, 1),
(4, '2026-11-03', '08:00:00', '08:30:00', 'Terjadwal', 2, 2, 1),
(5, '2026-11-03', '08:30:00', '09:00:00', 'Terjadwal', 2, 13, 1),
(6, '2026-11-03', '09:00:00', '10:00:00', 'Terjadwal', 3, 11, 1),
(7, '2026-11-03', '10:00:00', '10:30:00', 'Terjadwal', 2, 22, 1),
(8, '2026-11-03', '10:30:00', '11:30:00', 'Terjadwal', 3, 8, 1),
(9, '2026-11-03', '11:30:00', '12:00:00', 'Terjadwal', 2, 9, 1),
(10, '2026-11-03', '12:00:00', '12:30:00', 'Terjadwal', 2, 3, 1),
(11, '2026-11-03', '12:30:00', '13:00:00', 'Terjadwal', 3, 6, 1),
(12, '2026-11-03', '13:00:00', '14:00:00', 'Terjadwal', 3, 7, 1),
(13, '2026-11-03', '14:00:00', '14:30:00', 'Terjadwal', 2, 23, 1),
(14, '2026-11-03', '14:30:00', '15:00:00', 'Terjadwal', 3, 21, 1),
(15, '2026-11-03', '15:00:00', '16:00:00', 'Terjadwal', 2, 18, 1),
(16, '2026-11-03', '16:00:00', '16:30:00', 'Terjadwal', 3, 19, 1),
(17, '2026-11-03', '16:30:00', '17:30:00', 'Terjadwal', 2, 17, 1),
(18, '2026-11-03', '17:30:00', '18:00:00', 'Terjadwal', 3, 12, 1),
(19, '2026-11-03', '18:00:00', '19:00:00', 'Terjadwal', 3, 4, 1),
(20, '2026-11-03', '19:00:00', '19:30:00', 'Terjadwal', 2, 6, 1),
(21, '2026-11-03', '19:30:00', '20:30:00', 'Terjadwal', 3, 24, 1),
(22, '2026-11-03', '20:30:00', '21:30:00', 'Terjadwal', 2, 16, 1),
(23, '2026-11-03', '21:30:00', '22:00:00', 'Terjadwal', 3, 15, 1),
(24, '2026-11-03', '22:00:00', '23:00:00', 'Terjadwal', 3, 4, 1),
(25, '2026-11-03', '23:00:00', '23:30:00', 'Terjadwal', 2, 20, 1),
(26, '2026-11-03', '23:30:00', '23:59:59', 'Terjadwal', 3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `kanal`
--

CREATE TABLE `kanal` (
  `id_kanal` int NOT NULL,
  `nama_kanal` varchar(100) NOT NULL,
  `jenis_kanal` enum('Televisi','Website','YouTube','Instagram','TikTok') NOT NULL,
  `platform` varchar(50) NOT NULL,
  `status_kanal` enum('aktif','nonaktif') NOT NULL DEFAULT 'aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kanal`
--

INSERT INTO `kanal` (`id_kanal`, `nama_kanal`, `jenis_kanal`, `platform`, `status_kanal`) VALUES
(1, 'Prisma TV', 'Televisi', 'Siaran televisi', 'aktif'),
(2, 'Prisma News', 'Website', 'Website berita', 'aktif'),
(3, 'Prisma YouTube', 'YouTube', 'YouTube', 'aktif'),
(4, 'Prisma Instagram', 'Instagram', 'Instagram', 'aktif'),
(5, 'Prisma TikTok', 'TikTok', 'TikTok', 'aktif');

-- --------------------------------------------------------

--
-- Table structure for table `kerja_sama`
--

CREATE TABLE `kerja_sama` (
  `id_kerja_sama` int NOT NULL,
  `judul_kerja_sama` varchar(150) NOT NULL,
  `jenis_kerja_sama` varchar(100) NOT NULL,
  `tanggal_mulai` date NOT NULL,
  `tanggal_akhir` date NOT NULL,
  `status_kerja_sama` enum('Direncanakan','Berjalan','Selesai','Dibatalkan') NOT NULL,
  `nilai_kontrak` decimal(15,2) NOT NULL,
  `admin_id_pengguna` int NOT NULL,
  `program_tv_id_program` int DEFAULT NULL,
  `konten_id_konten` int DEFAULT NULL,
  `mitra_id_mitra` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kerja_sama`
--

INSERT INTO `kerja_sama` (`id_kerja_sama`, `judul_kerja_sama`, `jenis_kerja_sama`, `tanggal_mulai`, `tanggal_akhir`, `status_kerja_sama`, `nilai_kontrak`, `admin_id_pengguna`, `program_tv_id_program`, `konten_id_konten`, `mitra_id_mitra`) VALUES
(1, 'Produksi Liputan Budaya Daerah', 'Produksi konten', '2026-01-10', '2026-06-30', 'Selesai', '25000000.00', 1, 11, 4, 1),
(2, 'Pembuatan Video Profil UMKM', 'Produksi video', '2026-02-01', '2026-12-31', 'Berjalan', '18000000.00', 1, 22, 23, 2),
(3, 'Pemeliharaan Sistem Penyiaran', 'Dukungan teknologi', '2026-01-15', '2026-12-31', 'Berjalan', '45000000.00', 1, 1, NULL, 3),
(4, 'Pelatihan Jurnalisme Daerah', 'Pelatihan', '2026-03-01', '2026-08-31', 'Selesai', '8000000.00', 1, 5, 1, 4),
(5, 'Kampanye Literasi Digital', 'Kampanye edukasi', '2026-04-01', '2026-11-30', 'Berjalan', '12000000.00', 1, 21, 6, 5),
(6, 'Pengembangan Sistem Arsip Digital', 'Layanan teknologi', '2026-02-15', '2026-12-15', 'Berjalan', '30000000.00', 1, 13, 13, 6),
(7, 'Dokumentasi Festival Budaya', 'Dokumentasi', '2026-05-01', '2026-07-31', 'Selesai', '10000000.00', 1, 11, 4, 7),
(8, 'Penyediaan Peralatan Audio', 'Pengadaan peralatan', '2026-01-20', '2026-12-20', 'Berjalan', '22000000.00', 1, 7, NULL, 8),
(9, 'Produksi Infografik Layanan Publik', 'Desain konten', '2026-06-01', '2026-12-01', 'Berjalan', '7000000.00', 1, 2, 13, 9),
(10, 'Publikasi UMKM Lokal', 'Promosi dan publikasi', '2026-03-10', '2026-09-10', 'Selesai', '9000000.00', 1, 22, 23, 10),
(11, 'Penyimpanan Arsip Media', 'Layanan cloud', '2026-01-05', '2026-12-31', 'Berjalan', '16000000.00', 1, NULL, NULL, 11),
(12, 'Liputan Program Lingkungan', 'Produksi berita', '2026-04-15', '2026-10-15', 'Selesai', '6000000.00', 1, 11, 9, 12),
(13, 'Dokumentasi Kegiatan Komunitas', 'Dokumentasi', '2026-07-01', '2026-12-01', 'Berjalan', '5000000.00', 1, 23, 25, 13),
(14, 'Program Magang Jurnalistik', 'Pendidikan', '2026-08-01', '2026-12-31', 'Berjalan', '4000000.00', 1, 23, 25, 14),
(15, 'Distribusi Video Digital', 'Distribusi konten', '2026-09-01', '2026-12-31', 'Berjalan', '15000000.00', 1, 20, 19, 15),
(16, 'Produksi Serial Animasi Anak', 'Produksi animasi', '2026-05-01', '2026-12-31', 'Berjalan', '28000000.00', 1, 18, 16, 16),
(17, 'Kampanye Kreator Digital', 'Kampanye media sosial', '2026-06-15', '2026-12-15', 'Berjalan', '14000000.00', 1, 21, 19, 17),
(18, 'Paket Iklan Program Kuliner', 'Periklanan', '2026-07-01', '2026-10-31', 'Selesai', '11000000.00', 1, 8, 8, 18),
(19, 'Produksi Drama Mini', 'Produksi drama', '2026-04-01', '2026-12-31', 'Berjalan', '32000000.00', 1, 17, 18, 19),
(20, 'Sewa Studio dan Peralatan', 'Fasilitas produksi', '2026-01-01', '2026-12-31', 'Berjalan', '20000000.00', 1, 16, 18, 20);

-- --------------------------------------------------------

--
-- Table structure for table `konten`
--

CREATE TABLE `konten` (
  `id_konten` int NOT NULL,
  `judul_konten` varchar(150) NOT NULL,
  `jenis_konten` enum('Berita','Artikel','Video','Infografik','Wawancara') NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `ringkasan_konten` varchar(255) NOT NULL,
  `tanggal_pencatatan` date NOT NULL,
  `status_konten` enum('Dalam Proses','Siap Tayang','Sudah Tayang','Diarsipkan') NOT NULL DEFAULT 'Dalam Proses',
  `program_tv_id_program` int DEFAULT NULL,
  `petugas_konten_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `konten`
--

INSERT INTO `konten` (`id_konten`, `judul_konten`, `jenis_konten`, `kategori`, `ringkasan_konten`, `tanggal_pencatatan`, `status_konten`, `program_tv_id_program`, `petugas_konten_id_pengguna`) VALUES
(1, 'Pemkot Paparkan Prioritas Perbaikan Jalan', 'Berita', 'Daerah', 'Liputan ini merangkum rencana perbaikan beberapa ruas jalan, tahapan pekerjaan, perkiraan dampak terhadap arus kendaraan, dan imbauan bagi warga yang melintas.', '2026-10-01', 'Sudah Tayang', 5, 4),
(2, 'Cek Fakta: Benarkah Informasi Bantuan Ini Resmi?', 'Video', 'Cek Fakta', 'Tim memeriksa sumber pengumuman bantuan yang ramai dibagikan, membandingkannya dengan kanal resmi, serta menjelaskan ciri tautan palsu yang perlu diwaspadai.', '2026-10-02', 'Siap Tayang', 2, 5),
(3, 'Harga Pangan di Pasar Tradisional Pekan Ini', 'Berita', 'Ekonomi', 'Reporter membandingkan harga beras, cabai, telur, minyak goreng, dan kebutuhan pokok lain dari beberapa pedagang untuk memberikan gambaran perubahan harga kepada masyarakat.', '2026-10-03', 'Siap Tayang', 3, 6),
(4, 'Cerita Pengrajin Samarinda Menjaga Motif Tradisional', 'Video', 'Budaya', 'Profil pengrajin lokal yang mempertahankan motif tradisional melalui proses desain, pemilihan bahan, produksi, hingga pemasaran kepada pembeli muda.', '2026-10-04', 'Sudah Tayang', 11, 7),
(5, 'Atlet Muda Daerah Bersiap ke Kejuaraan Nasional', 'Berita', 'Olahraga', 'Liputan latihan, persiapan mental, dukungan pelatih, dan target atlet daerah menjelang kejuaraan nasional.', '2026-10-05', 'Siap Tayang', 12, 8),
(6, 'Lima Tips Aman Bertransaksi di Marketplace', 'Artikel', 'Teknologi', 'Artikel membahas pengecekan reputasi penjual, penggunaan pembayaran resmi, penyimpanan bukti transaksi, serta langkah yang dapat dilakukan ketika menemukan indikasi penipuan.', '2026-10-06', 'Dalam Proses', 13, 4),
(7, 'Resep Sarapan Praktis dengan Bahan Rumahan', 'Video', 'Kuliner', 'Video resep sarapan sederhana yang menunjukkan takaran bahan, urutan memasak, alternatif bahan, dan tips penyajian agar mudah diikuti penonton.', '2026-10-07', 'Sudah Tayang', 9, 5),
(8, 'Jajanan Lokal yang Ramai Diburu Anak Muda', 'Video', 'Kuliner', 'Liputan mencoba beberapa jajanan lokal, menampilkan kisaran harga, tekstur, rasa, lokasi penjual, dan cerita singkat pemilik usaha.', '2026-10-08', 'Sudah Tayang', 8, 6),
(9, 'Mengenal Satwa Endemik Kalimantan', 'Video', 'Lingkungan', 'Konten edukasi memperkenalkan satwa endemik Kalimantan, habitatnya, ancaman terhadap kelestarian, dan kebiasaan yang dapat membantu menjaga lingkungan.', '2026-10-09', 'Sudah Tayang', 11, 7),
(10, 'Dialog: Bagaimana Anak Muda Memulai Usaha?', 'Wawancara', 'UMKM', 'Diskusi dengan pelaku usaha muda tentang modal awal, riset pasar, pencatatan keuangan sederhana, promosi digital, dan kesalahan yang dapat dipelajari pemula.', '2026-10-10', 'Siap Tayang', 22, 8),
(11, 'Tren Video Pendek: Kreatif tanpa Menyebarkan Hoaks', 'Video', 'Media Sosial', 'Konten membahas tren video pendek dan mengajak kreator memeriksa fakta, mencantumkan sumber, serta menghindari judul yang menyesatkan.', '2026-10-11', 'Siap Tayang', 21, 4),
(12, 'Reels: Proses di Balik Pembuatan Berita', 'Video', 'Behind the Scenes', 'Video vertikal memperlihatkan tahapan peliputan dari perencanaan, wawancara, pemeriksaan fakta, penyuntingan, sampai materi siap dipublikasikan.', '2026-10-12', 'Sudah Tayang', 20, 5),
(13, 'Infografik: Cara Mengenali Tautan Phishing', 'Infografik', 'Keamanan Digital', 'Infografik menjelaskan tanda tautan mencurigakan, permintaan data pribadi yang tidak wajar, alamat situs tiruan, dan langkah aman sebelum mengeklik.', '2026-10-13', 'Sudah Tayang', 13, 6),
(14, 'Wawancara Musisi Lokal tentang Karya Terbaru', 'Wawancara', 'Musik', 'Percakapan membahas inspirasi lagu, proses rekaman, tantangan berkarya secara mandiri, dan cara musisi memperkenalkan karyanya kepada pendengar.', '2026-10-14', 'Siap Tayang', 15, 7),
(15, 'Jelajah Pasar: Cerita Pedagang dari Pagi Hari', 'Video', 'Feature', 'Liputan mengikuti persiapan pedagang, pemilihan barang, interaksi dengan pelanggan, dan perubahan aktivitas pasar dari pagi hingga siang.', '2026-10-15', 'Dalam Proses', 22, 8),
(16, 'Kartun Kiko: Belajar Berbagi dan Bekerja Sama', 'Video', 'Anak', 'Episode animasi fiktif tentang Kiko dan teman-temannya yang menyelesaikan masalah dengan berbagi tugas, mendengarkan pendapat, dan meminta maaf.', '2026-10-16', 'Sudah Tayang', 18, 4),
(17, 'Sahabat Rimba: Mengapa Hutan Perlu Dijaga?', 'Video', 'Anak', 'Animasi edukasi memperkenalkan fungsi hutan bagi satwa dan manusia serta kebiasaan sederhana untuk mengurangi sampah dan menjaga alam.', '2026-10-17', 'Sudah Tayang', 19, 5),
(18, 'Drama Kisah Senja: Pilihan yang Tidak Mudah', 'Video', 'Drama', 'Ringkasan episode drama fiktif mengenai keluarga yang menghadapi perbedaan pendapat dan belajar menyelesaikan masalah melalui komunikasi.', '2026-10-18', 'Dalam Proses', 17, 6),
(19, 'Prisma Viral: Tantangan Kreatif yang Aman', 'Video', 'Hiburan Digital', 'Video mengulas tantangan kreatif yang tidak berbahaya, menjelaskan aturan keselamatan, dan mengajak penonton membuat versi orisinal tanpa meniru aksi berisiko.', '2026-10-19', 'Siap Tayang', 20, 7),
(20, 'TikTok 60 Detik: Fakta Unik tentang Kota Samarinda', 'Video', 'Media Sosial', 'Video vertikal berisi fakta singkat mengenai tempat, budaya, atau kebiasaan lokal yang telah diperiksa oleh tim redaksi sebelum diunggah.', '2026-10-20', 'Sudah Tayang', 21, 8),
(21, 'Instagram Carousel: Panduan Memilah Sampah', 'Infografik', 'Lingkungan', 'Carousel membedakan sampah organik, anorganik, dan residu disertai contoh sehari-hari serta tips memilah sampah di rumah.', '2026-10-21', 'Sudah Tayang', 19, 4),
(22, 'Shorts: Tiga Hal Menarik dari Liputan Hari Ini', 'Video', 'Berita Digital', 'Video ringkas merangkum tiga informasi utama dari liputan redaksi, dengan konteks singkat dan rujukan ke berita lengkap.', '2026-10-22', 'Sudah Tayang', 21, 5),
(23, 'Cerita UMKM: Dari Dapur Rumah ke Pesanan Online', 'Wawancara', 'UMKM', 'Profil usaha makanan rumahan yang menjelaskan proses produksi, pengemasan, layanan pesan antar, dan strategi menjaga kualitas pesanan.', '2026-10-23', 'Siap Tayang', 22, 6),
(24, 'Fakta atau Mitos: Kebiasaan Sehat Sehari-hari', 'Video', 'Kesehatan', 'Narasumber menjelaskan beberapa klaim kesehatan populer dan membedakan informasi umum yang didukung sumber tepercaya dari mitos yang perlu diluruskan.', '2026-10-24', 'Sudah Tayang', 14, 7),
(25, 'Bincang Kampus: Mahasiswa dan Literasi Digital', 'Wawancara', 'Pendidikan', 'Diskusi mahasiswa mengenai cara mengevaluasi sumber, menjaga privasi, menghindari plagiarisme, dan menggunakan teknologi secara bertanggung jawab.', '2026-10-25', 'Siap Tayang', 23, 8),
(26, 'Behind the Scenes: Membuat Infografik Berita', 'Video', 'Produksi Konten', 'Tim desain menunjukkan proses memilih data, menyederhanakan informasi, memeriksa angka, dan menata visual agar mudah dibaca.', '2026-10-26', 'Sudah Tayang', 20, 4),
(27, 'Lensa Daerah: Ruang Publik dan Aktivitas Warga', 'Berita', 'Daerah', 'Liputan menyoroti penggunaan ruang publik, fasilitas yang tersedia, masukan warga, dan hal yang dapat ditingkatkan oleh pengelola.', '2026-10-27', 'Dalam Proses', 5, 5),
(28, 'Kuliner Kilat: Bekal Hemat untuk Mahasiswa', 'Video', 'Kuliner', 'Video resep bekal dengan bahan terjangkau, waktu persiapan singkat, dan saran penyimpanan yang aman untuk dibawa ke kampus.', '2026-10-28', 'Sudah Tayang', 9, 6),
(29, 'Cerita Kreator: Menjaga Konsistensi Membuat Konten', 'Wawancara', 'Kreator Digital', 'Wawancara membahas proses mencari ide, menyusun kalender unggahan, mengukur respons audiens, dan menjaga orisinalitas karya.', '2026-10-29', 'Siap Tayang', 21, 7),
(30, 'Rangkuman Pekan Ini: Berita, Budaya, dan Teknologi', 'Video', 'Nasional', 'Program merangkum isu penting selama sepekan, meliputi berita daerah, perkembangan teknologi, kegiatan budaya, dan cerita inspiratif masyarakat.', '2026-10-30', 'Sudah Tayang', 4, 8);

-- --------------------------------------------------------

--
-- Table structure for table `lampiran_konten`
--

CREATE TABLE `lampiran_konten` (
  `id_lampiran` int NOT NULL,
  `nama_file` varchar(255) NOT NULL,
  `lokasi_file` varchar(255) NOT NULL,
  `tanggal_unggah` datetime NOT NULL,
  `konten_id_konten` int NOT NULL,
  `petugas_konten_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lampiran_konten`
--

INSERT INTO `lampiran_konten` (`id_lampiran`, `nama_file`, `lokasi_file`, `tanggal_unggah`, `konten_id_konten`, `petugas_konten_id_pengguna`) VALUES
(1, 'pemkot_paparkan_prioritas_perbaikan_jalan.jpg', 'uploads/pemkot_paparkan_prioritas_perbaikan_jalan.jpg', '2026-10-01 10:00:00', 1, 4),
(2, 'cek_fakta_benarkah_informasi_bantuan_ini_resmi.mp4', 'uploads/cek_fakta_benarkah_informasi_bantuan_ini_resmi.mp4', '2026-10-02 10:00:00', 2, 5),
(3, 'harga_pangan_di_pasar_tradisional_pekan_ini.jpg', 'uploads/harga_pangan_di_pasar_tradisional_pekan_ini.jpg', '2026-10-03 10:00:00', 3, 6),
(4, 'cerita_pengrajin_samarinda_menjaga_motif_tradisional.mp4', 'uploads/cerita_pengrajin_samarinda_menjaga_motif_tradisional.mp4', '2026-10-04 10:00:00', 4, 7),
(5, 'atlet_muda_daerah_bersiap_ke_kejuaraan_nasional.jpg', 'uploads/atlet_muda_daerah_bersiap_ke_kejuaraan_nasional.jpg', '2026-10-05 10:00:00', 5, 8),
(6, 'lima_tips_aman_bertransaksi_di_marketplace.pdf', 'uploads/lima_tips_aman_bertransaksi_di_marketplace.pdf', '2026-10-06 10:00:00', 6, 4),
(7, 'resep_sarapan_praktis_dengan_bahan_rumahan.mp4', 'uploads/resep_sarapan_praktis_dengan_bahan_rumahan.mp4', '2026-10-07 10:00:00', 7, 5),
(8, 'jajanan_lokal_yang_ramai_diburu_anak_muda.mp4', 'uploads/jajanan_lokal_yang_ramai_diburu_anak_muda.mp4', '2026-10-08 10:00:00', 8, 6),
(9, 'mengenal_satwa_endemik_kalimantan.mp4', 'uploads/mengenal_satwa_endemik_kalimantan.mp4', '2026-10-09 10:00:00', 9, 7),
(10, 'dialog_bagaimana_anak_muda_memulai_usaha.jpg', 'uploads/dialog_bagaimana_anak_muda_memulai_usaha.jpg', '2026-10-10 10:00:00', 10, 8),
(11, 'tren_video_pendek_kreatif_tanpa_menyebarkan_hoaks.mp4', 'uploads/tren_video_pendek_kreatif_tanpa_menyebarkan_hoaks.mp4', '2026-10-11 10:00:00', 11, 4),
(12, 'reels_proses_di_balik_pembuatan_berita.mp4', 'uploads/reels_proses_di_balik_pembuatan_berita.mp4', '2026-10-12 10:00:00', 12, 5),
(13, 'infografik_cara_mengenali_tautan_phishing.png', 'uploads/infografik_cara_mengenali_tautan_phishing.png', '2026-10-13 10:00:00', 13, 6),
(14, 'wawancara_musisi_lokal_tentang_karya_terbaru.jpg', 'uploads/wawancara_musisi_lokal_tentang_karya_terbaru.jpg', '2026-10-14 10:00:00', 14, 7),
(15, 'jelajah_pasar_cerita_pedagang_dari_pagi_hari.mp4', 'uploads/jelajah_pasar_cerita_pedagang_dari_pagi_hari.mp4', '2026-10-15 10:00:00', 15, 8),
(16, 'kartun_kiko_belajar_berbagi_dan_bekerja_sama.mp4', 'uploads/kartun_kiko_belajar_berbagi_dan_bekerja_sama.mp4', '2026-10-16 10:00:00', 16, 4),
(17, 'sahabat_rimba_mengapa_hutan_perlu_dijaga.mp4', 'uploads/sahabat_rimba_mengapa_hutan_perlu_dijaga.mp4', '2026-10-17 10:00:00', 17, 5),
(18, 'drama_kisah_senja_pilihan_yang_tidak_mudah.mp4', 'uploads/drama_kisah_senja_pilihan_yang_tidak_mudah.mp4', '2026-10-18 10:00:00', 18, 6),
(19, 'prisma_viral_tantangan_kreatif_yang_aman.mp4', 'uploads/prisma_viral_tantangan_kreatif_yang_aman.mp4', '2026-10-19 10:00:00', 19, 7),
(20, 'tiktok_60_detik_fakta_unik_tentang_kota_samarinda.mp4', 'uploads/tiktok_60_detik_fakta_unik_tentang_kota_samarinda.mp4', '2026-10-20 10:00:00', 20, 8),
(21, 'instagram_carousel_panduan_memilah_sampah.png', 'uploads/instagram_carousel_panduan_memilah_sampah.png', '2026-10-21 10:00:00', 21, 4),
(22, 'shorts_tiga_hal_menarik_dari_liputan_hari_ini.mp4', 'uploads/shorts_tiga_hal_menarik_dari_liputan_hari_ini.mp4', '2026-10-22 10:00:00', 22, 5),
(23, 'cerita_umkm_dari_dapur_rumah_ke_pesanan_online.jpg', 'uploads/cerita_umkm_dari_dapur_rumah_ke_pesanan_online.jpg', '2026-10-23 10:00:00', 23, 6),
(24, 'fakta_atau_mitos_kebiasaan_sehat_sehari_hari.mp4', 'uploads/fakta_atau_mitos_kebiasaan_sehat_sehari_hari.mp4', '2026-10-24 10:00:00', 24, 7),
(25, 'bincang_kampus_mahasiswa_dan_literasi_digital.jpg', 'uploads/bincang_kampus_mahasiswa_dan_literasi_digital.jpg', '2026-10-25 10:00:00', 25, 8),
(26, 'behind_the_scenes_membuat_infografik_berita.mp4', 'uploads/behind_the_scenes_membuat_infografik_berita.mp4', '2026-10-26 10:00:00', 26, 4),
(27, 'lensa_daerah_ruang_publik_dan_aktivitas_warga.jpg', 'uploads/lensa_daerah_ruang_publik_dan_aktivitas_warga.jpg', '2026-10-27 10:00:00', 27, 5),
(28, 'kuliner_kilat_bekal_hemat_untuk_mahasiswa.mp4', 'uploads/kuliner_kilat_bekal_hemat_untuk_mahasiswa.mp4', '2026-10-28 10:00:00', 28, 6),
(29, 'cerita_kreator_menjaga_konsistensi_membuat_konten.jpg', 'uploads/cerita_kreator_menjaga_konsistensi_membuat_konten.jpg', '2026-10-29 10:00:00', 29, 7),
(30, 'rangkuman_pekan_ini_berita_budaya_dan_teknologi.mp4', 'uploads/rangkuman_pekan_ini_berita_budaya_dan_teknologi.mp4', '2026-10-30 10:00:00', 30, 8);

-- --------------------------------------------------------

--
-- Table structure for table `mitra`
--

CREATE TABLE `mitra` (
  `id_mitra` int NOT NULL,
  `nama_mitra` varchar(100) NOT NULL,
  `jenis_mitra` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `status_mitra` enum('aktif','nonaktif') NOT NULL DEFAULT 'aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `mitra`
--

INSERT INTO `mitra` (`id_mitra`, `nama_mitra`, `jenis_mitra`, `email`, `alamat`, `status_mitra`) VALUES
(1, 'Intuisi Media', 'Media digital', 'rizky.pratama@intuisi-media.example.com', 'Jalan KH Wahid Hasyim, Samarinda, Kalimantan Timur 76119', 'aktif'),
(2, 'KLIK SAMARINDA', 'Media berita digital', 'andi.saputra@klik-samarinda.example.com', 'Jalan Latsitarda, Samarinda, Kalimantan Timur 75300', 'aktif'),
(3, 'Toedjoe Sinar Group', 'Percetakan digital', 'fajar.ramadhan@toedjoesinar.example.com', 'Jalan Wahid Hasyim I No. 40, Sempaja, Samarinda, Kalimantan Timur', 'aktif'),
(4, 'Print Center ID', 'Percetakan digital dan offset', 'nadia.putri@printcenterid.example.com', 'Jalan Pangeran Suryanata No. 138, Kelurahan Bukit Pinang, Samarinda, Kalimantan Timur 75131', 'aktif'),
(5, 'PT Visualku Meta Media', 'Percetakan dan media promosi', 'aditya.firmansyah@visualkumeta.example.com', 'Jalan Adam Malik 2 No. 05/57 RT 005, Sungai Kunjang, Samarinda, Kalimantan Timur 75126', 'aktif'),
(6, 'CV Restu Ibu Digital Printing', 'Percetakan dan media promosi', 'siti.rahmawati@restuibu.example.com', 'Jalan Pahlawan No. 16, Samarinda, Kalimantan Timur', 'aktif'),
(7, 'Five Media Samarinda', 'Percetakan dan produk kreatif', 'bagas.setiawan@fivemedia.example.com', 'Cabang Wahid Hasyim I, Sempaja Selatan, Samarinda, Kalimantan Timur', 'aktif'),
(8, 'AMPLANG BUMBU DELUGA', 'UMKM makanan khas Samarinda', 'nur.aisyah@gmail.com', 'Jalan Ulin Gang Delima RT 06, Kelurahan Karang Asam Ilir, Kecamatan Sungai Kunjang, Samarinda, Kalimantan Timur', 'aktif'),
(9, 'AMPLANG FARIDAH', 'UMKM makanan khas Samarinda', 'faridah@gmail.com', 'Jalan Kahoi Blok B7 RT 34, Kelurahan Karang Anyar, Kecamatan Sungai Kunjang, Samarinda, Kalimantan Timur', 'aktif'),
(10, 'AKYA COOKIES', 'UMKM kue dan makanan', 'akya.cookies@gmail.com', 'Perumahan Arisco RT 21, Kelurahan Sambutan, Samarinda, Kalimantan Timur', 'aktif'),
(11, 'ALKHA COOKIES', 'UMKM kue dan makanan', 'alkha.cookies@gmail.com', 'Jalan Slamet Riyadi Gang Rukun RT 19, Kelurahan Karang Asam Ilir, Samarinda, Kalimantan Timur', 'aktif'),
(12, 'AMPLANG BUMBU USAHA NAZWA', 'UMKM makanan khas Samarinda', 'nazwa@gmail.com', 'Jalan Ulin Gang 23 RT 06 No. 15, Kelurahan Karang Asam Ilir, Kecamatan Sungai Kunjang, Samarinda, Kalimantan Timur', 'aktif'),
(13, 'AMPLANG HJ. JAUYAH', 'UMKM makanan khas Samarinda', 'hj.jauyah@gmail.com', 'Jalan Cendana Gang 4 RT 06, Kelurahan Karang Anyar, Samarinda, Kalimantan Timur', 'aktif'),
(14, 'ANEKA CEMILAN ADEL', 'UMKM makanan ringan', 'adel.cemilan@gmail.com', 'Jalan Emboen Suryana Gang Mawar RT 29, Kelurahan Sambutan, Samarinda, Kalimantan Timur', 'aktif'),
(15, 'ANEKA KUE / CAKE', 'UMKM kue dan cake', 'anekakue.cake@gmail.com', 'Jalan Cendana Gang 8 No. 68 RT 08, Kelurahan Karang Anyar, Samarinda, Kalimantan Timur', 'aktif'),
(16, 'ANEKA KUE BASAH', 'UMKM kue tradisional', 'dewi.lestari@gmail.com', 'Jalan Ulin Gang 8 No. 26 RT 25, Kelurahan Karang Anyar, Samarinda, Kalimantan Timur', 'aktif'),
(17, 'ANEKA KUE BASAH BY JAMRAH', 'UMKM kue basah', 'jamrah@gmail.com', 'Jalan Tengkawang RT 15, Kelurahan Karang Anyar, Samarinda, Kalimantan Timur', 'aktif'),
(18, 'ANEKA KUE JINTAN', 'UMKM kue tradisional', 'maya.sari@gmail.com', 'Jalan Rimbawan 1 No. 118 RT 33, Kelurahan Karang Anyar, Samarinda, Kalimantan Timur', 'aktif'),
(19, 'ANEKA KUE KERING BY HANDRI PUTIKAH', 'UMKM kue kering', 'handri.putikah@gmail.com', 'Jalan Cendana Gang 6 RT 08, Kelurahan Karang Anyar, Samarinda, Kalimantan Timur', 'aktif'),
(20, 'FARRAH CATERING (Hj. Hilmah)', 'UMKM katering dan kue', 'hilmah@gmail.com', 'Jalan Mutiara Gang 03 No. 31 RT 07, Kelurahan Pasar Pagi, Kecamatan Samarinda Kota, Samarinda, Kalimantan Timur', 'aktif');

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL,
  `status_akun` enum('aktif','nonaktif') NOT NULL DEFAULT 'aktif'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_lengkap`, `username`, `password`, `status_akun`) VALUES
(1, 'Rina Pratama', 'admin', 'adminprisma1', 'aktif'),
(2, 'Dimas Saputra', 'petugasjadwal1', 'petugasjadwal1', 'aktif'),
(3, 'Nadia Putri', 'petugasjadwal2', 'petugasjadwal2', 'aktif'),
(4, 'Fajar Ramadhan', 'petugaskonten1', 'petugaskonten1', 'aktif'),
(5, 'Siti Maharani', 'petugaskonten2', 'petugaskonten2', 'aktif'),
(6, 'Arif Wibowo', 'petugaskonten3', 'petugaskonten3', 'aktif'),
(7, 'Maya Lestari', 'petugaskonten4', 'petugaskonten4', 'aktif'),
(8, 'Bima Kurniawan', 'petugaskonten5', 'petugaskonten5', 'aktif');

-- --------------------------------------------------------

--
-- Table structure for table `petugas_jadwal`
--

CREATE TABLE `petugas_jadwal` (
  `id_pengguna` int NOT NULL,
  `cakupan_jadwal` varchar(100) NOT NULL,
  `tanggal_penugasan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `petugas_jadwal`
--

INSERT INTO `petugas_jadwal` (`id_pengguna`, `cakupan_jadwal`, `tanggal_penugasan`) VALUES
(2, 'Penjadwalan program harian', '2025-02-03'),
(3, 'Koordinasi perubahan jadwal', '2025-03-10');

-- --------------------------------------------------------

--
-- Table structure for table `petugas_konten`
--

CREATE TABLE `petugas_konten` (
  `id_pengguna` int NOT NULL,
  `spesialisasi_konten` varchar(100) NOT NULL,
  `tanggal_penugasan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `petugas_konten`
--

INSERT INTO `petugas_konten` (`id_pengguna`, `spesialisasi_konten`, `tanggal_penugasan`) VALUES
(4, 'Berita daerah', '2025-02-03'),
(5, 'Berita nasional', '2025-02-10'),
(6, 'Ekonomi dan bisnis', '2025-03-10'),
(7, 'Olahraga dan hiburan', '2025-04-07'),
(8, 'Artikel dan konten digital', '2025-05-12');

-- --------------------------------------------------------

--
-- Table structure for table `program_tv`
--

CREATE TABLE `program_tv` (
  `id_program` int NOT NULL,
  `nama_program` varchar(100) NOT NULL,
  `kategori_program` enum('Berita','Berita Daerah','Infotainment','Talkshow','Kuliner','Dokumenter','Olahraga','Teknologi','Edukasi','Musik','Film','Drama','Kartun','Kartun Edukasi','Hiburan Digital','Feature') NOT NULL,
  `url_program` varchar(200) DEFAULT NULL,
  `status_program` enum('aktif','nonaktif') NOT NULL DEFAULT 'aktif',
  `admin_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `program_tv`
--

INSERT INTO `program_tv` (`id_program`, `nama_program`, `kategori_program`, `url_program`, `status_program`, `admin_id_pengguna`) VALUES
(1, 'Prisma Pagi', 'Berita', 'https://example.com/prisma-pagi', 'aktif', 1),
(2, 'Fakta 360', 'Berita', NULL, 'aktif', 1),
(3, 'Prisma Siang', 'Berita', NULL, 'aktif', 1),
(4, 'Dunia Hari Ini', 'Berita', NULL, 'aktif', 1),
(5, 'Lensa Samarinda', 'Berita Daerah', NULL, 'aktif', 1),
(6, 'Ruang Seleb', 'Infotainment', NULL, 'aktif', 1),
(7, 'Cerita Kita', 'Talkshow', NULL, 'aktif', 1),
(8, 'Laper Jalanan', 'Kuliner', NULL, 'aktif', 1),
(9, 'Dapur Prisma', 'Kuliner', NULL, 'aktif', 1),
(10, 'Resep Bintang', 'Kuliner', NULL, 'aktif', 1),
(11, 'Jelajah Nusantara', 'Dokumenter', NULL, 'aktif', 1),
(12, 'Arena Prisma', 'Olahraga', NULL, 'aktif', 1),
(13, 'Tekno Dekat', 'Teknologi', NULL, 'aktif', 1),
(14, 'Sehat Bersama', 'Edukasi', NULL, 'aktif', 1),
(15, 'Nada Akhir Pekan', 'Musik', NULL, 'aktif', 1),
(16, 'Prisma Sinema', 'Film', NULL, 'aktif', 1),
(17, 'Kisah Senja', 'Drama', NULL, 'aktif', 1),
(18, 'Petualangan Kiko', 'Kartun', NULL, 'aktif', 1),
(19, 'Sahabat Rimba', 'Kartun Edukasi', NULL, 'aktif', 1),
(20, 'Prisma Viral', 'Hiburan Digital', NULL, 'aktif', 1),
(21, 'Scroll Cerdas', 'Hiburan Digital', NULL, 'aktif', 1),
(22, 'Cerita UMKM', 'Feature', NULL, 'aktif', 1),
(23, 'Bincang Kampus', 'Talkshow', NULL, 'aktif', 1),
(24, 'Malam Prisma', 'Berita', NULL, 'aktif', 1);

-- --------------------------------------------------------

--
-- Table structure for table `publikasi_konten`
--

CREATE TABLE `publikasi_konten` (
  `id_publikasi` int NOT NULL,
  `tanggal_publikasi` datetime NOT NULL,
  `status_publikasi` enum('Direncanakan','Terbit','Dibatalkan') NOT NULL DEFAULT 'Direncanakan',
  `link_publikasi` varchar(255) DEFAULT NULL,
  `konten_id_konten` int NOT NULL,
  `kanal_id_kanal` int NOT NULL,
  `petugas_konten_id_pengguna` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `publikasi_konten`
--

INSERT INTO `publikasi_konten` (`id_publikasi`, `tanggal_publikasi`, `status_publikasi`, `link_publikasi`, `konten_id_konten`, `kanal_id_kanal`, `petugas_konten_id_pengguna`) VALUES
(1, '2026-10-01 15:00:00', 'Terbit', 'https://prisma.example/berita/konten-01', 1, 2, 4),
(2, '2026-10-02 15:00:00', 'Terbit', 'https://instagram.example/prisma/konten-02', 2, 4, 5),
(3, '2026-10-03 15:00:00', 'Direncanakan', NULL, 3, 3, 6),
(4, '2026-10-04 15:00:00', 'Terbit', 'https://prisma.example/artikel/konten-04', 4, 2, 7),
(5, '2026-10-05 15:00:00', 'Direncanakan', NULL, 5, 5, 8),
(6, '2026-10-06 15:00:00', 'Direncanakan', NULL, 6, 2, 4),
(7, '2026-10-07 15:00:00', 'Terbit', 'https://instagram.example/prisma/konten-07', 7, 4, 5),
(8, '2026-10-08 15:00:00', 'Terbit', 'https://youtube.example/prisma/konten-08', 8, 3, 6),
(9, '2026-10-09 15:00:00', 'Terbit', 'https://prisma.example/artikel/konten-09', 9, 2, 7),
(10, '2026-10-10 15:00:00', 'Direncanakan', NULL, 10, 5, 8),
(11, '2026-10-11 15:00:00', 'Direncanakan', NULL, 11, 2, 4),
(12, '2026-10-12 15:00:00', 'Terbit', 'https://instagram.example/prisma/konten-12', 12, 4, 5),
(13, '2026-10-13 15:00:00', 'Terbit', 'https://youtube.example/prisma/konten-13', 13, 3, 6),
(14, '2026-10-14 15:00:00', 'Direncanakan', NULL, 14, 2, 7),
(15, '2026-10-15 15:00:00', 'Direncanakan', NULL, 15, 5, 8),
(16, '2026-10-16 15:00:00', 'Terbit', 'https://prisma.example/berita/konten-16', 16, 2, 4),
(17, '2026-10-17 15:00:00', 'Terbit', 'https://instagram.example/prisma/konten-17', 17, 4, 5),
(18, '2026-10-18 15:00:00', 'Direncanakan', NULL, 18, 3, 6),
(19, '2026-10-19 15:00:00', 'Direncanakan', NULL, 19, 2, 7),
(20, '2026-10-20 15:00:00', 'Terbit', 'https://tiktok.example/prisma/konten-20', 20, 5, 8),
(21, '2026-10-21 15:00:00', 'Terbit', 'https://prisma.example/berita/konten-21', 21, 2, 4),
(22, '2026-10-22 15:00:00', 'Terbit', 'https://instagram.example/prisma/konten-22', 22, 4, 5),
(23, '2026-10-23 15:00:00', 'Direncanakan', NULL, 23, 3, 6),
(24, '2026-10-24 15:00:00', 'Terbit', 'https://prisma.example/artikel/konten-24', 24, 2, 7),
(25, '2026-10-25 15:00:00', 'Direncanakan', NULL, 25, 5, 8),
(26, '2026-10-26 15:00:00', 'Terbit', 'https://prisma.example/berita/konten-26', 26, 2, 4),
(27, '2026-10-27 15:00:00', 'Direncanakan', NULL, 27, 4, 5),
(28, '2026-10-28 15:00:00', 'Terbit', 'https://youtube.example/prisma/konten-28', 28, 3, 6),
(29, '2026-10-29 15:00:00', 'Direncanakan', NULL, 29, 2, 7),
(30, '2026-10-30 15:00:00', 'Terbit', 'https://tiktok.example/prisma/konten-30', 30, 5, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `jadwal_tayang`
--
ALTER TABLE `jadwal_tayang`
  ADD PRIMARY KEY (`id_jadwal`),
  ADD KEY `petugas_jadwal_id_pengguna` (`petugas_jadwal_id_pengguna`),
  ADD KEY `program_tv_id_program` (`program_tv_id_program`),
  ADD KEY `kanal_id_kanal` (`kanal_id_kanal`),
  ADD KEY `idx_jadwal_tanggal_waktu` (`tanggal_tayang`,`waktu_mulai`);

--
-- Indexes for table `kanal`
--
ALTER TABLE `kanal`
  ADD PRIMARY KEY (`id_kanal`),
  ADD UNIQUE KEY `nama_kanal` (`nama_kanal`);

--
-- Indexes for table `kerja_sama`
--
ALTER TABLE `kerja_sama`
  ADD PRIMARY KEY (`id_kerja_sama`),
  ADD KEY `admin_id_pengguna` (`admin_id_pengguna`),
  ADD KEY `program_tv_id_program` (`program_tv_id_program`),
  ADD KEY `konten_id_konten` (`konten_id_konten`),
  ADD KEY `mitra_id_mitra` (`mitra_id_mitra`);

--
-- Indexes for table `konten`
--
ALTER TABLE `konten`
  ADD PRIMARY KEY (`id_konten`),
  ADD KEY `program_tv_id_program` (`program_tv_id_program`),
  ADD KEY `petugas_konten_id_pengguna` (`petugas_konten_id_pengguna`),
  ADD KEY `idx_konten_status` (`status_konten`),
  ADD KEY `idx_konten_tanggal` (`tanggal_pencatatan`);

--
-- Indexes for table `lampiran_konten`
--
ALTER TABLE `lampiran_konten`
  ADD PRIMARY KEY (`id_lampiran`),
  ADD KEY `konten_id_konten` (`konten_id_konten`),
  ADD KEY `petugas_konten_id_pengguna` (`petugas_konten_id_pengguna`);

--
-- Indexes for table `mitra`
--
ALTER TABLE `mitra`
  ADD PRIMARY KEY (`id_mitra`),
  ADD UNIQUE KEY `nama_mitra` (`nama_mitra`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `petugas_jadwal`
--
ALTER TABLE `petugas_jadwal`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `petugas_konten`
--
ALTER TABLE `petugas_konten`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- Indexes for table `program_tv`
--
ALTER TABLE `program_tv`
  ADD PRIMARY KEY (`id_program`),
  ADD UNIQUE KEY `nama_program` (`nama_program`),
  ADD KEY `admin_id_pengguna` (`admin_id_pengguna`);

--
-- Indexes for table `publikasi_konten`
--
ALTER TABLE `publikasi_konten`
  ADD PRIMARY KEY (`id_publikasi`),
  ADD KEY `konten_id_konten` (`konten_id_konten`),
  ADD KEY `kanal_id_kanal` (`kanal_id_kanal`),
  ADD KEY `petugas_konten_id_pengguna` (`petugas_konten_id_pengguna`),
  ADD KEY `idx_publikasi_tanggal` (`tanggal_publikasi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jadwal_tayang`
--
ALTER TABLE `jadwal_tayang`
  MODIFY `id_jadwal` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `kanal`
--
ALTER TABLE `kanal`
  MODIFY `id_kanal` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `kerja_sama`
--
ALTER TABLE `kerja_sama`
  MODIFY `id_kerja_sama` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `konten`
--
ALTER TABLE `konten`
  MODIFY `id_konten` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `lampiran_konten`
--
ALTER TABLE `lampiran_konten`
  MODIFY `id_lampiran` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `mitra`
--
ALTER TABLE `mitra`
  MODIFY `id_mitra` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `program_tv`
--
ALTER TABLE `program_tv`
  MODIFY `id_program` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `publikasi_konten`
--
ALTER TABLE `publikasi_konten`
  MODIFY `id_publikasi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `jadwal_tayang`
--
ALTER TABLE `jadwal_tayang`
  ADD CONSTRAINT `jadwal_tayang_ibfk_1` FOREIGN KEY (`petugas_jadwal_id_pengguna`) REFERENCES `petugas_jadwal` (`id_pengguna`),
  ADD CONSTRAINT `jadwal_tayang_ibfk_2` FOREIGN KEY (`program_tv_id_program`) REFERENCES `program_tv` (`id_program`),
  ADD CONSTRAINT `jadwal_tayang_ibfk_3` FOREIGN KEY (`kanal_id_kanal`) REFERENCES `kanal` (`id_kanal`);

--
-- Constraints for table `kerja_sama`
--
ALTER TABLE `kerja_sama`
  ADD CONSTRAINT `kerja_sama_ibfk_1` FOREIGN KEY (`admin_id_pengguna`) REFERENCES `admin` (`id_pengguna`),
  ADD CONSTRAINT `kerja_sama_ibfk_2` FOREIGN KEY (`program_tv_id_program`) REFERENCES `program_tv` (`id_program`) ON DELETE SET NULL,
  ADD CONSTRAINT `kerja_sama_ibfk_3` FOREIGN KEY (`konten_id_konten`) REFERENCES `konten` (`id_konten`) ON DELETE SET NULL,
  ADD CONSTRAINT `kerja_sama_ibfk_4` FOREIGN KEY (`mitra_id_mitra`) REFERENCES `mitra` (`id_mitra`);

--
-- Constraints for table `konten`
--
ALTER TABLE `konten`
  ADD CONSTRAINT `konten_ibfk_1` FOREIGN KEY (`program_tv_id_program`) REFERENCES `program_tv` (`id_program`) ON DELETE SET NULL,
  ADD CONSTRAINT `konten_ibfk_2` FOREIGN KEY (`petugas_konten_id_pengguna`) REFERENCES `petugas_konten` (`id_pengguna`);

--
-- Constraints for table `lampiran_konten`
--
ALTER TABLE `lampiran_konten`
  ADD CONSTRAINT `lampiran_konten_ibfk_1` FOREIGN KEY (`konten_id_konten`) REFERENCES `konten` (`id_konten`) ON DELETE CASCADE,
  ADD CONSTRAINT `lampiran_konten_ibfk_2` FOREIGN KEY (`petugas_konten_id_pengguna`) REFERENCES `petugas_konten` (`id_pengguna`);

--
-- Constraints for table `petugas_jadwal`
--
ALTER TABLE `petugas_jadwal`
  ADD CONSTRAINT `petugas_jadwal_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `petugas_konten`
--
ALTER TABLE `petugas_konten`
  ADD CONSTRAINT `petugas_konten_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `program_tv`
--
ALTER TABLE `program_tv`
  ADD CONSTRAINT `program_tv_ibfk_1` FOREIGN KEY (`admin_id_pengguna`) REFERENCES `admin` (`id_pengguna`);

--
-- Constraints for table `publikasi_konten`
--
ALTER TABLE `publikasi_konten`
  ADD CONSTRAINT `publikasi_konten_ibfk_1` FOREIGN KEY (`konten_id_konten`) REFERENCES `konten` (`id_konten`) ON DELETE CASCADE,
  ADD CONSTRAINT `publikasi_konten_ibfk_2` FOREIGN KEY (`kanal_id_kanal`) REFERENCES `kanal` (`id_kanal`),
  ADD CONSTRAINT `publikasi_konten_ibfk_3` FOREIGN KEY (`petugas_konten_id_pengguna`) REFERENCES `petugas_konten` (`id_pengguna`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
