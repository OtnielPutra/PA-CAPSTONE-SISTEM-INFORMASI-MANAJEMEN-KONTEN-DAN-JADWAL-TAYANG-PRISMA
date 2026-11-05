package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DAO;

/**
 * Class induk (abstract) untuk controller yang mengelola satu jenis data
 * (tambah, ubah, hapus, cari, tampil). Subclass cukup menentukan aturan
 * validasinya (validasi) dan, bila perlu, peran yang boleh mengelola data.
 *
 * Cara pakai dari view: panggil method, lalu baca hasilnya lewat getPesan().
 *
 * @param <T> jenis entitas yang dikelola
 */
public abstract class BaseController<T> {

    private final DAO<T> dao;
    private final String namaData;
    private String pesan = "";

    protected BaseController(DAO<T> dao, String namaData) {
        this.dao = dao;
        this.namaData = namaData;
    }

    /** Memeriksa data. Mengembalikan pesan kesalahan, atau null jika data sudah benar. */
    protected abstract String validasi(T objek);

    /** Peran yang boleh mengubah data ini. null berarti semua peran. */
    protected String getPeranYangDiizinkan() {
        return null;
    }

    // ---------- operasi baca ----------

    public List<T> tampilSemua() {
        try {
            return dao.getSemua();
        } catch (SQLException e) {
            pesan = pesanKesalahan(e);
            return new ArrayList<>();
        }
    }

    /** Kata kunci kosong sama dengan menampilkan semua data. */
    public List<T> cari(String kataKunci) {
        if (kataKunci == null || kataKunci.isBlank()) {
            return tampilSemua();
        }
        try {
            return dao.cari(kataKunci.trim());
        } catch (SQLException e) {
            pesan = pesanKesalahan(e);
            return new ArrayList<>();
        }
    }

    /** Mengambil satu data berdasarkan id (null jika tidak ada). */
    public T ambilSatu(int id) {
        try {
            return dao.getById(id);
        } catch (SQLException e) {
            pesan = pesanKesalahan(e);
            return null;
        }
    }

    // ---------- operasi tulis ----------

    public boolean tambah(T objek) {
        if (!punyaAkses()) {
            return false;
        }
        if (objek == null) {
            pesan = "Data " + namaData + " tidak valid";
            return false;
        }
        String kesalahan = validasi(objek);
        if (kesalahan != null) {
            pesan = kesalahan;
            return false;
        }
        try {
            boolean berhasil = dao.tambah(objek);
            pesan = berhasil ? "Data " + namaData + " berhasil dimasukkan"
                             : "Data " + namaData + " gagal dimasukkan";
            return berhasil;
        } catch (SQLException e) {
            pesan = pesanKesalahan(e);
            return false;
        }
    }

    public boolean ubah(T objek) {
        if (!punyaAkses()) {
            return false;
        }
        if (objek == null) {
            pesan = "Data " + namaData + " tidak valid";
            return false;
        }
        String kesalahan = validasi(objek);
        if (kesalahan != null) {
            pesan = kesalahan;
            return false;
        }
        try {
            boolean berhasil = dao.ubah(objek);
            pesan = berhasil ? "Data " + namaData + " berhasil diubah"
                             : "Data " + namaData + " tidak ditemukan";
            return berhasil;
        } catch (SQLException e) {
            pesan = pesanKesalahan(e);
            return false;
        }
    }

    public boolean hapus(int id) {
        if (!punyaAkses()) {
            return false;
        }
        try {
            boolean berhasil = dao.hapus(id);
            pesan = berhasil ? "Data " + namaData + " berhasil dihapus"
                             : "Data " + namaData + " tidak ditemukan";
            return berhasil;
        } catch (SQLException e) {
            pesan = pesanKesalahan(e);
            return false;
        }
    }

    // ---------- pesan hasil proses ----------

    /** Pesan hasil proses terakhir (sukses maupun gagal), siap ditampilkan di view. */
    public String getPesan() {
        return pesan;
    }

    protected void setPesan(String pesan) {
        this.pesan = pesan;
    }

    protected DAO<T> getDao() {
        return dao;
    }

    protected String getNamaData() {
        return namaData;
    }

    // ---------- bantuan untuk subclass ----------

    private boolean punyaAkses() {
        String peran = getPeranYangDiizinkan();
        if (peran == null || peran.equals(SesiLogin.getRole())) {
            return true;
        }
        pesan = "Anda tidak memiliki akses untuk mengelola data " + namaData;
        return false;
    }

    /** Id akun yang sedang login (0 jika belum login). */
    protected int idPenggunaAktif() {
        return SesiLogin.sudahLogin() ? SesiLogin.getPenggunaAktif().getIdPengguna() : 0;
    }

    /** Mengembalikan pesan pertama yang tidak null, atau null jika semuanya null. */
    protected static String pertama(String... hasilPemeriksaan) {
        for (String hasil : hasilPemeriksaan) {
            if (hasil != null) {
                return hasil;
            }
        }
        return null;
    }

    protected static String cekTeks(String label, String nilai, int maksimal) {
        if (nilai == null || nilai.isBlank()) {
            return label + " tidak boleh kosong";
        }
        if (nilai.trim().length() > maksimal) {
            return label + " maksimal " + maksimal + " karakter";
        }
        return null;
    }

    /** Untuk isian yang boleh kosong, tetapi bila diisi tidak boleh melebihi batas. */
    protected static String cekTeksOpsional(String label, String nilai, int maksimal) {
        if (nilai != null && nilai.trim().length() > maksimal) {
            return label + " maksimal " + maksimal + " karakter";
        }
        return null;
    }

    protected static String cekPilihan(String label, String nilai, List<String> daftar) {
        if (nilai == null || !daftar.contains(nilai)) {
            return label + " tidak valid";
        }
        return null;
    }

    protected static String cekWajib(String label, Object nilai) {
        return nilai == null ? label + " harus diisi" : null;
    }

    /** Mengubah SQLException menjadi pesan yang mudah dipahami pengguna. */
    protected static String pesanKesalahan(SQLException e) {
        switch (e.getErrorCode()) {
            case 1062:
                return "Data gagal disimpan karena nama atau username yang sama sudah ada";
            case 1451:
                return "Data tidak dapat dihapus atau diubah karena masih digunakan oleh data lain";
            case 1452:
                return "Data terkait (program, kanal, mitra, konten, atau petugas) tidak ditemukan";
            case 1045:
                return "Username atau password MySQL salah. Periksa pengaturan di class Koneksi";
            case 1049:
                return "Database tidak ditemukan. Pastikan database prisma sudah dibuat dan prisma.sql sudah diimpor";
            case 1146:
                return "Tabel tidak ditemukan. Pastikan prisma.sql sudah diimpor";
            default:
                String status = e.getSQLState();
                if (status != null && status.startsWith("08")) {
                    return "Tidak dapat terhubung ke database. Pastikan MySQL sudah berjalan dan pengaturan di class Koneksi benar";
                }
                return "Terjadi kesalahan database: " + e.getMessage();
        }
    }
}
