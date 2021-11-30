package com.niconi21.turismoargentina.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Result {

    private Usuario usuario = new Usuario();
    private Publicacion publicacion = new Publicacion();
    private ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();

    public Result() {
    }

    public Result parseResult(JSONObject resultJson) {
        Result result = new Result();
        try {
            result.setUsuario(this.usuario.jsonObjectToUsuario(resultJson.getJSONObject("usuario")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }


}
