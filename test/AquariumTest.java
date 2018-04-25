import itb.akadquarium.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    @Test
    void loadData() {
        try (PrintWriter printWriter = new PrintWriter("save.dat")) {

            printWriter.printf("%d\n", 25);
            printWriter.printf("%d\n", 1);
            printWriter.printf("%f %f\n", 300.0, 550.0);

            printWriter.printf("%d\n", 0);

            printWriter.printf("%d\n", 1);
            printWriter.printf("%f %f\n", 400.0, 300.0);

            printWriter.printf("%d\n", 0);

            printWriter.printf("%d\n", 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Aquarium aquarium = new Aquarium();
        Aquarium.loadData(aquarium);

        assertEquals(25, aquarium.getMoney().getData().intValue());
        assertEquals(1, aquarium.getEggState());
        assertEquals(300.0, aquarium.getSnail().getXi());
        assertEquals(550.0, aquarium.getSnail().getYi());
        assertEquals(0, aquarium.getListCoin().getCount());
        assertEquals(1, aquarium.getListFishFood().getCount());
        assertEquals(400.0, aquarium.getListFishFood().get(0).getXi());
        assertEquals(300.0, aquarium.getListFishFood().get(0).getYi());
        assertEquals(0, aquarium.getListGuppy().getCount());
        assertEquals(0, aquarium.getListPiranha().getCount());
    }

    @Test
    void saveData() {
        Aquarium pAquarium = new Aquarium();
        pAquarium.getMoney().setData(25);
        pAquarium.setEggState(1);
        pAquarium.getSnail().setXi(300.0);
        pAquarium.getSnail().setYi(550.0);
        pAquarium.getListFishFood().add(new FishFood(400.0, 300.0));

        Aquarium.saveData(pAquarium);

        try {
            Scanner scanner = new Scanner(new File("save.dat"));
            pAquarium = new Aquarium();

            pAquarium.getMoney().setData(scanner.nextInt());
            pAquarium.setEggState(scanner.nextInt());
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            pAquarium.getSnail().setXi(x);
            pAquarium.getSnail().setYi(y);

            int count = scanner.nextInt();
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

        assertEquals(25, pAquarium.getMoney().getData().intValue());
        assertEquals(1, pAquarium.getEggState());
        assertEquals(300.0, pAquarium.getSnail().getXi());
        assertEquals(550.0, pAquarium.getSnail().getYi());
        assertEquals(0, pAquarium.getListCoin().getCount());
        assertEquals(1, pAquarium.getListFishFood().getCount());
        assertEquals(400.0, pAquarium.getListFishFood().get(0).getXi());
        assertEquals(300.0, pAquarium.getListFishFood().get(0).getYi());
        assertEquals(0, pAquarium.getListGuppy().getCount());
        assertEquals(0, pAquarium.getListPiranha().getCount());
    }
}