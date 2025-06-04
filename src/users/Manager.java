package users;

public class Manager extends User {
    private String firstName;
    private String lastName;

    public Manager(String username, String password, String firstName, String lastName) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "[MANAGER] - " + getUsername() + "|" + firstName + "|" + lastName;
    }
}
