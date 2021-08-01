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
    void getVoornaamTest() {
        assertEquals("kees", eig1.getVoornaam());
    }

    @Test
    void setVoornaamTest() {
        eig1.setVoornaam("Peet");
        assertEquals("Peet", eig1.getVoornaam());
    }

    @Test
    void getAchternaamTest() {
        assertEquals("Lad", eig2.getAchternaam());
    }

    @Test
    void setAchternaamTest() {
        eig2.setAchternaam("Petertje");
        assertEquals("Petertje", eig2.getAchternaam());
    }


    @Test
    void validateEigenaarTest() throws Exception {
        AquariumManager.voegEigenaarToe(eig2);
        boolean res = Eigenaar.validateEigenaar(eig2.getVoornaam(), eig2.getAchternaam());
        assertTrue(res);
    }

    @Test
    void roleValidationEigenaarTest() {
        String role = Eigenaar.roleValidationEigenaar("Merlin", "Karin", "wachtwoord");
        assertEquals("user", role);
    }

    @Test
    void getEigenaarTest() throws Exception {
        AquariumManager.voegEigenaarToe(eig3);
        Eigenaar eigenaar = Eigenaar.getEigenaar("Merlin", "Karin");
        assertEquals(eig3, eigenaar);
    }

    @Test
    void getAquariumTest() throws Exception {
        Aquarium aq = new Aquarium("aq0", 2, 3, 4, "koraal", "zoet");
        eig3.voegAquariumToe(aq);
        assertEquals(aq, eig3.getAquariumByName("aq0"));
    }

}
