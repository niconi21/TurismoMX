package com.niconi21.turismoargentina.pages.usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.adapters.PublicacionAdapter;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.tools.Implementacion;

import java.util.ArrayList;


public class misPostsFragment extends Fragment {

    private RecyclerView _recyclerView;
    private ArrayList<Publicacion> _misPosts = new ArrayList<Publicacion>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this._recyclerView = (RecyclerView) view.findViewById(R.id.listaMisPosts);
        this._llenarListaPosts();
    }

    private void _llenarListaPosts() {
        for (int i = 0; i < 100; i++) {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo("Mi post Titulo " + i);
            this._misPosts.add(publicacion);
        }
        PublicacionAdapter publicacionAdapter = new PublicacionAdapter(this._misPosts);
        Implementacion.llenarListaRecycleView(this.getContext(), this._recyclerView, publicacionAdapter, this._misPosts);
    }
}