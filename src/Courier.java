import java.util.ArrayList;

public class Courier extends User {
    private String firstName;
    private String lastName;
    private ArrayList<Double> position;
    private String phoneNumber;
    private int deliveredOrdersCount = 0;
    private boolean onDuty = false;

    public Courier(String username, String password, String firstName, String lastName, ArrayList<Double> position, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Double> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Double> position) {
        this.position = position;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    public void increaseDeliveredOrdersCount() {
        deliveredOrdersCount++;
    }
}
