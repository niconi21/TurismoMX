package com.niconi21.turismoargentina.models;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {

    private String nombre = "";
    private String id = "";
    private String clave = "";
    private String correo = "";
    private Date fechaAlta = null;
    private ArrayList<Publicacion> favoritos = null;

    public Usuario() {
    }

    public Usuario jsonObjectToUsuario(JSONObject usuarioJson, Boolean getCorreo, Boolean getFecha) {
        Usuario usuario = new Usuario();
        try {
            usuario.setId(usuarioJson.getString("_id"));
            usuario.setNombre(usuarioJson.getString("nombre"));
            if (getCorreo)
                usuario.setCorreo(usuarioJson.getString("correo"));
            if (getFecha)
                usuario.setFechaAlta(((Date) usuarioJson.get("fechaAlta")));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return usuario;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public ArrayList<Publicacion> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<Publicacion> favoritos) {
        this.favoritos = favoritos;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nombre: " + getNombre() +
                "correo: " + getCorreo();
    }
}
