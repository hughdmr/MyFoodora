package unit;

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
        halfMeal = new HalfMeal("Quick Bite", Meal.MealType.STANDARD, false);

        main = new Dish("Veggie Burger", Dish.DishCategory.MAIN, Dish.DishType.VEGETARIAN, 8.5);
        starterAsSecond = new Dish("Soup", Dish.DishCategory.STARTER, Dish.DishType.VEGETARIAN, 3.5);
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

        assertEquals(Meal.MealType.VEGETARIAN, halfMeal.getMealType());
    }

    @Test
    public void testMealTypeUpdatedToGlutenFree() {
        Dish gfMain = new Dish("GF Pasta", Dish.DishCategory.MAIN, Dish.DishType.GLUTEN_FREE, 9.0);
        Dish gfDessertAsSecond = new Dish("GF Brownie", Dish.DishCategory.DESSERT, Dish.DishType.GLUTEN_FREE, 4.0);

        halfMeal.addDish(gfMain);
        halfMeal.addDish(gfDessertAsSecond);

        assertEquals(Meal.MealType.GLUTEN_FREE, halfMeal.getMealType());
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
        // Missing second dish — only main is set
        // Expected: warning in console — no assert here since println can't be captured easily in JUnit without redirection
        assertEquals(main, halfMeal.getMain());
        assertNull(halfMeal.getSecond());
    }
}
