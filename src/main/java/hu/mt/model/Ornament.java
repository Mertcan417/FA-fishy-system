package hu.mt.model;

import java.io.Serializable;

public class Ornament implements Serializable {

    private String naam;
    private String omschrijving;
    private String kleur;
    private boolean kanOpLuchtpomp;

    public Ornament(String nm, String os, String kr, boolean kolp) {
        naam = nm;
        omschrijving = os;
        kleur = kr;
        kanOpLuchtpomp = kolp;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String nm) {
        naam = nm;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String os) {
        omschrijving = os;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kr) {
        kleur = kr;
    }

    public boolean isKanOpLuchtpomp() {
        return kanOpLuchtpomp;
    }

    public void setKanOpLuchtpomp(boolean kolp) {
        kanOpLuchtpomp = kolp;
    }

    public String toString() {
        return "Ornament\nnaam: " + naam + "\nomschrijving: "
                + omschrijving + "\nkleur: " + kleur +
                "\nkanOpLuchtpomp: " + kanOpLuchtpomp;
    }
}
