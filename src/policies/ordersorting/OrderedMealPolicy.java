package policies.ordersorting;

import java.util.HashMap;
import java.util.ArrayList;

import system.Order;
import food.Meal;

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
    public void sort(ArrayList<Order> shippedOrders) {
        HashMap<T, Integer> countMap = new HashMap<>();

        for (Order order : shippedOrders) {
            for (Meal meal : order.getMealsList()) {
                if (mealType.isInstance(meal)) {
                    T typedMeal = mealType.cast(meal);
                    countMap.put(typedMeal, countMap.getOrDefault(typedMeal, 0) + 1);
                }
            }
        }

        ArrayList<HashMap.Entry<T, Integer>> sortedList = new ArrayList<>(countMap.entrySet());
        sortedList.sort((a, b) -> descendingOrder
                ? Integer.compare(b.getValue(), a.getValue())
                : Integer.compare(a.getValue(), b.getValue()));

        System.out.println(label + ":");
        for (HashMap.Entry<T, Integer> entry : sortedList) {
            System.out.println(entry.getKey().getName() + " - Ordered: " + entry.getValue());
        }
    }
}
