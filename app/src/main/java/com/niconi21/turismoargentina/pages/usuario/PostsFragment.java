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
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.services.PublicacionService;

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

        this._establecerItems(view);
        this._llenarListaPosts(view);

    }
    private void _establecerItems(View view) {
        this._recyclerView = (RecyclerView) view.findViewById(R.id.listaPosts);
    }
    private void _llenarListaPosts(View view) {
        PublicacionService publicacionService = new PublicacionService(this.getContext(), view);
        publicacionService.obtenerPublicaciones(this._recyclerView);

    }
}