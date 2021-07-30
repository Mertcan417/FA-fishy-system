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
    void negatieveGetallenTest() throws Exception {
        aq1.setHoogte(-2);
        assertEquals(0, aq1.getHoogte());
    }
}
