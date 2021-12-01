package com.niconi21.turismoargentina.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Result {

    private Usuario usuario = new Usuario();
    private Publicacion publicacion = new Publicacion();
    private ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();

    public Result() {
    }

    public String perseResultToken(JSONObject resultJson) {
        String token = "";
        try {
            token = resultJson.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return token;
        }
    }

    public Result parseResultPublicaciones(JSONObject resultJson) {
        Result result = new Result();
        try {
            result.setPublicaciones(this.publicacion.JsonArrayToPublicaciones(resultJson.getJSONArray("publicaciones")));
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return  result;
        }
    }

    public Result parseResultUsuario(JSONObject resultJson) {
        Result result = new Result();
        try {
            result.setUsuario(this.usuario.jsonObjectToUsuario(resultJson.getJSONObject("usuario"), true, true));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Result parseResultPublicacion(JSONObject resultJson) {
        Result result = new Result();
        try {
            result.setPublicacion(this.publicacion.jsonObjectToPublicacion(resultJson.getJSONObject("publicacion"), false));
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

    @Override
    public String toString() {
        return "Result{" +
                "usuario=" + usuario +
                ", publicacion=" + publicacion +
                '}';
    }
}
