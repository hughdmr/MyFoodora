import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import food.Meal;
import food.Dish;

public class MealTest {

    private Meal meal;

    @Before
    public void setUp() {
        // Création d'une instance via une sous-classe fictive ou anonyme (car myfoodora.Meal est protégée)
        meal = new Meal("Test myfoodora.Meal", Meal.Type.STANDARD, Meal.Size.HALF, false) {};
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Test myfoodora.Meal", meal.getName());
        assertEquals(Meal.Type.STANDARD, meal.getType());
        assertEquals(Meal.Size.HALF, meal.getSize());
        assertFalse(meal.isMealOfTheWeek());

        meal.setName("Updated myfoodora.Meal");
        meal.setType(Meal.Type.VEGETARIAN);
        meal.setMealOfTheWeek(true);

        assertEquals("Updated myfoodora.Meal", meal.getName());
        assertEquals(Meal.Type.VEGETARIAN, meal.getType());
        assertTrue(meal.isMealOfTheWeek());
    }

    @Test
    public void testToStringContainsFields() {
        String output = meal.toString();

        assertTrue(output.contains("name: Test myfoodora.Meal"));
        assertTrue(output.contains("type: STANDARD"));
        assertTrue(output.contains("size: HALF"));
        assertTrue(output.contains("mealOfTheWeek: false"));
    }

    @Test
    public void testGetMealTypeFromDishType() {
        assertEquals(Meal.Type.STANDARD, Meal.getMealType(Dish.Type.STANDARD));
        assertEquals(Meal.Type.VEGETARIAN, Meal.getMealType(Dish.Type.VEGETARIAN));
        assertEquals(Meal.Type.GLUTEN_FREE, Meal.getMealType(Dish.Type.GLUTEN_FREE));
    }

    @Test
    public void testDefaultGetPriceReturnsZero() {
        assertEquals(0.0, meal.getPrice(), 0.001);
    }

    @Test
    public void testAddDishDoesNothingInBaseClass() {
        Dish dish = new Dish("Soup", Dish.Category.STARTER, Dish.Type.STANDARD, 4.0);
        meal.addDish(dish);
    }
}
