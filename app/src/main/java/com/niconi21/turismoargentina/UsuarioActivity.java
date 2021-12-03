package com.niconi21.turismoargentina;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.niconi21.turismoargentina.tools.Foto;
import com.niconi21.turismoargentina.tools.Navegacion;
import com.niconi21.turismoargentina.tools.Permisos;

public class UsuarioActivity extends AppCompatActivity {
    private NavHostFragment _navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Permisos.permisoCamaraStorage(getApplicationContext(), this);
        this._navegacion();
    }

    private void _navegacion() {
        this._navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        Navegacion.configBottomNavigationView(this, this._navHostFragment);
        Navegacion.configMaterialToolbarInicio(this, this._navHostFragment);

    }

    public void capturarFoto(View view) {
        Foto.capturarFoto(this);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Intent intent = new Intent(this, DatosPostActivity.class);
            intent.putExtra("imagen", imageBitmap);
            startActivity(intent);
        }
    }
}