<p align="center">
██████╗░██████╗░██╗░██████╗███╗░░░███╗░█████╗░
██╔══██╗██╔══██╗██║██╔════╝████╗░████║██╔══██╗
██████╔╝██████╔╝██║╚█████╗░██╔████╔██║███████║
██╔═══╝░██╔══██╗██║░╚═══██╗██║╚██╔╝██║██╔══██║
██║░░░░░██║░░██║██║██████╔╝██║░╚═╝░██║██║░░██║
╚═╝░░░░░╚═╝░░╚═╝╚═╝╚═════╝░╚═╝░░░░░╚═╝╚═╝░░╚═╝
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Apache_NetBeans-1B6AC6?style=for-the-badge&logo=apachenetbeans&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-00758F?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Laragon-0E9E59?style=for-the-badge&logo=laragon&logoColor=white"/>
  <img src="https://img.shields.io/badge/phpMyAdmin-F89C0E?style=for-the-badge&logo=phpmyadmin&logoColor=white"/>
</p>

----

## 1. Nama Program
  <b>SISTEM INFORMASI MANAJEMEN KONTEN DAN JADWAL TAYANG PRISMA</b>



## 2. Deskripsi Singkat

PrismaTV merupakan aplikasi desktop berbasis Java yang dikembangkan menggunakan Java Swing untuk membantu pengelolaan konten dan jadwal penayangan program televisi. Aplikasi ini menyediakan berbagai fitur pengelolaan data, meliputi konten siaran, program TV, kanal televisi, jadwal tayang, mitra, kerja sama, lampiran konten, serta publikasi konten.

Aplikasi menyediakan tiga jenis hak akses, yaitu Admin, Petugas Jadwal, dan Petugas Konten. Admin memiliki akses untuk mengelola akun dan keseluruhan data aplikasi, Petugas Jadwal bertanggung jawab terhadap pengelolaan jadwal penayangan, sedangkan Petugas Konten bertanggung jawab terhadap pengelolaan konten siaran. Seluruh data aplikasi disimpan menggunakan database MySQL dan dihubungkan melalui JDBC sehingga proses pengelolaan dan penyajian informasi dapat dilakukan secara terintegrasi.

## 3. Fitur Utama Program

### 3.1 Login dan Hak Akses

- Pengguna dapat masuk ke aplikasi menggunakan username dan password.
- Sistem melakukan pemeriksaan identitas dan password sebelum memberikan akses.
- Sistem membedakan tiga jenis hak akses, yaitu Admin, Petugas Jadwal, dan Petugas Konten.
- Sistem menampilkan menu sesuai dengan hak akses masing-masing pengguna.
- Setiap pengguna hanya dapat mengakses fitur yang sesuai dengan tanggung jawabnya.

### 3.2 Dashboard

- Menampilkan halaman utama setelah pengguna berhasil login.
- Menampilkan ringkasan jumlah konten, program TV, jadwal tayang, dan kerja sama.
- Menampilkan informasi jadwal tayang terbaru.
- Menampilkan ringkasan kerja sama berdasarkan mitra.

### 3.3 Manajemen Konten

- Menampilkan daftar konten siaran yang tersimpan dalam database.
- Menambahkan data konten baru.
- Mengubah informasi konten yang sudah tersedia.
- Menghapus data konten sesuai kebutuhan.
- Mengelola informasi konten berdasarkan data yang dimasukkan.

### 3.4 Manajemen Program TV

- Menampilkan daftar program televisi yang tersedia.
- Menambahkan program TV baru.
- Mengubah informasi program TV.
- Menghapus data program TV.
- Mengelola informasi program yang digunakan dalam proses penjadwalan dan penayangan.
- Pengelolaan program TV dibatasi berdasarkan hak akses pengguna.

### 3.5 Manajemen Jadwal Tayang

- Menampilkan daftar jadwal penayangan program televisi.
- Menambahkan jadwal tayang baru.
- Mengubah informasi jadwal penayangan.
- Menghapus jadwal tayang.
- Mengelola hubungan antara program TV dan jadwal penayangannya.

### 3.6 Manajemen Publikasi Konten

