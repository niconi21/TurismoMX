package com.niconi21.turismoargentina.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Coordenada {
    private double latitud;
    private double altitud;

    public Coordenada() {
        this(0, 0);
    }

    public Coordenada(double latitud, double longitud) {
        this.setLatitud(latitud);
        this.setAltitud(longitud);
    }

    public Coordenada jsonObjectToCoordenada(JSONObject coordenadaJson) {
        Coordenada coordenada = new Coordenada();
        try {
            coordenada.setLatitud(coordenadaJson.getDouble("latitud"));
            coordenada.setAltitud(coordenadaJson.getDouble("altitud"));
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

    public double getAltitud() {
        return altitud;
    }

    public void setAltitud(double altitud) {
        this.altitud = altitud;
    }

    @Override
    public String toString() {
        return "Coordenada{" +
                "latitud=" + latitud +
                ", altitud=" + altitud +
                '}';
    }
}
