package com.niconi21.turismoargentina.models;

import java.util.Date;

public class Comentario {

    private String comentario;
    private Usuario usuario;
    private Date fecha;
    private Boolean isAutor;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAutor() {
        return isAutor;
    }

    public void setAutor(Boolean autor) {
        isAutor = autor;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "comentario='" + comentario + '\'' +
                ", usuario=" + usuario +
                ", fecha=" + fecha +
                ", isAutor=" + isAutor +
                '}';
    }
}
