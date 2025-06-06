package unit;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

import users.Customer;
import fidelity.PointFidelityCard;
import fidelity.FidelityCard;

public class CustomerTest {

    private Customer customer;
    private ArrayList<Double> address;

    @Before
    public void setUp() {
        address = new ArrayList<>();
        address.add(45.75);
        address.add(4.85);

        customer = new Customer(
                "customerUser",
                "customerPass",
                "Alice",
                "Martin",
                address,
                "alice@example.com",
                "0612345678"
        );
    }

    @Test
    public void testConstructorInitializesCorrectly() {
        assertEquals("Alice", customer.getFirstName());
        assertEquals("Martin", customer.getLastName());
        assertEquals("alice@example.com", customer.getEmail());
        assertEquals("0612345678", customer.getPhoneNumber());
        assertEquals(address, customer.getAddress());
        assertTrue(customer.isActive());
        assertNotNull(customer.getFidelityCard());
        assertEquals(0, customer.getPoints());
    }

    @Test
    public void testSettersAndGetters() {
        customer.setFirstName("Bob");
        customer.setLastName("Durand");
        customer.setEmail("bob@example.com");
        customer.setPhoneNumber("0698765432");

        ArrayList<Double> newAddress = new ArrayList<>();
        newAddress.add(43.3);
        newAddress.add(5.4);
        customer.setAddress(newAddress);
        customer.setActive(false);
        customer.setPoints(20);

        assertEquals("Bob", customer.getFirstName());
        assertEquals("Durand", customer.getLastName());
        assertEquals("bob@example.com", customer.getEmail());
        assertEquals("0698765432", customer.getPhoneNumber());
        assertEquals(newAddress, customer.getAddress());
        assertFalse(customer.isActive());
        assertEquals(20, customer.getPoints());
    }

    @Test
    public void testAddPoints() {
        customer.addPoints(10);
        customer.addPoints(5);
        assertEquals(15, customer.getPoints());
    }

    @Test
    public void testCalculateFidelityPriceWithBasicCard() {
        double price = 100.0;
        double result = customer.calculateFidelityPrice(price);
        assertEquals(100.0, result, 0.001); // fidelity.BasicFidelityCard returns full price
    }

    @Test
    public void testRegisterAndUnregisterFidelityCard() {
        FidelityCard pointCard = new PointFidelityCard();
        customer.registerFidelityCard(pointCard);
        assertEquals(pointCard, customer.getFidelityCard());

        customer.unregisterFidelityCard();
        assertNull(customer.getFidelityCard());
    }

    @Test
    public void testToStringIncludesExpectedData() {
        String output = customer.toString();
        assertTrue(output.contains("customerUser"));
        assertTrue(output.contains("Alice Martin"));
        assertTrue(output.contains("alice@example.com"));
        assertTrue(output.contains("0612345678"));
        assertTrue(output.contains("BasicFidelityCard"));
    }

    @Test
    public void testToStringWithPointFidelityCard() {
        FidelityCard pointCard = new PointFidelityCard();
        customer.registerFidelityCard(pointCard);
        customer.addPoints(30);
        String output = customer.toString();

        assertTrue(output.contains("Fidelity card: PointFidelityCard"));
        assertTrue(output.contains("Points: 30"));
    }
}
