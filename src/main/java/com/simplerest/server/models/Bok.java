package com.simplerest.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="bok")
public class Bok {
    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int id;
    private int utgitt_ar;
    private String tittel;

    @ManyToMany(targetEntity = Forfatter.class)
    @JsonIgnoreProperties(value="bok")
    private List<Forfatter> forfatter;

    public Bok() {}

    public Bok(int utgitt_ar, String tittel, List<Forfatter> forfatterList) {
        this.utgitt_ar = utgitt_ar;
        this.tittel = tittel;
        this.forfatter = forfatterList;
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

    public List<Forfatter> getForfatterList() {
        return forfatter;
    }

    public void setForfatterList(List<Forfatter> forfatterList) {
        this.forfatter = forfatterList;
    }
}