- Menampilkan daftar publikasi konten.
- Menambahkan data publikasi konten.
- Mengubah informasi publikasi yang sudah tersedia.
- Menghapus data publikasi.
- Mengelola informasi publikasi berdasarkan konten yang akan ditayangkan.

### 3.7 Manajemen Lampiran Konten

- Menampilkan daftar lampiran yang berkaitan dengan konten.
- Menambahkan lampiran pada data konten.
- Mengubah informasi lampiran.
- Menghapus lampiran yang sudah tidak diperlukan.
- Mengelola data pendukung yang berkaitan dengan konten siaran.

### 3.8 Manajemen Data Kanal

- Menampilkan daftar kanal televisi yang tersedia.
- Menambahkan data kanal baru.
- Mengubah informasi kanal.
- Menghapus data kanal.
- Mengelola informasi kanal yang digunakan dalam sistem.

### 3.9 Manajemen Data Mitra

- Menampilkan daftar mitra yang bekerja sama dengan perusahaan.
- Menambahkan data mitra baru.
- Mengubah informasi mitra.
- Menghapus data mitra.
- Mengelola informasi mitra yang berkaitan dengan kegiatan kerja sama.

### 3.10 Manajemen Kerja Sama

- Menampilkan daftar kerja sama dengan mitra.
- Menambahkan data kerja sama baru.
- Mengubah informasi kerja sama.
- Menghapus data kerja sama.
- Mengelola informasi hubungan kerja sama antara perusahaan dan mitra.

### 3.11 Manajemen Akun Pengguna

- Menampilkan daftar akun yang terdaftar dalam sistem.
- Menambahkan akun pengguna baru.
- Mengubah informasi akun pengguna.
- Menghapus akun pengguna.
- Mengatur jenis hak akses berdasarkan peran pengguna.
- Mengelola status akun pengguna, seperti aktif dan nonaktif.

### 3.12 Monitor Data

- Menampilkan data program TV, konten, jadwal tayang, kerja sama, dan mitra.
- Menyediakan tampilan informasi secara terpusat.
- Memungkinkan pengguna melihat data sesuai dengan hak aksesnya.
- Menyediakan akses pemantauan data tanpa melakukan perubahan terhadap data.

### 3.13 Logout

- Menyediakan fitur untuk mengakhiri sesi pengguna.
- Menghapus informasi sesi login yang sedang berlangsung.
- Mengarahkan pengguna kembali ke halaman login setelah logout.

## 4. Tech Stack

| Komponen | Teknologi | Kegunaan pada Prisma TV |
|---|---|---|
| Bahasa pemrograman | Java | Membangun seluruh logika berorientasi objek, entitas data, dan pengendali aplikasi. |
| Database | MySQL / MariaDB | Menyimpan tabel relasional (`pengguna`, `program_tv`, `kanal`, `mitra`, dll). |
| Koneksi database | JDBC (Java Database Connectivity) | Menghubungkan class `Koneksi` Java ke server database MySQL lokal. |
| Database driver | MySQL Connector/J (`8.4.0`) | Driver resmi yang diunduh via Maven untuk menjembatani komunikasi Java dan MySQL. |
| GUI | Java Swing (NetBeans GUI Builder) | Membangun antarmuka desktop (`login.java`, form menu, tabel data, tombol interaktif). |
| Build tool | Apache Maven | Mengatur dependensi proyek, versi Java, dan proses *build* aplikasi. |

## 5. Perancangan Sistem

### 5.1 Flowchart

Flowchart digunakan untuk menjelaskan alur proses aplikasi dari awal dijalankan sampai fitur digunakan oleh masing-masing *role* (Admin, Petugas Jadwal, Petugas Konten).

<details>
<summary><b>Flowchart menu utama</b></summary>
<img width="827" height="454" alt="image" src="https://github.com/user-attachments/assets/d0004fc0-dc22-43a9-a6e8-ddedb31a8cce" />


</details>

<details>
<summary><b>Flowchart monitor data</b></summary>
<img width="584" height="1225" alt="image" src="https://github.com/user-attachments/assets/8f11cbb9-d711-4890-a380-49142c2813be" />


