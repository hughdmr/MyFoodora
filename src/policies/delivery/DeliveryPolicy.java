package policies.delivery;

import java.util.ArrayList;

import users.Courier;
import system.Order;

public interface DeliveryPolicy {
    Courier selectCourier(Order order, ArrayList<Courier> couriers);
}
