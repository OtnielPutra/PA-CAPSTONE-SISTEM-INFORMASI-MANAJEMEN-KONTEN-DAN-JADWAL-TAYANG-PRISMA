package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AkunDAO {

    public static class AkunData {

        private int idPengguna;
        private String namaLengkap;
        private String username;
        private String role;
        private String statusAkun;
        private String keterangan;

        public AkunData(int idPengguna, String namaLengkap,
                String username, String role,
                String statusAkun, String keterangan) {

            this.idPengguna = idPengguna;
            this.namaLengkap = namaLengkap;
            this.username = username;
            this.role = role;
            this.statusAkun = statusAkun;
            this.keterangan = keterangan;
        }

        public int getIdPengguna() {
            return idPengguna;
        }

        public String getNamaLengkap() {
            return namaLengkap;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }

        public String getStatusAkun() {
            return statusAkun;
        }

        public String getKeterangan() {
            return keterangan;
        }
    }

    private static final String QUERY_AKUN =
        "SELECT p.id_pengguna, p.nama_lengkap, p.username, " +
        "p.status_akun, " +
        "CASE " +
        "WHEN a.id_pengguna IS NOT NULL THEN 'Admin' " +
        "WHEN pj.id_pengguna IS NOT NULL THEN 'Petugas Jadwal' " +
        "WHEN pk.id_pengguna IS NOT NULL THEN 'Petugas Konten' " +
        "ELSE 'Tidak Diketahui' END AS role, " +
        "COALESCE(pj.cakupan_jadwal, " +
        "pk.spesialisasi_konten, 'Administrator') AS keterangan " +
        "FROM pengguna p " +
        "LEFT JOIN admin a ON p.id_pengguna = a.id_pengguna " +
        "LEFT JOIN petugas_jadwal pj ON p.id_pengguna = pj.id_pengguna " +
        "LEFT JOIN petugas_konten pk ON p.id_pengguna = pk.id_pengguna ";

    // Mengambil seluruh data akun.
    public List<AkunData> getSemua() throws SQLException {

        List<AkunData> daftar = new ArrayList<>();

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(
                 QUERY_AKUN + "ORDER BY p.id_pengguna ASC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                daftar.add(ambilData(rs));
            }
        }

        return daftar;
    }

    // Mengambil akun berdasarkan ID.
    public AkunData getById(int id) throws SQLException {

        try (Connection conn = Koneksi.getKoneksi();
             PreparedStatement ps = conn.prepareStatement(
                 QUERY_AKUN + "WHERE p.id_pengguna = ?")) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return ambilData(rs);
                }
            }
        }

        return null;
    }

    // Mengubah ResultSet menjadi objek AkunData.
    private AkunData ambilData(ResultSet rs) throws SQLException {

        return new AkunData(
            rs.getInt("id_pengguna"),
            rs.getString("nama_lengkap"),
            rs.getString("username"),
            rs.getString("role"),
            rs.getString("status_akun"),
            rs.getString("keterangan")
        );
    }

    // Validasi role akun.
    private void validasiRole(String role) throws SQLException {

        if (!"Admin".equals(role)
                && !"Petugas Jadwal".equals(role)
                && !"Petugas Konten".equals(role)) {

            throw new SQLException("Role akun tidak valid.");
        }
    }

    // Menambahkan akun baru.
    public void tambah(String nama, String username,
            String password, String status,
            String role, String keterangan) throws SQLException {

        nama = nama.trim();
        username = username.trim();
        password = password.trim();

        validasiRole(role);

        if (!"aktif".equals(status) && !"nonaktif".equals(status)) {
            throw new SQLException("Status akun tidak valid.");
        }

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
            throw new SQLException("Data akun wajib diisi.");
        }

        if (nama.length() > 100 || username.length() > 50
                || password.length() > 30) {
            throw new SQLException("Panjang data melebihi batas database.");
        }

        try (Connection conn = Koneksi.getKoneksi()) {

            conn.setAutoCommit(false);

            try {
                String sql = "INSERT INTO pengguna " +
                    "(nama_lengkap, username, password, status_akun) " +
                    "VALUES (?, ?, ?, ?)";

                int id;

                try (PreparedStatement ps = conn.prepareStatement(
                        sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                    ps.setString(1, nama);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, status);

                    ps.executeUpdate();

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (!rs.next()) {
                            throw new SQLException("ID pengguna gagal dibuat.");
                        }

                        id = rs.getInt(1);
                    }
                }

                tambahRole(conn, id, role, keterangan);

                conn.commit();

            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    // Menambahkan data role sesuai jenis akun.
    private void tambahRole(Connection conn, int id,
            String role, String keterangan) throws SQLException {

        String sql;
        String detail = (keterangan == null || keterangan.trim().isEmpty())
                ? "Penugasan umum" : keterangan.trim();

        switch (role) {

            case "Admin":
                sql = "INSERT INTO admin " +
                    "(id_pengguna, tanggal_penugasan) " +
                    "VALUES (?, CURRENT_DATE())";
                break;

            case "Petugas Jadwal":
                sql = "INSERT INTO petugas_jadwal " +
                    "(id_pengguna, cakupan_jadwal, tanggal_penugasan) " +
                    "VALUES (?, ?, CURRENT_DATE())";
                break;

            case "Petugas Konten":
                sql = "INSERT INTO petugas_konten " +
                    "(id_pengguna, spesialisasi_konten, tanggal_penugasan) " +
                    "VALUES (?, ?, CURRENT_DATE())";
                break;

            default:
                throw new SQLException("Role tidak dikenali.");
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (!"Admin".equals(role)) {
                ps.setString(2, detail);
            }

            ps.executeUpdate();
        }
    }

    // Mengubah data akun.
    public void update(int id, String nama, String username,
            String password, String status,
            String roleLama, String roleBaru,
            String keterangan) throws SQLException {

        nama = nama.trim();
        username = username.trim();

        validasiRole(roleLama);
        validasiRole(roleBaru);

        if (!"aktif".equals(status) && !"nonaktif".equals(status)) {
            throw new SQLException("Status akun tidak valid.");
        }

        if (nama.isEmpty() || username.isEmpty()) {
            throw new SQLException("Nama dan username wajib diisi.");
        }

        if (nama.length() > 100 || username.length() > 50
                || password.length() > 30) {
            throw new SQLException("Panjang data melebihi batas database.");
        }

        try (Connection conn = Koneksi.getKoneksi()) {

            conn.setAutoCommit(false);

            try {
                String sql = "UPDATE pengguna SET " +
                    "nama_lengkap = ?, username = ?, " +
                    "password = CASE WHEN ? = '' THEN password ELSE ? END, " +
                    "status_akun = ? WHERE id_pengguna = ?";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, nama);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, password);
                    ps.setString(5, status);
                    ps.setInt(6, id);

                    if (ps.executeUpdate() == 0) {
                        throw new SQLException("Akun tidak ditemukan.");
                    }
                }

                if (!roleLama.equals(roleBaru)) {
                    hapusRole(conn, id, roleLama);
                    tambahRole(conn, id, roleBaru, keterangan);
                } else {
                    updateKeterangan(conn, id, roleBaru, keterangan);
                }

                conn.commit();

            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    // Menghapus role lama.
    private void hapusRole(Connection conn, int id,
            String role) throws SQLException {

        String tabel;

        switch (role) {

            case "Admin":
                tabel = "admin";
                break;

            case "Petugas Jadwal":
                tabel = "petugas_jadwal";
                break;

            case "Petugas Konten":
                tabel = "petugas_konten";
                break;

            default:
                throw new SQLException("Role tidak valid.");
        }

        String sql = "DELETE FROM " + tabel +
                     " WHERE id_pengguna = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new SQLException("Data role tidak ditemukan.");
            }
        }
    }

    // Mengubah keterangan petugas.
    private void updateKeterangan(Connection conn, int id,
            String role, String keterangan) throws SQLException {

        if ("Admin".equals(role)) {
            return;
        }

        String detail = (keterangan == null || keterangan.trim().isEmpty())
                ? "Penugasan umum" : keterangan.trim();

        String sql;

        if ("Petugas Jadwal".equals(role)) {

            sql = "UPDATE petugas_jadwal SET cakupan_jadwal = ? " +
                  "WHERE id_pengguna = ?";

        } else if ("Petugas Konten".equals(role)) {

            sql = "UPDATE petugas_konten SET spesialisasi_konten = ? " +
                  "WHERE id_pengguna = ?";

        } else {
            throw new SQLException("Role tidak valid.");
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, detail);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }

    // Menghapus akun.
    public void hapus(int id, String role) throws SQLException {

        validasiRole(role);

        try (Connection conn = Koneksi.getKoneksi()) {

            conn.setAutoCommit(false);

            try {
                hapusRole(conn, id, role);

                String sql = "DELETE FROM pengguna WHERE id_pengguna = ?";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setInt(1, id);

                    if (ps.executeUpdate() == 0) {
                        throw new SQLException("Akun tidak ditemukan.");
                    }
                }

                conn.commit();

            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}