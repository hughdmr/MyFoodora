import java.util.List;

public class Meal {
    public enum MealType {
        STANDARD, VEGETARIAN, GLUTEN_FREE
    }

    public enum MealSize {
        HALF, FULL
    }

    private String name;
    private MealType mealType;
    private MealSize mealSize;
    private boolean mealOfTheWeek;
    private double mealPrice;

    protected Meal(String name, MealType mealType, MealSize mealSize, boolean mealOfTheWeek) {
        this.name = name;
        this.mealType = mealType;
        this.mealSize = mealSize;
        this.mealOfTheWeek = mealOfTheWeek;
        // this.mealPrice = calculatePrice();
    }

   // public <Dish> getDishs(); // chaque sous-classe doit renvoyer ses dishs

    // private double calculatePrice() {
        // float basePrice =  sum (getdishs().Price);
        // return mealOfTheWeek ? basePrice * 0.1f : basePrice * 0.05f;
    //}
    public void addDish(Dish dish) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public MealSize getMealSize() {
        return mealSize;
    }

    public void setMealOfTheWeek(boolean mealOfTheWeek) {
        this.mealOfTheWeek = mealOfTheWeek;
    }

    public boolean isMealOfTheWeek() {
        return mealOfTheWeek;
    }

    public double getMealPrice() {
        return mealPrice;
    }

    public static MealType getMealType(Dish.DishType dishType) {
        return switch (dishType) {
            case STANDARD -> MealType.STANDARD;
            case VEGETARIAN -> MealType.VEGETARIAN;
            case GLUTEN_FREE -> MealType.GLUTEN_FREE;
        };
    }

    public String toString() {
        return "Meal: ["
                + "name: " + name
                + ", mealType: " + mealType
                + ", mealSize: " + mealSize
                + "]";
    }
}


