package food;

/**
 *
 * @author gravlax,hugues
 * A class to represent a FullMeal from a restaurant,
 * which is a kind of Meal
 *
 */
public class FullMeal extends Meal {
    private Dish starter;
    private Dish main;
    private Dish dessert;

    public FullMeal(String name, Type type, boolean mealOfTheWeek) {
        super(name, type, Size.FULL, mealOfTheWeek);
    }

    // Getters and Setters
    public Dish getStarter() { return starter; }
    public Dish getMain() {
        return main;
    }
    public Dish getDessert() { return dessert; }

    public void setMealofTheWeek(boolean mealOfTheWeek) { setMealOfTheWeek(mealOfTheWeek); }

    // Other methods
    /**
     * Get the price of the complete meal, which is the sum of its single dishes
     */
    public double getPrice() {
        return main.getPrice() + starter.getPrice() + dessert.getPrice();
    }

    /**
     * Add a dish to the FullMeal
     * This method will also update the meal status and Type
     * @param dish the dish to add to the meal
     */
    public void addDish(Dish dish) {
        // Add dish to Meal according to the category
        Dish.Category dishCategory = dish.getCategory();
        switch (dishCategory) {
            case STARTER -> this.starter = dish;
            case MAIN -> this.main = dish;
            case DESSERT -> this.dessert = dish;
        }

        // Update Meal status and Meal Type
        if (this.starter != null && this.main != null && this.dessert != null) {
            Type starterType = getMealType(this.starter.getType());
            Type mainType = getMealType(this.main.getType());
            Type dessertType = getMealType(this.dessert.getType());
            // Meal is Gluten Free
            if (starterType.equals(Type.GLUTEN_FREE) && mainType.equals(Type.GLUTEN_FREE) && dessertType.equals(Type.GLUTEN_FREE)) {
                super.setType(Type.GLUTEN_FREE);
            }
            // Meal is Vegetarian
            else if (starterType.equals(Type.VEGETARIAN) && mainType.equals(Type.VEGETARIAN) && dessertType.equals(Type.VEGETARIAN)) {
                super.setType(Type.VEGETARIAN);
            }
            this.setComplete(true);
        }
        else System.out.println("The full menu is not complete, don't forget to add starter and main and dessert");
    }

    // Display
    @Override
    public String toString() {
        return "[(FULL MEAL) - | " + super.toString() + "\n"
                + " -> Starter: " + starter + "\n"
                + " -> Main: " + main + "\n"
                + " -> Dessert: " + dessert + " ]";
    }
}

