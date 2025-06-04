import java.util.List;

public interface ShippedOrderSortingPolicy {
    void sort(List<Order> shippedOrders);
}
