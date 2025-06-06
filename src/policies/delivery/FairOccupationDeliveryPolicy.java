package policies.delivery;

import java.util.ArrayList;

import users.Courier;
import system.Order;

/**
 *
 * @author gravlax,hugues
 * Implements the DeliveryPolicy interface for selecting courier based
 * on the least busy one.
 *
 */
public class FairOccupationDeliveryPolicy implements DeliveryPolicy {
    private Courier getLeastBusy(ArrayList<Courier> couriers) {
        Courier leastBusy = couriers.get(0);
        double minCount = couriers.get(0).getDeliveredOrdersCount();

        for(Courier courier : couriers) {
            int ordersCount = courier.getDeliveredOrdersCount();
            if(ordersCount < minCount) {
                minCount = ordersCount;
                leastBusy = courier;
            }
        }
        return leastBusy;
    }

    public Courier selectCourier(Order order, ArrayList<Courier> couriers) {
        return getLeastBusy(couriers);
    }
}
