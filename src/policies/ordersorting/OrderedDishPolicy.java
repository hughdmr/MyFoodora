package policies.ordersorting;

import java.util.HashMap;
import java.util.ArrayList;

import food.Dish;
import system.Order;

/**
 *
 * @author gravlax,hugues
 * Implements the ShippedOrderSortingPolicy for sorting orders by dishes and the number
 * of times they have been selected alone "à la carte"
 *
 */
public class OrderedDishPolicy implements ShippedOrderSortingPolicy {

    private final boolean descendingOrder;
    private final String label;

    public OrderedDishPolicy(boolean descendingOrder, String label) {
        this.descendingOrder = descendingOrder;
        this.label = label;
    }

    @Override
    public void sort(ArrayList<Order> shippedOrders) {
        HashMap<Dish, Integer> countMap = new HashMap<>();

        for (Order order : shippedOrders) {
            for (Dish dish : order.getDishesList()) {
                countMap.put(dish, countMap.getOrDefault(dish, 0) + 1);
            }
        }

        ArrayList<HashMap.Entry<Dish, Integer>> sortedList = new ArrayList<>(countMap.entrySet());
        sortedList.sort((a, b) -> descendingOrder
                ? Integer.compare(b.getValue(), a.getValue())
                : Integer.compare(a.getValue(), b.getValue()));

        System.out.println(label + ":");
        for (HashMap.Entry<Dish, Integer> entry : sortedList) {
            System.out.println(entry.getKey().getName() + " - Ordered: " + entry.getValue());
        }
    }
}
