package com.simplerest.server.objects;

import java.util.ArrayList;
import java.util.List;

public class Bok {
    private final int utgitt_ar;
    private final String tittel;
    private List<Forfatter> forfattere;
    private List<Integer> forfatterIds;
    public int id;

    public Bok(int utgitt_ar, String tittel, List<Integer> forfatterIds) {
        this.utgitt_ar = utgitt_ar;
        this.tittel = tittel;
        this.forfattere = new ArrayList<>();
        this.forfatterIds = forfatterIds;
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

    public List<Integer> getForfatterIds() {
        return forfatterIds;
    }
}
