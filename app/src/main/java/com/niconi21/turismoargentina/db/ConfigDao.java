package com.niconi21.turismoargentina.db;


import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ConfigDao {

    @Query("select * from ConfigEntity")
    List<ConfigEntity> getAllConfigs();

    @Query("select * from ConfigEntity WHERE uid = :id")
    ConfigEntity getConfig(Long id);

    @Insert
    void insertConfig(ConfigEntity config);

    @Query("DELETE FROM ConfigEntity")
    void deleteConfigs();

    @Query("UPDATE ConfigEntity set valor = :valor")
    void updateConfig(String valor);
}
