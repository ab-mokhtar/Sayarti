package com.example.sayarti;

public class Product {
    private String nom,prix,type_carburant,puissance_fiscale,puissance_ch,boite,path,trans;

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setType_carburant(String type_carburant) {
        this.type_carburant = type_carburant;
    }

    public void setPuissance_fiscale(String puissance_fiscale) {
        this.puissance_fiscale = puissance_fiscale;
    }

    public void setPuissance_ch(String puissance_ch) {
        this.puissance_ch = puissance_ch;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        int pos=prix.length();
        String ch1=prix.substring(pos-3,pos);
        ch1=" "+ch1;
        String ch2=prix.substring(0,pos-3);
        prix=ch2+ch1;

        return prix;
    }

    public String getType_carburant() {
        return type_carburant;
    }

    public String getPuissance_fiscale() {
        return puissance_fiscale;
    }

    public String getPuissance_ch() {
        return puissance_ch;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        String ch = path;
        int x = ch.indexOf("sayartiv3");
        if (x>0){
        int y = ch.indexOf("wp-content");
        String ch1 = "https://www.sayarti.tn//";
        String res =ch1 + ch.substring(y);
        this.path = res;}
        else{
            this.path=path;
        }
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getBoite() {
        return boite;
    }

    public Product(String nom, String prix, String type_carburant, String puissance_fiscale,
                   String puissance_ch, String boite, String path,String trans) {
        this.nom = nom;
        this.prix = prix;
        this.type_carburant = type_carburant;
        this.puissance_fiscale = puissance_fiscale;
        this.puissance_ch = puissance_ch;
        this.boite = boite;
        this.trans = trans;
        setPath(path);
    }

    public Product(String nom) {
        this.nom = nom;

    }


}