package users;

import fidelity.BasicFidelityCard;
import fidelity.FidelityCard;
import fidelity.PointFidelityCard;

import java.util.ArrayList;

public class Customer extends User {
    private String firstName;
    private String lastName;
    private ArrayList<Double> address;
    private String email;
    private String phoneNumber;
    private boolean active = true;
    private FidelityCard fidelityCard;
    private int points = 0; // with fidelity.PointFidelityCard

    public Customer(String username, String password, String firstName, String lastName, ArrayList<Double> address, String email, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fidelityCard = new BasicFidelityCard();
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public ArrayList<Double> getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public boolean isActive() {
        return active;
    }
    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }
    public int getPoints() {
        return points;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAddress(ArrayList<Double> address) {
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    // Other methods
    public void addPoints(int points) {
        this.points += points;
    }

    public double calculateFidelityPrice(double basePrice) {
        return fidelityCard.calculateFidelityPrice(basePrice, this);
    }

    public void registerFidelityCard(FidelityCard card) {
        this.fidelityCard = card;
        System.out.println("Registered to " + card.getClass().getSimpleName());
    }

    public void unregisterFidelityCard() {
        this.fidelityCard = null;
        System.out.println("Unregistered from fidelity card");
    }

    // Display
    @Override
    public String toString() {
        return "[(CUSTOMER) - | name: " + firstName + " " + lastName +
                " | username: " + getUsername() +
                " | Email: " + email +
                " | Phone: " + phoneNumber +
                " | Fidelity card: " + (fidelityCard == null ? "Not registered" : fidelityCard.getClass().getSimpleName())
                + (fidelityCard instanceof PointFidelityCard ? " | Points: " + points : "") + "]";
    }
}
