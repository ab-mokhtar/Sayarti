package com.example.sayarti;

public class Declaration {
    private String matricule;
    private String type_panne;
    private String localisation;
    private Boolean etat;
    private String  tel;

    public Declaration(String matricule, String type_panne, String localisation) {
        this.matricule = matricule;
        this.type_panne = type_panne;
        this.localisation = localisation;
        this.etat = false;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Declaration() {
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType_panne() {
        return type_panne;
    }

    public void setType_panne(String type_panne) {
        this.type_panne = type_panne;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
