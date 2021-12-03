package com.niconi21.turismoargentina.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.PostDetalleActivity;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.services.PublicacionService;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Validaciones;

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
        private PublicacionService _publicacionService;
        private Publicacion _publicacion;

        public ViewHoldersPublicacion(@NonNull View itemView) {
            super(itemView);
            this._publicacionService = new PublicacionService(this.itemView.getContext(), this.itemView);
            this.context = itemView.getContext();
            this.titulo = itemView.findViewById(R.id.tituloPost);
            this.fecha = itemView.findViewById(R.id.fechaPost);
            this.usuario = itemView.findViewById(R.id.usuarioItemPost);
            this.descripcion = itemView.findViewById(R.id.descripcionItemPost);
            this.imagen = itemView.findViewById(R.id.imgPostItem);
            this.card = itemView.findViewById(R.id.cardItemPost);
            this.editar = itemView.findViewById(R.id.editarPostItem);
            this.eliminar = itemView.findViewById(R.id.eliminarPostItem);
            this.favorito = itemView.findViewById(R.id.agregarFavoritoPostItem);

        }

        private void _ocultarIconos() {
            switch (this._publicacion.getTipo()) {
                case "post":
                    this.editar.setVisibility(View.GONE);
                    this.eliminar.setVisibility(View.GONE);
                    break;
                case "favorito":
                    this.editar.setVisibility(View.GONE);
                    this.eliminar.setVisibility(View.GONE);
                    this.favorito.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    this.favorito.setContentDescription("Eliminar de favoritos");
                    break;
                case "misPost":
                    this.favorito.setVisibility(View.GONE);

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
            this.agregarFavorito();
            this.eliminarPublicacion();
            this.editarPublicacion();
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

        public void agregarFavorito() {
            this.favorito.setOnClickListener(v -> {
                if (!this._publicacion.getTipo().equalsIgnoreCase("favorito")) {
                    this._publicacionService.agregarFavorito(this._publicacion.getId());

                } else {
                    this._publicacionService.eliminarFavorito(this._publicacion.getId());
                }
            });
        }

        public void eliminarPublicacion() {
            this.eliminar.setOnClickListener(v -> {
                this._publicacionService.eliminarPublicacion(this._publicacion.getId());
            });
        }

        public void editarPublicacion() {
            this.editar.setOnClickListener(v -> {
                View content = LayoutInflater.from(context).inflate(R.layout.dialog_actualizar_post, null);

                TextInputLayout descripcion = content.findViewById(R.id.tfdescripcionPostDialog);
                descripcion.getEditText().setText(this._publicacion.getDescripcion());
                Validaciones.textChangedListener(descripcion, context.getString(R.string.mgsErrorDescripcion));

                MaterialAlertDialogBuilder dialogBuilder = Mensajes.Dialogs(this.context, context.getString(R.string.actualizarPublicacion), context.getString(R.string.msgDescripcionActualizarPost));

                dialogBuilder.setView(content);
                dialogBuilder.setPositiveButton(context.getString(R.string.actualizarPublicacion), (dialog, which) -> {
                    Boolean isValidDescripcion = Validaciones.isValid(descripcion, context.getString(R.string.mgsErrorDescripcion));
                    if(isValidDescripcion){
                        String descripcionString = descripcion.getEditText().getText().toString();
                        this._publicacionService.actualizarPublicacion(this._publicacion.getId(), descripcionString);
                    }else{
                        Mensajes.MensajeSnackBar(itemView.getRootView(), context.getString(R.string.mgsErrorGeneral), Snackbar.LENGTH_SHORT);
                    }
                });
                dialogBuilder.create();
                dialogBuilder.show();
            });
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
