package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.LampiranKonten;
import model.Pengguna;
import model.dao.LampiranKontenDAO;

/**
 * Menu Lampiran (Petugas Konten). File yang diunggah disalin ke folder
 * "uploads" di folder kerja program, lalu lokasinya dicatat di database.
 */
public class LampiranController extends BaseController<LampiranKonten> {

    public static final String FOLDER_UNGGAH = "uploads";

    private final LampiranKontenDAO lampiranDAO;

    public LampiranController() {
        this(new LampiranKontenDAO());
    }

    private LampiranController(LampiranKontenDAO dao) {
        super(dao, "Lampiran");
        this.lampiranDAO = dao;
    }

    @Override
    protected String getPeranYangDiizinkan() {
        return Pengguna.ROLE_PETUGAS_KONTEN;
    }

    @Override
    protected String validasi(LampiranKonten l) {
        return pertama(
                cekTeks("Nama file", l.getNamaFile(), 255),
                cekTeks("Lokasi file", l.getLokasiFile(), 255),
                l.getIdKonten() <= 0 ? "Konten harus dipilih" : null);
    }

    /** Waktu unggah dan petugas diisi otomatis. */
    @Override
    public boolean tambah(LampiranKonten lampiran) {
        if (lampiran != null) {
            if (lampiran.getTanggalUnggah() == null) {
                lampiran.setTanggalUnggah(LocalDateTime.now());
            }
            if (lampiran.getIdPetugasKonten() <= 0) {
                lampiran.setIdPetugasKonten(idPenggunaAktif());
            }
        }
        return super.tambah(lampiran);
    }

    /** Semua lampiran milik satu konten. */
    public List<LampiranKonten> tampilByKonten(int idKonten) {
        try {
            return lampiranDAO.getByKonten(idKonten);
        } catch (SQLException e) {
            setPesan(pesanKesalahan(e));
            return new ArrayList<>();
        }
    }

    /** Menyalin file ke folder uploads lalu mencatatnya sebagai lampiran konten. */
    public boolean unggah(File sumber, int idKonten) {
        if (sumber == null || !sumber.isFile()) {
            setPesan("File tidak ditemukan");
            return false;
        }
        try {
            Path tujuan = salinKeFolderUnggah(sumber);
            LampiranKonten lampiran = new LampiranKonten();
            lampiran.setNamaFile(sumber.getName());
            lampiran.setLokasiFile(FOLDER_UNGGAH + "/" + tujuan.getFileName());
            lampiran.setIdKonten(idKonten);
            if (!tambah(lampiran)) {
                Files.deleteIfExists(tujuan); // pencatatan gagal: jangan tinggalkan file yatim
                return false;
            }
            return true;
        } catch (IOException e) {
            setPesan("Gagal menyalin file: " + e.getMessage());
            return false;
        }
    }

    /** "Ganti Lampiran": file lama diganti file baru pada baris lampiran yang sama. */
    public boolean gantiFile(int idLampiran, File sumberBaru) {
        LampiranKonten lampiran = ambilSatu(idLampiran);
        if (lampiran == null) {
            setPesan("Lampiran tidak ditemukan");
            return false;
        }
        if (sumberBaru == null || !sumberBaru.isFile()) {
            setPesan("File tidak ditemukan");
            return false;
        }
        String lokasiLama = lampiran.getLokasiFile();
        try {
            Path tujuan = salinKeFolderUnggah(sumberBaru);
            lampiran.setNamaFile(sumberBaru.getName());
            lampiran.setLokasiFile(FOLDER_UNGGAH + "/" + tujuan.getFileName());
            lampiran.setTanggalUnggah(LocalDateTime.now());
            if (!ubah(lampiran)) {
                Files.deleteIfExists(tujuan);
                return false;
            }
            hapusFileFisik(lokasiLama);
            return true;
        } catch (IOException e) {
            setPesan("Gagal menyalin file: " + e.getMessage());
            return false;
        }
    }

    /** Menghapus data lampiran beserta file-nya di folder uploads (jika ada). */
    @Override
    public boolean hapus(int id) {
        LampiranKonten lampiran = ambilSatu(id);
        boolean berhasil = super.hapus(id);
        if (berhasil && lampiran != null) {
            hapusFileFisik(lampiran.getLokasiFile());
        }
        return berhasil;
    }

    private Path salinKeFolderUnggah(File sumber) throws IOException {
        Path folder = Paths.get(FOLDER_UNGGAH);
        Files.createDirectories(folder);
        Path tujuan = folder.resolve(sumber.getName());
        if (Files.exists(tujuan)) {
            tujuan = folder.resolve(System.currentTimeMillis() + "_" + sumber.getName());
        }
        Files.copy(sumber.toPath(), tujuan);
        return tujuan;
    }

    /** Hanya menghapus file yang memang berada di dalam folder uploads. */
    private void hapusFileFisik(String lokasi) {
        if (lokasi == null) {
            return;
        }
        try {
            Path path = Paths.get(lokasi).normalize();
            if (path.startsWith(FOLDER_UNGGAH)) {
                Files.deleteIfExists(path);
            }
        } catch (IOException | RuntimeException e) {
            // file fisik gagal dihapus: data di database tetap sudah terhapus
        }
    }
}
