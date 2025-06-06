package unit;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

import fidelity.BasicFidelityCard;
import users.Customer;

public class BasicFidelityCardTest {

    private BasicFidelityCard card;
    private Customer dummyCustomer;

    @Before
    public void setUp() {
        card = new BasicFidelityCard();
        dummyCustomer = new Customer("john_doe", "password", "John", "Doe", new ArrayList<>(), "john@doe", "01010101");
    }

    @Test
    public void testCalculateFidelityPriceReturnsTotalPrice() {
        double totalPrice = 50.0;
        double result = card.calculateFidelityPrice(totalPrice, dummyCustomer);
        assertEquals(totalPrice, result, 0.001);
    }
}
