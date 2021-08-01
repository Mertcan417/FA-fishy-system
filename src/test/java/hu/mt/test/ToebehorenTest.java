package hu.mt.test;

import hu.mt.model.Toebehoren;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToebehorenTest {

    private Toebehoren tbh1;

    @BeforeEach
    public void init() {
        tbh1 = new Toebehoren("Octopusje", 1245, "blauw en knapperig");
    }

    @Test
    void getModelTest() {
        assertEquals("Octopusje", tbh1.getModel());
    }

    @Test
    void setModelTest() {
        tbh1.setModel("Karino");
        assertEquals("Karino", tbh1.getModel());
    }

    @Test
    void getSerienummerTest() {
        assertEquals(1245, tbh1.getSerienummer());
    }

    @Test
    void setSerienummerTest() {
        tbh1.setSerienummer(1912);
        assertEquals(1912, tbh1.getSerienummer());
    }

    @Test
    void getOmschrijvingTest() {
        assertEquals("blauw en knapperig", tbh1.getOmschrijving());
    }

    @Test
    void setOmschrijvingTest() {
        tbh1.setOmschrijving("rose en groot");
        assertEquals("rose en groot", tbh1.getOmschrijving());
    }

}
