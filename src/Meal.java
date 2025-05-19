import java.util.List;

public abstract class Meal {
    public enum MealType {
        STANDARD, VEGETARIAN, GLUTEN_FREE
    }

    public enum MealSize {
        HALF, FULL
    }

    private MealType mealType;
    private MealSize mealSize;
    private boolean mealOfTheWeek;
    private double mealPrice;

    protected Meal(MealType mealType, MealSize mealSize, boolean mealOfTheWeek) {
        this.mealType = mealType;
        this.mealSize = mealSize;
        this.mealOfTheWeek = mealOfTheWeek;
        this.mealPrice = calculatePrice();
    }

    public abstract List<String> getItems(); // chaque sous-classe doit renvoyer ses items

    private double calculatePrice() {
        // float basePrice =  sum (getItems().Price);
        return mealOfTheWeek ? basePrice * 0.1f : basePrice * 0.05f;
    }

    public MealType getMealType() {
        return mealType;
    }

    public MealSize getMealSize() {
        return mealSize;
    }

    public boolean isMealOfTheWeek() {
        return mealOfTheWeek;
    }

    public double getMealPrice() {
        return mealPrice;
    }
}


