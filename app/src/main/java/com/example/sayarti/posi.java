package com.example.sayarti;

public class posi {
    private Double lati,longi;
    private String name,marque,tel;

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

    public String getTel() {
        return tel;
    }

    public posi(Double lati, Double longi, String name, String marque, String tel) {
        this.lati = lati;
        this.longi = longi;
        this.name = name;
        this.marque = marque;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return  name +"/"+
                 lati +"/"+
                + longi +"/"
                 + marque  ;
    }
}
