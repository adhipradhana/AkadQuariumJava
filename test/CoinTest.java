import itb.akadquarium.Coin;
import itb.akadquarium.LinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinTest {
    @Test
    void getRadius() {
        Coin coin = new Coin(3.0, 4.0, 50);
        assertEquals(25, coin.getRadius());
    }

    @Test
    void getValue() {
        Coin coin = new Coin(3.0, 4.0, 50);
        assertEquals(50, coin.getValue());
    }

    @Test
    void setListCoin() {
        LinkedList<Coin> listCoin = new LinkedList<>();
        listCoin.add(new Coin(3.0, 4.0, 50));
        Coin.setListCoin(listCoin);
        assertEquals(3.0, listCoin.get(0).getXi());
        assertEquals(4.0, listCoin.get(0).getYi());
        assertEquals(50, listCoin.get(0).getValue());
    }

    @Test
    void getListCoin() {
        LinkedList<Coin> listCoin = new LinkedList<>();
        listCoin.add(new Coin(3.0, 4.0, 50));
        Coin.setListCoin(listCoin);
        assertEquals(listCoin, Coin.getListCoin());
    }
}