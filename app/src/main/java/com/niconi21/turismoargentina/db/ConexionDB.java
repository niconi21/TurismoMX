package com.niconi21.turismoargentina.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UsuarioEntity.class}, version = 1)
public abstract class ConexionDB extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
}
