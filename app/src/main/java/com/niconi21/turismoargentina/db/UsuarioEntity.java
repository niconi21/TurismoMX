package com.niconi21.turismoargentina.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UsuarioEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name="nombre")
    public String nombre;

    @ColumnInfo(name="correo")
    public String correo;

    @ColumnInfo(name="token")
    public String token;
}
