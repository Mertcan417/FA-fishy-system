package hu.mt.test;

import hu.mt.model.AquariumManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumManagerTest {

    @Test
    void getInstallatieNaamTest(){
        AquariumManager am = new AquariumManager("Bart installation");
        assertEquals("Bart installation",am.getInstallatienaam());
    }

    @Test
    void setInstallatieNaamTest(){
        AquariumManager am = new AquariumManager("Bart installation");
        am.setInstallatienaam("Jos installation");
        assertEquals("Jos installation", am.getInstallatienaam());
    }

}
