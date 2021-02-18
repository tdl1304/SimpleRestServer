package com.simplerest.server;


public class Forfatter {
    private final int fodt_ar;
    private final String fornavn;
    private final String etternavn;
    private Adresse adresse;
    public int id;

    public Forfatter(int fodt_ar, String fornavn, String etternavn) {
        this.fodt_ar = fodt_ar;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFodt_ar() {
        return fodt_ar;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public int getId() {
        return id;
    }
}
