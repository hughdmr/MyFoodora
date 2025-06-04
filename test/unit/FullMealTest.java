package unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import food.Dish;
import food.Meal;
import food.FullMeal;

public class FullMealTest {

    private FullMeal fullMeal;
    private Dish starter;
    private Dish main;
    private Dish dessert;

    @Before
    public void setUp() {
        fullMeal = new FullMeal("Healthy Combo", Meal.MealType.STANDARD, false);

        starter = new Dish("Salad", Dish.DishCategory.STARTER, Dish.DishType.VEGETARIAN, 5.0);
        main = new Dish("Vegetarian Pasta", Dish.DishCategory.MAIN, Dish.DishType.VEGETARIAN, 10.0);
        dessert = new Dish("Fruit Salad", Dish.DishCategory.DESSERT, Dish.DishType.VEGETARIAN, 4.0);
    }

    @Test
    public void testAddDishCorrectlyAssignsDish() {
        fullMeal.addDish(starter);
        fullMeal.addDish(main);
        fullMeal.addDish(dessert);

        assertEquals(starter, fullMeal.getStarter());
        assertEquals(main, fullMeal.getMain());
        assertEquals(dessert, fullMeal.getDessert());
    }

    @Test
    public void testMealTypeUpdatedToVegetarianWhenAllDishesAreVegetarian() {
        fullMeal.addDish(starter);
        fullMeal.addDish(main);
        fullMeal.addDish(dessert);

        assertEquals(Meal.MealType.VEGETARIAN, fullMeal.getMealType());
    }

    @Test
    public void testMealTypeUpdatedToGlutenFreeWhenAllDishesAreGlutenFree() {
        Dish gfStarter = new Dish("GF Salad", Dish.DishCategory.STARTER, Dish.DishType.GLUTEN_FREE, 5.0);
        Dish gfMain = new Dish("GF Main", Dish.DishCategory.MAIN, Dish.DishType.GLUTEN_FREE, 10.0);
        Dish gfDessert = new Dish("GF Cake", Dish.DishCategory.DESSERT, Dish.DishType.GLUTEN_FREE, 4.0);

        fullMeal.addDish(gfStarter);
        fullMeal.addDish(gfMain);
        fullMeal.addDish(gfDessert);

        assertEquals(Meal.MealType.GLUTEN_FREE, fullMeal.getMealType());
    }

    @Test
    public void testToStringContainsDishDetails() {
        fullMeal.addDish(starter);
        fullMeal.addDish(main);
        fullMeal.addDish(dessert);

        String output = fullMeal.toString();
        assertTrue(output.contains("Salad"));
        assertTrue(output.contains("Vegetarian Pasta"));
        assertTrue(output.contains("Fruit Salad"));
    }

    @Test
    public void testSetMealOfTheWeek() {
        fullMeal.setMealofTheWeek(true);
        assertTrue(fullMeal.isMealOfTheWeek());
    }
}
