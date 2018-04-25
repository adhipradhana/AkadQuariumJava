import itb.akadquarium.LinkedList;
import itb.akadquarium.Piranha;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PiranhaTest {

    @Test
    void setHunger() {
        Piranha piranha = new Piranha();
        piranha.setHunger(3);
        assertEquals(3, piranha.getHunger());
    }

    @Test
    void setListPiranha() {
        LinkedList<Piranha> listOfPiranha= new LinkedList<>();
        listOfPiranha.add(new Piranha());
        Piranha.setListPiranha(listOfPiranha);
        assertEquals(listOfPiranha, Piranha.getListPiranha());
    }

    @Test
    void setLastMoveTime() {
        Piranha piranha = new Piranha();
        piranha.setLastMoveTime(1000);
        assertEquals(1000, piranha.getLastMoveTime());
    }

    @Test
    void setLastHungerTime() {
        Piranha piranha = new Piranha();
        piranha.setLastHungerTime(1000);
        assertEquals(1000, piranha.getLastHungerTime());
    }

    @Test
    void getRadius() {
        Piranha piranha = new Piranha();
        assertEquals(40, piranha.getRadius());
    }

    @Test
    void isHungry() {
        Piranha piranha = new Piranha();
        piranha.setHunger(29);
        assertTrue(piranha.isHungry());
        piranha.setHunger(30);
        assertTrue(piranha.isHungry());
        piranha.setHunger(31);
        assertFalse(piranha.isHungry());
    }
}