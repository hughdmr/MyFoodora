import java.util.Random;

public class LotteryFidelityCard implements FidelityCard {
    private static final double WIN_PROBABILITY = 0.05; // 5%
    public double calculateFidelityPrice(double totalPrice, Customer customer) {
        Random rand = new Random();
        if (rand.nextDouble() < WIN_PROBABILITY) {
            return 0.0; // Free meal
        }
        return totalPrice;
    }
}
