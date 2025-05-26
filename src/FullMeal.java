import java.util.List;

public class FullMeal extends Meal {
    private Dish starter;
    private Dish main;
    private Dish dessert;

    public FullMeal(MealType type, Dish starter, Dish main, Dish dessert, boolean mealOfTheWeek) {
        super(type, MealSize.FULL, mealOfTheWeek);
        this.starter = starter;
        this.main = main;
        this.dessert = dessert;
    }

    @Override
    public List<Dish> getDishs() {
        return List.of(starter, main, dessert);
    }

    public Dish getStarter() {
        return starter;
    }

    public Dish getMain() {
        return main;
    }

    public Dish getDessert() {
        return dessert;
    }
}

