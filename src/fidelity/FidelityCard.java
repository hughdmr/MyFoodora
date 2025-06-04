package fidelity;
import users.Customer;

public interface FidelityCard {
    public double calculateFidelityPrice(double totalPrice, Customer customer);
}
