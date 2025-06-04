import java.util.ArrayList;

public class PointFidelityCard implements FidelityCard {
    public double calculateFidelityPrice(double totalPrice, Customer customer) {
        int newPoints = (int)(totalPrice / 10);
        customer.addPoints(newPoints);

        if (customer.getPoints() >= 100) {
            customer.setPoints(customer.getPoints() - 100);
            return totalPrice * 0.9;
        }

        return totalPrice;
    }
}
