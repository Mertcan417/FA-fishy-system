package hu.mt.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Aquarium implements Serializable {

    private String naam;
    private int lengte;
    private int breedte;
    private int hoogte;
    private String bodemSoort;
    private String waterSoort;
    private ArrayList<Ornament> ornamenten;
    private ArrayList<Bewoner> bewoners;

    public Aquarium(String nm, int lt, int bt, int ht, String bs, String ws) throws Exception {
        naam = nm;
        bodemSoort = bs;
        waterSoort = ws;
        ornamenten = new ArrayList<Ornament>();
        bewoners = new ArrayList<Bewoner>();
        lengte = lt;
        breedte = bt;
        hoogte = ht;

        if (lt <= 0 || bt <= 0 || ht <= 0) {
            throw new Exception("Het ingevulde getal mag niet negatief zijn!");
        }

    }

    public ArrayList<Ornament> getOrnamenten() {
        return ornamenten;
    }

    public void voegOrnamentToe(Ornament om) throws Exception {
        for (Ornament ornament : ornamenten) {
            if (ornament.getNaam().equals(om.getNaam())) {
                throw new Exception("De ornament bestaat al!");
            }
        }
        ornamenten.add(om);
    }

    public void verwijderOrnament(Ornament om) throws Exception {
        if (!ornamenten.contains(om)) {
            throw new Exception("De ornament bestaat niet!");
        }
        ornamenten.remove(om);
    }

    public ArrayList<Bewoner> getBewoners() {
        return bewoners;
    }

    public void voegBewonerToe(Bewoner bwn) {

        for (Bewoner bewoner : bewoners) {
            if (bewoner.getSoortnaam().equals(bwn.getSoortnaam()) && bewoner.getKleurnaam().equals(bwn.getKleurnaam())) {
                int bewonerAantal = bewoner.getAantal();
                bewoner.setAantal(bewonerAantal + bwn.getAantal());
            }
        }
        bewoners.add(bwn);
    }

    public void verwijderBewoner(Bewoner bwn) throws Exception {
        if (!bewoners.contains(bwn)) {
            throw new Exception("De bewoner bestaat niet!");
        }
        bewoners.remove(bwn);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String nm) {
        naam = nm;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lt) throws Exception {
        lengte = lt;
        if (lt <= 0) {
            lengte = 0;
            //throw new Exception("lengte moet groter zijn dan nul!");
        }
    }

    public int getBreedte() {
        return breedte;
    }

    public void setBreedte(int bt) throws Exception {
        breedte = bt;
        if (bt <= 0) {
            breedte = 0;
            //throw new Exception("breedte moet groter zijn dan nul!");
        }
    }

    public int getHoogte() {
        return hoogte;
    }

    public void setHoogte(int ht) throws Exception {
        hoogte = ht;
        if (ht <= 0) {
            hoogte = 0;
//            throw new Exception("hoogte moet groter zijn dan nul!");
        }
    }

    public String getBodemSoort() {
        return bodemSoort;
    }

    public void setBodemSoort(String bs) {
        bodemSoort = bs;
    }

    public String getWaterSoort() {
        return waterSoort;
    }

    public void setWaterSoort(String ws) {
        waterSoort = ws;
    }

    public String toString() {
        return "Aquarium\nnaam: " + naam + "\nlengte: " + lengte +
                "\nbreedte: " + breedte +
                "\nhoogte: " + hoogte +
                "\nbodemsoort: " + bodemSoort +
                "\nwatersoort: " + waterSoort;
    }
}
