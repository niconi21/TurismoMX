package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.ChipGroup;
import com.niconi21.turismoargentina.adapters.ComentarioAdapter;
import com.niconi21.turismoargentina.models.Comentario;
import com.niconi21.turismoargentina.services.PublicacionService;
import com.niconi21.turismoargentina.tools.Implementacion;

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
        this._llenarComentarios();
        this._llenarDatos();
    }

    private void _establecerItems() {
        this._recyclerView = findViewById(R.id.listaComentarios);
        this._titulo = findViewById(R.id.tituloPostDetalle);
        this._fecha = findViewById(R.id.fechaPostDetalle);
        this._imagen = findViewById(R.id.imagenPostDetalle);
        this._chipEtiquetas = findViewById(R.id.etiquetasPostDetalle);
        this._descripcion = findViewById(R.id.descripcionItemPost);
        this._usuario = findViewById(R.id.usuarioPostDetalle);
    }

    private void _llenarDatos(){
        View view = getWindow().getDecorView().getRootView();
        PublicacionService publicacionService=new PublicacionService(this.getApplicationContext(),view);

    }

    private void _llenarComentarios() {
        for (int i = 0; i < 10; i++) {
            Comentario comentario = new Comentario();
            comentario.setComentario("Comentario " + (i + 1));
            this._comentarios.add(comentario);
        }

        ComentarioAdapter comentarioAdapter = new ComentarioAdapter(this._comentarios);
        Implementacion.llenarListaRecycleView(this.getApplicationContext(), this._recyclerView, comentarioAdapter, this._comentarios);

    }
}