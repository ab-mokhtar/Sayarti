package com.example.sayarti;

public class Notes {
    String matricule;
    String note;
    String date;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    String uId;

    public Notes() {
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }
}

