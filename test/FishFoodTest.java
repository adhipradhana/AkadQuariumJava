import itb.akadquarium.FishFood;
import itb.akadquarium.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FishFoodTest {

    @Test
    void setListFishFood() {
        LinkedList<FishFood> listFishFood = new LinkedList<>();
        listFishFood.add(new FishFood(3.0, 4.0));
        assertEquals(3.0, listFishFood.get(0).getXi());
        assertEquals(4.0, listFishFood.get(0).getYi());
    }

    @Test
    void getRadius() {
        FishFood fishFood = new FishFood(3.0, 4.0);
        assertEquals(5, fishFood.getRadius());
    }

    @Test
    void getVelocity() {
        assertEquals(0.5, FishFood.getVelocity());
    }

    @Test
    void getListFishFood() {
        LinkedList<FishFood> listFishFood = new LinkedList<>();
        listFishFood.add(new FishFood(3.0, 4.0));
        FishFood.setListFishFood(listFishFood);
        assertEquals(listFishFood, FishFood.getListFishFood());
    }
}