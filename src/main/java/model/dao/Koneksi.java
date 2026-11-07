package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Satu-satunya tempat pengaturan koneksi ke database MySQL.
 * Jika port, nama database, username, atau password MySQL Anda berbeda,
 * cukup ubah konstanta di bawah ini.
 */
public class Koneksi {

    private static final String HOST = "localhost";
    private static final String PORT = "3306";          
    private static final String DATABASE = "prisma_tv";   // nama database sesuai prisma_tv.sql
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
            + "?useSSL=false&allowPublicKeyRetrieval=true";

    // Class ini hanya berisi method static, tidak perlu dibuat objeknya
    private Koneksi() {
    }

    /** Membuka koneksi baru. Pemanggil wajib menutupnya (try-with-resources). */
    public static Connection getKoneksi() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
