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
        fullMeal = new FullMeal("Healthy Combo", Meal.Type.STANDARD, false);

        starter = new Dish("Salad", Dish.Category.STARTER, Dish.Type.VEGETARIAN, 5.0);
        main = new Dish("Vegetarian Pasta", Dish.Category.MAIN, Dish.Type.VEGETARIAN, 10.0);
        dessert = new Dish("Fruit Salad", Dish.Category.DESSERT, Dish.Type.VEGETARIAN, 4.0);
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

        assertEquals(Meal.Type.VEGETARIAN, fullMeal.getType());
    }

    @Test
    public void testMealTypeUpdatedToGlutenFreeWhenAllDishesAreGlutenFree() {
        Dish gfStarter = new Dish("GF Salad", Dish.Category.STARTER, Dish.Type.GLUTEN_FREE, 5.0);
        Dish gfMain = new Dish("GF Main", Dish.Category.MAIN, Dish.Type.GLUTEN_FREE, 10.0);
        Dish gfDessert = new Dish("GF Cake", Dish.Category.DESSERT, Dish.Type.GLUTEN_FREE, 4.0);

        fullMeal.addDish(gfStarter);
        fullMeal.addDish(gfMain);
        fullMeal.addDish(gfDessert);

        assertEquals(Meal.Type.GLUTEN_FREE, fullMeal.getType());
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
