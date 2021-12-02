package com.niconi21.turismoargentina.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.models.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapaService {

    private Peticiones _peticiones;
    private Context context;
    private View view;
    final private String ENDPOINT = "/publicacion";

    public MapaService(Context context, View view) {
        this.context = context;
        this.view = view;
        this._peticiones = new Peticiones(context);
    }

    @SuppressLint("NewApi")
    public void obtenerPublicaciones(GoogleMap map) {
        JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.GET, context.getString(R.string.URL_API) + this.ENDPOINT + "/obtener/todas", new JSONObject(),
                response -> {
                    try {
                        Result result = new Result();
                        ArrayList<Publicacion> publicaciones = result.parseResultPublicaciones(response.getJSONObject("result")).getPublicaciones();
                        publicaciones.forEach(publicacion -> {
                            publicacion.setTipo("post");
                            LatLng actual = new LatLng(publicacion.getUbicacion().getLatitud(), publicacion.getUbicacion().getLongitud());
                            MarkerOptions marca = new MarkerOptions();
                            marca.position(actual);
                            marca.title(publicacion.getTitulo());
                            marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

                            map.addMarker(marca);

                        });


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
