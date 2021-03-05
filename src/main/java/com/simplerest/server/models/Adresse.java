package com.simplerest.server.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name="adresse")
public class Adresse {
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
    private String gateadresse;
    private int hus_nr;
    private int post_nr;
    private String land;
    private String by;

    public Adresse() {}

    public Adresse(String gateadresse, int hus_nr, int post_nr, String land, String by) {
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
