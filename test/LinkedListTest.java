import itb.akadquarium.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void get() {
        LinkedList<Integer> listOfInteger = new LinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        assertEquals(2, listOfInteger.get(0).intValue());
    }

    @Test
    void getCount() {
        LinkedList<Integer> listOfInteger = new LinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        assertEquals(2, listOfInteger.getCount());
    }

    @Test
    void isEmpty() {
        LinkedList<Integer> listOfInteger = new LinkedList<>();
        assertTrue(listOfInteger.isEmpty());
        listOfInteger.add(1);
        listOfInteger.add(2);
        assertFalse(listOfInteger.isEmpty());
    }

    @Test
    void add() {
        LinkedList<Integer> listOfInteger = new LinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        assertEquals(2, listOfInteger.get(0).intValue());
    }

    @Test
    void remove() {
        LinkedList<Integer> listOfInteger = new LinkedList<>();
        listOfInteger.add(1);
        listOfInteger.add(2);
        assertEquals(2, listOfInteger.getCount());
        listOfInteger.remove(1);
        assertEquals(1, listOfInteger.getCount());
    }
}