package com.niconi21.turismoargentina.models;

import com.niconi21.turismoargentina.interfaces.IPublicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public Publicacion() {
    }

    public Publicacion jsonObjectToPublicacion(JSONObject publicacionJson) {
        Publicacion publicacion = new Publicacion();
        try {
            publicacion.setTitulo(publicacionJson.getString("titulo"));
            publicacion.setDescripcion(publicacionJson.getString("descripcion"));
            publicacion.setUbicacion(this.getUbicacion().jsonObjectToCoordenada(publicacionJson.getJSONObject("ubicacion")));
            publicacion.setUsuario((new Usuario()).jsonObjectToUsuario(publicacionJson.getJSONObject("usuario")));
            publicacion.setImagen(publicacionJson.getString("imagen"));
            publicacion.setEtiquetas(this.jsonArrayToEtiquetas(publicacionJson.getJSONArray("etiquetas")));
            publicacion.setComentarios(this.jsonArrayToComentario(publicacionJson.getJSONArray("comentarios")));
            publicacion.setFecha(((Date) publicacionJson.get("fecha")));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return publicacion;
        }
    }

    private ArrayList<String> jsonArrayToEtiquetas(JSONArray etiquetasArray) {
        return null;
    }

    private ArrayList<Comentario> jsonArrayToComentario(JSONArray comentariosArray) {
        return null;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}
