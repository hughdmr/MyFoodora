package users;

import java.util.ArrayList;

/**
 *
 * @author gravlax,hugues
 * A class to represent a Courier, which can deliver
 * dishes and meals from a Restaurant to a Customer
 * through an Order
 *
 */
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

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public ArrayList<Double> getPosition() {
        return position;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getDeliveredOrdersCount() {
        return deliveredOrdersCount;
    }
    public boolean isOnDuty() {
        return onDuty;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPosition(ArrayList<Double> position) {
        this.position = position;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setDeliveredOrdersCount(int deliveredOrdersCount) {
        this.deliveredOrdersCount = deliveredOrdersCount;
    }
    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    // Other methods

    /**
     * Increase the count of delivered orders by 1
     */
    public void increaseDeliveredOrdersCount() {
        deliveredOrdersCount++;
    }

    // Display
    @Override
    public String toString() {
        return "[(COURIER) - | name: " + firstName + " " + lastName +
                " | Username: " + getUsername() +
                " | Phone: " + phoneNumber +
                " | Delivered Orders: " + deliveredOrdersCount +
                " | On Duty: " + (onDuty ? "Yes" : "No") +
                " | Position: " + position + "]";
    }
}
