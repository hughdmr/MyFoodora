package policies.delivery;

import java.util.ArrayList;

import users.Courier;
import system.Order;

/**
 *
 * @author gravlax,hugues
 * An interface to represent any kind of DeliveryPolicy used by MyFoodora
 * system, which change how courier are selected for a specific Order.
 *
 */
public interface DeliveryPolicy {
    Courier selectCourier(Order order, ArrayList<Courier> couriers);
}
