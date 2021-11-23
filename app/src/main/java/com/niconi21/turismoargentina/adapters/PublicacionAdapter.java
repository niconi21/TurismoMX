package com.niconi21.turismoargentina.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niconi21.turismoargentina.PostDetalleActivity;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Publicacion;

import java.util.ArrayList;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.ViewHoldersPublicacion> {

    public ArrayList<Publicacion> publicaciones;

    public PublicacionAdapter(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    @NonNull
    @Override
    public ViewHoldersPublicacion onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_publicacion, parent, false);
        return new ViewHoldersPublicacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldersPublicacion holder, int position) {
        holder.asiganarDatos(publicaciones.get(position));
        holder.onClickListener();
    }

    @Override
    public int getItemCount() {
        return this.publicaciones.size();
    }


    public class ViewHoldersPublicacion extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titulo;
        public ImageView imagen;
        public CardView card;
        public Context context;

        private Publicacion _publicacion;
        public ViewHoldersPublicacion(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.titulo = (TextView) itemView.findViewById(R.id.tituloPost);
            this.imagen = (ImageView) itemView.findViewById(R.id.imgPostItem);
            this.card = (CardView) itemView.findViewById(R.id.cardItemPost);

        }

        public void asiganarDatos(Publicacion publicacion) {
            this._publicacion = publicacion;
            this.titulo.setText(publicacion.getTitulo());
            this.descargarImagen(this.itemView);
        }

        public void descargarImagen(View itemView) {
            this.imagen.setOnLongClickListener(v -> {
                Bitmap finalBitmap = ((BitmapDrawable) this.imagen.getDrawable()).getBitmap();
                Glide.with(itemView.getContext()).load("https://cdn2.excelsior.com.mx/media/styles/image800x600/public/pictures/2021/06/09/2592367.jpg").into(this.imagen);

                MediaStore.Images.Media.insertImage(itemView.getContext().getContentResolver(), finalBitmap, "imagenPrueba.png", "yourDescription");
                Toast.makeText(itemView.getContext(), R.string.imagenSave, Toast.LENGTH_LONG).show();
                return true;
            });
        }

        public void onClickListener() {
            this.card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cardItemPost:
                    Intent intent = new Intent(this.context, PostDetalleActivity.class);
                    intent.putExtra("titulo",this._publicacion.titulo);
                    ((Activity)context).startActivityForResult(intent,1);
                    break;
            }
        }
    }
}
