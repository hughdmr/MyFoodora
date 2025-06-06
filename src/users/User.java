package users;

/**
 *
 * @author gravlax,hugues
 * A class to represent a User, which can log in the system
 * with a username and a password.
 *
 */
public class User {
    private static int counter;
    private final int id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = ++counter;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
