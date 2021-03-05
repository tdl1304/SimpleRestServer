package com.simplerest.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="forfatter")
public class Forfatter {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    public int id;
    private int fodt_ar;
    private String fornavn;
    private String etternavn;

    @OneToOne
    private Adresse adresse;

    @ManyToMany(targetEntity = Bok.class)
    @JsonIgnoreProperties(value="forfatter")
    private List<Bok> bok;

    public Forfatter() {}

    public Forfatter(int fodt_ar, String fornavn, String etternavn, Adresse adresse, List<Bok> bokList) {
        this.fodt_ar = fodt_ar;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.bok= bokList;
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

    public List<Bok> getBokList() {
        return bok;
    }

    public void setBokList(List<Bok> bokList) {
        this.bok= bokList;
    }
}
