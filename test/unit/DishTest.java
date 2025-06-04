package unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import food.Dish;

public class DishTest {

    private Dish dish;

    @Before
    public void setUp() {
        dish = new Dish("Spaghetti", Dish.Category.MAIN, Dish.Type.STANDARD, 12.5);
    }

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        assertEquals("Spaghetti", dish.getName());
        assertEquals(Dish.Category.MAIN, dish.getCategory());
        assertEquals(Dish.Type.STANDARD, dish.getType());
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
        dish.setCategory(Dish.Category.DESSERT);
        assertEquals(Dish.Category.DESSERT, dish.getCategory());
    }

    @Test
    public void testSetDishType() {
        dish.setType(Dish.Type.VEGETARIAN);
        assertEquals(Dish.Type.VEGETARIAN, dish.getType());
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
