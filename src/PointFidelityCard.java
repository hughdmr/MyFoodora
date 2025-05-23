import java.util.ArrayList;

public class PointFidelityCard implements FidelityCard {
    @Override
    public double calculatePrice(double totalPrice, Customer customer) {
        int newPoints = (int)(totalPrice / 10);
        customer.addPoints(newPoints);

        if (customer.getPoints() >= 100) {
            customer.setPoints(customer.getPoints() - 100);
            return totalPrice * 0.9; // 10% discount
        }

        return totalPrice;
    }
}
