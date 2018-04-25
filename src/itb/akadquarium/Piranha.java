package itb.akadquarium;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Random;

/**
 * Class itb.akadquarium.Piranha.
 * This class implements itb.akadquarium.Fish.
 * itb.akadquarium.Piranha is an aquarium object than can be bought
 * during the game using the coins that the player has.
 * It also can drop coin directly after itb.akadquarium.Piranha
 * eats itb.akadquarium.Guppy.
 */
public class Piranha extends AquariumObject implements Fish {
    private double targetX;
    private double targetY;
    private Guppy targetFood;
    private int hunger;
    private long lastHungerTime;
    private long lastMoveTime;
    private static LinkedList<Coin> listCoin;
    private static LinkedList<Guppy> listGuppy;
    private static LinkedList<Piranha> listPiranha;
    private static final int HUNGER_TIME = 30;
    private static final int MAX_HUNGER = 60;
    private static final double VELOCITY = 1;
    private static final int GUPPY_PRICE = 15;
    private static final double RADIUS = 40;
    private static BufferedImage piranhaLeft;
    private static BufferedImage piranhaRight;
    private static final int MAX_TARGET_X = 1000;
    private static final int OFFSET_X = 40;
    private static final int MAX_TARGET_Y = 550;
    private static final int Y_LOWER_BOUND = 120;
    private static final double HUNGRY_SPEED_MULTIPLIER = 1.5;
    private static final int DECREASE_HUNGER_TIME = 1000;
    private static final int MOVE_TIME = 3000;
    private static final int SCREEN_WIDTH = 1080;
    private static final int MINIMUM_DISTANCE = 200;

    /**
     * Instantiate itb.akadquarium.Piranha.
     */
    public Piranha() {
        this.hunger = Piranha.MAX_HUNGER;
        this.targetFood = null;
        this.lastHungerTime = getTimeNow();
        this.lastMoveTime = getTimeNow();
        Random rand = new Random();
        this.targetX = rand.nextInt(MAX_TARGET_X) + OFFSET_X;
        this.targetY = rand.nextInt(MAX_TARGET_Y)  + Y_LOWER_BOUND;
        this.setYi(rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND);
        this.setXi(rand.nextInt(MAX_TARGET_X) + OFFSET_X);
    }

    /**
     * Set hunger.
     *
     * @param pHunger is integer represents hunger
     */
    public void setHunger(final int pHunger) {
        this.hunger = pHunger;
    }

    /**
     * Set image of itb.akadquarium.Piranha facing left.
     *
     * @param pPiranhaLeft is BufferedImage
     */
    static void setPiranhaLeft(final BufferedImage pPiranhaLeft) {
        Piranha.piranhaLeft = pPiranhaLeft;
    }

    /**
     * Set image of itb.akadquarium.Piranha facing right.
     *
     * @param pPiranhaRight is BufferedImage
     */
    static void setPiranhaRight(final BufferedImage pPiranhaRight) {
        Piranha.piranhaRight = pPiranhaRight;
    }

    /**
     * Set list of coins.
     *
     * @param pListCoin is itb.akadquarium.LinkedList of itb.akadquarium.Coin
     */
    static void setListCoin(final LinkedList<Coin> pListCoin) {
        Piranha.listCoin = pListCoin;
    }

    /**
     * Set list of itb.akadquarium.Guppy.
     *
     * @param pListGuppy is itb.akadquarium.LinkedList of itb.akadquarium.Guppy
     */
    static void setListGuppy(final LinkedList<Guppy> pListGuppy) {
        Piranha.listGuppy = pListGuppy;
    }

    /**
     * Set list of itb.akadquarium.Piranha.
     *
     * @param pListPiranha is itb.akadquarium.LinkedList
     *                     of itb.akadquarium.Piranha
     */
    public static void setListPiranha(final LinkedList<Piranha> pListPiranha) {
        Piranha.listPiranha = pListPiranha;
    }

    /**
     * Set piranha last move time.
     *
     * @param pLastMoveTime is time now
     */
    public void setLastMoveTime(final long pLastMoveTime) {
        this.lastMoveTime = pLastMoveTime;
    }

    /**
     * Set piranha last hunger time.
     *
     * @param pLastHungerTime is time now
     */
    public void setLastHungerTime(final long pLastHungerTime) {
        this.lastHungerTime = pLastHungerTime;
    }

    /**
     * Get piranha's radius.
     *
     * @return piranha's radius
     */
    @Override
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Get hunger.
     *
     * @return hunger
     */
    public int getHunger() {
        return this.hunger;
    }

    /**
     * Get last hunger time.
     *
     * @return last hunger time
     */
    public long getLastHungerTime() {
        return lastHungerTime;
    }

    /**
     * Get last move time.
     *
     * @return last move time
     */
    public long getLastMoveTime() {
        return lastMoveTime;
    }

    /**
     * Get list of coins.
     *
     * @return list of coins
     */
    private static LinkedList<Coin> getListCoin() {
        return listCoin;
    }

