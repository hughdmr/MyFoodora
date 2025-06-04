package fidelity;
import users.Customer;

public class BasicFidelityCard implements FidelityCard {
    public double calculateFidelityPrice(double totalPrice, Customer customer) {
        return totalPrice;
    }
}
