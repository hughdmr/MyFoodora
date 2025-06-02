import java.util.ArrayList;

public class MyFoodoraSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Manager> managers = new ArrayList<>();
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Courier> couriers = new ArrayList<>();
    private ArrayList<Order> completedOrders = new ArrayList<>();
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

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Courier> getCouriers() {
        return couriers;
    }

    public ArrayList<Order> getCompletedOrders() {
        return completedOrders;
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
        completedOrders.add(order);
    }
}
