import itb.akadquarium.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void setNext() {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        node1.setNext(node2);
        assertEquals(node2, node1.getNext());
    }

    @Test
    void setData() {
        Node<Integer> node1 = new Node<>(1);
        node1.setData(2);
        assertEquals(2, node1.getData().intValue());
    }
}