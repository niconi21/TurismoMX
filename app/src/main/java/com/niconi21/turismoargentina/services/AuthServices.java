package com.niconi21.turismoargentina.services;

import android.content.Context;
import android.view.View;

import androidx.navigation.NavController;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.db.UsuarioEntity;
import com.niconi21.turismoargentina.models.Result;
import com.niconi21.turismoargentina.models.Usuario;
import com.niconi21.turismoargentina.tools.Navegacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AuthServices {

    private Peticiones _peticiones;
    private Context context;
    private View view;
    final private String ENDPOINT = "/auth";

    public AuthServices(Context context, View view) {
        this.context = context;
        this.view = view;
        this._peticiones = new Peticiones(context);
    }

    public void Login(String correo, String clave, NavController navigation) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("correo", correo);
        parametros.put("clave", clave);
        JsonObjectRequest peticion = this._peticiones.getJson(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/login", parametros,
                response -> {
                    try {
                        this._showMessage(response);
                        if (response.getBoolean("ok")) {
                            Result result = new Result();
                            JSONObject resultJson = response.getJSONObject("result");
                            Usuario usuario = result.parseResultUsuario(resultJson).getUsuario();
                            UsuarioEntity usuarioEntity = new UsuarioEntity();
                            usuarioEntity.nombre = usuario.getNombre();
                            usuarioEntity.correo = usuario.getCorreo();
                            usuarioEntity.token = result.perseResultToken(resultJson);
                            SingletonDB.insertUsuario(usuarioEntity);
                            Navegacion.setNavegacion(navigation, R.id.usuarioActivity);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> this._showMessage(error)
        );
        this._peticiones.agregarCola(peticion);
    }

    public void registro(Usuario usuario) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("correo", usuario.getCorreo());
        parametros.put("clave", usuario.getClave());
        JsonObjectRequest peticion = this._peticiones.getJson(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/registro", parametros,
                response -> this._showMessage(response)
                ,
                error -> this._showMessage(error)
        );
        this._peticiones.agregarCola(peticion);
    }

    public void recuperarClave(String correo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("correo", correo);
        JsonObjectRequest peticion = this._peticiones.getJson(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/recuperarClave", parametros,
                response -> this._showMessage(response),
                error -> this._showMessage(error)
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
