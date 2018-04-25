import itb.akadquarium.Guppy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumObjectTest {

    @Test
    void setXi() {
        Guppy guppy = new Guppy();
        guppy.setXi(3.0);
        assertEquals(3.0, guppy.getXi());
    }

    @Test
    void setYi() {
        Guppy guppy = new Guppy();
        guppy.setYi(3.0);
        assertEquals(3.0, guppy.getYi());
    }

    @Test
    void getDistance() {
        Guppy guppy = new Guppy();
        guppy.setXi(3.0);
        guppy.setYi(3.0);
        Guppy guppy2 = new Guppy();
        guppy2.setXi(3.0);
        guppy2.setYi(3.0);
        assertEquals(0, guppy.getDistance(guppy2));
    }

    @Test
    void isIntersect1() {
        Guppy guppy = new Guppy();
        guppy.setXi(3.0);
        guppy.setYi(3.0);
        Guppy guppy2 = new Guppy();
        guppy2.setXi(3.0);
        guppy2.setYi(3.0);
        assertTrue(guppy.isIntersect(guppy2));
    }

    @Test
    void isIntersect2() {
        Guppy guppy = new Guppy();
        guppy.setXi(3.0);
        guppy.setYi(3.0);
        assertTrue(guppy.isIntersect(3.0, 3.0, 40));
    }
}