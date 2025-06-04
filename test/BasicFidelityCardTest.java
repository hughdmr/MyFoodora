import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BasicFidelityCardTest {

    private BasicFidelityCard card;
    private Customer dummyCustomer;

    @Before
    public void setUp() {
        card = new BasicFidelityCard();
        // Création d'un client fictif — remplace avec un vrai constructeur si nécessaire
        dummyCustomer = new Customer("john_doe", "password", "John", "Doe", new ArrayList<>(), "john@doe", "01010101");
    }

    @Test
    public void testCalculateFidelityPriceReturnsTotalPrice() {
        double totalPrice = 50.0;
        double result = card.calculateFidelityPrice(totalPrice, dummyCustomer);
        assertEquals(totalPrice, result, 0.001);
    }
}
