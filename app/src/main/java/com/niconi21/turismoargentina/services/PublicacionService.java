package com.niconi21.turismoargentina.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.niconi21.turismoargentina.DatosPostActivity;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.adapters.PublicacionAdapter;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.models.Result;
import com.niconi21.turismoargentina.models.Usuario;
import com.niconi21.turismoargentina.tools.Implementacion;
import com.niconi21.turismoargentina.tools.Navegacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PublicacionService {

    private Peticiones _peticiones;
    private Context context;
    private View view;
    final private String ENDPOINT = "/publicacion";

    public PublicacionService(Context context, View view) {
        this.context = context;
        this.view = view;
        this._peticiones = new Peticiones(context);
    }

    @SuppressLint("NewApi")
    public void obtenerPublicaciones(RecyclerView recyclerView) {
         JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.GET, context.getString(R.string.URL_API) + this.ENDPOINT + "/obtener/todas", new JSONObject(),
                response -> {
                    try {
                        Result result = new Result();
                        ArrayList<Publicacion> publicaciones = result.parseResultPublicaciones(response.getJSONObject("result")).getPublicaciones();
                        publicaciones.forEach( publicacion -> {
                            publicacion.setTipo("post");
                        });
                        PublicacionAdapter publicacionAdapter = new PublicacionAdapter(publicaciones);
                        Implementacion.llenarListaRecycleView(this.context, recyclerView, publicacionAdapter, publicaciones);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ,
                error -> {
                    this._showMessage(error);
                }
        );

        this._peticiones.agregarCola(peticion);
    }


    @SuppressLint("NewApi")
    public void agregarPublicacion(Publicacion publicacion, DatosPostActivity activity) {
        try {
            JSONObject body = new JSONObject();
            JSONObject ubicacion = new JSONObject();

            String[] etiquetas = new String[publicacion.getEtiquetas().size()];
            etiquetas = publicacion.getEtiquetas().toArray(etiquetas);

            ubicacion.put("altitud", publicacion.getUbicacion().getAltitud());
            ubicacion.put("latitud", publicacion.getUbicacion().getLatitud());

            body.put("titulo", publicacion.getTitulo());
            body.put("descripcion", publicacion.getDescripcion());
            body.put("ubicacion", ubicacion);
            body.put("etiquetas", new JSONArray(etiquetas));
            JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/agregar", body,
                    response -> {
                        try {
                            this._showMessage(response);
                            Result result = new Result();
                            String id = result.parseResultPublicacion(response.getJSONObject("result")).getPublicacion().getId();
                            //Subir imagen
                            activity.regresar(this.view);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ,
                    error -> {
                        this._showMessage(error);
                    }
            );

            this._peticiones.agregarCola(peticion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void _showMessage(VolleyError error) {
        try {
            this._peticiones.showMessageSnakbar(this._peticiones.getJson(error.networkResponse), this.view);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void _showMessage(JSONObject response) {
        this._peticiones.showMessageSnakbar(response, this.view);
    }
}
