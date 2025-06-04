package policies.delivery;

import java.util.ArrayList;

import users.Courier;
import system.Order;

public interface DeliveryPolicy {
    public Courier selectCourier(Order order, ArrayList<Courier> couriers);
}
