package food;

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

    protected Meal(String name, MealType mealType, MealSize mealSize, boolean mealOfTheWeek) {
        this.name = name;
        this.mealType = mealType;
        this.mealSize = mealSize;
        this.mealOfTheWeek = mealOfTheWeek;
    }

   // public <myfoodora.Dish> getDishs(); // chaque sous-classe doit renvoyer ses dishs

    // private double calculatePrice() {
        // float basePrice =  sum (getdishs().Price);
        // return mealOfTheWeek ? basePrice * 0.1f : basePrice * 0.05f;
    //}

    public double getPrice() {
        // TODO
        return 0;
    }

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

    public static MealType getMealType(Dish.DishType dishType) {
        return switch (dishType) {
            case STANDARD -> MealType.STANDARD;
            case VEGETARIAN -> MealType.VEGETARIAN;
            case GLUTEN_FREE -> MealType.GLUTEN_FREE;
        };
    }

    public String toString() {
        return "myfoodora.Meal: ["
                + "name: " + name
                + ", mealType: " + mealType
                + ", mealSize: " + mealSize
                + ", mealOfTheWeek: " + mealOfTheWeek
                + "]";
    }
}


