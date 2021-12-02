package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.niconi21.turismoargentina.adapters.ComentarioAdapter;
import com.niconi21.turismoargentina.models.Comentario;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.services.PublicacionService;
import com.niconi21.turismoargentina.tools.Implementacion;

import java.text.DateFormat;
import java.util.ArrayList;

public class PostDetalleActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private TextView _titulo;
    private TextView _descripcion;
    private TextView _fecha;
    private TextView _usuario;
    private ImageView _imagen;
    private ChipGroup _chipEtiquetas;

    private String _id;
    private ArrayList<Comentario> _comentarios = new ArrayList<Comentario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detalle);
        this._id = getIntent().getStringExtra("id");
        this._establecerItems();
        this._obtenerPublicacion();
    }

    private void _establecerItems() {
        this._recyclerView = findViewById(R.id.listaComentarios);
        this._titulo = findViewById(R.id.tituloPostDetalle);
        this._fecha = findViewById(R.id.fechaPostDetalle);
        this._imagen = findViewById(R.id.imagenPostDetalle);
        this._chipEtiquetas = findViewById(R.id.etiquetasPostDetalle);
        this._descripcion = findViewById(R.id.descripcionPostDetalle);
        this._usuario = findViewById(R.id.usuarioPostDetalle);
    }

    private void _obtenerPublicacion() {
        View view = getWindow().getDecorView().getRootView();
        PublicacionService publicacionService = new PublicacionService(this.getApplicationContext(), view);
        publicacionService.obtenerPublicacion(this, this._id);
    }

    @SuppressLint("NewApi")
    public void establecerDatos(Publicacion publicacion) {
        System.out.println(publicacion);
        this._usuario.setText(publicacion.getUsuario().getNombre());
        this._titulo.setText(publicacion.getTitulo());
        publicacion.getEtiquetas().forEach( etiqueta ->{
            Chip chip = new Chip(this);
            chip.setText(etiqueta);
            this._chipEtiquetas.addView(chip);
        });
        this._descripcion.setText(publicacion.getDescripcion());
        this._comentarios = publicacion.getComentarios();
        this._fecha.setText(publicacion.getFecha());
        Glide.with(getApplicationContext()).load(publicacion.getImagen()).into(this._imagen);
        this._llenarComentarios();
    }

    private void _llenarComentarios() {

        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(this._comentarios);
        Implementacion.llenarListaRecycleView(this.getApplicationContext(), this._recyclerView, comentarioAdapter, this._comentarios);

    }
}