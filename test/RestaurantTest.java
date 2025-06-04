import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RestaurantTest {

    private Restaurant restaurant;
    private Dish testDish;
    private Meal testMeal;

    @Before
    public void setUp() {
        ArrayList<Double> position = new ArrayList<>();
        position.add(48.8566);
        position.add(2.3522);

        restaurant = new Restaurant("restoUser", "pass", "Le Resto", position);

        testDish = new Dish("Pasta", Dish.DishCategory.MAIN, Dish.DishType.STANDARD, 10.0);
        testMeal = new Meal("Midi", Meal.MealType.STANDARD, Meal.MealSize.FULL, false);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Le Resto", restaurant.getName());
        assertEquals("restoUser", restaurant.getUsername());
        assertEquals(2, restaurant.getPosition().size());
        assertEquals(48.8566, restaurant.getPosition().get(0), 0.0001);
    }

    @Test
    public void testSetNameAndPosition() {
        restaurant.setName("New Resto");
        assertEquals("New Resto", restaurant.getName());

        ArrayList<Double> newPos = new ArrayList<>();
        newPos.add(40.7128);
        newPos.add(-74.0060);
        restaurant.setPosition(newPos);
        assertEquals(newPos, restaurant.getPosition());
    }

    @Test
    public void testAddAndGetDish() throws Exception {
        restaurant.addDish(testDish);
        Dish foundDish = restaurant.getDish("Pasta");
        assertEquals(testDish, foundDish);
    }

    @Test(expected = Exception.class)
    public void testGetDishThrowsException() throws Exception {
        restaurant.getDish("NonExistentDish");
    }

    @Test
    public void testAddAndGetMeal() throws Exception {
        restaurant.addMeal(testMeal);
        Meal foundMeal = restaurant.getMeal("Midi");
        assertEquals(testMeal, foundMeal);
    }

    @Test(expected = Exception.class)
    public void testGetMealThrowsException() throws Exception {
        restaurant.getMeal("NonExistentMeal");
    }

    @Test
    public void testDiscountSettersAndGetters() {
        restaurant.setGenericDiscount(0.1);
        assertEquals(0.1, restaurant.getGenericDiscount(), 0.0001);

        restaurant.setSpecialDiscount(0.15);
        assertEquals(0.15, restaurant.getSpecialDiscount(), 0.0001);
    }

    @Test
    public void testDeliveredOrdersCount() {
        restaurant.setDeliveredOrdersCount(5);
        assertEquals(5, restaurant.getDeliveredOrdersCount());
    }

    @Test
    public void testToString() {
        String str = restaurant.toString();
        assertTrue(str.contains("Le Resto"));
        assertTrue(str.contains("restoUser"));
        assertTrue(str.contains("Delivered Orders"));
    }
}
