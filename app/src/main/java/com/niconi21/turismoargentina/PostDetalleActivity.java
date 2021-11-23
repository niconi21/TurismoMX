package com.niconi21.turismoargentina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.niconi21.turismoargentina.adapters.ComentarioAdapter;
import com.niconi21.turismoargentina.models.Comentario;
import com.niconi21.turismoargentina.tools.Implementacion;

import java.util.ArrayList;

public class PostDetalleActivity extends AppCompatActivity {
    private RecyclerView _recyclerView;
    private ArrayList<Comentario> _comentarios = new ArrayList<Comentario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detalle);

        this._establecerItems();
        this._llenarComentarios();
    }

    private void _establecerItems() {
        this._recyclerView = (RecyclerView) findViewById(R.id.listaComentarios);

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