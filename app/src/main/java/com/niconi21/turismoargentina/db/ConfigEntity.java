package com.niconi21.turismoargentina.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ConfigEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name="clave")
    public String clave;

    @ColumnInfo(name="valor")
    public String valor;
}

