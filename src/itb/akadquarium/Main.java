package itb.akadquarium;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/**
 * Class itb.akadquarium.Main.
 * This is the controller class to run another classes during the game
 */
public final class Main {
    /**
     * jFrame.
     */
    private static JFrame jFrame;
    /**
     * audioIn.
     */
    private static AudioInputStream audioIn;
    /**
     * Window Width size.
     */
    private static final int WINDOW_WIDTH = 1100;
    /**
     * Window Height size.
     */
    private static final int WINDOW_HEIGHT = 720;
    /**
     * Nano seconds.
     */
    private static final int NANO_SECONDS = 1000000;
    /**
     * Thousand.
     */
    private static final int THOUSAND = 1000;
    /**
     * fps.
     */
    private static final int FRAME_PER_SECOND = 45;

    /**
     * Default constructor.
     */
    private Main() {

    }

    /**
     * This method instantiates JFrame.
     *
     * @return JFrame
     */
    private static JFrame initGameFrame() {
        JFrame pJFrame = new JFrame();
        pJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pJFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        pJFrame.setVisible(true);

        return pJFrame;
    }

    /**
     * This method used to update the frame while the game is running.
     */
    private static void frameUpdate() {
        long begin;
        long diff;
        long sleep;
        int fps = THOUSAND / FRAME_PER_SECOND;

        jFrame.invalidate();
        jFrame.validate();
        begin = System.nanoTime() / NANO_SECONDS;
        jFrame.repaint();
        diff = System.nanoTime() / NANO_SECONDS - begin;
        sleep = fps - (int) diff;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

    /**
     * This method used to load resources,
     * especially for the visual audio purposes.
     */
    private static void loadResources() {
        try {
            Coin.setImageCoin(ImageIO.read(
                    new File("./sprite/coin.png")));
            Coin.setImageDiamond(ImageIO.read(
                    new File("./sprite/diamond.png")));
            Snail.setSnailLeft(ImageIO.read(
                    new File("./sprite/snaill.png")));
            Snail.setSnailRight(ImageIO.read(
                    new File("./sprite/snailr.png")));
            FishFood.setImage(ImageIO.read(
                    new File("./sprite/food.png")));
            Guppy.setStateOneGuppyLeft(ImageIO.read(
                    new File("./sprite/guppyl1.png")));
            Guppy.setStateOneGuppyRight(ImageIO.read(
                    new File("./sprite/guppyr1.png")));
            Guppy.setStateTwoGuppyLeft(ImageIO.read(
                    new File("./sprite/guppyl2.png")));
            Guppy.setStateTwoGuppyRight(ImageIO.read(
                    new File("./sprite/guppyr2.png")));
            Guppy.setStateThreeGuppyLeft(ImageIO.read(
                    new File("./sprite/guppyl3.png")));
            Guppy.setStateThreeGuppyRight(ImageIO.read(
                    new File("./sprite/guppyr3.png")));
            Piranha.setPiranhaLeft(ImageIO.read(
                    new File("./sprite/piranhal.png")));
            Piranha.setPiranhaRight(ImageIO.read(
                    new File("./sprite/piranhar.png")));
            Aquarium.setBackground(ImageIO.read(
                    new File("./sprite/bg.png")));
            Aquarium.setEggPictureOne(ImageIO.read(
                    new File("./sprite/egg1.png")));
            Aquarium.setEggPictureTwo(ImageIO.read(
                    new File("./sprite/egg2.png")));
            Aquarium.setEggPictureThree(ImageIO.read(
                    new File("./sprite/egg3.png")));
            Aquarium.setMenuBackground(ImageIO.read(
                    new File("./sprite/menu.jpg")));
            Aquarium.setWinBackground(ImageIO.read(
                    new File("./sprite/win.jpg")));
            Aquarium.setLoseBackground(ImageIO.read(
                    new File("./sprite/lose.jpg")));
            Aquarium.setFontType(Font.createFont(Font.TRUETYPE_FONT,
                    new File("./sprite/LemonMilk.otf")));
            audioIn = AudioSystem.getAudioInputStream(
                    new File("./sprite/bgm.wav"));
        } catch (IOException | FontFormatException
                | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * itb.akadquarium.Main program.
     *
     * @param args is array string when execute program from command line
     */
    public static void main(final String[] args) {
        jFrame = initGameFrame();
        loadResources();
        Timer timer = new Timer(1, e -> frameUpdate());
        Clip c = null;
        try {
            c = AudioSystem.getClip();
            c.open(audioIn);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        if (c != null) {
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }
        Aquarium aquarium = new Aquarium();
        jFrame.getContentPane().add(aquarium);
        timer.start();
    }
}
