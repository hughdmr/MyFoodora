import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

import users.Customer;
import fidelity.LotteryFidelityCard;

public class LotteryFidelityCardTest {

    private LotteryFidelityCard card;
    private Customer dummyCustomer;
    private ArrayList<Double> position;


    @Before
    public void setUp() {
        card = new LotteryFidelityCard();
        position = new ArrayList<>();
        position.add(48.8566);
        position.add(2.3522);
        dummyCustomer = new Customer("JohnDoe", "12345", "John", "Doe", position, "john@doe", "0101010101") ;
    }

    @Test
    public void testCalculateFidelityPriceReturnsZeroSometimes() {
        boolean won = false;
        boolean paid = false;

        // Repeat many times to observe both possible outcomes (simulate luck)
        for (int i = 0; i < 1000; i++) {
            double result = card.calculateFidelityPrice(50.0, dummyCustomer);
            if (result == 0.0) won = true;
            if (result == 50.0) paid = true;
            if (won && paid) break;
        }

        assertTrue("Expected to win at least once in 1000 tries", won);
        assertTrue("Expected to pay full price at least once in 1000 tries", paid);
    }

    @Test
    public void testCalculateFidelityPriceReturnsFullPriceMostOfTime() {
        int wins = 0;
        int trials = 1000;

        for (int i = 0; i < trials; i++) {
            if (card.calculateFidelityPrice(100.0, dummyCustomer) == 0.0) {
                wins++;
            }
        }

        double observedWinRate = (double) wins / trials;
        assertTrue("Win rate should be roughly 5%, got: " + observedWinRate,
                observedWinRate > 0.03 && observedWinRate < 0.07);
    }
}
