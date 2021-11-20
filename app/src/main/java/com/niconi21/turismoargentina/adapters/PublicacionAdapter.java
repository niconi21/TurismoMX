package com.niconi21.turismoargentina.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Publicacion;

import java.util.ArrayList;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.ViewHoldersDatos> {

    ArrayList<Publicacion> publicaciones;

    public PublicacionAdapter(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public PublicacionAdapter.ViewHoldersDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_publicacion, parent, false);
        return new ViewHoldersDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicacionAdapter.ViewHoldersDatos holder, int position) {
        holder.asiganrDatos(publicaciones.get(position));

    }

    @Override
    public int getItemCount() {
        return this.publicaciones.size();
    }

    public class ViewHoldersDatos extends RecyclerView.ViewHolder {

        public TextView titulo;
        public ImageView imagen;

        public ViewHoldersDatos(@NonNull View itemView) {

            super(itemView);
            this.titulo = (TextView) itemView.findViewById(R.id.tituloPost);
            this.imagen = (ImageView) itemView.findViewById(R.id.imgPostItem);
            this.imagen.setOnLongClickListener(v -> {
                Toast.makeText(itemView.getContext(), "Imagen", Toast.LENGTH_LONG).show();
                Glide.with(itemView.getContext()).load(R.drawable.common_google_signin_btn_icon_dark).into(this.imagen);

                return true;
            });
        }

        public void asiganrDatos(Publicacion publicacion) {
            this.titulo.setText(publicacion.getTitulo());


        }
    }
}
