package com.niconi21.turismoargentina.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Publicacion {

    private String id = "";
    private String imagen = "";
    private String titulo = "";
    private String descripcion = "";
    private Coordenada ubicacion = new Coordenada();
    private Usuario usuario = new Usuario();
    private ArrayList<String> etiquetas = new ArrayList<String>();
    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
    private Date fecha = new Date();
    private String tipo = "";


    public Publicacion() {
    }

    public ArrayList<Publicacion> JsonArrayToPublicaciones(JSONArray publicacionesArray) {
        ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();
        try {
            for (int i = 0; i < publicacionesArray.length(); i++) {
                Publicacion publicacion = new Publicacion();
                JSONObject actual = publicacionesArray.getJSONObject(i);
                publicacion.setId(actual.getString("_id"));
                publicacion.setTitulo(actual.getString("titulo"));
                publicacion.setDescripcion(actual.getString("descripcion"));
                publicacion.setImagen(actual.getString("imagen"));
                publicacion.setUbicacion(publicacion.getUbicacion().jsonObjectToCoordenada(actual.getJSONObject("ubicacion")));
                publicacion.setEtiquetas(publicacion.jsonArrayToEtiquetas(actual.getJSONArray("etiquetas")));
                publicacion.setUsuario(publicacion.getUsuario().jsonObjectToUsuario(actual.getJSONObject("usuario"), false, false));
                publicaciones.add(publicacion);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return publicaciones;
        }
    }

    public Publicacion jsonObjectToPublicacion(JSONObject publicacionJson, Boolean getUsuario) {
        Publicacion publicacion = new Publicacion();
        try {
            publicacion.setId(publicacionJson.getString("_id"));
            publicacion.setTitulo(publicacionJson.getString("titulo"));
            publicacion.setDescripcion(publicacionJson.getString("descripcion"));
            publicacion.setUbicacion(this.getUbicacion().jsonObjectToCoordenada(publicacionJson.getJSONObject("ubicacion")));
            if (getUsuario)
                publicacion.setUsuario((new Usuario()).jsonObjectToUsuario(publicacionJson.getJSONObject("usuario"), false, false));
            publicacion.setImagen(publicacionJson.getString("imagen"));
            publicacion.setEtiquetas(this.jsonArrayToEtiquetas(publicacionJson.getJSONArray("ṕetiquetas")));
            publicacion.setComentarios(this.jsonArrayToComentario(publicacionJson.getJSONArray("comentarios")));
            publicacion.setFecha(((Date) publicacionJson.get("fecha")));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return publicacion;
        }
    }

    private ArrayList<String> jsonArrayToEtiquetas(JSONArray etiquetasArray) {
        ArrayList<String> etiquetas = new ArrayList<String>();
        try {
            for (int i = 0; i < etiquetasArray.length(); i++) {
                etiquetas.add(etiquetasArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return etiquetas;

        }
    }

    private ArrayList<Comentario> jsonArrayToComentario(JSONArray comentariosArray) {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        try {
            for (int i = 0; i < comentariosArray.length(); i++) {
                JSONObject comentarioJson = comentariosArray.getJSONObject(i);
                Comentario comentario = new Comentario();

                comentario.setComentario(comentarioJson.getString("comentario"));
                comentario.setUsuario((new Usuario()).jsonObjectToUsuario(comentarioJson.getJSONObject("usuario"), false, false));

                comentarios.add(((Comentario) comentariosArray.get(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return comentarios;

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Coordenada getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Coordenada ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getFecha() {
        return DateFormat.getDateInstance(DateFormat.LONG).format(this.fecha);
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "id='" + id + '\'' +
                ", imagen='" + imagen + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ubicacion=" + ubicacion +
                ", usuario=" + usuario +
                ", etiquetas=" + etiquetas +
                ", comentarios=" + comentarios +
                ", fecha=" + fecha +
                '}';
    }
}
