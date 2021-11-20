package com.niconi21.turismoargentina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.niconi21.turismoargentina.tools.Permisos;

public class DatosPostActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_post);
        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("imagen");

        ImageView imagen = (ImageView) findViewById(R.id.imgDatosPosts);
        imagen.setImageBitmap(bitmap);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        this.ubicacion();
    }

    @SuppressLint("MissingPermission")
    private void ubicacion() {
        if (Permisos.permisoUbicacion(getApplicationContext(), this)) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                TextView tvCoordenadas = findViewById(R.id.tvCoordenadasPost);
                                tvCoordenadas.setText("Latitud: " + location.getLatitude() + "\n" + "Longitud: " + location.getAltitude());
                            }
                        }
                    });
        }
    }

    public void regresar(View view) {
        setResult(1);
        finish();
    }
}