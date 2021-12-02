package com.niconi21.turismoargentina.services;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.niconi21.turismoargentina.db.SingletonDB;
import com.niconi21.turismoargentina.tools.Mensajes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Peticiones {

    private RequestQueue _requestQueue;

    public Peticiones(Context context) {
        _requestQueue = Volley.newRequestQueue(context);
    }

    public JsonObjectRequest getJson(int metodo, String url, Response.Listener<JSONObject> res, Response.ErrorListener error) {
        return new JsonObjectRequest(metodo, url, null, res, error);
    }

    public JsonObjectRequest getJson(int metodo, String url, HashMap<String, Object> params, Response.Listener<JSONObject> res, Response.ErrorListener error) {
        JSONObject body = new JSONObject(params);
        return new JsonObjectRequest(metodo, url, body, res, error);
    }

    public JsonObjectRequest getJsonWithHeader(int metodo, String url, JSONObject body, Response.Listener<JSONObject> res, Response.ErrorListener error) {
        Map<String, String> headers = new HashMap<String, String >();
        headers.put("token", SingletonDB.getToken());
        headers.put("Content-Type", "application/json");
        return new JsonObjectRequest(metodo, url, body, res, error) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
    }
    public JsonObjectRequest getJsonWithHeaderIMG(int metodo, String url,  Response.Listener<JSONObject> res, Response.ErrorListener error, String img) {
        Map<String, String> headers = new HashMap<String, String >();
        headers.put("token", SingletonDB.getToken());
        headers.put("Content-Type", "application/json");
        return new JsonObjectRequest(metodo, url, null, res, error) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put("imagen", img);
                //Parámetros de retorno
                return params;
            }
        };
    }

    public void agregarCola(JsonObjectRequest request) {
        this._requestQueue.add(request);
    }

    public JSONObject getJson(NetworkResponse response) throws JSONException {
        String data = new String(response.data);
        return new JSONObject(data);
    }

    public void showMessageSnakbar(JSONObject json, View view) {
        try {
            Mensajes.MensajeSnackBar(view, json.getString("message"), Snackbar.LENGTH_SHORT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

