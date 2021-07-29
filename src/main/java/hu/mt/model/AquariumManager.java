package hu.mt.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AquariumManager implements Serializable {

    private String installatienaam;
    public static ArrayList<Eigenaar> alleEigenaren = new ArrayList<Eigenaar>();
    public static ArrayList<Bewoner> alleBewoners = new ArrayList<Bewoner>();
    public static ArrayList<Toebehoren> alleToebehoren = new ArrayList<Toebehoren>();

    public AquariumManager(String in) {
        installatienaam = in;
    }

    public static ArrayList<Eigenaar> getEigenaren() {
        return alleEigenaren;
    }

    public static void voegEigenaarToe(Eigenaar en) throws Exception {
        for (Eigenaar eigenaar : alleEigenaren) {
            if (eigenaar.getVoornaam().equals(en.getVoornaam())
                    && eigenaar.getAchternaam().equals(en.getAchternaam())) {
                throw new Exception("De eigenaar bestaat al!");
            }
        }
        alleEigenaren.add(en);
    }


    public static void verwijderEigenaar(Eigenaar en) throws Exception {
        for (Eigenaar eigenaar : alleEigenaren) {
            if (eigenaar.getVoornaam().equals(en.getVoornaam())
                    && eigenaar.getAchternaam().equals(en.getAchternaam())) {
                alleEigenaren.remove(eigenaar);
            }
        }
        throw new Exception("De eigenaar bestaat niet!");
    }


    public static ArrayList<Toebehoren> getToebehoren() {
        return alleToebehoren;
    }

    public static void voegToebehorenToe(Toebehoren tbh) throws Exception {
        for (Toebehoren toebehoren : alleToebehoren) {
            if (toebehoren.getSerienummer() == tbh.getSerienummer()) {
                throw new Exception("De toebehoren bestaat al!");
            }
        }
        alleToebehoren.add(tbh);
    }

    public static void verwijderToebehoren(Toebehoren tbh) throws Exception {
        if (!alleToebehoren.contains(tbh)) {
            throw new Exception("De toebehoren bestaat niet!");
        }
        alleToebehoren.remove(tbh);
    }

    public static ArrayList<Bewoner> getBewoners() {
        return alleBewoners;
    }

    public static void voegBewonerToe(Bewoner bwn) {
        for (Bewoner bewoner : alleBewoners) {
            if (bewoner.getSoortnaam().equals(bwn.getSoortnaam()) && bewoner.getKleurnaam().equals(bwn.getKleurnaam())) {
                int aantal = bewoner.getAantal();
                bewoner.setAantal(aantal + 1);
            }
        }
        alleBewoners.add(bwn);
    }


    public static void verwijderBewoner(Bewoner bwn) throws Exception {
        if (!alleBewoners.contains(bwn)) {
            throw new Exception("De bewoner bestaat niet!");
        }
        alleBewoners.remove(bwn);
    }

    public String getInstallatienaam() {
        return installatienaam;
    }

    public void setInstallatienaam(String in) {
        installatienaam = in;
    }

    public String toString() {

        return "AquariumManager\n" + "installatienaam: " + installatienaam
                + "\neigenaren: " + alleEigenaren + "\nbewoners: " + alleBewoners + "\ntoebehoren: " + alleToebehoren;
    }
}
