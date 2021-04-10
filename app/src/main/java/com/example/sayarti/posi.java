package com.example.sayarti;

public class posi {
    private Double lati,longi;
    private String name,marque;

    public Double getLati() {
        return lati;
    }

    public Double getLongi() {
        return longi;
    }

    public String getName() {
        return name;
    }

    public String getMarque() {
        return marque;
    }

    public posi(Double lati, Double longi, String name, String marque) {
        this.lati = lati;
        this.longi = longi;
        this.name = name;
        this.marque = marque;
    }
}
