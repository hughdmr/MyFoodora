package users;

public class Manager extends User {
    private String firstName;
    private String lastName;

    public Manager(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Display
    @Override
    public String toString() {
        return "[(MANAGER) - | name:" + firstName + " " + lastName +
                " | username: " + getUsername();
    }
}
