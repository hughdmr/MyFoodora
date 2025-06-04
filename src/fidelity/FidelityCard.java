package fidelity;
import users.Customer;

public interface FidelityCard {
    double calculateFidelityPrice(double totalPrice, Customer customer);
}
