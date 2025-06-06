package policies.ordersorting;

import java.util.ArrayList;

import system.Order;

/**
 *
 * @author gravlax,hugues
 * Implements the ShippedOrderSortingPolicy interface for sorting the shipped orders
 * according to different criteria.
 *
 */
public interface ShippedOrderSortingPolicy {
    void sort(ArrayList<Order> shippedOrders);
}