</details>

<details>
<summary><b>Flowchart Menu Admin</b></summary>
<img width="455" height="701" alt="image" src="https://github.com/user-attachments/assets/8cecfd25-91f5-4fec-a19b-65c8cfda8350" />

</details>

<details>
<summary><b>Flowchart Admin – Menu Program </b></summary>
<img width="827" height="612" alt="image" src="https://github.com/user-attachments/assets/348346e6-3864-426f-901b-92ff96194347" />

![Flowchart Admin – Menu Program ](Gambar/flowchart-Admin – Menu Program l.png)

</details>

<details>
<summary><b>Flowchart Admin  Menu Mitra </b></summary>

<img width="825" height="528" alt="image" src="https://github.com/user-attachments/assets/e7c5503a-e8f0-4f62-b10d-fba48f0f956f" />


</details><details>
<summary><b>Flowchart Admin - Menu Kerja Sama </b></summary>

<img width="826" height="518" alt="image" src="https://github.com/user-attachments/assets/7576dd37-5f03-4961-8526-0cc919f3318d" />


</details><details>
<summary><b>Flowchart Admin - Menu Kanal </b></summary>

<img width="826" height="492" alt="image" src="https://github.com/user-attachments/assets/97ba21dc-bf72-4f58-9348-91cf0c4c07b1" />

![Flowchart Admin - Menu Kanal ](Gambar/Admin - Menu Kanal .png)

</details><details>
<summary><b>Flowchart Menu Petugas Konten </b></summary>

<img width="515" height="652" alt="image" src="https://github.com/user-attachments/assets/e8890f0e-dc00-4b38-8d2e-307daccba867" />


</details><details>
<summary><b>Flowchart Petugas Konten - Menu Konten </b></summary>

<img width="826" height="624" alt="image" src="https://github.com/user-attachments/assets/c4edc0c9-46a3-43b0-a7c1-0005b6496ee0" />


</details><details>
<summary><b>Flowchart Petugas Konten - Menu Publikasi</b></summary>

<img width="826" height="600" alt="image" src="https://github.com/user-attachments/assets/ee50b66e-59eb-4e0b-811e-ec9f1e8a7a92" />


</details><details>
<summary><b>Flowchart Petugas Jadwal - Menu Jadwal </b></summary>

<img width="708" height="552" alt="image" src="https://github.com/user-attachments/assets/7141df46-c595-4128-980a-c75a17af0903" />

</details>


### 5.2 Use Case Diagram


<img width="948" height="1195" alt="Image" src="https://github.com/user-attachments/assets/0a15e06a-6c75-4695-ba9c-9d84198de00f" />



Use Case Diagram menggambarkan interaksi aktor dengan sistem Prisma TV:

- **Admin:** Melakukan login, mengelola akun pengguna, mengontrol data program TV, kanal, mitra, dan kerja sama.
- **Petugas Konten:** Melakukan login, mencatat dan mengelola konten siaran, lampiran file, serta publikasi konten.
- **Petugas Jadwal:** Melakukan login, menyusun, mengubah, dan memantau jadwal tayang program pada kanal siaran.

### 5.3 Entity Relationship Diagram (ERD)

<img width="1228" height="821" alt="Image" src="https://github.com/user-attachments/assets/54e7546f-0002-4ff0-8f15-d5fe9868c39c" />

<img width="1458" height="1008" alt="Image" src="https://github.com/user-attachments/assets/cadfd615-5f54-41d8-9aba-90db2e3ce3e8" />


ERD mendefinisikan relasi tabel dalam database `prisma`, yang menghubungkan entitas induk `pengguna` dengan tabel turunan (`admin`, `petugas_jadwal`, `petugas_konten`), serta relasi transaksional ke tabel `program_tv`, `kanal`, `mitra`, `kerja_sama`, `konten`, `lampiran_konten`, `publikasi_konten`, dan `jadwal_tayang`.

## 6. Struktur Folder/Package

Proyek ini dibangun menggunakan struktur 5 *package* Maven untuk menerapkan prinsip pemisahan tanggung jawab (*Separation of Concerns*):

