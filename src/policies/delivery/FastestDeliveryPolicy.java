package policies.delivery;

import java.util.ArrayList;
import java.util.HashMap;

import users.Courier;
import system.Order;

public class FastestDeliveryPolicy implements DeliveryPolicy {

    public double getDistance(ArrayList<Double> position1, ArrayList<Double> position2) {
        return Math.sqrt(
                Math.pow(position2.get(0) - position1.get(0), 2)
                        + Math.pow(position2.get(1) - position1.get(1), 2));
    }

    private Courier getMinDistance(HashMap<Courier, Double> map) {
        ArrayList<Courier> couriers = new ArrayList<>(map.keySet());
        Courier minKey = couriers.get(0);
        double minDistance = map.get(minKey);

        for(Courier courier : couriers) {
            double distance = map.get(courier);
            if(distance < minDistance) {
                minDistance = distance;
                minKey = courier;
            }
        }
        return minKey;
    }

    public Courier selectCourier(Order order, ArrayList<Courier> couriers) {
        ArrayList<Double> restaurantAddress = order.getRestaurant().getPosition();
        HashMap<Courier, Double> couriersDistances = new HashMap<>();

        for (Courier courier : couriers) {
            couriersDistances.put(courier, getDistance(restaurantAddress, courier.getPosition()));
        }
        return getMinDistance(couriersDistances);
    }
}
