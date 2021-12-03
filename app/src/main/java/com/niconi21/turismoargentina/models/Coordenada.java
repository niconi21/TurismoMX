package com.niconi21.turismoargentina.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Coordenada {
    private double latitud;
    private double longitud;
    private String lugar;

    public Coordenada() {
        this(0, 0);
    }

    public Coordenada(double latitud, double longitud) {
        this.setLatitud(latitud);
        this.setLongitud(longitud);
    }

    public Coordenada jsonObjectToCoordenada(JSONObject coordenadaJson) {
        Coordenada coordenada = new Coordenada();
        try {
            coordenada.setLatitud(coordenadaJson.getDouble("latitud"));
            coordenada.setLongitud(coordenadaJson.getDouble("altitud"));
            coordenada.setLugar(coordenadaJson.getString("lugar"));
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return coordenada;
        }
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return "{" +
                "\"latitud\":" + latitud +
                ", \"altitud\":" + longitud +
                '}';
    }

}