    /**
     * Get list of itb.akadquarium.Guppy.
     *
     * @return list of itb.akadquarium.Guppy
     */
    private static LinkedList<Guppy> getListGuppy() {
        return listGuppy;
    }

    /**
     * Get list of itb.akadquarium.Piranha.
     *
     * @return list of itb.akadquarium.Piranha
     */
    public static LinkedList<Piranha> getListPiranha() {
        return listPiranha;
    }

    /**
     * This method checks whether itb.akadquarium.Piranha is hungry or not.
     * Hungry is defined as if hunger is less than or equal to HUNGER_TIME.
     *
     * @return TRUE if itb.akadquarium.Piranha is hungry, otherwise FALSE
     */
    public boolean isHungry() {
        return Piranha.HUNGER_TIME >= this.hunger;
    }

    /**
     * This method will eat itb.akadquarium.Guppy.
     * itb.akadquarium.Piranha only eats when
     * itb.akadquarium.Piranha is hungry and
     * will try to reach the nearest food available.
     */
    @Override
    public void eat() {
        this.dropCoin();
        getListGuppy().remove(this.targetFood);
        this.targetFood = null;
        this.hunger = Piranha.MAX_HUNGER;
    }

    /**
     * This method will drop coin.
     * itb.akadquarium.Piranha will drop coin directly after eating.
     * Also, coin value vary and be calculated as follows:
     * GUPPY_PRICE multiply by STATE_OF_GUPPY added by one.
     */
    @Override
    public void dropCoin() {
        getListCoin().add(new Coin(this.getXi(), this.getYi(),
                Piranha.GUPPY_PRICE * (targetFood.getState() + 1)));
    }

    /**
     * This method is used to move the object.
     * itb.akadquarium.Piranha's movements are described as below:
     * 1. If itb.akadquarium.Piranha is not hungry,
     * itb.akadquarium.Piranha will move randomly.
     * 2. If itb.akadquarium.Piranha is hungry and there is no food available,
     * then it moves randomly.
     * 3. If itb.akadquarium.Piranha is hungry and there is food available,
     * then it moves towards the nearest food.
     *
     * @param g is Graphics
     */
    @Override
    public void move(final Graphics g) {
        // Decreasing hunger
        if (getTimeNow() - this.lastHungerTime >= DECREASE_HUNGER_TIME) {
            setHunger(getHunger() - 1);
            this.lastHungerTime = getTimeNow();
        }

        Guppy tempGuppy = null;

        // itb.akadquarium.Piranha's movement
        if (this.isHungry() && Piranha.getListGuppy().getCount() != 0) {
            // itb.akadquarium.Piranha is in hungry state
            LinkedList<Guppy> pListGuppy = getListGuppy();

            int idx = 0;
            double min = getDistance(pListGuppy.get(0));

            // Finding the nearest itb.akadquarium.Guppy position
            for (int i = 1; i < pListGuppy.getCount(); i++) {
                double temp = getDistance(pListGuppy.get(i));
                if (min > temp) {
                    idx = i;
                    min = temp;
                }
            }

            tempGuppy = listGuppy.get(idx);
        }

        if (tempGuppy != null) {
            // itb.akadquarium.Piranha is moving toward the nearest
            // itb.akadquarium.Guppy
            targetFood = tempGuppy;

            double angle = Math.atan2(targetFood.getYi() - this.getYi(),
                    targetFood.getXi() - this.getXi());

            this.setXi(getXi() + VELOCITY * (HUNGRY_SPEED_MULTIPLIER)
                    * Math.cos(angle) * 1);
            this.setYi(getYi() + VELOCITY * (HUNGRY_SPEED_MULTIPLIER)
                    * Math.sin(angle) * 1);

            if (Math.cos(angle) >= 0) {
                draw(g, piranhaRight);
            } else {
                draw(g, piranhaLeft);
            }

            if (isIntersect(targetFood)) {
                // itb.akadquarium.Piranha is eating
                eat();
            }
        } else {
            // itb.akadquarium.Piranha moves randomly
            if (getTimeNow() - lastMoveTime >= MOVE_TIME) {
                Random rand = new Random();
                this.targetX = rand.nextInt(SCREEN_WIDTH);
                while (Math.abs(targetX - this.getXi()) < MINIMUM_DISTANCE) {
                    targetX = rand.nextInt(SCREEN_WIDTH);
                }
                this.targetY = rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND;
                while (Math.abs(targetY - this.getYi()) < MINIMUM_DISTANCE) {
                    targetY = rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND;
                }
                lastMoveTime = getTimeNow();
            }
            double angle = Math.atan2(targetY - this.getYi(),
                    targetX - this.getXi());

            if (Math.floor(targetX) != Math.floor(getXi())
                    && Math.floor(targetY) != Math.floor(getYi())) {
                this.setXi(getXi() + VELOCITY * Math.cos(angle));
                this.setYi(getYi() + VELOCITY * Math.sin(angle));
            }

            if (Math.cos(angle) >= 0) {
                draw(g, piranhaRight);
            } else {
                draw(g, piranhaLeft);
            }
        }

        this.targetFood = null;

        if (hunger <= 0) {
            getListPiranha().remove(this);
        }
    }
}