```text
src/main/java/
 ├── main/
 │    └─ Main.java
 ├── model/
 │    ├─ Pengguna.java (Abstract Class)
 │    ├─ Admin.java
 │    ├─ PetugasJadwal.java
 │    ├─ PetugasKonten.java
 │    └─ [Entitas Lain: ProgramTV.java, Kanal.java, Mitra.java, dll]
 ├── model.dao/
 │    ├─ Koneksi.java
 │    ├─ DAO.java (Interface)
 │    └─ [Implementasi DAO: PenggunaDAO.java, ProgramTVDAO.java, dll]
 └── controller/
      ├─ BaseController.java (Abstract Class)
      ├─ SesiLogin.java
      ├─ LoginController.java
      └─ [Controller Fitur Lainnya]
 └── view/
      ├─ login.java (Java Swing GUI Form)
      ├─ [Form Swing Lainnya: FormMenuAdmin.java, dll]
      ├─ TabelHelper.java
      └─ FormHelper.java
```

Penjelasan Package Berdasarkan Implementasi Nyata
main

File Utama: Main.java

Penjelasan: Berfungsi sebagai titik masuk (entry point) aplikasi. Method di Main.java langsung mengeksekusi jendela form login pertama melalui baris new login().setVisible(true);.

model

File Utama: Pengguna.java, Admin.java, ProgramTV.java, dll.

Penjelasan: Menampung struktur entitas data murni (POJO) dan abstract class induk. Class di package ini sama sekali tidak memuat query SQL, murni hanya merepresentasikan kolom-kolom tabel database ke dalam atribut objek Java.

model.dao

File Utama: Koneksi.java, DAO.java, PenggunaDAO.java, dll.

Penjelasan: Pusat penanganan komunikasi basis data. Class Koneksi.java menggunakan JDBC DriverManager.getConnection("jdbc:mysql://localhost:3308/prisma", ...) untuk menghubungkan aplikasi ke database prisma. Seluruh query SQL dieksekusi di dalam class implementasi DAO seperti PenggunaDAO.java.

controller

File Utama: LoginController.java, SesiLogin.java, dll.

Penjelasan: Menjadi jembatan logika antara View dan Model. Contohnya, LoginController.java menerima parameter input teks dari login.java, memvalidasi kekosongan teks, lalu memanggil PenggunaDAO.login() dan menyimpan sesi aktif ke SesiLogin.java.

view

File Utama: login.java, TabelHelper.java, FormHelper.java

Penjelasan: Berisi antarmuka visual (JFrame Form) yang dibuat melalui NetBeans GUI Builder. Dilengkapi dengan class utilitas pendukung seperti TabelHelper.java untuk mempermudah pengisian data ke dalam JTable.

## 7. Penerapan 4 Pilar OOP (Disertai Contoh Kode Riil Program)

### 7.1 Encapsulation (Enkapsulasi)
Penerapan dalam Prisma TV: Seluruh atribut pada class entitas (seperti username, password, namaLengkap) disembunyikan menggunakan access modifier private. Akses baca dan tulis mutlak dikendalikan melalui method getter dan setter publik.

Contoh pada kode (model.Pengguna.java):

```
public abstract class Pengguna {
    private int idPengguna;
    private String username; // Atribut diproteksi dengan private

    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }
}
```

### 7.2 Inheritance (Pewarisan)
Penerapan dalam Prisma TV: Diterapkan pada hierarki pengguna aplikasi. Class Admin, PetugasJadwal, dan PetugasKonten bertindak sebagai subclass yang mewarisi seluruh atribut umum serta constructor dari superclass abstract Pengguna menggunakan keyword extends dan super().

Contoh pada kode (model.Admin.java):

```
public class Admin extends Pengguna {
    private String tanggalPenugasan;

    public Admin(int idPengguna, String namaLengkap, String username, String password, String statusAkun, String tanggalPenugasan) {
        super(idPengguna, namaLengkap, username, password, statusAkun); // Mewarisi konstruktor induk
        this.tanggalPenugasan = tanggalPenugasan;
    }
}
```

