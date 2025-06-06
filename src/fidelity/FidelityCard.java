package fidelity;
import users.Customer;

/**
 *
 * @author gravlax,hugues
 * An interface to represent any kind of FidelityCard a Customer can
 * use during an Order.
 *
 */
public interface FidelityCard {
    double calculateFidelityPrice(double totalPrice, Customer customer);
}
