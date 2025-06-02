import java.util.ArrayList;

public class Order {
    private static int orderID;
    private Restaurant restaurant;
    private Customer customer;
    private Courier courier;
    private float price;
    private ArrayList<Dish> singleDishes;
    private ArrayList<Meal> menus;

    public Order(Restaurant restaurant, Customer customer) {
        orderID++;
        this.restaurant = restaurant;
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void addSingleDish(Dish dish) {
        singleDishes.add(dish);
    }

    public void addMenu(Meal meal) {
        menus.add(meal);
    }
}
