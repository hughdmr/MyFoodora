package unit;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

import fidelity.PointFidelityCard;
import users.Customer;

public class PointFidelityCardTest {

    private PointFidelityCard card;
    private Customer customer;

    @Before
    public void setUp() {
        card = new PointFidelityCard();
        ArrayList<Double> position = new ArrayList<>();
        position.add(48.8566);
        position.add(2.3522);
        customer = new Customer("JohnDoe", "12345", "John", "Doe", position, "john@doe.com", "0101010101");
        customer.setPoints(0);  // On commence à 0 points
    }

    @Test
    public void testPointsAccumulation() {
        double price1 = 45.0; // 4 points
        double price2 = 25.0; // 2 points
        card.calculateFidelityPrice(price1, customer);
        assertEquals(4, customer.getPoints());

        card.calculateFidelityPrice(price2, customer);
        assertEquals(6, customer.getPoints());
    }

    @Test
    public void testDiscountAppliedWhenPointsReached() {
        customer.setPoints(95);
        double totalPrice = 50.0;

        double discountedPrice = card.calculateFidelityPrice(totalPrice, customer);

        assertEquals(0, customer.getPoints());
        assertEquals(totalPrice * 0.9, discountedPrice, 0.001);
    }

    @Test
    public void testNoDiscountIfPointsBelowThreshold() {
        customer.setPoints(50);
        double totalPrice = 30.0;

        double price = card.calculateFidelityPrice(totalPrice, customer);

        assertEquals(53, customer.getPoints());
        assertEquals(totalPrice, price, 0.001);
    }
}
