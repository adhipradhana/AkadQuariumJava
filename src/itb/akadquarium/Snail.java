package itb.akadquarium;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Class itb.akadquarium.Snail.
 * This class is derived from itb.akadquarium.AquariumObject.
 * itb.akadquarium.Snail is an aquarium object that collects coin.
 */
public class Snail extends AquariumObject {
    private Node<Integer> money;
    private static BufferedImage snailLeft;
    private static BufferedImage snailRight;
    private static final double RADIUS = 30;
    private static final double VELOCITY = 1;
    private static LinkedList<Coin> listCoin;

    /**
     * Instantiate itb.akadquarium.Snail.
     *
     * @param pX is abscissa.
     * @param pY is ordinate.
     */
    public Snail(final double pX, final double pY) {
        this.setXi(pX);
        this.setYi(pY);
    }

    /**
     * Set list of coin.
     *
     * @param pListCoin is list of coin
     */
    public static void setListCoin(final LinkedList<Coin> pListCoin) {
        Snail.listCoin = pListCoin;
    }

    /**
     * Set money.
     *
     * @param pMoney is money
     */
    public void setMoney(final Node<Integer> pMoney) {
        this.money = pMoney;
    }

    /**
     * Set image of itb.akadquarium.Snail facing left.
     *
     * @param pSnailLeft is BufferedImage
     */
    static void setSnailLeft(final BufferedImage pSnailLeft) {
        Snail.snailLeft = pSnailLeft;
    }

    /**
     * Set image of itb.akadquarium.Snail facing right.
     *
     * @param pSnailRight is BufferedImage
     */
    static void setSnailRight(final BufferedImage pSnailRight) {
        Snail.snailRight = pSnailRight;
    }

    /**
     * Get snail radius.
     *
     * @return snail radius
     */
    @Override
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Get money.
     *
     * @return node of money
     */
    public Node<Integer> getMoney() {
        return money;
    }

    /**
     * Get list of coin.
     *
     * @return list of coin
     */
    private static LinkedList<Coin> getListCoin() {
        return listCoin;
    }

    /**
     * This method is used to grab the coin.
     *
     * @param coin is the coin
     */
    public void grabCoin(final Coin coin)  {
        money.setData(money.getData() + coin.getValue());
        getListCoin().remove(coin);
    }

    /**
     * This method is used to move the object.
     * itb.akadquarium.Snail's movements are described as below:
     * 1. If there is coin available, itb.akadquarium.Snail
     * will move towards the nearest coin.
     * 2. If there is no coin available,
     * itb.akadquarium.Snail will just stay at its current location.
     *
     * @param g is Graphics
     */
    @Override
    public void move(final Graphics g) {
        int move, idx;
        double min, temp;
        Coin coin = null;

        if (getListCoin().getCount() == 0) {
            move = 0;
        } else {
            min = this.getDistance(getListCoin().get(0));
            idx = 0;
            for (int i = 0; i < getListCoin().getCount(); i++) {
                temp = this.getDistance(getListCoin().get(i));
                if (min > temp) {
                    idx = i;
                    min = temp;
                }
            }
            move = 1;
            coin = getListCoin().get(idx);
        }

        if (move == 1) {
            if (Math.floor(coin.getXi()) < Math.floor(getXi())) {
                this.setXi(this.getXi() - VELOCITY);
                draw(g, snailLeft);
            } else if (Math.floor(coin.getXi()) > Math.floor(getXi())) {
                this.setXi(this.getXi() + VELOCITY);
                draw(g, snailRight);
            } else {
                draw(g, snailRight);
            }

            if (this.isIntersect(coin)) {
                this.grabCoin(coin);
            }
            return;
        }
        draw(g, snailRight);
    }
}
