package com.niconi21.turismoargentina.models;

public class Coordenada {
    private double latitud;
    private double altitud;

    public Coordenada() {
        this(0,0);
    }

    public Coordenada(double latitud, double longitud) {
        this.setLatitud(latitud);
        this.setAltitud(longitud);
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
