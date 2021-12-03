package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.adapters.ComentarioAdapter;
import com.niconi21.turismoargentina.models.Comentario;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.services.ComentarioServices;
import com.niconi21.turismoargentina.services.PublicacionService;
import com.niconi21.turismoargentina.tools.Implementacion;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Validaciones;

import java.text.DateFormat;
import java.util.ArrayList;

public class PostDetalleActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private TextInputLayout _comentario;
    private TextView _titulo;
    private TextView _descripcion;
    private TextView _fecha;
    private TextView _usuario;
    private TextView _lugar;
    private ImageView _imagen;
    private ImageButton _enviarComentario;
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
        this._lugar = findViewById(R.id.lugarPostDetalle);
        this._enviarComentario = findViewById(R.id.btnEnviarComentario);
        this._comentario = findViewById(R.id.tfComentarioPost);

        Validaciones.textChangedListener(this._comentario, getString(R.string.mgsErrorComentario));
    }

    private void _obtenerPublicacion() {
        View view = getWindow().getDecorView().getRootView();
        PublicacionService publicacionService = new PublicacionService(this.getApplicationContext(), view);
        publicacionService.obtenerPublicacion(this, this._id);
    }

    @SuppressLint("NewApi")
    public void establecerDatos(Publicacion publicacion) {
        this._usuario.setText(publicacion.getUsuario().getNombre());
        this._titulo.setText(publicacion.getTitulo());
        publicacion.getEtiquetas().forEach(etiqueta -> {
            Chip chip = new Chip(this);
            chip.setText(etiqueta);
            this._chipEtiquetas.addView(chip);
        });
        this._descripcion.setText(publicacion.getDescripcion());
        this._comentarios = publicacion.getComentarios();
        this._fecha.setText(publicacion.getFecha());
        this._lugar.setText((publicacion.getUbicacion().getLugar()));
        Glide.with(getApplicationContext()).load(publicacion.getImagen()).into(this._imagen);
        this._llenarComentarios(publicacion);
    }

    public void subirComentario(View view) {
        String comentario = "";
        Boolean isValidComentario = Validaciones.isValid(this._comentario, getString(R.string.mgsErrorComentario));
        if (isValidComentario) {
            this._enviarComentario.setEnabled(false);
            comentario = this._comentario.getEditText().getText().toString();
            ComentarioServices comentarioServices = new ComentarioServices(this.getApplicationContext(), view);
            comentarioServices.agregarComentario(comentario, this._id, this._enviarComentario, this._comentario);
        } else
            Mensajes.MensajeSnackBar(view, getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
    }

    @SuppressLint("NewApi")
    private void _llenarComentarios(Publicacion publicacion) {
        this._comentarios.forEach(comentario -> {
            comentario.setAutor(comentario.getUsuario().getId().equalsIgnoreCase(publicacion.getUsuario().getId()));
        });

        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(this._comentarios);
        Implementacion.llenarListaRecycleView(this.getApplicationContext(), this._recyclerView, comentarioAdapter, this._comentarios);

    }
}