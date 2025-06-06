package fidelity;
import users.Customer;

/**
 *
 * @author gravlax,hugues
 * Implements the FidelityCard interface for computing prices for orders with the point system.
 * With the PointFidelityCard, Users don't have access to Restaurant special discounts. They follow
 * another system: Users get 1 point each 10€. When they have 100 points, they can have
 * a 10% discount on their order.
 *
 */
public class PointFidelityCard implements FidelityCard {
    public double calculateFidelityPrice(double totalPrice, Customer customer) {
        int newPoints = (int) Math.floor(totalPrice / 10.0);
        customer.addPoints(newPoints);

        if (customer.getPoints() >= 100) {
            customer.setPoints(customer.getPoints() - 100);
            return totalPrice * 0.9;
        }
        else return totalPrice;
    }
}
