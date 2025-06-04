import java.util.*;

public class OrderedMealPolicy<T extends Meal> implements ShippedOrderSortingPolicy {

    private final Class<T> mealType;
    private final boolean descendingOrder;
    private final String label;

    public OrderedMealPolicy(Class<T> mealType, boolean descendingOrder, String label) {
        this.mealType = mealType;
        this.descendingOrder = descendingOrder;
        this.label = label;
    }

    @Override
    public void sort(List<Order> shippedOrders) {
        Map<T, Integer> countMap = new HashMap<>();

        for (Order order : shippedOrders) {
            for (Meal meal : order.getMealsList()) {
                if (mealType.isInstance(meal)) {
                    T typedMeal = mealType.cast(meal);
                    countMap.put(typedMeal, countMap.getOrDefault(typedMeal, 0) + 1);
                }
            }
        }

        List<Map.Entry<T, Integer>> sortedList = new ArrayList<>(countMap.entrySet());
        sortedList.sort((a, b) -> descendingOrder
                ? Integer.compare(b.getValue(), a.getValue())
                : Integer.compare(a.getValue(), b.getValue()));

        System.out.println(label + ":");
        for (Map.Entry<T, Integer> entry : sortedList) {
            System.out.println(entry.getKey().getName() + " - Ordered: " + entry.getValue());
        }
    }
}
