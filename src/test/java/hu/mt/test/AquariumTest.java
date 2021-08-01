package hu.mt.test;

import hu.mt.model.Aquarium;
import hu.mt.model.Bewoner;
import hu.mt.model.Ornament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    private Aquarium aq1;

    @BeforeEach
    public void init() throws Exception {
        aq1 = new Aquarium("nemo", 1, 3, 2, "koraal", "zoet");
    }

    @Test
    void getNaamTest() {
        assertEquals("nemo", aq1.getNaam());
    }

    @Test
    void setNaamTest() {
        aq1.setNaam("haai");
        assertEquals("haai", aq1.getNaam());
    }
    @Test
    void getLengteTest() {
        assertEquals(1, aq1.getLengte());
    }

    @Test
    void setLengteTest() throws Exception{
        aq1.setLengte(4);
        assertEquals(4, aq1.getLengte());
    }
    @Test
    void getBreedteTest() {
        assertEquals(3, aq1.getBreedte());
    }

    @Test
    void setBreedteTest() throws Exception{
        aq1.setBreedte(6);
        assertEquals(6, aq1.getBreedte());
    }
    @Test
    void getHoogteTest() {
        assertEquals(2, aq1.getHoogte());
    }

    @Test
    void setHoogteTest() throws Exception{
        aq1.setHoogte(9);
        assertEquals(9, aq1.getHoogte());
    }

    @Test
    void getBodemSoortTest() {
        assertEquals("koraal", aq1.getBodemSoort());
    }

    @Test
    void setBodemSoortTest() {
        aq1.setBodemSoort("gras");
        assertEquals("gras", aq1.getBodemSoort());
    }
    @Test
    void getWaterSoortTest() {
        assertEquals("zoet", aq1.getWaterSoort());
    }

    @Test
    void setWaterSoortTest() {
        aq1.setWaterSoort("zout");
        assertEquals("zout", aq1.getWaterSoort());
    }


    @Test
    void negatieveGetallenTest() throws Exception {
        aq1.setHoogte(-2);
        assertEquals(0, aq1.getHoogte());
    }
}
