package model.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Kontrak dasar akses data. Setiap tabel punya implementasinya sendiri.
 *
 * @param <T> jenis entitas yang diolah
 */
public interface DAO<T> {

    boolean tambah(T objek) throws SQLException;

    boolean ubah(T objek) throws SQLException;

    boolean hapus(int id) throws SQLException;

    List<T> getSemua() throws SQLException;

    List<T> cari(String kataKunci) throws SQLException;

    T getById(int id) throws SQLException;
}
