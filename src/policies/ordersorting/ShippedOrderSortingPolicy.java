package policies.ordersorting;

import java.util.ArrayList;

import system.Order;

public interface ShippedOrderSortingPolicy {
    void sort(ArrayList<Order> shippedOrders);
}