### 7.3 Polymorphism (Polimorfisme)
Penerapan dalam Prisma TV: Diterapkan melalui mekanisme method overriding pada fungsi getRole(). Setiap subclass pengguna mengimplementasikan ulang method ini dengan mengembalikan teks peran yang spesifik, sehingga objek bertipe Pengguna dapat menghasilkan perilaku dinamis saat dipanggil di controller.

Contoh pada kode (Admin.java vs PetugasJadwal.java):

```
Java
// Di dalam Admin.java:
@Override
public String getRole() { return "Admin"; }

// Di dalam PetugasJadwal.java:
@Override
public String getRole() { return "Petugas Jadwal"; }
```

### 7.4 Abstraction (Abstraksi)
Penerapan dalam Prisma TV: Diterapkan melalui abstract class (Pengguna, BaseController) dan interface generik DAO<T>. Interface ini menetapkan standar baku operasi CRUD (tambah, ubah, hapus, getSemua, cari) yang wajib dipatuhi oleh seluruh class DAO tanpa membocorkan detail perintah SQL ke lapisan tampilan.

Contoh pada kode (model.dao.DAO.java):

```
Java
public interface DAO<T> {
    void tambah(T entity);
    void ubah(T entity);
    void hapus(int id);
    List<T> getSemua();
    List<T> cari(String keyword);
}
```


## 8. Cara Penggunaan Program
Ini adalah panduan atau cara penggunaan dari program ini

### 8.1 Menu Login
  
<img width="953" height="507" alt="image" src="https://github.com/user-attachments/assets/2a460a7a-7515-48f2-9bf0-d6dc378a6fbd" />

program dimulai dari menu awal yaitu menu login. disini pengguna akan login ke akun yang terdaftar didalam database. setelah memasukan username dan password yang benar, maka pengguna akan langsung masuk kedalam program sesuai dari role / peras pengguna tersebut didalam perusahaan prisma

### 8.2 Dashboard
<img width="1687" height="967" alt="image" src="https://github.com/user-attachments/assets/3086a87c-641d-4eeb-ae2e-dae3b008bfe5" />

Dashboard adalah halaman utama yang tampil setelah pengguna berhasil login, berisi sapaan dengan nama dan peran pengguna, empat kartu ringkasan data yaitu Total Konten, Program TV, Jadwal Tayang, dan Kerja Sama, serta tabel Jadwal Tayang Terbaru yang menampilkan program, kanal, tanggal, waktu, dan status jadwal, lengkap dengan identitas pengguna dan tombol Log Out di bagian atas. Kegunaannya adalah memberi gambaran cepat tentang kondisi data sistem tanpa harus membuka menu satu per satu, memantau jadwal tayang terbaru beserta statusnya, menjadi titik awal untuk berpindah ke menu pengelolaan lain sesuai peran pengguna, serta memungkinkan pengguna keluar dari sistem dengan aman.

### 8.3 Monitor Data
<img width="1687" height="977" alt="image" src="https://github.com/user-attachments/assets/54fbfc1d-9965-4681-8012-a2655681c2ff" />

Monitor Data adalah halaman untuk memantau seluruh data utama sistem dalam bentuk tabel, dengan lima tab pilihan yaitu Program TV, Konten, Jadwal Tayang, Kerja Sama, dan Mitra. Pada tab yang sedang dibuka, misalnya Program TV, halaman menampilkan daftar data lengkap dengan kolom ID, Nama Program, Kategori, URL, dan Status, sebuah kolom pencarian dengan tombol Cari dan Reset, jumlah seluruh data di bagian bawah tabel, serta identitas pengguna dan tombol Log Out di bagian atas. Kegunaannya adalah memudahkan pengguna melihat seluruh data sistem dari satu tempat tanpa membuka menu pengelolaan satu per satu, mencari data tertentu dengan cepat menggunakan kata kunci, berpindah antar jenis data hanya dengan mengganti tab, serta menjaga keamanan data karena halaman ini hanya untuk melihat dan tidak digunakan untuk menambah, mengubah, atau menghapus data.


</details>

<details>
<summary><h2>8.4 Menu Admin</h2></summary>

