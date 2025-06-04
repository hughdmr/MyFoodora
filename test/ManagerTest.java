import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerTest {

    private Manager manager;

    @Before
    public void setUp() {
        manager = new Manager("managerUser", "securePass", "Alice", "Dupont");
    }

    @Test
    public void testConstructorInitializesCorrectly() {
        assertEquals("managerUser", manager.getUsername());
        assertEquals("securePass", manager.getPassword());
        assertEquals("Alice", manager.getFirstName());
        assertEquals("Dupont", manager.getLastName());
    }

    @Test
    public void testSetFirstName() {
        manager.setFirstName("Bob");
        assertEquals("Bob", manager.getFirstName());
    }

    @Test
    public void testSetLastName() {
        manager.setLastName("Martin");
        assertEquals("Martin", manager.getLastName());
    }
}
