package com.niconi21.turismoargentina.db;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class SingletonDB {
    public static ConexionDB DATABASE;

    public static void conexionDB(Context context, String dbName) {
        DATABASE = Room.databaseBuilder(context.getApplicationContext(), ConexionDB.class, dbName).allowMainThreadQueries().build();
    }


    public static List<UsuarioEntity> getUsuarios() {
        return DATABASE.usuarioDao().getAllUsuarios();
    }

    public static void insertUsuario(UsuarioEntity usuarioEntity) {
        DATABASE.usuarioDao().insertUsuario(usuarioEntity);
    }

    public static void deleteUsuarios() {
        DATABASE.usuarioDao().deleteUsuarios();
    }

    public static String getToken(){
        List<UsuarioEntity> usuarioEntities = getUsuarios();
        return usuarioEntities.get(0).token;
    }
}
