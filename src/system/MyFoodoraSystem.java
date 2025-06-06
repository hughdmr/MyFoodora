package system;

import users.*;
import policies.delivery.*;
import policies.profit.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Date;

/**
 *
 * @author gravlax,hugues
 * A class to represent MyFoodora main system, gathering User management,
 * Meal and Dish management for a Restaurant, Order computation and distribution
 * to a Courier and many utilities for the system.
 *
 */
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

    /**
     * Get completed orders
     */
    public ArrayList<Order> getCompletedOrders() {
        return (ArrayList<Order>) orders
                .stream()
                .filter(Order::isCompleted)
                .collect(Collectors.toList());
    }

    /**
     * Get in progess orders (not completed)
     */
    public ArrayList<Order> getProgressOrders() {
        return (ArrayList<Order>) orders
                .stream()
                .filter(m -> !m.isCompleted())
                .collect(Collectors.toList());
    }

    /**
     * Get order by its name
     * @param orderName the order name as str
     * @throws Exception if no order with this name are found
     */
    public Order getOrder(String orderName) throws Exception {
        return this.getOrders()
                .stream()
                .filter(m -> m.getName().equals(orderName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.System.Order [" + orderName + "] not found or already completed"));
    }

    /**
     * Get in progress order by its name
     * @param orderName the order name as str
     * @throws Exception if no order with this name are found
     */
    public Order getProgressOrder(String orderName) throws Exception {
        return this.getProgressOrders()
                .stream()
                .filter(m -> m.getName().equals(orderName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.System.Order [" + orderName + "] not found or already completed"));
    }

    /**
     * Filter orders between a start and an end Date
     * @param orders the orders array
     * @param startDate the startDate to filter from
     * @param endDate the endDate to filter to
     */
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
    /**
     * Add a manager to MyFoodora system
     * @param manager the manager to add
     */
    public void addManager(Manager manager) {
        managers.add(manager);
        users.add(manager);
    }

    /**
     * Add a restaurant to MyFoodora system
     * @param restaurant the restaurant to add
     */
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
        users.add(restaurant);
    }

    /**
     * Add a customer to MyFoodora system
     * @param customer the customer to add
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        users.add(customer);
    }

    /**
     * Add a courier to MyFoodora system
     * @param courier the courier to add
     */
    public void addCourier(Courier courier) {
        couriers.add(courier);
        users.add(courier);
    }

    /**
     * Add a order to MyFoodora system
     * @param order the order to add
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Compute the profit of MyFoodora with business metrics,
     * according to a simple formula
     * @param income the money from all orders
     * @param nbOrders the number of completed orders
     * @param serviceFee the constant serviceFee for each order
     * @param markupPercentage the variable fee for each order
     * @param deliveryCost the cost of the delivery for each order
     */
    public double computeProfit(double income, int nbOrders, double serviceFee, double markupPercentage, double deliveryCost) {
        return income * (markupPercentage / 100) + nbOrders * (serviceFee - deliveryCost);
    }

    /**
     * Compute the total income of MyFoodora. This is the sum
     * of prices of all completed orders.
     */
    public double getTotalIncome() {
        return getCompletedOrders()
                .stream()
                .mapToDouble(Order::getPrice).sum();
    }

    /**
     * Compute the total income of MyFoodora between 2 dates. This is the sum
     * of prices of all completed orders.
     * @param startDate the startDate to compute from
     * @param endDate the endDate to compute to
     */
    public double getTotalIncomeBetween(Date startDate, Date endDate) {
        return filterBetween(getCompletedOrders(), startDate, endDate)
                .stream()
                .mapToDouble(Order::getPrice).sum();
    }

    /**
     * Compute the total profit of MyFoodora system
     */
    public double getTotalProfit() {
        int nbOrders = getCompletedOrders().size();
        double totalIncome = getTotalIncome();
        return computeProfit(totalIncome, nbOrders, serviceFee, markupPercentage, deliveryCost);
    }

    /**
     * Compute the profit of MyFoodora between 2 dates.
     * @param startDate the startDate to compute from
     * @param endDate the endDate to compute to
     */
    public double getTotalProfitBetween(Date startDate, Date endDate) {
        int nbOrders = filterBetween(getCompletedOrders(), startDate, endDate).size();
        double totalIncome = getTotalIncomeBetween(startDate, endDate);
        return computeProfit(totalIncome, nbOrders, serviceFee, markupPercentage, deliveryCost);
    }

    /**
     * This method is for the system administrator
     * According to the profit policy chosen, compute
     * one of the three fees - serviceFee, MarkupPercentage
     * or DeliveryCost - MyFoodora system has to
     * set in order to get to the target profit.
     * @param targetProfit the profit to target
     */
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

    /**
     * Return the best available courier for a specific
     * order, according to the deliveryPolicy chosen
     * @param order the order of interest
     */
    public Courier getBestCourier(Order order) {
        // Keep only onDuty couriers
        ArrayList<Courier> onDutyCourier = (ArrayList<Courier>) couriers
                        .stream()
                        .filter(Courier::isOnDuty)
                        .collect(Collectors.toList());
        return deliveryPolicy.selectCourier(order, onDutyCourier);
    }

    /**
     * When all dishes and meals have been selected and
     * the customer wants to complete his order:
     * - get the best courier and set it to the order
     * - set the date of the order
     * - compute the price of the order
     * - mark the order as completed
     * - move the courier at the customer position
     * @param order the order of interest
     * @param date the date of the order
     */
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
