import java.util.ArrayList;

public interface FidelityCard {
    public double calculatePrice(ArrayList<Item> cart, Customer customer);
}
