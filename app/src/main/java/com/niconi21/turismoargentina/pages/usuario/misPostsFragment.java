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
import com.niconi21.turismoargentina.services.PublicacionService;
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
        this._establecerItems(view);
        this._llenarListaPosts(view);
    }

    private void _establecerItems(View view) {
        this._recyclerView = view.findViewById(R.id.listaMisPosts);
    }

    private void _llenarListaPosts(View view) {
        PublicacionService publicacionService = new PublicacionService(this.getContext(), view);
        publicacionService.obtenerMisPublicaciones(this._recyclerView);
    }
}