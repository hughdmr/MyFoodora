import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import food.HalfMeal;
import food.Meal;
import food.Dish;

public class HalfMealTest {

    private HalfMeal halfMeal;
    private Dish main;
    private Dish starterAsSecond;

    @Before
    public void setUp() {
        halfMeal = new HalfMeal("Quick Bite", Meal.Type.STANDARD, false);

        main = new Dish("Veggie Burger", Dish.Category.MAIN, Dish.Type.VEGETARIAN, 8.5);
        starterAsSecond = new Dish("Soup", Dish.Category.STARTER, Dish.Type.VEGETARIAN, 3.5);
    }

    @Test
    public void testAddDishAssignsCorrectly() {
        halfMeal.addDish(main);
        halfMeal.addDish(starterAsSecond);

        assertEquals(main, halfMeal.getMain());
        assertEquals(starterAsSecond, halfMeal.getSecond());
    }

    @Test
    public void testMealTypeUpdatedToVegetarian() {
        halfMeal.addDish(main);
        halfMeal.addDish(starterAsSecond);

        assertEquals(Meal.Type.VEGETARIAN, halfMeal.getType());
    }

    @Test
    public void testMealTypeUpdatedToGlutenFree() {
        Dish gfMain = new Dish("GF Pasta", Dish.Category.MAIN, Dish.Type.GLUTEN_FREE, 9.0);
        Dish gfDessertAsSecond = new Dish("GF Brownie", Dish.Category.DESSERT, Dish.Type.GLUTEN_FREE, 4.0);

        halfMeal.addDish(gfMain);
        halfMeal.addDish(gfDessertAsSecond);

        assertEquals(Meal.Type.GLUTEN_FREE, halfMeal.getType());
    }

    @Test
    public void testSetMealOfTheWeek() {
        halfMeal.setMealofTheWeek(true);
        assertTrue(halfMeal.isMealOfTheWeek());
    }

    @Test
    public void testToStringContainsDishes() {
        halfMeal.addDish(main);
        halfMeal.addDish(starterAsSecond);

        String output = halfMeal.toString();
        assertTrue(output.contains("Veggie Burger"));
        assertTrue(output.contains("Soup"));
    }

    @Test
    public void testIncompleteMealPrintsWarning() {
        halfMeal.addDish(main);
        assertEquals(main, halfMeal.getMain());
        assertNull(halfMeal.getSecond());
    }
}
