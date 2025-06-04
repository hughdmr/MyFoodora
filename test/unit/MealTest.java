package unit;

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
        meal = new Meal("Test myfoodora.Meal", Meal.MealType.STANDARD, Meal.MealSize.HALF, false) {};
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Test myfoodora.Meal", meal.getName());
        assertEquals(Meal.MealType.STANDARD, meal.getMealType());
        assertEquals(Meal.MealSize.HALF, meal.getMealSize());
        assertFalse(meal.isMealOfTheWeek());

        meal.setName("Updated myfoodora.Meal");
        meal.setMealType(Meal.MealType.VEGETARIAN);
        meal.setMealOfTheWeek(true);

        assertEquals("Updated myfoodora.Meal", meal.getName());
        assertEquals(Meal.MealType.VEGETARIAN, meal.getMealType());
        assertTrue(meal.isMealOfTheWeek());
    }

    @Test
    public void testToStringContainsFields() {
        String output = meal.toString();

        assertTrue(output.contains("name: Test myfoodora.Meal"));
        assertTrue(output.contains("mealType: STANDARD"));
        assertTrue(output.contains("mealSize: HALF"));
        assertTrue(output.contains("mealOfTheWeek: false"));
    }

    @Test
    public void testGetMealTypeFromDishType() {
        assertEquals(Meal.MealType.STANDARD, Meal.getMealType(Dish.DishType.STANDARD));
        assertEquals(Meal.MealType.VEGETARIAN, Meal.getMealType(Dish.DishType.VEGETARIAN));
        assertEquals(Meal.MealType.GLUTEN_FREE, Meal.getMealType(Dish.DishType.GLUTEN_FREE));
    }

    @Test
    public void testDefaultGetPriceReturnsZero() {
        assertEquals(0.0, meal.getPrice(), 0.001);
    }

    @Test
    public void testAddDishDoesNothingInBaseClass() {
        Dish dish = new Dish("Soup", Dish.DishCategory.STARTER, Dish.DishType.STANDARD, 4.0);
        // Ne devrait rien faire car pas de logique dans myfoodora.Meal
        meal.addDish(dish);
        // Pas de getDishs() pour vérifier, donc on vérifie juste qu'aucune exception ne se produit
    }
}
