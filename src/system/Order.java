package system;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import fidelity.BasicFidelityCard;
import food.Dish;
import food.Meal;
import users.Courier;
import users.Customer;
import users.Restaurant;

/**
 *
 * @author gravlax,hugues
 * A class to represent an Order of Meals and Dishes from a Restaurant by a Customer,
 * which will be delivered by a Courier
 *
 */
public class Order {
    private final String name;
    private final Restaurant restaurant;
    private final Customer customer;
    private Courier courier;
    private double price;
    private Date date;
    private boolean completed;
    private final ArrayList<Dish> dishesList = new ArrayList<>();
    private final ArrayList<Meal> mealsList = new ArrayList<>();

    public Order(String orderName, Restaurant restaurant, Customer customer) {
        this.name = orderName;
        this.restaurant = restaurant;
        this.customer = customer;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Courier getCourier() {
        return courier;
    }
    public double getPrice() {
        return price;
    }
    public Date getDate() {
        return date;
    }
    public boolean isCompleted() {
        return completed;
    }
    public ArrayList<Dish> getDishesList() {
        return dishesList;
    }
    public ArrayList<Meal> getMealsList() { return mealsList; }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    /**
     * Set the date of the order. Set to today if no date provided
     * @param dateString which should be in MM/dd/yyyy format
     */
    public void setDate(String dateString) {
        if (dateString.isEmpty()) {
            this.date = new Date();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
            this.date = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
    }
    public void setCompleted(boolean completed) { this.completed = completed; }

    // Other methods
    /**
     * Add a dish to the order
     * @param dish the dish to add to the order
     */
    public void addSingleDish(Dish dish) {
        dishesList.add(dish);
    }

    /**
     * Add a meal to the order
     * @param meal the meal to add to the order
     */
    public void addMenu(Meal meal) {
        mealsList.add(meal);
    }

    /**
     * Compute the price of the order
     * Sum of the single dishes prices + meal prices with relevant discounts
     */
    public void computePrice() {
        double dishesPrice = dishesList.stream().mapToDouble(Dish::getPrice).sum();
        double mealsPrice = mealsList.stream().mapToDouble(
                meal -> {
                    if (meal.isMealOfTheWeek() && customer.getFidelityCard().equals(new BasicFidelityCard()))
                        return meal.getPrice() * restaurant.getSpecialDiscount();
                    else return meal.getPrice() * restaurant.getGenericDiscount();
                }).sum();
        this.price = dishesPrice + mealsPrice;
    }

    // Display
    @Override
    public String toString() {
        return "[(ORDER) - | name: " + name
                + " | restaurant: " + restaurant.getName() + " | customer: " + customer.getUsername()
                + " | courier: " + courier.getUsername() + " | price: " + price
                + " | date: " + date + " | completed: " + completed + " ]";
    }
}
