package com.simplerest.server.entities;
import javax.persistence.*;
@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String gateadresse;
    private int hus_nr;
    private int post_nr;
    private String land;
    private String by;

    public Adresse() {}

    public Adresse(int id, String gateadresse, int hus_nr, int post_nr, String land, String by) {
        this.id = id;
        this.gateadresse = gateadresse;
        this.hus_nr = hus_nr;
        this.post_nr = post_nr;
        this.land = land;
        this.by = by;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getGateadresse() {
        return gateadresse;
    }

    public int getHus_nr() {
        return hus_nr;
    }

    public int getPost_nr() {
        return post_nr;
    }

    public String getLand() {
        return land;
    }

    public String getBy() {
        return by;
    }
}
