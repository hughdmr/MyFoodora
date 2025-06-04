package system;

import users.*;
import policies.delivery.*;
import policies.profit.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Date;

public class MyFoodoraSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Manager> managers = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Courier> couriers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private DeliveryPolicy deliveryPolicy = new FairOccupationDeliveryPolicy();
    private ProfitPolicy profitPolicy = new DeliveryCostProfitPolicy();

    private float serviceFee = 10.0f;
    private float markupPercentage = 2.0f;
    private float deliveryCost = 5.0f;

    public MyFoodoraSystem() {
        Manager adminManager = new Manager("ceo", "123456789", "ceo", "admin");
        addManager(adminManager);
    }

    // Getters and Setters
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
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
    public Restaurant getRestaurant(String restaurantName) throws Exception {
        return this.getRestaurants()
                .stream()
                .filter(m -> m.getName().equals(restaurantName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.Restaurant [" + restaurantName + "] not found."));
    }

    public ArrayList<Courier> getCouriers() {
        return couriers;
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
    public Courier getCourier(String courierName) throws Exception {
        return this.getCouriers()
                .stream()
                .filter(m -> m.getUsername().equals(courierName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.System.Order [" + courierName + "] not found or already completed"));
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
                .orElseThrow(() -> new Exception("myfoodora.System.Order [" + orderName + "] not found or already completed"));
    }
    public Order getProgressOrder(String orderName) throws Exception {
        return this.getProgressOrders()
                .stream()
                .filter(m -> m.getName().equals(orderName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.System.Order [" + orderName + "] not found or already completed"));
    }
    public ArrayList<Order> filterBetween(ArrayList<Order> orders, Date startDate, Date endDate) {
        return (ArrayList<Order>) orders.stream().filter(order -> {
                    Date date = order.getDate();
                    return date != null && date.after(startDate) && date.before(endDate);
                })
                .collect(Collectors.toList());
    }

    public DeliveryPolicy getDeliveryPolicy() {
        return deliveryPolicy;
    }
    public ProfitPolicy getProfitPolicy() {
        return profitPolicy;
    }

    public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
        this.deliveryPolicy = deliveryPolicy;
    }
    public void setProfitPolicy(ProfitPolicy profitPolicy) {
        this.profitPolicy = profitPolicy;
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

    public void setServiceFee(float serviceFee) {
        this.serviceFee = serviceFee;
    }
    public void setMarkupPercentage(float markupPercentage) {
        this.markupPercentage = markupPercentage;
    }
    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    // Other methods
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

    public double computeProfit(double income, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return income * (markupPercentage / 100) + nbOrders * (serviceFee - deliveryCost);
    }

    public double getTotalIncome() {
        return getCompletedOrders()
                .stream()
                .mapToDouble(Order::getPrice).sum();
    }
    public double getTotalIncomeBetween(Date startDate, Date endDate) {
        return filterBetween(getCompletedOrders(), startDate, endDate)
                .stream()
                .mapToDouble(Order::getPrice).sum();
    }

    public double getTotalProfit() {
        int nbOrders = getCompletedOrders().size();
        double totalIncome = getTotalIncome();
        return computeProfit(totalIncome, nbOrders, serviceFee, markupPercentage, deliveryCost);
    }
    public double getTotalProfitBetween(Date startDate, Date endDate) {
        int nbOrders = filterBetween(getCompletedOrders(), startDate, endDate).size();
        double totalIncome = getTotalIncomeBetween(startDate, endDate);
        return computeProfit(totalIncome, nbOrders, serviceFee, markupPercentage, deliveryCost);
    }
    public double computeProfitVariable(double targetProfit) {
        Date today = new Date();
        Date oneMonthAgo = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
        double totalIncome = getTotalIncomeBetween(oneMonthAgo, today);
        return profitPolicy.computeVariable(
                targetProfit,
                totalIncome,
                getCompletedOrders().size(),
                serviceFee,
                markupPercentage,
                deliveryCost);
    }

    public Courier getBestCourier(Order order) {
        // Keep only onDuty couriers
        ArrayList<Courier> onDutyCourier = (ArrayList<Courier>) couriers
                        .stream()
                        .filter(Courier::isOnDuty)
                        .collect(Collectors.toList());
        return deliveryPolicy.selectCourier(order, onDutyCourier);
    }
    public void completeOrder(Order order, String date) {
        Courier bestCourier = getBestCourier(order);
        order.setDate(date);
        order.setCourier(bestCourier);
        order.computePrice();
        order.setCompleted(true);
        bestCourier.setPosition(order.getCustomer().getAddress());
    }

    // Display
    @Override
    public String toString() {
        return "-------- SYSTEM STATE -------- " + "\n*"
                + managers.toString() + "\n*"
                + customers.toString() + "\n*"
                + restaurants.toString() + "\n*"
                + couriers.toString() + "\n*"
                + orders.toString() + "\n*"
                + "-------- SYSTEM POLICIES --------" + "\n*"
                + deliveryPolicy.toString() + "\n*"
                + profitPolicy.toString() + "\n*"
                + "-------- SYSTEM FEES --------" + "\n*"
                + serviceFee + "\n*"
                + markupPercentage + "\n*"
                + deliveryCost + "\n";
    }
}
