import itb.akadquarium.Coin;
import itb.akadquarium.LinkedList;
import itb.akadquarium.Node;
import itb.akadquarium.Snail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnailTest {

    @Test
    void setMoney() {
        Snail snail = new Snail(30, 575);
        snail.setMoney(new Node<>(10));
        assertEquals(10, snail.getMoney().getData().intValue());
    }

    @Test
    void getRadius() {
        Snail snail = new Snail(30, 575);
        assertEquals(30, snail.getRadius());
    }

    @Test
    void grabCoin() {
        Snail snail = new Snail(30, 575);
        snail.setMoney(new Node<>(10));
        LinkedList<Coin> listOfCoin = new LinkedList<>();
        Coin coin = new Coin(30, 570, 50);
        listOfCoin.add(coin);
        Snail.setListCoin(listOfCoin);
        assertEquals(10, snail.getMoney().getData().intValue());
        assertEquals(1, listOfCoin.getCount());
        snail.grabCoin(listOfCoin.get(0));
        assertEquals(60, snail.getMoney().getData().intValue());
        assertEquals(0, listOfCoin.getCount());
    }
}