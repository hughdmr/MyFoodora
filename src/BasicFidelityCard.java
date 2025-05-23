import java.util.ArrayList;

public class BasicFidelityCard implements FidelityCard {
    @Override
    public double calculatePrice(double totalPrice, Customer customer) {
        // Access to restaurant special offers can be handled elsewhere
        return totalPrice;
    }
}
