package com.example.sayarti;




public class posi {
    private final Double lati;
    private final Double longi;
    private final String name;
    private final String marque;
    private final String tel;
    private final String type;

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

    public String getType() {
        return type;
    }

    public posi(Double lati, Double longi, String name, String marque, String tel, String type) {
        this.lati = lati;
        this.longi = longi;
        this.name = name;
        this.marque = marque;
        this.tel = tel;
        this.type=type;
    }



    @Override
    public String toString() {
        return  name +"/"+
                 lati +"/"+
                + longi +"/"
                 + marque  ;
    }
}
