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
    public void addDish(Dish dish) {
        this.menu.add(dish);
    }
    public void removeDish(Dish dish) {
        this.menu.remove(dish);
    }
    public Dish getDish(String dishName) throws Exception {
        return this.getMenu()
                .stream()
                .filter(m -> m.getName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.Dish not found: " + dishName));
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }
    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }
    public Meal getMeal(String mealName) throws Exception {
        return this.getMeals()
                .stream()
                .filter(m -> m.getName().equals(mealName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.Meal not found: " + mealName));
    }
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

