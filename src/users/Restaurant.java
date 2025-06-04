package users;

import food.Dish;
import food.Meal;

import java.util.ArrayList;

public class Restaurant extends User {
    private String name;
    private ArrayList<Double> position;
    private ArrayList<Dish> menu = new ArrayList<>();
    private ArrayList<Meal> meals = new ArrayList<>();
    private double genericDiscount = 0.05;  // 5%
    private double specialDiscount = 0.10;
    private int deliveredOrdersCount = 0;// 10%

    public Restaurant(String username, String password, String name, ArrayList<Double> position) {
        super(username, password);
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Double> position) {
        this.position = position;
    }

    // Menu management
    public void addDish(Dish dish) {
        this.menu.add(dish);
    }

    public void removeDish(Dish dish) {
        this.menu.remove(dish);
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public Dish getDish(String dishName) throws Exception {
        return this.getMenu()
                .stream()
                .filter(m -> m.getName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new Exception("myfoodora.Dish not found: " + dishName));
    }

    // myfoodora.Meal management
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public Meal getMeal(String mealName) throws Exception {
        Meal meal = this.getMeals()
                    .stream()
                    .filter(m -> m.getName().equals(mealName))
                    .findFirst()
                    .orElseThrow(() -> new Exception("myfoodora.Meal not found: " + mealName));
        return meal;
    }

    // Discount management
    public double getGenericDiscount() {
        return genericDiscount;
    }

    public void setGenericDiscount(double genericDiscount) {
        this.genericDiscount = genericDiscount;
    }

    public double getSpecialDiscount() {
        return specialDiscount;
    }

    public void setSpecialDiscount(double specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public int getDeliveredOrdersCount() {
        return deliveredOrdersCount;
    }

    public void setDeliveredOrdersCount(int deliveredOrdersCount) {
        this.deliveredOrdersCount = deliveredOrdersCount;
    }

    @Override
    public String toString() {
        return name + " | Username: " + getUsername() +
                " | Position: " + position
                + " | Delivered Orders: " + deliveredOrdersCount;
    }
}

