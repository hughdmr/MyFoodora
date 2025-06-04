import java.util.*;

public class OrderedDishPolicy implements ShippedOrderSortingPolicy {

    private final boolean descendingOrder;
    private final String label;

    public OrderedDishPolicy(boolean descendingOrder, String label) {
        this.descendingOrder = descendingOrder;
        this.label = label;
    }

    @Override
    public void sort(List<Order> shippedOrders) {
        Map<Dish, Integer> countMap = new HashMap<>();

        for (Order order : shippedOrders) {
            for (Dish dish : order.getDishesList()) {
                countMap.put(dish, countMap.getOrDefault(dish, 0) + 1);
            }
        }

        List<Map.Entry<Dish, Integer>> sortedList = new ArrayList<>(countMap.entrySet());
        sortedList.sort((a, b) -> descendingOrder
                ? Integer.compare(b.getValue(), a.getValue())
                : Integer.compare(a.getValue(), b.getValue()));

        System.out.println(label + ":");
        for (Map.Entry<Dish, Integer> entry : sortedList) {
            System.out.println(entry.getKey().getName() + " - Ordered: " + entry.getValue());
        }
    }
}
