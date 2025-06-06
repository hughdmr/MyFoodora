package fidelity;
import users.Customer;

/**
 *
 * @author gravlax,hugues
 * Implements the FidelityCard interface for computing prices for orders in the classic way.
 * There are no discounts with the BasicFidelityCard, but you are eligible to Restaurants
 * special offers
 *
 */
public class BasicFidelityCard implements FidelityCard {
    public double calculateFidelityPrice(double totalPrice, Customer customer) {
        return totalPrice;
    }
}
