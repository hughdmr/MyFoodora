import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

import users.Courier;

public class CourierTest {

    private Courier courier;
    private ArrayList<Double> position;

    @Before
    public void setUp() {
        position = new ArrayList<>();
        position.add(48.8566);
        position.add(2.3522);

        courier = new Courier("courierUser", "courierPass", "Jean", "Dupont", position, "0601020304");
    }

    @Test
    public void testConstructorInitializesCorrectly() {
        assertEquals("Jean", courier.getFirstName());
        assertEquals("Dupont", courier.getLastName());
        assertEquals("0601020304", courier.getPhoneNumber());
        assertEquals(position, courier.getPosition());
        assertEquals("courierUser", courier.getUsername());
        assertEquals(0, courier.getDeliveredOrdersCount());
        assertFalse(courier.isOnDuty());
    }

    @Test
    public void testSettersAndGetters() {
        courier.setFirstName("Marc");
        courier.setLastName("Durand");
        courier.setPhoneNumber("0611223344");
        ArrayList<Double> newPos = new ArrayList<>();
        newPos.add(1.0);
        newPos.add(2.0);
        courier.setPosition(newPos);
        courier.setOnDuty(true);
        courier.setDeliveredOrdersCount(5);

        assertEquals("Marc", courier.getFirstName());
        assertEquals("Durand", courier.getLastName());
        assertEquals("0611223344", courier.getPhoneNumber());
        assertEquals(newPos, courier.getPosition());
        assertTrue(courier.isOnDuty());
        assertEquals(5, courier.getDeliveredOrdersCount());
    }

    @Test
    public void testIncreaseDeliveredOrdersCount() {
        courier.increaseDeliveredOrdersCount();
        courier.increaseDeliveredOrdersCount();
        assertEquals(2, courier.getDeliveredOrdersCount());
    }

    @Test
    public void testToStringContainsExpectedData() {
        String result = courier.toString();
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
        assertTrue(result.contains("0601020304"));
        assertTrue(result.contains("Delivered Orders: 0"));
        assertTrue(result.contains("On Duty: No"));
    }
}
