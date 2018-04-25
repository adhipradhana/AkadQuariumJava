package itb.akadquarium;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Class itb.akadquarium.FishFood.
 * This class is derived from itb.akadquarium.AquariumObject.
 * It is used as the food of itb.akadquarium.Guppy - a derived
 * itb.akadquarium.Fish object.
 * itb.akadquarium.FishFood is bought by the player
 * using the coin while the game is running.
 */
public class FishFood extends AquariumObject {
    private static final double VELOCITY = 0.5;
    private static final double RADIUS = 5;
    private static final double LOW_BOUNDARY = 600;
    private static BufferedImage image;
    private static LinkedList<FishFood> listFishFood;

    /**
     * Instantiate itb.akadquarium.FishFood.
     *
     * @param x is the abscissa
     * @param y is the ordinate
     */
    public FishFood(final double x, final double y) {
        super.setXi(x);
        super.setYi(y);
    }

    /**
     * Set image of itb.akadquarium.FishFood.
     *
     * @param pImage is BufferedImage
     */
    static void setImage(final BufferedImage pImage) {
        FishFood.image = pImage;
    }

    /**
     * Set list of fish food.
     *
     * @param pListFishFood is the list of itb.akadquarium.FishFood
     */
    public static void setListFishFood(
            final LinkedList<FishFood> pListFishFood) {
        FishFood.listFishFood = pListFishFood;
    }

    /**
     * Get radius.
     *
     * @return radius
     */
    @Override
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Get velocity.
     *
     * @return velocity
     */
    public static double getVelocity() {
        return VELOCITY;
    }

    /**
     * Get list of itb.akadquarium.FishFood.
     *
     * @return listFishFood list fish food
     */
    public static LinkedList<FishFood> getListFishFood() {
        return listFishFood;
    }

    /**
     * itb.akadquarium.FishFood is moving down as free fall.
     * After it reaches ground, it will disappear.
     *
     * @param g is the Graphics
     */
    @Override
    public void move(final Graphics g) {
        if (this.getYi() <= LOW_BOUNDARY) {
            this.setYi(this.getYi() + getVelocity()
                    * 1);
        } else {
            getListFishFood().remove(this);
        }
        draw(g, image);
    }
}
