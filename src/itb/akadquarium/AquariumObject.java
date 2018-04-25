package itb.akadquarium;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.time.Clock;

/**
 * Class itb.akadquarium.AquariumObject.
 * This class is used as the abstraction class for another
 * aquarium objects for example: itb.akadquarium.Guppy, itb.akadquarium.Piranha,
 * itb.akadquarium.Snail, itb.akadquarium.FishFood, etc
 */
public abstract class AquariumObject {
    private double x, y;

    /**
     * Set the abscissa.
     *
     * @param pX is the abscissa
     */
    public void setXi(final double pX) {
        this.x = pX;
    }

    /**
     * Set the ordinate.
     *
     * @param pY is the ordinate
     */
    public void setYi(final double pY) {
        this.y = pY;
    }

    /**
     * Get abscissa.
     *
     * @return abscissa
     */
    public double getXi() {
        return x;
    }

    /**
     * Get ordinate.
     *
     * @return ordinate
     */
    public double getYi() {
        return y;
    }

    /**
     * This method calculates the distance between two objects.
     * Distance is defined as the square of the sum of the difference
     * between x (abscissa values) and y (ordinate values) of both objects.
     *
     * @param aquariumObject is the itb.akadquarium.AquariumObject
     * @return distance
     */
    public double getDistance(final AquariumObject aquariumObject) {
        double pX = this.getXi() - aquariumObject.getXi();
        double pY = this.getYi() - aquariumObject.getYi();
        return pX * pX + pY * pY;
    }

    /**
     * Get the current time.
     *
     * @return current time in millisecond.
     */
    long getTimeNow() {
        return Clock.systemDefaultZone().millis();
    }

    /**
     * This method will check whether these both objects intersect.
     * Intersect is defined as if square of the difference between x and y
     * position of both object is less than or equal to square of its radius.
     *
     * @param aquariumObject is the itb.akadquarium.AquariumObject
     * @return TRUE if both object intersect, otherwise FALSE
     */
    public boolean isIntersect(final AquariumObject aquariumObject) {
        double rad = this.getRadius() + aquariumObject.getRadius();
        return this.getDistance(aquariumObject) <= rad * rad;
    }

    /**
     * This method will check whether these two point intersect.
     * Intersect is defined as if the square of the difference between
     * x and y coordinate is less than or equal to square of its radius.
     *
     * @param pX is the abscissa
     * @param pY is the ordinate
     * @param r is the radius
     * @return TRUE if both points intersect, otherwise FALSE
     */
    public boolean isIntersect(final double pX,
                               final double pY, final double r) {
        double x1 = this.getXi() - pX;
        double y1 = this.getYi() - pY;
        double rad = this.getRadius() + r;
        return x1 * x1 + y1 * y1 <= rad * rad;
    }

    /**
     * This method will draw the aquarium object
     * on the aquarium while the game is running.
     *
     * @param g is Graphics
     * @param img is BufferedImage
     */
    void draw(final Graphics g, final BufferedImage img) {
        g.drawImage(img, (int) (Math.floor(getXi()) - img.getWidth() / 2),
                (int) (Math.floor(getYi()) - img.getHeight() / 2), null);
    }

    /**
     * Abstract method to move the object.
     *
     * @param g is the Graphics
     */
    abstract void move(Graphics g);

    /**
     * Abstract method to get the radius.
     *
     * @return radius
     */
    abstract double getRadius();
}
