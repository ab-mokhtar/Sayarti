package com.example.sayarti;

public class User {
    private String mat , nom , mdp, idUnique;

    public User(String id,String mat, String nom, String mdp)
    {
        this.idUnique = idUnique;
        this.mat = mat;
        this.nom = nom;
        this.mdp = mdp;
    }
    public User()
    {

    }

    public String getIdUnique() {
        return idUnique;
    }

    public void setIdUnique(String idUnique) {
        this.idUnique = idUnique;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
