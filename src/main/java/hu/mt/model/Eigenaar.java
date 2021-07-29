package hu.mt.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;

public class Eigenaar implements Serializable, Principal {

    private String voornaam;
    private String achternaam;
    private ArrayList<Toebehoren> toebehoren = new ArrayList<Toebehoren>();
    private ArrayList<Aquarium> aquariua = new ArrayList<Aquarium>();

    private String name;
    private String pass;
    private String role;

    static {
        try {
            Eigenaar beheerder = new Eigenaar("rik", "hik");
            beheerder.setRole("beheerder");
            AquariumManager.voegEigenaarToe(beheerder);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Eigenaar(String vn, String an) {
        voornaam = vn;
        achternaam = an;
        role = "user";
        pass = "wachtwoord";
    }

    public String getPass() {
        return pass;
    }

    public static String roleValidationEigenaar(String vn, String an, String pass) {
        for (Eigenaar eig : AquariumManager.alleEigenaren) {
            if (eig.voornaam.equals(vn) && eig.achternaam.equals(an) && eig.getPass().equals(pass)) {
                return eig.role;
            }
        }
        return null;
    }

    public ArrayList<Toebehoren> getToebehoren() {
        return toebehoren;
    }

    public void voegToebehorenToe(Toebehoren tbh) throws Exception {
        for (Toebehoren toebehoren : toebehoren) {
            if (toebehoren.getSerienummer() == tbh.getSerienummer()) {
                throw new Exception("Toebehoren bestaat al!");
            }
        }
        toebehoren.add(tbh);
    }

    public void verwijderToebehoren(Toebehoren tbh2) throws Exception {
        for (Toebehoren tbh1 : toebehoren) {
            if (tbh1.getSerienummer() == tbh2.getSerienummer()) {
                toebehoren.remove(tbh1);
            }
        }
        throw new Exception("Toebehoren bestaat niet!");
    }

    public static boolean validateEigenaar(String vn, String an) {
        boolean res = false;
        for (Eigenaar eigenaar : AquariumManager.getEigenaren()) {
            if (eigenaar.getVoornaam().equals(vn) && eigenaar.getAchternaam().equals(an)) {
                res = true;
            }
        }
        return res;
    }

    public static Eigenaar getEigenaar(String vn, String an) throws Exception {
        for (Eigenaar eigenaar : AquariumManager.getEigenaren()) {
            if (eigenaar.getVoornaam().equals(vn) && eigenaar.getAchternaam().equals(an)) {
                return eigenaar;
            }
        }
        throw new Exception("Eigenaar bestaat niet!");
    }

    public ArrayList<Aquarium> getAquariua() {
        return aquariua;
    }

    public void voegAquariumToe(Aquarium aqm) throws Exception {
        for (Aquarium aquarium : aquariua) {
            if (aquarium.getNaam().equals(aqm.getNaam())) {
                throw new Exception("Deze aquarium naam bestaat al!");
            }
        }
        aquariua.add(aqm);
    }

    public Aquarium getAquariumByName(String nm) throws Exception {
        for (Aquarium aquarium : aquariua) {
            if (aquarium.getNaam().equals(nm)) {
                return aquarium;
            }
        }
        throw new Exception("Aquarium bestaat niet!");
    }

    public void verwijderAquarium(Aquarium aqm) throws Exception {
        if (!aquariua.contains(aqm)) {
            throw new Exception("Aquarium bestaat niet!");
        }
        aquariua.remove(aqm);
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String vn) {
        voornaam = vn;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String an) {
        achternaam = an;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String rl) {
        role = rl;
    }

    public String toString() {
        return "Eigenaar\nvoornaam: " + voornaam +
                "\nachternaam: " + achternaam + "\naquaria:\n" +
                aquariua + "\ntoebehoren:\n" + toebehoren;
    }
}
