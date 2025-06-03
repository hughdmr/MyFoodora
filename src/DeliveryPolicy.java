import java.util.ArrayList;

public interface DeliveryPolicy {
    public Courier selectCourier(Order order, ArrayList<Courier> couriers);
}
