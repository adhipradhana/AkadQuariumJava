package itb.akadquarium;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class itb.akadquarium.Guppy.
 * This class is derived from itb.akadquarium.AquariumObject.
 * itb.akadquarium.Guppy is an aquarium object than can be bought
 * during the game using the coins that the player has.
 * It also can drop coins in a regular interval of time with variety of value.
 */

public class Guppy extends AquariumObject implements Fish {
    /**
     * variable state is itb.akadquarium.Guppy condition.
     */
    private int state;
    private int hunger;
    private int timesEaten;
    private long lastMoveTime;
    private long lastDropTime;
    private long lastHungerTime;
    private double targetX;
    private double targetY;
    private FishFood targetFood;
    private static LinkedList<Coin> listCoin;
    private static LinkedList<Guppy> listGuppy;
    private static LinkedList<FishFood> listFishFood;
    private static final int DROP_TIME = 15;
    private static final int MAX_HUNGER = 60;
    private static final int HUNGER_TIME = 30;
    private static final int COIN_DROP_VALUE = 7;
    private static final double RADIUS = 40;
    private static final double VELOCITY = 1;

    private static final int MAX_TARGET_X = 1000;
    private static final int OFFSET_X = 40;
    private static final int MAX_TARGET_Y = 550;
    private static final int Y_LOWER_BOUND = 120;
    private static final int STATE_BOUND = 3;
    private static final double HUNGRY_SPEED_MULTIPLIER = 1.5;
    private static final int DECREASE_HUNGER_TIME = 1000;
    private static final int MOVE_TIME = 3000;
    private static final int SCREEN_WIDTH = 1080;
    private static final int MINIMUM_DISTANCE = 200;

    private static BufferedImage stateOneGuppyLeft;
    private static BufferedImage stateOneGuppyRight;
    private static BufferedImage stateTwoGuppyLeft;
    private static BufferedImage stateTwoGuppyRight;
    private static BufferedImage stateThreeGuppyLeft;
    private static BufferedImage stateThreeGuppyRight;

    /**
     * Instantiate itb.akadquarium.Guppy.
     */
    public Guppy() {
        this.state = 1;
        this.timesEaten = 0;
        this.hunger = Guppy.MAX_HUNGER;
        this.targetFood = null;
        this.lastDropTime = getTimeNow();
        this.lastHungerTime = getTimeNow();
        this.lastMoveTime = getTimeNow();
        Random rand = new Random();
        this.targetX = rand.nextInt(MAX_TARGET_X) + OFFSET_X;
        this.targetY = rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND;
        this.setXi(rand.nextInt(MAX_TARGET_X) + OFFSET_X);
        this.setYi(rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND);
    }

    /**
     * Set list of coins.
     *
     * @param pListCoin is list of coins
     */
    static void setListCoin(
            final LinkedList<Coin> pListCoin) {
        Guppy.listCoin = pListCoin;
    }

    /**
     * Set list of itb.akadquarium.Guppy.
     *
     * @param pListGuppy is list of itb.akadquarium.Guppy
     */
    public static void setListGuppy(
            final LinkedList<Guppy> pListGuppy) {
        Guppy.listGuppy = pListGuppy;
    }

    /**
     * Set list of itb.akadquarium.FishFood.
     *
     * @param pListFishFood is list of itb.akadquarium.FishFood
     */
    static void setListFishFood(
            final LinkedList<FishFood> pListFishFood) {
        Guppy.listFishFood = pListFishFood;
    }

    /**
     * Set image of itb.akadquarium.Guppy.
     * itb.akadquarium.Guppy is on first level and facing left.
     *
     * @param pStateOneGuppyLeft is BufferedImage
     */
    static void setStateOneGuppyLeft(
            final BufferedImage pStateOneGuppyLeft) {
        Guppy.stateOneGuppyLeft = pStateOneGuppyLeft;
    }

    /**
     * Set image of itb.akadquarium.Guppy.
     * itb.akadquarium.Guppy is on first level and facing right.
     *
     * @param pStateOneGuppyRight is BufferedImage
     */
    static void setStateOneGuppyRight(
            final BufferedImage pStateOneGuppyRight) {
        Guppy.stateOneGuppyRight = pStateOneGuppyRight;
    }

    /**
     * Set image of itb.akadquarium.Guppy.
     * itb.akadquarium.Guppy is on second level and facing left.
     *
     * @param pStateTwoGuppyLeft is BufferedImage
     */
    static void setStateTwoGuppyLeft(
            final BufferedImage pStateTwoGuppyLeft) {
        Guppy.stateTwoGuppyLeft = pStateTwoGuppyLeft;
    }