#### 8.4.1 Program Tv
<img width="1897" height="1006" alt="image" src="https://github.com/user-attachments/assets/fe3cc3bb-3f23-4fb1-bd67-e691806cd191" />

<img width="552" height="371" alt="image" src="https://github.com/user-attachments/assets/8693a92c-7731-4ef9-86c5-c597d1cb6319" />

<img width="530" height="348" alt="image" src="https://github.com/user-attachments/assets/22ebf37f-a5ca-4a82-aa52-de658ea240d6" />

Program TV adalah halaman pengelolaan data program televisi yang hanya dapat diakses oleh Admin, berisi tabel daftar program dengan kolom ID, Nama Program, Kategori, URL, dan Status, kolom pencarian dengan tombol Cari dan Reset, jumlah seluruh data di bagian bawah tabel, tombol Tambah Program, Ubah, dan Hapus, serta menu navigasi di sisi kiri untuk berpindah ke menu lain. Saat tombol Tambah Program ditekan, muncul jendela isian dengan kolom Nama Program, Kategori Program, URL Program, dan Status, sedangkan tombol Ubah membuka jendela serupa yang sudah terisi data program yang dipilih, dengan kolom bertanda wajib harus diisi sebelum data disimpan. Kegunaannya adalah membantu Admin menambah program baru, memperbarui informasi program seperti nama, kategori, alamat URL, dan status aktif atau nonaktif, menghapus program yang tidak digunakan lagi, serta mencari program tertentu dengan cepat menggunakan kata kunci, sehingga data program yang menjadi dasar jadwal tayang, konten, dan kerja sama tetap rapi dan akurat.

#### 8.4.2 Data Kanal
<img width="1892" height="1005" alt="image" src="https://github.com/user-attachments/assets/970f8e13-217f-48ec-94ac-9c9a94b95332" />

Data Kanal adalah halaman pengelolaan data kanal penayangan dan publikasi konten yang hanya dapat diakses oleh Admin, berisi tabel daftar kanal, kolom pencarian, serta tombol Tambah Kanal, Ubah, dan Hapus. Kegunaannya adalah membantu Admin menambah, memperbarui, menghapus, dan mencari data kanal seperti televisi, website, YouTube, Instagram, dan TikTok agar data yang menjadi dasar jadwal tayang dan publikasi konten tetap rapi dan akurat.

#### 8.4.3 Data Mitra
<img width="1902" height="993" alt="image" src="https://github.com/user-attachments/assets/cc5d5f81-d896-427f-a0b8-92f1942aff7c" />

Data Mitra adalah halaman pengelolaan data perusahaan atau pihak yang bekerja sama yang hanya dapat diakses oleh Admin, berisi tabel daftar mitra dengan nama, jenis, email, alamat, dan status, kolom pencarian, serta tombol Tambah Mitra, Ubah, dan Hapus. Kegunaannya adalah membantu Admin menambah, memperbarui, menghapus, dan mencari data mitra agar data yang menjadi dasar kerja sama tetap rapi dan akurat.

#### 8.4.4 Kerja Sama
<img width="1902" height="997" alt="image" src="https://github.com/user-attachments/assets/d89aec52-351d-4b7e-a884-11159b392911" />

Kerja Sama adalah halaman pengelolaan kontrak yang terhubung dengan mitra dan hanya dapat diakses oleh Admin, berisi tabel daftar kerja sama dengan judul, mitra, jenis, periode, nilai kontrak, program TV, dan status, kolom pencarian, serta tombol Tambah Kerja Sama, Ubah, dan Hapus. Kegunaannya adalah membantu Admin menambah, memperbarui, menghapus, dan mencari data kerja sama agar kontrak beserta periode, nilai, dan statusnya tercatat rapi dan mudah dipantau.

#### 8.4.5 Akun
<img width="1895" height="1001" alt="image" src="https://github.com/user-attachments/assets/bc536d11-3c9f-48ad-8a79-8b303abaf2dc" />

