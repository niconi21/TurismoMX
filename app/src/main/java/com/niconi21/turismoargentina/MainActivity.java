package com.niconi21.turismoargentina;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.db.UsuarioEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        SingletonDB.conexionDB(this.getApplicationContext(), getString(R.string.DB_NAME));
        this._validarSesionAbierta();
    }

    private void _validarSesionAbierta() {
        List<UsuarioEntity> usuarioEntities = SingletonDB.getUsuarios();

        if (usuarioEntities.size() == 1) {
            Intent intent = new Intent(this.getApplicationContext(), UsuarioActivity.class);
            startActivity(intent);
        }
        if (usuarioEntities.size() > 1) {
                SingletonDB.deleteUsuarios();
        }
    }

}