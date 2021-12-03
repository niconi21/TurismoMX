package com.niconi21.turismoargentina.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;


import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputLayout;
import com.niconi21.turismoargentina.DatosPostActivity;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.Publicacion;
import com.niconi21.turismoargentina.models.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ComentarioServices {

    private Peticiones _peticiones;
    private Context context;
    private View view;
    final private String ENDPOINT = "/publicacion";

    public ComentarioServices(Context context, View view) {
        this.context = context;
        this.view = view;
        this._peticiones = new Peticiones(context);
    }


    @SuppressLint("NewApi")
    public void agregarComentario(String comentario, String _id, ImageButton btn, TextInputLayout til) {
        try {
            JSONObject body = new JSONObject();
            body.put("comentario", comentario);
            JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/agregar/comentario/"+_id, body,
                    response -> {
                        btn.setEnabled(true);
                        til.getEditText().setText("");
                        this._showMessage(response);
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
