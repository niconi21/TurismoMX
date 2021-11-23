package com.niconi21.turismoargentina.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Comentario;

import java.util.ArrayList;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ViewHolderComentario> {

    public ArrayList<Comentario> comentarios;

    public ComentarioAdapter(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ViewHolderComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comentario, parent, false);
        return new ViewHolderComentario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderComentario holder, int position) {
        holder.asignarDatos(this.comentarios.get(position));
    }

    @Override
    public int getItemCount() {
        return this.comentarios.size();
    }

    public class ViewHolderComentario extends RecyclerView.ViewHolder {

        public TextView comentario;
        public ViewHolderComentario(@NonNull View itemView) {
            super(itemView);
            this.comentario = (TextView)itemView.findViewById(R.id.comentarioItemC);

        }

        public void asignarDatos(Comentario comentario) {
            this.comentario.setText(comentario.getComentario());
        }
    }
}
