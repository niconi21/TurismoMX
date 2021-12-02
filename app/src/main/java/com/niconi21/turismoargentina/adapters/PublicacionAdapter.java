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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.niconi21.turismoargentina.PostDetalleActivity;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.UsuarioActivity;
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
        public TextView fecha;
        public TextView usuario;
        public TextView descripcion;
        public ImageView imagen;
        public ImageButton editar;
        public ImageButton eliminar;
        public ImageButton favorito;
        public CardView card;
        public Context context;
        private Publicacion _publicacion;

        public ViewHoldersPublicacion(@NonNull View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            this.titulo = itemView.findViewById(R.id.tituloPost);
            this.fecha = itemView.findViewById(R.id.fechaPost);
            this.usuario = itemView.findViewById(R.id.usuarioItemPost);
            this.descripcion = itemView.findViewById(R.id.descripcionItemPost);
            this.imagen = itemView.findViewById(R.id.imgPostItem);
            this.card = itemView.findViewById(R.id.cardItemPost);
            this.editar = itemView.findViewById(R.id.editarPostItem);
            this.eliminar = itemView.findViewById(R.id.eliminarPostItem);
            this.favorito = itemView.findViewById(R.id.favoritoPostItem);

        }

        private void _ocultarIconos() {
            switch (this._publicacion.getTipo()) {
                case "post":
                        this.editar.setVisibility(View.GONE);
                        this.eliminar.setVisibility(View.GONE);
                    break;
                case "favorito":
                    break;
                case "misPost":
                    break;
            }

        }

        public void asiganarDatos(Publicacion publicacion) {
            this._publicacion = publicacion;
            this._ocultarIconos();
            this.titulo.setText(publicacion.getTitulo());
            this.fecha.setText(publicacion.getFecha());
            this.descripcion.setText(publicacion.getDescripcion());
            this.usuario.setText(publicacion.getUsuario().getNombre());
            Glide.with(itemView.getContext()).load(this._publicacion.getImagen()).into(this.imagen);
            this.descargarImagen();
        }

        public void descargarImagen() {
            this.imagen.setOnLongClickListener(v -> {

                BottomSheetDialogFragment bsi = new BottomSheetsImagen();

                ((BottomSheetsImagen) bsi).publicacion = this._publicacion;
                bsi.show(((FragmentActivity) this.itemView.getContext()).getSupportFragmentManager(), "etiqueta");

                return true;
            });
        }

        public void onClickListener() {
            this.card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cardItemPost:
                    Intent intent = new Intent(this.context, PostDetalleActivity.class);
                    intent.putExtra("id", this._publicacion.getId());

                    ((Activity) context).startActivityForResult(intent, 1);
                    break;
            }
        }
    }
}
