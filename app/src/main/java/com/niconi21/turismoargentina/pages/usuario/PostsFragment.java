package com.niconi21.turismoargentina.pages.usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.adapters.PublicacionAdapter;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.tools.Implementacion;

import java.util.ArrayList;

public class PostsFragment extends Fragment {

    private ArrayList<Publicacion> _publicaciones = new ArrayList<Publicacion>();
    private RecyclerView _recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._recyclerView = (RecyclerView) view.findViewById(R.id.listaPosts);
        this._llenarListaPosts();

    }

    private void _llenarListaPosts() {

        for (int i = 0; i < 100; i++) {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo("Posts Titulo " + i);
            this._publicaciones.add(publicacion);
        }

        PublicacionAdapter publicacionAdapter = new PublicacionAdapter(this._publicaciones);
        Implementacion.llenarListaRecycleView(this.getContext(), this._recyclerView, publicacionAdapter ,this._publicaciones);
    }
}