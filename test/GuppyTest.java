import itb.akadquarium.Guppy;
import itb.akadquarium.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuppyTest {

    @Test
    void setListGuppy() {
        LinkedList<Guppy> listGuppy = new LinkedList<>();
        Guppy guppy = new Guppy();
        listGuppy.add(guppy);
        Guppy.setListGuppy(listGuppy);
        assertEquals(listGuppy, guppy.getListGuppy());
    }

    @Test
    void setHunger() {
        Guppy guppy = new Guppy();
        guppy.setHunger(1000);
        assertEquals(1000, guppy.getHunger());
    }

    @Test
    void setState() {
        Guppy guppy = new Guppy();
        guppy.setState(2);
        assertEquals(2, guppy.getState());
    }

    @Test
    void setTimesEaten() {
        Guppy guppy = new Guppy();
        guppy.setTimesEaten(5);
        assertEquals(5, guppy.getTimesEaten());
    }

    @Test
    void setLastHungerTime() {
        Guppy guppy = new Guppy();
        guppy.setLastHungerTime(1000);
        assertEquals(1000, guppy.getLastHungerTime());
    }

    @Test
    void setLastMoveTime() {
        Guppy guppy = new Guppy();
        guppy.setLastMoveTime(1000);
        assertEquals(1000, guppy.getLastMoveTime());
    }

    @Test
    void setLastDropTime() {
        Guppy guppy = new Guppy();
        guppy.setLastDropTime(1000);
        assertEquals(1000, guppy.getLastDropTime());
    }

    @Test
    void getRadius() {
        Guppy guppy = new Guppy();
        assertEquals(40, guppy.getRadius());
    }

    @Test
    void equals() {
        Guppy guppy = new Guppy();
        guppy.setLastHungerTime(1000);
        assertEquals(guppy, guppy);
    }

    @Test
    void isHungry() {
        Guppy guppy = new Guppy();
        guppy.setHunger(29);
        assertTrue(guppy.isHungry());
        guppy.setHunger(30);
        assertTrue(guppy.isHungry());
        guppy.setHunger(31);
        assertFalse(guppy.isHungry());
    }

}