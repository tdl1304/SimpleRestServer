package com.simplerest.server.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Forfatter {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
    private int fodt_ar;
    private String fornavn;
    private String etternavn;

    @OneToOne
    private Adresse adresse;

    @ManyToMany(targetEntity = Bok.class)
    private Set bokSet;

    public Forfatter() {
        super();
    }

    public Forfatter(int id, int fodt_ar, String fornavn, String etternavn, Adresse adresse, Set bokSet) {
        this.id = id;
        this.fodt_ar = fodt_ar;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.bokSet = bokSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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

    public Set getBokSet() {
        return bokSet;
    }

    public void setBokSet(Set bokSet) {
        this.bokSet = bokSet;
    }
}
