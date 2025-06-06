package fidelity;

import java.util.Random;
import users.Customer;

/**
 *
 * @author gravlax,hugues
 * Implements the FidelityCard interface for computing prices for orders with the lottery system.
 * With the LotteryFidelityCard, Users don't have access to Restaurant special discounts, but they
 * have a 5% probability to get their order for free one time a day.
 *
 */
public class LotteryFidelityCard implements FidelityCard {
    private static final double WIN_PROBABILITY = 0.05;

    public double calculateFidelityPrice(double totalPrice, Customer customer) {
        Random rand = new Random();
        if (rand.nextDouble() < WIN_PROBABILITY) return 0.0;
        else return totalPrice;
    }
}
