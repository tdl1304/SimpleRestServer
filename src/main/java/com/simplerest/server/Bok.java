package com.simplerest.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bok {
    private final int utgitt_ar;
    private final String tittel;
    private List<Forfatter> forfattere;
    public int id;

    public Bok(int utgitt_ar, String tittel) {
        this.utgitt_ar = utgitt_ar;
        this.tittel = tittel;
        this.forfattere = new ArrayList<>();
    }

    public int getUtgitt_ar() {
        return utgitt_ar;
    }

    public String getTittel() {
        return tittel;
    }

    public List<Forfatter> getForfattere() {
        return forfattere;
    }

    /**
     * Add forfatter to bok object
     * @param forfatter
     * @return true if success or false if already exists
     */
    public boolean addForfatter(Forfatter forfatter) {
        if(!forfattere.contains(forfatter)) {
            forfattere.add(forfatter);
            return true;
        } else return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
