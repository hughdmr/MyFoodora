import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Date;


public class MyFoodoraSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Manager> managers = new ArrayList<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Courier> couriers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private DeliveryPolicy deliveryPolicy = new FairOccupationDeliveryPolicy();

    private float serviceFee;
    private float markupPercentage;
    private float deliveryCost;

    public MyFoodoraSystem() {
        Manager adminManager = new Manager("ceo", "123456789", "ceo", "admin");
        managers.add(adminManager);
        users.add(adminManager);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Restaurant getRestaurant(String restaurantName) throws Exception {
        return this.getRestaurants()
                .stream()
                .filter(m -> m.getName().equals(restaurantName))
                .findFirst()
                .orElseThrow(() -> new Exception("Restaurant [" + restaurantName + "] not found."));
    }

    public ArrayList<Restaurant> getRestaurantsSortedByDeliveries() {
        return this.getRestaurants()
                .stream()
                .sorted((c1, c2) -> Integer.compare(
                        c2.getDeliveredOrdersCount(),
                        c1.getDeliveredOrdersCount()
                ))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Courier> getCouriers() {
        return couriers;
    }

    public Courier getCourier(String courierName) throws Exception {
        return this.getCouriers()
                .stream()
                .filter(m -> m.getUsername().equals(courierName))
                .findFirst()
                .orElseThrow(() -> new Exception("Order [" + courierName + "] not found or already completed"));
    }

    public ArrayList<Courier> getCourierSortedByDeliveries() {
        return this.getCouriers()
                .stream()
                .sorted((c1, c2) -> Integer.compare(
                        c2.getDeliveredOrdersCount(),
                        c1.getDeliveredOrdersCount()
                ))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Order> getCompletedOrders() {
        return (ArrayList<Order>) orders
                .stream()
                .filter(Order::isCompleted)
                .collect(Collectors.toList());
    }

    public ArrayList<Order> getProgressOrders() {
        return (ArrayList<Order>) orders
                .stream()
                .filter(m -> !m.isCompleted())
                .collect(Collectors.toList());
    }

    public Order getOrder(String orderName) throws Exception {
        return this.getOrders()
                .stream()
                .filter(m -> m.getName().equals(orderName))
                .findFirst()
                .orElseThrow(() -> new Exception("Order [" + orderName + "] not found or already completed"));
    }

    public Order getProgressOrder(String orderName) throws Exception {
        return this.getProgressOrders()
                .stream()
                .filter(m -> m.getName().equals(orderName))
                .findFirst()
                .orElseThrow(() -> new Exception("Order [" + orderName + "] not found or already completed"));
    }

    public float getServiceFee() {
        return serviceFee;
    }

    public float getMarkupPercentage() {
        return markupPercentage;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void addManager(Manager manager) {
        managers.add(manager);
        users.add(manager);
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        users.add(restaurant);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        users.add(customer);
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
        users.add(courier);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public double getTotalProfit() {
        return getCompletedOrders()
                .stream()
                .mapToDouble(order ->
                        order.getPrice() * (markupPercentage / 100.0) + serviceFee - deliveryCost
                ).sum();
    }

    public double getTotalProfitBetween(Date startDate, Date endDate) {
        return getCompletedOrders()
                .stream()
                .filter(order -> {
                    Date date = order.getDate();
                    return date != null && !date.before(startDate) && !date.after(endDate);
                })
                .mapToDouble(order ->
                        order.getPrice() * (markupPercentage / 100.0) + serviceFee - deliveryCost
                ).sum();
    }

    public DeliveryPolicy getDeliveryPolicy() {
        return deliveryPolicy;
    }

    public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
        this.deliveryPolicy = deliveryPolicy;
    }

    public Courier getBestCourier(Order order) {
        return deliveryPolicy.selectCourier(order, couriers);
    }

    public void completeOrder(Order order, String date) {
        Courier bestCourier = getBestCourier(order);
        order.completeOrder(bestCourier, date);
        bestCourier.setPosition(order.getCustomer().getAddress());
    }
}
