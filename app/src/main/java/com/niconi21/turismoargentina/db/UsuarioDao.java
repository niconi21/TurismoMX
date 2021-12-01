package com.niconi21.turismoargentina.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("select * from UsuarioEntity")
    List<UsuarioEntity> getAllUsuarios();

    @Insert
    void insertUsuario(UsuarioEntity user);

    @Query("DELETE FROM UsuarioEntity")
    void deleteUsuarios();


}
