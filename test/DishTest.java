import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DishTest {

    private Dish dish;

    @Before
    public void setUp() {
        dish = new Dish("Spaghetti", Dish.DishCategory.MAIN, Dish.DishType.STANDARD, 12.5);
    }

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertEquals("Spaghetti", dish.getName());
        assertEquals(Dish.DishCategory.MAIN, dish.getDishCategory());
        assertEquals(Dish.DishType.STANDARD, dish.getDishType());
        assertEquals(12.5, dish.getPrice(), 0.001);
    }

    @Test
    public void testSetName() {
        dish.setName("Lasagna");
        assertEquals("Lasagna", dish.getName());
    }

    @Test
    public void testSetPrice() {
        dish.setPrice(15.0);
        assertEquals(15.0, dish.getPrice(), 0.001);
    }

    @Test
    public void testSetDishCategory() {
        dish.setDishCategory(Dish.DishCategory.DESSERT);
        assertEquals(Dish.DishCategory.DESSERT, dish.getDishCategory());
    }

    @Test
    public void testSetDishType() {
        dish.setDishType(Dish.DishType.VEGETARIAN);
        assertEquals(Dish.DishType.VEGETARIAN, dish.getDishType());
    }

    @Test
    public void testToStringContainsRelevantInformation() {
        String output = dish.toString();
        assertTrue(output.contains("Spaghetti"));
        assertTrue(output.contains("MAIN"));
        assertTrue(output.contains("STANDARD"));
        assertTrue(output.contains("12.5"));
    }
}
