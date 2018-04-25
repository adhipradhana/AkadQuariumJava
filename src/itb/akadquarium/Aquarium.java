package itb.akadquarium;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JPanel;

/**
 * itb.akadquarium.Aquarium class.
 * This class instantiates itb.akadquarium.Aquarium.
 */
public class Aquarium extends JPanel implements MouseListener {
    /**
     * Main area.
     */
    public static final int MAIN_AREA = 90;
    /**
     * biggest font size.
     */
    public static final int BIG_FONT_SIZE = 50;
    /**
     * state one.
     */
    public static final int EGG_STATE_1 = 1;
    /**
     * state two.
     */
    public static final int EGG_STATE_2 = 2;
    /**
     * state three.
     */
    public static final int EGG_STATE_3 = 3;
    /**
     * y fish food.
     */
    public static final int Y_FISH_FOOD = 115;
    /**
     * x guppy high.
     */
    public static final int X_GUPPY_HIGH = 120;
    /**
     * x guppy low.
     */
    public static final int X_GUPPY_LOW = 35;
    /**
     * x piranha low.
     */
    public static final int X_PIRANHA_LOW = 160;
    /**
     * x piranha high.
     */
    public static final int X_PIRANHA_HIGH = 235;
    /**
     * x egg low.
     */
    public static final int X_EGG_LOW = 255;
    /**
     * x egg high.
     */
    public static final int X_EGG_HIGH = 333;
    /**
     * x menu low.
     */
    public static final int X_MENU_LOW = 935;
    /**
     * x menu high.
     */
    public static final int X_MENU_HIGH = 1000;
    /**
     * smallest font size.
     */
    public static final int SMALLEST_FONT_SIZE = 10;
    /**
     * win state.
     */
    public static final int WIN_STATE = 4;
    /**
     * Mouse position on screen (horizontal).
     */
    private int xClick;
    /**
     * Mouse position on screen (vertical).
     */
    private int yClick;
    /**
     * State of egg.
     */
    private int eggState;
    /**
     * Marks running.
     */
    private boolean run;
    /**
     * Marks paused.
     */
    private boolean paused;
    /**
     * Marks saving a file.
     */
    private boolean accessSave;
    /**
     * player money.
     */
    private Node<Integer> money;
    /**
     * font type.
     */
    private static Font fontType;
    /**
     * snail.
     */
    private Snail snail;
    /**
     * list coin.
     */
    private LinkedList<Coin> listCoin;
    /**
     * list guppy.
     */
    private LinkedList<Guppy> listGuppy;
    /**
     * list piranha.
     */
    private LinkedList<Piranha> listPiranha;
    /**
     * list fish food.
     */
    private LinkedList<FishFood> listFishFood;
    /**
     * background image.
     */
    private static BufferedImage background;
    /**
     * egg one image.
     */
    private static BufferedImage eggPictureOne;
    /**
     * egg two image.
     */
    private static BufferedImage eggPictureTwo;
    /**
     * win background.
     */
    private static BufferedImage winBackground;
    /**
     * menu background.
     */
    private static BufferedImage menuBackground;
    /**
     * lose background.
     */
    private static BufferedImage loseBackground;
    /**
     * egg picture three.
     */
    private static BufferedImage eggPictureThree;
    /**
     * egg y.
     */
    private static final Integer EGG_Y = 5;
    /**
     * egg x.
     */
    private static final Integer EGG_X = 270;
    /**
     * money y.
     */
    private static final Integer MONEY_Y = 85;
    /**
     * money x.
     */
    private static final Integer MONEY_X = 940;
    /**
     * money value init.
     */
    private static final Integer INIT_MONEY = 25;
    /**
     * small font size.
     */
    public static final int SMALL_FONT_SIZE = 15;
    /**
     * guppy value.
     */
    private static final Integer GUPPY_VALUE = SMALL_FONT_SIZE;
    /**
     * str state x.
     */
    private static final Integer STR_STATE_X = 400;
    /**
     * str stat y.
     */
    private static final Integer STR_STATE_Y = 475;
    /**
     * text pos.
     */
    private static final Integer TEXT_POS = 350;
    /**
     * num one x.
     */
    private static final Integer NUM_ONE_X = 315;
    /**
     * num one y.
     */
    private static final Integer NUM_ONE_Y = 650;
    /**
     * piranha value.
     */
    private static final Integer PIRANHA_VALUE = 30;
    /**
     * fish food value.
     */
    private static final Integer FISHFOOD_VALUE = 5;
    /**
     * init snail x.
     */
    private static final Integer INIT_SNAIL_X = 320;
    /**
     * init snail y.
     */
    private static final Integer INIT_SNAIL_Y = 575;
    /**
     * first egg value.
     */
    private static final Integer FIRST_EGG_VALUE = 100;
    /**
     * second egg value.
     */
    private static final Integer SECOND_EGG_VALUE = 150;
    /**
     * third egg value.
     */
    private static final Integer THIRD_EGG_VALUE = 250;
    /**
     * item y.
     */
    private static final Integer ITEM_Y = 87;
    /**
     * guppy value x.
     */
    private static final Integer GUPPY_VALUE_X = 70;
    /**
     * piranha value x.
     */
    private static final Integer PIRANHA_VALUE_X = 185;
    /**
     * egg value x.
     */
    private static final Integer EGG_VALUE_X = 280;

