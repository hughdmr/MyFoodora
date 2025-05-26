import java.util.ArrayList;
import java.util.UUID;

public class Restaurant extends User {
    private String name;
    private UUID id;
    private ArrayList<Double> position;
    private ArrayList<Dish> menu = new ArrayList<>();
    private ArrayList<Meal> meals = new ArrayList<>();
    private double genericDiscount = 0.05;  // 5%
    private double specialDiscount = 0.10;  // 10%

    public Restaurant(String username, String password, String name, ArrayList<Double> position) {
        super(username, password);
        this.name = name;
        this.position = position;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
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

    // Meal management
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        this.meals.remove(meal);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
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

}

