package hu.mt.test;

import hu.mt.model.Bewoner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BewonerTest {

    private Bewoner bwn1;
    private Bewoner bwn2;
    private Bewoner bwn3;

    @BeforeEach
    public void init() throws Exception {
        bwn1 = new Bewoner("nemo", "geel", 3,true, "stekel");
        bwn2 = new Bewoner("joja", "groen", 4,false, "glitter");
        bwn3 = new Bewoner("octo", "blauw", 4, true, "octopus");
    }

    @Test
    void getNaamTest(){
        assertEquals("geel", bwn1.getKleurnaam());
    }

    @Test
    void setNaamTest(){
        bwn2.setKleurnaam("rose");
        assertEquals("rose", bwn2.getKleurnaam());
    }

    @Test
    void negatieveGetallenTest(){
        bwn2.setAantal(-2);
        assertEquals(0, bwn2.getAantal());
    }

}