    /**
     * Set image of itb.akadquarium.Guppy.
     * itb.akadquarium.Guppy is on second level and facing right.
     *
     * @param pStateTwoGuppyRight is BufferedImage
     */
    static void setStateTwoGuppyRight(
            final BufferedImage pStateTwoGuppyRight) {
        Guppy.stateTwoGuppyRight = pStateTwoGuppyRight;
    }

    /**
     * Set image of itb.akadquarium.Guppy.
     * itb.akadquarium.Guppy is on third level and facing left.
     *
     * @param pStateThreeGuppyLeft is BufferedImage
     */
    static void setStateThreeGuppyLeft(
            final BufferedImage pStateThreeGuppyLeft) {
        Guppy.stateThreeGuppyLeft = pStateThreeGuppyLeft;
    }

    /**
     * Set image of itb.akadquarium.Guppy.
     * itb.akadquarium.Guppy is on third level and facing right.
     *
     * @param pStateThreeGuppyRight is BufferedImage
     */
    static void setStateThreeGuppyRight(
            final BufferedImage pStateThreeGuppyRight) {
        Guppy.stateThreeGuppyRight = pStateThreeGuppyRight;
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
     * Set state.
     *
     * @param pState is integer represents state
     */
    public void setState(final int pState) {
        this.state = pState;
    }

    /**
     * Set total times eaten.
     *
     * @param pTimesEaten is integer represents times eaten
     */
    public void setTimesEaten(final int pTimesEaten) {
        this.timesEaten = pTimesEaten;
    }

    /**
     * Set guppy last hunger time.
     *
     * @param pLastHungerTime is time now
     */
    public void setLastHungerTime(final long pLastHungerTime) {
        this.lastHungerTime = pLastHungerTime;
    }

    /**
     * Set guppy last move time.
     *
     * @param pLastMoveTime is time now
     */
    public void setLastMoveTime(final long pLastMoveTime) {
        this.lastMoveTime = pLastMoveTime;
    }

    /**
     * Set guppy last drop time.
     *
     * @param pLastDropTime is time now
     */
    public void setLastDropTime(final long pLastDropTime) {
        this.lastDropTime = pLastDropTime;
    }

    /**
     * Get radius.
     *
     * @return radius
     */
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Get hunger.
     *
     * @return hunger which is the time between now and the last eat time
     */
    public int getHunger() {
        return this.hunger;
    }

    /**
     * Get times eaten.
     *
     * @return times eaten which is the number of times that the fish has eaten
     */
    public int getTimesEaten() {
        return this.timesEaten;
    }

    /**
     * Get state.
     *
     * @return state
     */
    public int getState() {
        return this.state;
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
     * Get last drop time.
     *
     * @return last drop time
     */
    public long getLastDropTime() {
        return lastDropTime;
    }

    /**
     * Get list of coins.
     *
     * @return list of coins
     */
    private LinkedList<Coin> getListCoin() {
        return listCoin;
    }

    /**
     * Get list of fish food.
     *
     * @return list of fish food
     */
    private LinkedList<FishFood> getListFishFood() {
        return listFishFood;
    }

    /**
     * Get list of guppy.
     *
     * @return list of guppy
     */
    public LinkedList<Guppy> getListGuppy() {
        return listGuppy;
    }

    /**
     * This method checks whether both
     * itb.akadquarium.Guppy are the same object.
     * It is defined as if both x and y coordinates are the same.
     * @param pGuppy is itb.akadquarium.Guppy
     * @return TRUE if both itb.akadquarium.Guppy are the same object,
     * otherwise FALSE
     */
    boolean equals(final Guppy pGuppy) {
        return ((this.getXi() == pGuppy.getXi())
                && (this.getYi() == pGuppy.getYi()));
    }

    /**
     * This method checks whether itb.akadquarium.Guppy is hungry or not.
     * Hungry is defined as if hunger is less than or equal to HUNGER_TIME.
     * @return TRUE if itb.akadquarium.Guppy is hungry, otherwise FALSE
     */
    public boolean isHungry() {
        return (hunger <= HUNGER_TIME);
    }

    /**
     * This method will eat itb.akadquarium.FishFood.
     * itb.akadquarium.Guppy only eats when itb.akadquarium.Guppy is hungry and
     * will try to reach the nearest food available.
     */
    @Override
    public void eat() {
        getListFishFood().remove(targetFood);
        targetFood = null;
        setHunger(MAX_HUNGER);
        setTimesEaten(getTimesEaten() + 1);

        if ((getTimesEaten() != 0) && (getTimesEaten() % STATE_BOUND == 0)
                && (getState() < STATE_BOUND)) {
            setState(getState() + 1);
        }
    }

    /**
     * This method will drop coin.
     * itb.akadquarium.Coin will drop regularly based on certain interval.
     * Also, coin value calculated from the multiplication of COIN_DROP_VALUE
     * and state
     */
    @Override
    public void dropCoin() {
        this.getListCoin().add(new Coin(getXi(), getYi(),
                Guppy.COIN_DROP_VALUE * state));
        lastDropTime = getTimeNow();
    }

    /**
     * This method is used to move the object.
     * itb.akadquarium.Guppy's movements are described as below:
     * 1. If itb.akadquarium.Guppy is not hungry,
     * itb.akadquarium.Guppy will move randomly.
     * 2. If itb.akadquarium.Guppy is hungry and there is no food available,
     * then it moves randomly.
     * 3. If itb.akadquarium.Guppy is hungry and there is food available,
     * then it moves towards the nearest food.
     *
     * @param g is Graphics
     */
    @Override
    void move(final Graphics g) {
        if (getTimeNow() - lastHungerTime >= DECREASE_HUNGER_TIME) {
            setHunger(getHunger() - 1);
            lastHungerTime = getTimeNow();
        }

        if (getTimeNow() - lastDropTime >= Guppy.DROP_TIME
                * DECREASE_HUNGER_TIME) {
            dropCoin();
        }

        FishFood tempFood = null;
        if ((isHungry()) && (getListFishFood().getCount() != 0)) {
            LinkedList<FishFood> pListFishFood = getListFishFood();

            int idx = 0;
            double min = getDistance(pListFishFood.get(0));

            for (int i = 1; i < pListFishFood.getCount(); i++) {
                double temp = getDistance(pListFishFood.get(i));
                if (min > temp) {
                    idx = i;
                    min = temp;
                }
            }

            tempFood = pListFishFood.get(idx);
        }

        if (tempFood != null) {
            this.targetFood = tempFood;

            double angle = Math.atan2(targetFood.getYi() - this.getYi(),
                    targetFood.getXi() - this.getXi());

            this.setXi(getXi() + Guppy.VELOCITY * (HUNGRY_SPEED_MULTIPLIER)
                    * Math.cos(angle) * 1);
            this.setYi(getYi() + Guppy.VELOCITY * (HUNGRY_SPEED_MULTIPLIER)
                    * Math.sin(angle) * 1);

            if (Math.cos(angle) >= 0) {
                drawGuppy(g, stateOneGuppyRight,
                        stateTwoGuppyRight, stateThreeGuppyRight);
            } else {
                drawGuppy(g, stateOneGuppyLeft,
                        stateTwoGuppyLeft, stateThreeGuppyLeft);
            }

            if (isIntersect(targetFood)) {
                eat();
            }
        } else {
            if (getTimeNow() - lastMoveTime >= MOVE_TIME) {
                Random rand = new Random();
                targetX = rand.nextInt(SCREEN_WIDTH);
                while (Math.abs(targetX - getXi()) < MINIMUM_DISTANCE) {
                    targetX = rand.nextInt(SCREEN_WIDTH);
                }
                targetY = rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND;
                while (Math.abs(targetY - getYi()) < MINIMUM_DISTANCE) {
                    targetY = rand.nextInt(MAX_TARGET_Y) + Y_LOWER_BOUND;
                }
                lastMoveTime = getTimeNow();
            }
            double angle = Math.atan2(this.targetY - this.getYi(),
                    this.targetX - this.getXi());

            if (Math.floor(targetX) != Math.floor(getXi())
                    && Math.floor(targetY) != Math.floor(getYi())) {
                this.setXi(getXi() + VELOCITY * Math.cos(angle) * 1);
                this.setYi(getYi() + VELOCITY * Math.sin(angle) * 1);
            }

            if (Math.cos(angle) >= 0) {
                drawGuppy(g, stateOneGuppyRight,
                        stateTwoGuppyRight, stateThreeGuppyRight);
            } else {
                drawGuppy(g, stateOneGuppyLeft,
                        stateTwoGuppyLeft, stateThreeGuppyLeft);
            }

        }
        targetFood = null;

        if (hunger <= 0) {
            getListGuppy().remove(this);
        }
    }

    /**
     * This method will draw itb.akadquarium.Guppy.
     * @param g is Graphics
     * @param stateOneGuppy is state of first level itb.akadquarium.Guppy
     * @param stateTwoGuppy is state of second level itb.akadquarium.Guppy
     * @param stateThreeGuppy is state of third level itb.akadquarium.Guppy
     */
    private void drawGuppy(final Graphics g, final BufferedImage stateOneGuppy,
                           final BufferedImage stateTwoGuppy,
                           final BufferedImage stateThreeGuppy) {
        if (state == 1) {
            draw(g, stateOneGuppy);
        } else if (state == 2) {
            draw(g, stateTwoGuppy);
        } else {
            draw(g, stateThreeGuppy);
        }
    }
}
