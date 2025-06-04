package fidelity;
import users.Customer;

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
