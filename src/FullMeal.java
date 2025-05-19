import java.util.List;

public class FullMeal extends Meal {
    private String starter;
    private String main;
    private String dessert;

    public FullMeal(MealType type, String starter, String main, String dessert, boolean mealOfTheWeek) {
        super(type, MealSize.FULL, mealOfTheWeek);
        this.starter = starter;
        this.main = main;
        this.dessert = dessert;
    }

    @Override
    public List<String> getItems() {
        return List.of(starter, main, dessert);
    }

    public String getStarter() {
        return starter;
    }

    public String getMain() {
        return main;
    }

    public String getDessert() {
        return dessert;
    }
}

