package com.simplerest.server.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bok {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int utgitt_ar;
    private String tittel;

    @ManyToMany(targetEntity = Forfatter.class)
    private Set forfatterSet;

    public Bok() {}

    public Bok(int id, int utgitt_ar, String tittel, Set forfatterSet) {
        this.id = id;
        this.utgitt_ar = utgitt_ar;
        this.tittel = tittel;
        this.forfatterSet = forfatterSet;
    }

    public int getUtgitt_ar() {
        return utgitt_ar;
    }

    public String getTittel() {
        return tittel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set getForfatterSet() {
        return forfatterSet;
    }

    public void setForfatterSet(Set forfatterSet) {
        this.forfatterSet = forfatterSet;
    }
}
