import policies.delivery.DeliveryPolicy;

import java.util.ArrayList;

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
