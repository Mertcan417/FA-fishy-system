package hu.mt.test;

import hu.mt.model.Ornament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrnamentTest {

    private Ornament om1;

    @BeforeEach
    public void init() {
        om1 = new Ornament("Wendy", "heel groot en mooi!", "rood", true);
    }

    @Test
    void getNaamTest() {
        assertEquals("Wendy", om1.getNaam());
    }

    @Test
    void setNaamTest() {
        om1.setNaam("Karino");
        assertEquals("Karino", om1.getNaam());
    }

    @Test
    void getOmschrijvingTest() {
        assertEquals("heel groot en mooi!", om1.getOmschrijving());
    }

    @Test
    void setOmschrijvingTest() {
        om1.setOmschrijving("Heel lelijk en klein!");
        assertEquals("Heel lelijk en klein!", om1.getOmschrijving());
    }

    @Test
    void getKleurTest() {
        assertEquals("rood", om1.getKleur());
    }

    @Test
    void setKleurTest() {
        om1.setKleur("geel");
        assertEquals("geel", om1.getKleur());
    }

    @Test
    void getKanOpLuchtPompTest() {
        assertTrue(om1.isKanOpLuchtpomp());
    }

    @Test
    void setKanOpLuchtPompTest() {
        om1.setKanOpLuchtpomp(false);
        assertFalse(om1.isKanOpLuchtpomp());
    }

}
