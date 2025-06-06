package users;

import food.Dish;
import food.Meal;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author gravlax,hugues
 * A class to represent a Restaurant, which can have several
 * dishes and meals ordered by a Customer through an Order
 *
 */
public class Restaurant extends User {
    private String name;
    private ArrayList<Double> position;
    private final ArrayList<Dish> menu = new ArrayList<>();
    private final ArrayList<Meal> meals = new ArrayList<>();
    private double genericDiscount = 0.05;  // 5%
    private double specialDiscount = 0.10;  // 10%
    private int deliveredOrdersCount = 0;

    public Restaurant(String username, String password, String name, ArrayList<Double> position) {
        super(username, password);
        this.name = name;
        this.position = position;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public ArrayList<Double> getPosition() {
        return position;
    }
    public ArrayList<Dish> getMenu() {
        return menu;
    }
    public ArrayList<Meal> getMeals() {
        return meals;
    }
    public double getGenericDiscount() {
        return genericDiscount;
    }
    public double getSpecialDiscount() {
        return specialDiscount;
    }
    public int getDeliveredOrdersCount() {
        return deliveredOrdersCount;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(ArrayList<Double> position) {
        this.position = position;
    }
    public void setGenericDiscount(double genericDiscount) {
        this.genericDiscount = genericDiscount;
    }
    public void setSpecialDiscount(double specialDiscount) {
        this.specialDiscount = specialDiscount;
    }
    public void setDeliveredOrdersCount(int deliveredOrdersCount) {
        this.deliveredOrdersCount = deliveredOrdersCount;
    }

    // Other methods

    /**
     * Add a dish to the Restaurant menu
     * @param dish the dish to add to the Restaurant menu
     */
    public void addDish(Dish dish) {
        this.menu.add(dish);
    }

    /**
     * Remove a dish from the Restaurant menu
     * @param dish the dish to remove to the Restaurant menu
     */
    public void removeDish(Dish dish) {
        this.menu.remove(dish);
    }

    /**
     * Get a dish from the Restaurant menu
     * @param dishName the name of the dish in str type
     */
    public Dish getDish(String dishName) throws Exception {
        return this.getMenu()
                .stream()
                .filter(m -> m.getName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.Dish not found: " + dishName));
    }

    /**
     * Add a meal to the Restaurant menu
     * @param meal the meal to add to the Restaurant menu
     */
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    /**
     * Remove a meal from the Restaurant menu
     * @param meal the meal to remove from the Restaurant menu
     */
    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }

    /**
     * Get a meal from the Restaurant menu
     * @param mealName the name of the meal in str type
     */
    public Meal getMeal(String mealName) throws Exception {
        return this.getMeals()
                .stream()
                .filter(m -> m.getName().equals(mealName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.Meal not found: " + mealName));
    }

    /**
     * Get an array of completed meals from the Restaurant
     */
    public ArrayList<Meal> getCompleteMeals() {
        return (ArrayList<Meal>) getMeals()
                .stream()
                .filter(Meal::isComplete)
                .collect(Collectors.toList());
    }

    // Display
    @Override
    public String toString() {
        return "[(RESTAURANT) - | name:" + name +
                " | Username: " + getUsername() +
                " | Position: " + position +
                " | Delivered Orders: " + deliveredOrdersCount;
    }
}

