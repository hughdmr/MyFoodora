package food;

/**
 *
 * @author gravlax,hugues
 * A class to represent a Meal from a restaurant, which can
 * be bought by a Customer through an Order. The Meal can
 * be Half or Full.
 *
 */
public class Meal {
    public enum Type {
        STANDARD, VEGETARIAN, GLUTEN_FREE
    }

    public enum Size {
        HALF, FULL
    }

    private String name;
    private Type type;
    private final Size size;
    private boolean mealOfTheWeek;
    private boolean complete = false;

    public Meal(String name, Type type, Size size, boolean mealOfTheWeek) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.mealOfTheWeek = mealOfTheWeek;
    }

    // Getters and Setters
    public String getName() { return name; }
    public Type getType() { return type; }
    public Size getSize() { return size; }
    public boolean isMealOfTheWeek() { return mealOfTheWeek; }
    public boolean isComplete() { return complete; }

    public void setName(String name) { this.name = name; }
    public void setType(Type type) { this.type = type; }
    public void setMealOfTheWeek(boolean mealOfTheWeek) { this.mealOfTheWeek = mealOfTheWeek; }
    public void setComplete(boolean complete) { this.complete = complete; }

    // Other methods
    public double getPrice() { return 0; }

    public void addDish(Dish dish) {}

    public static Type getMealType(Dish.Type dishType) {
        return switch (dishType) {
            case STANDARD -> Type.STANDARD;
            case VEGETARIAN -> Type.VEGETARIAN;
            case GLUTEN_FREE -> Type.GLUTEN_FREE;
        };
    }

    // Display
    @Override
    public String toString() {
        return "[(MEAL) - | name: " + name + " | type: " + type
                + " | size: " + size + " | mealOfTheWeek: " + mealOfTheWeek + " ]";
    }
}


