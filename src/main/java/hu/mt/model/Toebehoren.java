package hu.mt.model;

import java.io.Serializable;

public class Toebehoren implements Serializable {

    private String model;
    private int serienummer;
    private String omschrijving;

    public Toebehoren(String mdl, int snmr, String os) {
        model = mdl;
        serienummer = snmr;
        omschrijving = os;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String mdl) {
        model = mdl;
    }

    public int getSerienummer() {
        return serienummer;
    }

    public void setSerienummer(int snmr) {
        serienummer = snmr;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String os) {
        omschrijving = os;
    }

    public String toString() {
        return "Toebehoren\nmodel: " + model + "\nserienummer: " + serienummer +
                "\nomschrijving: " + omschrijving;
    }
}
