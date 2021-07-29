package hu.mt.model;

import java.io.Serializable;

public class Bewoner implements Serializable {

    private String soortnaam;
    private String kleurnaam;
    private int aantal;
    private boolean groepsDier;
    private String type;

    public Bewoner(String sn, String kn, int at, boolean gd, String tp) throws Exception {
        soortnaam = sn;
        kleurnaam = kn;
        groepsDier = gd;
        type = tp;
        aantal = at;

        if (at <= 0) {
            throw new Exception("aantal moet groter zijn dan nul!");
        }
    }

    public String getSoortnaam() {
        return soortnaam;
    }

    public void setSoortnaam(String sn) {
        soortnaam = sn;
    }

    public String getKleurnaam() {
        return kleurnaam;
    }

    public void setKleurnaam(String kn) {
        kleurnaam = kn;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int at) {
        aantal = at;
        if (at <= 0) {
            aantal = 0;
        }
    }

    public boolean isGroepsDier() {
        return groepsDier;
    }

    public void setGroepsDier(boolean gd) {
        groepsDier = gd;
    }

    public String getType() {
        return type;
    }

    public void setType(String tp) {
        type = tp;
    }

    public String toString() {
        return "Bewoner\nsoortnaam: " + soortnaam + "\nkleurnaam: " +
                kleurnaam + "\naantal: " + aantal + "\ngroepsdier: " + groepsDier +
                "\ntype: " + type;
    }
}
