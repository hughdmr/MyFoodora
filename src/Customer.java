import java.util.ArrayList;
import java.util.UUID;

public class Customer extends User {
    private String firstName;
    private String lastName;
    private ArrayList<Double> address;
    private String email;
    private String phoneNumber;
    private boolean active = true;
    private FidelityCard fidelityCard;
    private int points = 0; // with PointFidelityCard

    public Customer(String username, String password, String firstName, String lastName, ArrayList<Double> address, String email, String phoneNumber) {
        super(username, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public ArrayList<Double> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Double> address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public FidelityCard getFidelityCard() {
        return fidelityCard;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public double calculateFinalPrice(double basePrice) {
        return fidelityCard.calculatePrice(basePrice, this);
    }

    // register/unregister to/from a fidelity card plan
    public void registerFidelityCard(FidelityCard card) {
        this.fidelityCard = card;
        System.out.println("Registered to " + card.getClass().getSimpleName());
    }

    public void unregisterFidelityCard() {
        this.fidelityCard = null;
        System.out.println("Unregistered from fidelity card");
    }

    // access the information related to their account: ... and points acquired with a fidelity program
    public void viewAccountInfo() {
        System.out.println("Username: " + getUsername());
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);

        if (fidelityCard == null) {
            System.out.println("Fidelity card: Not registered");
        } else {
            System.out.println("Fidelity card type: " + fidelityCard.getClass().getSimpleName());
            if (fidelityCard instanceof PointFidelityCard) {
                System.out.println("Points: " + points);
            }
        }
    }
}
