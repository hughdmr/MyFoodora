import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import users.User;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("testuser", "pass123");
    }

    @Test
    public void testConstructorInitializesCorrectly() {
        assertEquals("testuser", user.getUsername());
        assertEquals("pass123", user.getPassword());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newname");
        assertEquals("newname", user.getUsername());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newpass");
        assertEquals("newpass", user.getPassword());
    }

    @Test
    public void testUserIdIsIncremented() {
        User u1 = new User("u1", "p1");
        User u2 = new User("u2", "p2");
        assertTrue(u2.getId() > u1.getId());
    }
}
