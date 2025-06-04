import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderTest {

    private Order order;
    private Restaurant restaurant;
    private Customer customer;
    private Courier courier;
    private Dish dish1;
    private Meal meal1;

    @Before
    public void setUp() {
        restaurant = new Restaurant("Restaurant", "hhh", "restname", new ArrayList<>(Arrays.asList(0.0, 0.0)));

        customer = new Customer("JohnDoe", "12345", "John", "Doe", new ArrayList<>(), "<EMAIL>", "01010101");
        courier = new Courier("Jane Rider", "67890", "Jane", "Rider", new ArrayList<>(), "0000000");
        order = new Order("Order1", restaurant, customer);

        dish1 = new Dish("Pizza", Dish.DishCategory.MAIN, Dish.DishType.STANDARD, 12.5);
        meal1 = new Meal("Lunch Menu", Meal.MealType.STANDARD, Meal.MealSize.FULL, false) {
            @Override
            public double getPrice() {
                return 20.0;
            }
        };
    }

    @Test
    public void testAddSingleDishAndAddMenu() {
        order.addSingleDish(dish1);
        order.addMenu(meal1);

        double expectedPrice = dish1.getPrice() + meal1.getPrice();
        assertEquals(expectedPrice, order.computePrice(), 0.001);
    }

    @Test
    public void testCompleteOrderSetsAllFields() throws Exception {
        order.addSingleDish(dish1);
        order.addMenu(meal1);

        String dateStr = "2023/06/01 10:00:00";
        order.completeOrder(courier, dateStr);

        // Date parsing to check correctness
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date expectedDate = sdf.parse(dateStr);

        assertEquals(expectedDate, order.getDate());
        assertTrue(order.isCompleted());
        assertEquals(courier, order.getCourier());
        assertEquals(order.computePrice(), order.getPrice(), 0.001);
    }

    @Test
    public void testCompleteOrderWithEmptyDateUsesCurrentDate() {
        order.addSingleDish(dish1);

        order.completeOrder(courier, "");

        assertNotNull(order.getDate());
        assertTrue(order.isCompleted());
        assertEquals(courier, order.getCourier());
        assertEquals(order.computePrice(), order.getPrice(), 0.001);
    }
}
