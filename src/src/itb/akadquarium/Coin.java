package itb.akadquarium;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Class itb.akadquarium.Coin.
 * This class is derived from itb.akadquarium.AquariumObject and used for
 * instantiate coins in the game in order to increase money.
 * Furthermore, coins are produced by all derived class of itb.akadquarium.Fish
 */
public class Coin extends AquariumObject {
    /**
     * value.
     */
    private final int value;
    /**
     * coin image.
     */
    private static BufferedImage imageCoin;
    /**
     * diamond image.
     */
    private static BufferedImage imageDiamond;
    /**
     * list coin.
     */
    private static LinkedList<Coin> listCoin;
    /**
     * min diamond.
     */
    private static final int MIN_DIAMOND = 20;
    /**
     * low bound.
     */
    private static final int LOW_BOUNDARY = 575;
    /**
     * radius.
     */
    private static final double RADIUS = 25;
    /**
     * velocity.
     */
    private static final double VELOCITY = 0.5;

    /**
     * Instantiates a new itb.akadquarium.Coin.
     *
     * @param x is the abscissa
     * @param y is the ordinate
     * @param pValue is the coin value
     */
    public Coin(final double x, final double y, final int pValue) {
        this.setXi(x);
        this.setYi(y);
        this.value = pValue;
    }

    /**
     * Set image of coin.
     *
     * @param pImageCoin is the BufferedImage
     */
    static void setImageCoin(final BufferedImage pImageCoin) {
        Coin.imageCoin = pImageCoin;
    }

    /**
     * Set image of diamond.
     *
     * @param pImageDiamond is the BufferedImage
     */
    static void setImageDiamond(final BufferedImage pImageDiamond) {
        Coin.imageDiamond = pImageDiamond;
    }

    /**
     * Set list of coin.
     *
     * @param pListCoin is the list coin
     */
    public static void setListCoin(final LinkedList<Coin> pListCoin) {
        Coin.listCoin = pListCoin;
    }

    /**
     * Get coin's radius.
     *
     * @return coin's radius
     */
    @Override
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Get coin's value.
     *
     * @return coin's value
     */
    public int getValue() {
        return value;
    }

    /**
     * Get list of coin.
     *
     * @return the list of coin
     */
    public static LinkedList<Coin> getListCoin() {
        return listCoin;
    }

    /**
     * itb.akadquarium.Coin is moving down as free fall.
     * After it reaches ground, it will stop.
     *
     * @param g is the Graphics
     */
    @Override
    public void move(final Graphics g) {
        if (getYi() <= LOW_BOUNDARY) {
            setYi(getYi() + VELOCITY * 1);
        }
        if (getValue() >= MIN_DIAMOND) {
            draw(g, imageDiamond);
        } else {
            draw(g, imageCoin);
        }
    }
}
