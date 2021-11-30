package com.niconi21.turismoargentina.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Peticiones {

    private RequestQueue _requestQueue;

    public Peticiones(Context context) {
        _requestQueue = Volley.newRequestQueue(context);
    }

    public JsonObjectRequest getJson(int metodo, String url, Response.Listener<JSONObject> res, Response.ErrorListener error) {
        return new JsonObjectRequest(metodo, url, null, res, error);
    }

    public JsonObjectRequest getJson(int metodo, String url, HashMap<String, String> params, Response.Listener<JSONObject> res, Response.ErrorListener error) {
        JSONObject body = new JSONObject(params);
        return new JsonObjectRequest(metodo, url, body, res, error);
    }

    public void agregarCola(JsonObjectRequest request) {
        this._requestQueue.add(request);
    }
}

