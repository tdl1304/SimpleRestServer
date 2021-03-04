package com.simplerest.server.objects;

public class Adresse {
    private final String gateadresse;
    private final int hus_nr;
    private final int post_nr;
    private final String land;
    private final String by;
    public int id;

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

    public int getId() {
        return id;
    }
}
