package com.niconi21.turismoargentina.tools;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niconi21.turismoargentina.adapters.PublicacionAdapter;
import com.niconi21.turismoargentina.models.Publicacion;

import java.util.ArrayList;

public class Implementacion {


    public static void llenarListaRecycleView(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter, ArrayList lista) {
        recyclerView.setLayoutManager(
                new LinearLayoutManager(context));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}
