import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String orderName;
    private Restaurant restaurant;
    private Customer customer;
    private Courier courier;
    private double price;
    private Date date;
    private boolean completed;
    private ArrayList<Dish> dishesList = new ArrayList<>();;
    private ArrayList<Meal> mealsList = new ArrayList<>();;

    public Order(String orderName, Restaurant restaurant, Customer customer) {
        this.orderName = orderName;
        this.restaurant = restaurant;
        this.customer = customer;
    }

    public String getName() {
        return orderName;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void addSingleDish(Dish dish) {
        dishesList.add(dish);
    }

    public ArrayList<Meal> getMealsList() {
        return mealsList;
    }

    public ArrayList<Dish> getDishesList() {
        return dishesList;
    }

    public void addMenu(Meal meal) {
        mealsList.add(meal);
    }

    public double computePrice() {
        double dishesPrice = dishesList.stream().mapToDouble(Dish::getPrice).sum();
        double mealsPrice = mealsList.stream().mapToDouble(Meal::getPrice).sum();
        return dishesPrice + mealsPrice;
    }

    public void completeOrder(Courier courier, String date) {
        if (date.isEmpty()) {
            this.date = new Date();
        } else {
            this.date = new Date(date);
        }
        this.price = computePrice();
        this.courier = courier;
        this.completed = true;
    }
}
