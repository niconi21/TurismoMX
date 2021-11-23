package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.niconi21.turismoargentina.tools.Permisos;

public class DatosPostActivity extends AppCompatActivity {
    private FusedLocationProviderClient _fusedLocationClient;
    private Bitmap _imgIntent;
    private ImageView _imageView;
    private TextView _tvCoordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_post);

        this._establecerItems();
        this._recuperarInfoFromIntent();
        this._establecerImagen();
        this.ubicacion();
    }

    private void _recuperarInfoFromIntent() {
        Intent intent = getIntent();
        this._imgIntent = (Bitmap) intent.getParcelableExtra("imagen");
    }

    private void _establecerItems() {
        this._imageView = (ImageView) findViewById(R.id.imgDatosPosts);
        this._fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        this._tvCoordenadas = findViewById(R.id.tvCoordenadasPost);

    }

    private void _establecerImagen() {
        this._imageView.setImageBitmap(this._imgIntent);
    }


    @SuppressLint("MissingPermission")
    private void ubicacion() {
        if (Permisos.permisoUbicacion(getApplicationContext(), this)) {
            this._fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                this._tvCoordenadas.setText("Latitud: " + location.getLatitude() + "\n" + "Longitud: " + location.getAltitude());
            });
        } else {
            this._tvCoordenadas.setText("No hay permisos concedidos");
        }
    }


    public void regresar(View view) {
        onBackPressed();
    }
}