    /**
     * Instantiates a new itb.akadquarium.Aquarium.
     */
    public Aquarium() {
        listGuppy = new LinkedList<>();
        listPiranha = new LinkedList<>();
        listFishFood = new LinkedList<>();
        listCoin = new LinkedList<>();
        snail = new Snail(INIT_SNAIL_X, INIT_SNAIL_Y);
        money = new Node<>(INIT_MONEY);
        eggState = 0;
        run = true;
        paused = false;
        accessSave = false;
        addMouseListener(this);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (e.getKeyChar() == '1') {
                    accessSave = true;
                }
            }

            @Override
            public void keyPressed(final KeyEvent e) {

            }

            @Override
            public void keyReleased(final KeyEvent e) {

            }
        });

        //setting list
        Snail.setListCoin(getListCoin());
        snail.setMoney(money);
        Coin.setListCoin(getListCoin());
        FishFood.setListFishFood(getListFishFood());
        Guppy.setListCoin(getListCoin());
        Guppy.setListFishFood(getListFishFood());
        Guppy.setListGuppy(getListGuppy());
        Piranha.setListGuppy(getListGuppy());
        Piranha.setListCoin(getListCoin());
        Piranha.setListPiranha(getListPiranha());
    }

    /**
     * This method is used to load data that has been saved to resume the game.
     *
     * @param pAquarium is the itb.akadquarium.Aquarium
     */
    public static void loadData(final Aquarium pAquarium) {
        double x, y;
        int count;
        File file = new File("save.dat");
        try {
            Scanner scanner = new Scanner(file);
            pAquarium.getMoney().setData(scanner.nextInt());
            pAquarium.setEggState(scanner.nextInt());
            x = scanner.nextDouble();
            y = scanner.nextDouble();

            pAquarium.getSnail().setXi(x);
            pAquarium.getSnail().setYi(y);

            count = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                int val = scanner.nextInt();
                pAquarium.getListCoin().add(new Coin(x, y, val));
            }

            count = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                pAquarium.getListFishFood().add(new FishFood(x, y));
            }

            count = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                int hunger = scanner.nextInt();
                int state = scanner.nextInt();
                int eaten = scanner.nextInt();
                Guppy guppy = new Guppy();
                guppy.setXi(x);
                guppy.setYi(y);
                guppy.setHunger(hunger);
                guppy.setState(state);
                guppy.setTimesEaten(eaten);
                pAquarium.getListGuppy().add(guppy);
            }

            count = scanner.nextInt();
            for (int i = 0; i < count; i++) {
                x = scanner.nextDouble();
                y = scanner.nextDouble();
                int hunger = scanner.nextInt();
                Piranha piranha = new Piranha();
                piranha.setXi(x);
                piranha.setYi(y);
                piranha.setHunger(hunger);
                pAquarium.getListPiranha().add(piranha);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to save the current game.
     *
     * @param pAquarium is itb.akadquarium.Aquarium
     */
    public static void saveData(final Aquarium pAquarium) {
        try (PrintWriter printWriter = new PrintWriter("save.dat")) {

            printWriter.printf("%d\n", pAquarium.getMoney().getData());
            printWriter.printf("%d\n", pAquarium.getEggState());
            printWriter.printf("%f %f\n", pAquarium.getSnail().getXi(),
                    pAquarium.getSnail().getYi());

            printWriter.printf("%d\n", pAquarium.getListCoin().getCount());
            for (int i = 0; i < pAquarium.getListCoin().getCount(); i++) {
                Coin c = pAquarium.getListCoin().get(i);
                printWriter.printf("%f %f %d\n", c.getXi(), c.getYi(),
                        c.getValue());
            }

            printWriter.printf("%d\n", pAquarium.getListFishFood().getCount());
            for (int i = 0; i < pAquarium.getListFishFood().getCount(); i++) {
                FishFood fF = pAquarium.getListFishFood().get(i);
                printWriter.printf("%f %f\n", fF.getXi(), fF.getYi());
            }

            printWriter.printf("%d\n", pAquarium.getListGuppy().getCount());
            for (int i = 0; i < pAquarium.getListGuppy().getCount(); i++) {
                Guppy g = pAquarium.getListGuppy().get(i);
                printWriter.printf("%f %f %d %d %d\n", g.getXi(), g.getYi(),
                        g.getHunger(), g.getState(), g.getTimesEaten());
            }

            printWriter.printf("%d\n", pAquarium.getListPiranha().getCount());
            for (int i = 0; i < pAquarium.getListPiranha().getCount(); i++) {
                Piranha p = pAquarium.getListPiranha().get(i);
                printWriter.printf("%f %f %d\n", p.getXi(), p.getYi(),
                        p.getHunger());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set x clicked coordinate position.
     *
     * @param pXClick is the abscissa
     */
    private void setxClick(final int pXClick) {
        this.xClick = pXClick;
    }

    /**
     * Set y clicked coordinate position.
     *
     * @param pYClick is the ordinate
     */
    private void setyClick(final int pYClick) {
        this.yClick = pYClick;
    }

    /**
     * Set egg state value.
     *
     * @param pEggState egg state
     */
    public void setEggState(final int pEggState) {
        this.eggState = pEggState;
    }

    /**
     * Set first egg picture.
     *
     * @param pEggPictureOne is the BufferedImage
     */
    static void setEggPictureOne(final BufferedImage pEggPictureOne) {
        Aquarium.eggPictureOne = pEggPictureOne;
    }

    /**
     * Set second egg picture.
     *
     * @param pEggPictureTwo is the BufferedImage
     */
    static void setEggPictureTwo(final BufferedImage pEggPictureTwo) {
        Aquarium.eggPictureTwo = pEggPictureTwo;
    }

    /**
     * Set third egg picture.
     *
     * @param pEggPictureThree is the BufferedImage
     */
    static void setEggPictureThree(
            final BufferedImage pEggPictureThree) {
        Aquarium.eggPictureThree = pEggPictureThree;
    }

    /**
     * Set window background when the game is running.
     *
     * @param pBackground is the BufferedImage
     */
    static void setBackground(final BufferedImage pBackground) {
        Aquarium.background = pBackground;
    }

    /**
     * Set window background when the player wins.
     *
     * @param pWinBackground is the BufferedImage
     */
    static void setWinBackground(final BufferedImage pWinBackground) {
        Aquarium.winBackground = pWinBackground;
    }

    /**
     * Set the window background when the player loses.
     *
     * @param pLoseBackground is the BufferedImage
     */
    static void setLoseBackground(final BufferedImage pLoseBackground) {
        Aquarium.loseBackground = pLoseBackground;
    }

    /**
     * Set the window menu background.
     *
     * @param pMenuBackground is the BufferedImage
     */
    static void setMenuBackground(final BufferedImage pMenuBackground) {
        Aquarium.menuBackground = pMenuBackground;
    }

    /**
     * Set the font type.
     *
     * @param pFontType is the Font
     */
    static void setFontType(final Font pFontType) {
        Aquarium.fontType = pFontType;
    }

    /**
     * Get list of itb.akadquarium.Guppy.
     *
     * @return itb.akadquarium.LinkedList<itb.akadquarium.Guppy>
     */
    public LinkedList<Guppy> getListGuppy() {
        return this.listGuppy;
    }

    /**
     * Get list of itb.akadquarium.Piranha.
     *
     * @return itb.akadquarium.LinkedList<itb.akadquarium.Piranha>
     */
    public LinkedList<Piranha> getListPiranha() {
        return this.listPiranha;
    }

    /**
     * Get list of itb.akadquarium.Coin.
     *
     * @return itb.akadquarium.LinkedList<itb.akadquarium.Coin>
     */
    public LinkedList<Coin> getListCoin() {
        return this.listCoin;
    }

    /**
     * Get list of itb.akadquarium.FishFood.
     *
     * @return itb.akadquarium.LinkedList<itb.akadquarium.FishFood>
     */
    public LinkedList<FishFood> getListFishFood() {
        return listFishFood;
    }

    /**
     * Get snail.
     *
     * @return snail
     */
    public Snail getSnail() {
        return this.snail;
    }

    /**
     * Get money.
     *
     * @return money
     */
    public Node<Integer> getMoney() {
        return money;
    }

    /**
     * Get egg state.
     *
     * @return eggState
     */
    public int getEggState() {
        return eggState;
    }

    /**
     * Actions when the mouse is clicked.
     *
     * @param e is the MouseEvent
     */
    @Override
    public void mouseClicked(final MouseEvent e) { }

    /**
     * Actions when the mouse is pressed.
     *
     * @param e is the MouseEvent
     */
    @Override
    public void mousePressed(final MouseEvent e) {
        setxClick(e.getX());
        setyClick(e.getY());
    }

    /**
     * Actions when the mouse is released.
     *
     * @param e is the MouseEvent
     */
    @Override
    public void mouseReleased(final MouseEvent e) { }

    /**
     * Actions when the mouse is entered.
     *
     * @param e is the MouseEvent
     */
    @Override
    public void mouseEntered(final MouseEvent e) { }

    /**
     * Actions when the mouse id exited.
     *
     * @param e is the MouseEvent
     */
    @Override
    public void mouseExited(final MouseEvent e) { }

    /**
     * Invoke all object in itb.akadquarium.Aquarium to move.
     *
     * @param g is Graphics
     */
    private void moveAll(final Graphics g) {
        getSnail().move(g);
        for (int i = 0; i < getListPiranha().getCount(); i++) {
            getListPiranha().get(i).move(g);
        }
        for (int i = 0; i < getListGuppy().getCount(); i++) {
            getListGuppy().get(i).move(g);
        }
        for (int i = 0; i < getListCoin().getCount(); i++) {
            getListCoin().get(i).move(g);
        }
        for (int i = 0; i < getListFishFood().getCount(); i++) {
            getListFishFood().get(i).move(g);
        }
    }

    /**
     * Drawing win condition.
     *
     * @param g Graphics
     */
    private void drawWin(final Graphics g) {
        g.drawImage(winBackground,
                getWidth() / EGG_STATE_2 - winBackground.getWidth()
                        / EGG_STATE_2,
                getHeight() / EGG_STATE_2
                        - winBackground.getHeight() / EGG_STATE_2,
                null);
        Font font = fontType.deriveFont((float) BIG_FONT_SIZE);
        g.setFont(font);
        g.setColor(Color.GREEN);

        g.drawString("WIN", STR_STATE_X, TEXT_POS);
    }

    /**
     * Drawing lose condition.
     *
     * @param g Graphics
     */
    private void drawLose(final Graphics g) {
        g.drawImage(loseBackground,
                getWidth() / EGG_STATE_2 - loseBackground.getWidth()
                        / EGG_STATE_2,
                getHeight() / EGG_STATE_2
                        - loseBackground.getHeight() / EGG_STATE_2,
                null);
        Font font = fontType.deriveFont((float) BIG_FONT_SIZE);
        g.setFont(font);
        g.setColor(Color.RED);

        g.drawString("LOSE", STR_STATE_X, TEXT_POS);
    }

    /**
     * Drawing egg in menu.
     *
     * @param g Graphics
     */
    private void drawEgg(final Graphics g) {
        if (eggState == EGG_STATE_1) {
            g.drawImage(eggPictureOne, EGG_X, EGG_Y, null);
            g.drawString(Integer.toString(FIRST_EGG_VALUE),
                    EGG_VALUE_X, ITEM_Y);
        } else if (eggState == EGG_STATE_2) {
            g.drawImage(eggPictureTwo, EGG_X, EGG_Y, null);
            g.drawString(Integer.toString(SECOND_EGG_VALUE),
                    EGG_VALUE_X, ITEM_Y);
        } else if (eggState == EGG_STATE_3) {
            g.drawImage(eggPictureThree, EGG_X, EGG_Y, null);
            g.drawString(Integer.toString(THIRD_EGG_VALUE),
                    EGG_VALUE_X, ITEM_Y);
        }
    }

    /**
     * Resetting all time in fish.
     */
    private void resetTime() {
        for (int i = 0; i < listGuppy.getCount(); i++) {
            Guppy pGuppy = listGuppy.get(i);
            pGuppy.setLastDropTime(pGuppy.getTimeNow());
            pGuppy.setLastHungerTime(pGuppy.getTimeNow());
            pGuppy.setLastMoveTime(pGuppy.getTimeNow());
        }
        for (int i = 0; i < listPiranha.getCount(); i++) {
            Piranha pPiranha = listPiranha.get(i);
            pPiranha.setLastHungerTime(pPiranha.getTimeNow());
            pPiranha.setLastMoveTime(pPiranha.getTimeNow());
        }
    }

    /**
     * Draw aquarium objects while the game is running.
     *
     * @param g is Graphics
     */
    @Override
    public void paint(final Graphics g) {
        grabFocus();
        if (eggState == 0) {
            g.drawImage(menuBackground,
                    getWidth() / EGG_STATE_2 - menuBackground.getWidth()
                            / EGG_STATE_2,
                    getHeight() / EGG_STATE_2
                            - menuBackground.getHeight() / EGG_STATE_2,
                    null);
            Font font = fontType.deriveFont((float) BIG_FONT_SIZE);
            g.setFont(font);
            g.setColor(Color.WHITE);

            g.drawString("PLAY", STR_STATE_X, STR_STATE_Y);

            g.setFont(font.deriveFont((float) SMALL_FONT_SIZE));
            g.drawString("PRESS NUM 1 TO LOAD GAME", NUM_ONE_X, NUM_ONE_Y);
            if (accessSave) {
                Aquarium.loadData(this);
                accessSave = false;
                return;
            }
            if (xClick != 0) {
                getListGuppy().add(new Guppy());
                eggState++;
                setxClick(0);
                setyClick(0);
            }
        } else if (paused) {
            Font font = fontType.deriveFont((float) BIG_FONT_SIZE);
            g.setFont(font);
            g.setColor(Color.WHITE);

            g.drawImage(background, 0, 0, null);

            g.drawString("PAUSED", TEXT_POS, TEXT_POS);

            g.setFont(font.deriveFont((float) SMALL_FONT_SIZE));
            g.drawString("PRESS NUM 1 TO SAVE GAME", NUM_ONE_X, NUM_ONE_Y);
            if (accessSave) {
                Aquarium.saveData(this);
                accessSave = false;
            }

            if (xClick != 0) {
                paused = false;
                resetTime();
                setxClick(0);
                setyClick(0);
            }
        } else if (eggState >= EGG_STATE_1 && eggState <= EGG_STATE_3 && run) {
            Font font = fontType.deriveFont((float) SMALL_FONT_SIZE);
            g.setFont(font);
            g.setColor(Color.WHITE);

            // Buy things
            if (xClick != 0) {
                if (yClick > MAIN_AREA) {
                    boolean getCoin = false;
                    for (int i = 0; i < this.getListCoin().getCount()
                            && !getCoin; i++) {
                        if (this.getListCoin().get(i).isIntersect(
                                xClick, yClick, EGG_STATE_1)) {
                            money.setData(money.getData()
                                    + getListCoin().get(i).getValue());
                            getListCoin().remove(getListCoin().get(i));
                            getCoin = true;
                            setxClick(0);
                            setyClick(0);
                        }
                    }
                    if (!getCoin) {
                        if (money.getData() >= FISHFOOD_VALUE) {
                            getListFishFood().add(new FishFood(xClick,
                                    Y_FISH_FOOD));
                            money.setData(money.getData() - FISHFOOD_VALUE);
                        }
                        setxClick(0);
                        setyClick(0);
                    }
                } else {
                    if (xClick < X_GUPPY_HIGH && xClick > X_GUPPY_LOW) {
                        if (money.getData() >= GUPPY_VALUE) {
                            getListGuppy().add(new Guppy());
                            money.setData(money.getData() - GUPPY_VALUE);
                        }
                        setxClick(0);
                        setyClick(0);
                    } else if (xClick > X_PIRANHA_LOW && xClick
                            < X_PIRANHA_HIGH) {
                        if (money.getData() >= PIRANHA_VALUE) {
                            getListPiranha().add(new Piranha());
                            money.setData(money.getData() - PIRANHA_VALUE);
                        }
                        setxClick(0);
                        setyClick(0);
                    } else if (xClick > X_EGG_LOW && xClick < X_EGG_HIGH) {
                        if (eggState == EGG_STATE_1
                                && money.getData() >= FIRST_EGG_VALUE) {
                            eggState++;
                            money.setData(money.getData() - FIRST_EGG_VALUE);
                        } else if (eggState == EGG_STATE_2
                                && money.getData() >= SECOND_EGG_VALUE) {
                            eggState++;
                            money.setData(money.getData() - SECOND_EGG_VALUE);
                        } else if (eggState == EGG_STATE_3
                                && money.getData() >= THIRD_EGG_VALUE) {
                            eggState++;
                            money.setData(money.getData() - THIRD_EGG_VALUE);
                        }
                        setxClick(0);
                        setyClick(0);
                    } else if (xClick > X_MENU_LOW && xClick < X_MENU_HIGH) {
                        paused = true;
                        setxClick(0);
                        setyClick(0);
                    }
                }
            }

            g.drawImage(background, 0, 0, null);
            g.drawString(Integer.toString(money.getData()), MONEY_X, MONEY_Y);

            //change font
            g.setFont(font.deriveFont((float) SMALLEST_FONT_SIZE));
            g.setColor(Color.WHITE);

            drawEgg(g);
            g.drawString(Integer.toString(GUPPY_VALUE), GUPPY_VALUE_X, ITEM_Y);
            g.drawString(Integer.toString(PIRANHA_VALUE),
                    PIRANHA_VALUE_X, ITEM_Y);

            moveAll(g);

            // Check lose state
            if (getListPiranha().getCount() == 0
                    && getListGuppy().getCount() == 0
                    && money.getData() < GUPPY_VALUE) {
                run = false;
            }

        } else if (eggState == WIN_STATE) {
            drawWin(g);
        } else {
            drawLose(g);
        }
    }
}
