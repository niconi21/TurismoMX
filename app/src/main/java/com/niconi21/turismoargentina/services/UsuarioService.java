package com.niconi21.turismoargentina.services;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.db.UsuarioEntity;
import com.niconi21.turismoargentina.pages.usuario.PerfilFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioService {
    private Peticiones _peticiones;
    private Context context;
    private View view;
    final private String ENDPOINT = "/usuario";

    public UsuarioService(Context context, View view) {
        this.context = context;
        this.view = view;
        this._peticiones = new Peticiones(context);
    }

    public void cambiarClave(String claveActual, String claveNueva){
        try {
            JSONObject body = new JSONObject();
            body.put("claveActual", claveActual);
            body.put("claveNueva", claveNueva);
            JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.PUT, context.getString(R.string.URL_API) + this.ENDPOINT + "/actualizarClave/" , body,
                    response -> this._showMessage(response),
                    error -> this._showMessage(error)
            );
            this._peticiones.agregarCola(peticion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void actualizarUsuario(String nombre, String correo, UsuarioEntity usuario, PerfilFragment perfil){
        try {
            JSONObject body = new JSONObject();
            body.put("nombre", nombre);
            body.put("correo", correo);
            JsonObjectRequest peticion = this._peticiones.getJsonWithHeader(Request.Method.PUT, context.getString(R.string.URL_API) + this.ENDPOINT + "/actualizar/" , body,
                    response -> {
                        this._showMessage(response);
                        SingletonDB.updateUsuario(nombre, correo, usuario.uid);
                        perfil.llenarDatosUsuario();
                    },
                    error -> this._showMessage(error)
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
