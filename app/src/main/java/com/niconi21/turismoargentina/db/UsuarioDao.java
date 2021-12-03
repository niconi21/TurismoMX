package com.niconi21.turismoargentina.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("select * from UsuarioEntity")
    List<UsuarioEntity> getAllUsuarios();

    @Insert
    void insertUsuario(UsuarioEntity user);

    @Query("DELETE FROM UsuarioEntity")
    void deleteUsuarios();

    @Query("UPDATE UsuarioEntity SET nombre = :nombre,  correo = :correo WHERE uid = :uid")
    void updateUsuarios(String nombre, String correo, int uid);


}
