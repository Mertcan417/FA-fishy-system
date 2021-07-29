package hu.mt.test;

import hu.mt.model.Aquarium;
import hu.mt.model.AquariumManager;
import hu.mt.model.Eigenaar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EigenaarTest {

    private Eigenaar eig1;
    private Eigenaar eig2;
    private Eigenaar eig3;

    @BeforeEach
    public void init() {
        eig1 = new Eigenaar("kees", "jan");
        eig2 = new Eigenaar("Piet", "Lad");
        eig3 = new Eigenaar("Merlin", "Karin");
    }

    @Test
    void getNaamTest(){
        assertEquals("kees",eig1.getVoornaam());
    }

    @Test
    void setNaamTest(){
        eig2.setVoornaam("Peet");
        assertEquals("Peet", eig2.getVoornaam());
    }

    @Test
    void validateEigenaarTest() throws Exception {
        AquariumManager.voegEigenaarToe(eig2);
        boolean res = Eigenaar.validateEigenaar(eig2.getVoornaam(), eig2.getAchternaam());
        assertEquals(true,res);
    }

    @Test
    void roleValidationEigenaarTest() {
        String role = Eigenaar.roleValidationEigenaar("Merlin","Karin", "wachtwoord");
        assertEquals("user", role);
    }

    @Test
    void getEigenaarTest() throws Exception{
        AquariumManager.voegEigenaarToe(eig3);
        Eigenaar eigenaar = Eigenaar.getEigenaar("Merlin", "Karin");
        assertEquals(eig3,eigenaar);
    }

    @Test
    void getAquariumTest() throws Exception{
        Aquarium aq = new Aquarium("aq0", 2,3,4,"koraal", "zoet");
        eig3.voegAquariumToe(aq);
        assertEquals(aq,eig3.getAquariumByName("aq0"));
    }
}
