package com.niconi21.turismoargentina;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.models.Coordenada;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.services.PublicacionService;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Permisos;
import com.niconi21.turismoargentina.tools.Validaciones;

import java.util.ArrayList;

public class DatosPostActivity extends AppCompatActivity {

    private FusedLocationProviderClient _fusedLocationClient;
    private Bitmap _imgIntent;

    private Button _btnSubitPost;

    private ImageView _imageView;
    private ImageButton _imgbAgregarTag;

    private TextInputLayout _titulo;
    private TextInputLayout _descripcion;
    private TextInputLayout _etiqueta;

    private ChipGroup _chipGroupTags;

    private ArrayList<String> _tags = new ArrayList<String>();
    private Coordenada _coordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_post);

        this._establecerItems();
        this._recuperarInfoFromIntent();
        this._establecerImagen();
        this._ubicacion();
        this._agregarTags();
        this._subirPost();
    }

    private void _recuperarInfoFromIntent() {
        Intent intent = getIntent();
        this._imgIntent = (Bitmap) intent.getParcelableExtra("imagen");
    }

    private void _establecerItems() {
        this._btnSubitPost = findViewById(R.id.btnSubirPost);
        this._imageView = findViewById(R.id.imgDatosPosts);
        this._fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        this._titulo = findViewById(R.id.tfTituloDatosPost);
        this._descripcion = findViewById(R.id.tfDescripcionDatosPost);
        this._etiqueta = findViewById(R.id.tfEtiquetaDatosPost);
        this._imgbAgregarTag = findViewById(R.id.imgbAgregarTag);
        this._chipGroupTags = findViewById(R.id.tagsDatosPost);

        Validaciones.textChangedListener(this._titulo, getString(R.string.mgsErrorTitulo));
        Validaciones.textChangedListener(this._descripcion, getString(R.string.mgsErrorDescripcion));
        Validaciones.textChangedListener(this._etiqueta, getString(R.string.mgsErrorEtiqueta));
    }

    private void _establecerImagen() {
        this._imageView.setImageBitmap(this._imgIntent);
    }


    @SuppressLint("MissingPermission")
    private void _ubicacion() {
        if (Permisos.permisoUbicacion(getApplicationContext(), this)) {
            this._fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                this._coordenadas = new Coordenada(location.getLatitude(), location.getAltitude());
            });
        }else{
            Mensajes.MensajeToast(this, "Acceso a la ubicación denegado", Toast.LENGTH_SHORT);
        }
    }

    private void _agregarTags() {
        this._imgbAgregarTag.setOnClickListener(view -> {
            Boolean isValidTag = Validaciones.isValid(this._etiqueta, getString(R.string.mgsErrorEtiqueta));
            if (isValidTag) {
                String etiqueta = this._etiqueta.getEditText().getText().toString().toLowerCase();
                if (this._tags.indexOf(etiqueta) == -1) {
                    this._tags.add(etiqueta);
                    Chip chip = new Chip(this);
                    chip.setText(etiqueta);
                    chip.setChipIconVisible(true);
                    chip.setChipIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_cancel_24));
                    chip.setIconEndPadding(1);
                    chip.setOnClickListener(v -> {
                        Chip actual = ((Chip) v);
                        String texto = actual.getText().toString().toLowerCase();
                        this._tags.remove(texto);
                        this._chipGroupTags.removeView(v);
                    });
                    this._chipGroupTags.addView(chip);
                }
            }
        });
    }

    private void _subirPost() {
        this._btnSubitPost.setOnClickListener( v -> {
            Boolean isValidTitulo = Validaciones.isValid(this._titulo, getString(R.string.mgsErrorTitulo));
            Boolean isValidDescription = Validaciones.isValid(this._descripcion, getString(R.string.mgsErrorDescripcion));

            if(isValidDescription && isValidTitulo){

                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(this._titulo.getEditText().getText().toString());
                publicacion.setDescripcion(this._descripcion.getEditText().getText().toString());
                publicacion.setUbicacion(this._coordenadas);
                publicacion.setEtiquetas(this._tags);

                PublicacionService publicacionService = new PublicacionService(this.getApplicationContext(), v);
                publicacionService.agregarPublicacion(publicacion,this);
            }else{
                Mensajes.MensajeSnackBar(v, getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
            }
        });

    }

    public void regresar(View view) {
        onBackPressed();
    }
}