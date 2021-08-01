package hu.mt.test;

import hu.mt.model.Bewoner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BewonerTest {

    private Bewoner bwn1;

    @BeforeEach
    public void init() throws Exception {
        bwn1 = new Bewoner("nemo", "geel", 3,true, "stekel");
    }

    @Test
    void getSoortnaamTest(){
        assertEquals("nemo", bwn1.getSoortnaam());
    }

    @Test
    void setSoortnaamTest(){
        bwn1.setSoortnaam("haai");
        assertEquals("haai", bwn1.getSoortnaam());
    }

    @Test
    void getKleurnaamTest(){
        assertEquals("geel", bwn1.getKleurnaam());
    }

    @Test
    void setKleurnaamTest(){
        bwn1.setKleurnaam("rose");
        assertEquals("rose", bwn1.getKleurnaam());
    }

    @Test
    void getAantalTest(){
        assertEquals(3, bwn1.getAantal());
    }

    @Test
    void setAantalTest(){
        bwn1.setAantal(10);
        assertEquals(10, bwn1.getAantal());
    }

    @Test
    void getGroepsdierTest(){
        assertTrue(bwn1.isGroepsDier());
    }

    @Test
    void setGroepsdierTest(){
        bwn1.setGroepsDier(false);
        assertFalse(bwn1.isGroepsDier());
    }

    @Test
    void getTypeTest(){
        assertEquals("stekel", bwn1.getType());
    }

    @Test
    void setTypeTest(){
        bwn1.setType("baars");
        assertEquals("baars", bwn1.getType());
    }


    @Test
    void negatieveGetallenTest(){
        bwn1.setAantal(-2);
        assertEquals(0, bwn1.getAantal());
    }

}