Akun Pengguna adalah halaman pengelolaan data pengguna dan peran dalam sistem yang hanya dapat diakses oleh Admin, berisi tabel daftar akun dengan nama lengkap, username, peran, detail penugasan, tanggal penugasan, dan status, kolom pencarian, serta tombol Tambah Akun, Ubah, dan Hapus. Kegunaannya adalah membantu Admin menambah, memperbarui, menghapus, dan mencari akun Admin, Petugas Jadwal, maupun Petugas Konten agar hak akses setiap pengguna tercatat rapi dan terkendali.


</details>

<details>
<summary><h2>8.5 Menu Petugas Konten</h2></summary>

#### 8.5.1 Manajemen Konten
<img width="1908" height="1007" alt="image" src="https://github.com/user-attachments/assets/8cbd0842-ea3d-4591-8122-3926776be966" />

Manajemen Konten adalah halaman pengelolaan konten yang hanya dapat diakses oleh Petugas Konten, berisi tabel daftar konten dengan judul, jenis, kategori, program TV, tanggal catat, dan status, kolom pencarian dengan penyaring status, serta tombol Tambah Konten, Ubah, dan Hapus. Kegunaannya adalah membantu Petugas Konten menambah, memperbarui, menghapus, dan mencari konten serta menghubungkannya dengan program TV, sehingga perkembangan setiap konten dari Dalam Proses hingga Sudah Tayang dapat dipantau dengan rapi.

#### 8.5.2 Publikasi Konten
<img width="1892" height="1012" alt="image" src="https://github.com/user-attachments/assets/dbefc23e-6b65-4996-89a7-c09d65c9226b" />

Publikasi Konten adalah halaman pencatatan publikasi konten melalui kanal yang tersedia dan hanya dapat diakses oleh Petugas Konten, berisi tabel daftar publikasi dengan konten, kanal, tanggal, link, dan status, kolom pencarian, serta tombol Tambah Publikasi, Ubah, dan Hapus. Kegunaannya adalah membantu Petugas Konten menambah, memperbarui, menghapus, dan mencari data publikasi agar setiap konten yang direncanakan maupun sudah terbit di kanal tertentu tercatat rapi lengkap dengan link-nya.

#### 8.5.3 Lampiran Konten
<img width="1893" height="1013" alt="image" src="https://github.com/user-attachments/assets/c279b518-5e4d-40af-ab08-176e90b7e112" />

Lampiran Konten adalah halaman pengelolaan file pendukung yang terhubung ke konten dan hanya dapat diakses oleh Petugas Konten, berisi tabel daftar lampiran dengan konten, nama file, lokasi file, dan tanggal unggah, kolom pencarian, serta tombol Tambah Lampiran, Ubah, dan Hapus. Kegunaannya adalah membantu Petugas Konten menambah, memperbarui, menghapus, dan mencari lampiran seperti gambar, video, atau dokumen agar setiap konten memiliki file pendukung yang tercatat rapi.

</details>

<details>
<summary><h2>8.6 Menu Petugas Jadwal</h2></summary>

#### 8.6.1 Menu Jadwal Tayang
<img width="1893" height="1008" alt="image" src="https://github.com/user-attachments/assets/63bfdce9-6a24-4a0c-aed9-b1415b123b22" />

Jadwal Tayang adalah halaman pengaturan tanggal dan waktu penayangan program yang hanya dapat diakses oleh Petugas Jadwal, berisi tabel daftar jadwal dengan program TV, kanal, tanggal, waktu, petugas, dan status, penyaring pencarian berdasarkan tanggal, waktu mulai, dan waktu selesai, serta tombol Tambah Jadwal, Ubah, Nonaktifkan, dan Hapus. Kegunaannya adalah membantu Petugas Jadwal menambah, memperbarui, menonaktifkan, menghapus, dan mencari jadwal agar penayangan setiap program tersusun rapi dan tidak bentrok.


</details>
----

# KELOMPOK 15 CAPSTONE
| No | Nama | NIM |
|:---:|:---|:---:|
| 1 | Aulia Sheva Savitri | 2509116001 |
| 2 | Muhammad Yusri Jibran Sugiharta Jusuf | 2509116030 |
| 3 | Jihan Shafira Rahmah| 2509116073 |
| 4 | Otniel Putra Wardana | 2509116081 |
