package com.niconi21.turismoargentina.services;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;
import com.niconi21.turismoargentina.R;
import com.niconi21.turismoargentina.models.ResponseCustom;
import com.niconi21.turismoargentina.models.Usuario;
import com.niconi21.turismoargentina.tools.Mensajes;
import com.niconi21.turismoargentina.tools.Navegacion;

import org.json.JSONException;

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
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("correo", correo);
        parametros.put("clave", clave);
        ResponseCustom respuesta = new ResponseCustom();
        JsonObjectRequest peticion = this._peticiones.getJson(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/login", parametros,
                response -> {
                    respuesta.setResponse(response);
                    Mensajes.MensajeToast(this.context, respuesta.getMessage(), Toast.LENGTH_SHORT);
                    Navegacion.setNavegacion(navigation, R.id.usuarioActivity);
                },
                error -> {
                    System.out.println(error);
                });
        this._peticiones.agregarCola(peticion);
    }

    public void registro(Usuario usuario) {
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("nombre", usuario.getNombre());
        parametros.put("correo", usuario.getCorreo());
        parametros.put("clave", usuario.getClave());
        ResponseCustom respuesta = new ResponseCustom();
        JsonObjectRequest peticion = this._peticiones.getJson(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/registro", parametros,
                response -> {
                    respuesta.setResponse(response);
                    Mensajes.MensajeSnackBar(this.view, respuesta.getMessage(), Snackbar.LENGTH_SHORT);

                },
                error -> {
                    System.out.println(error.networkResponse.statusCode);
                    error.printStackTrace();
                });
        this._peticiones.agregarCola(peticion);
    }

    public void recuperarClave(String correo) {
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("correo", correo);
        ResponseCustom respuesta = new ResponseCustom();
        JsonObjectRequest peticion = this._peticiones.getJson(Request.Method.POST, context.getString(R.string.URL_API) + this.ENDPOINT + "/recuperarClave", parametros,
                response -> {
                    respuesta.setResponse(response);
                    Mensajes.MensajeSnackBar(this.view, respuesta.getMessage(), Snackbar.LENGTH_SHORT);

                },
                error -> {
                    System.out.println(error);
                });
        this._peticiones.agregarCola(peticion);
    }
}
