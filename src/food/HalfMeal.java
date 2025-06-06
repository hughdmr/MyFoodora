package food;

/**
 *
 * @author gravlax,hugues
 * A class to represent a HalfMeal from a restaurant,
 * which is a kind of Meal
 *
 */
public class HalfMeal extends Meal {
    private Dish main;
    private Dish second;

    public HalfMeal(String name, Type type, boolean mealOfTheWeek) {
        super(name, type, Size.HALF, mealOfTheWeek);
    }

    // Getters and Setters
    public Dish getMain() {
        return main;
    }
    public Dish getSecond() {
        return second;
    }

    public void setMealofTheWeek(boolean mealOfTheWeek) { setMealOfTheWeek(mealOfTheWeek);}

    // Other methods
    public double getPrice() { return main.getPrice() + second.getPrice(); }

    public void addDish(Dish dish) {
        // Add dish to Meal according to the category
        Dish.Category dishCategory = dish.getCategory();
        switch (dishCategory) {
            case STARTER, DESSERT -> this.second = dish;
            case MAIN -> this.main = dish;
        }

        // Update Meal status and Meal Type
        if (this.main != null && this.second != null) {
            Type mainType = getMealType(this.main.getType());
            Type secondType = getMealType(this.second.getType());
            // Meal is Gluten Free
            if (mainType.equals(Type.GLUTEN_FREE) && secondType.equals(Type.GLUTEN_FREE)) {
                super.setType(Type.GLUTEN_FREE);
            }
            // Meal is Vegetarian
            else if (mainType.equals(Type.VEGETARIAN) && secondType.equals(Type.VEGETARIAN)) {
                super.setType(Type.VEGETARIAN);
            }
            this.setComplete(true);
        }
        else System.out.println("The half menu is not complete, don't forget to add main and second");
    }

    // Display
    @Override
    public String toString() {
        return "[(HALF MEAL) - | " + super.toString() + "\n"
                + " -> Main: " + main + "\n"
                + " -> Second: " + second + " ]";
    }
}

