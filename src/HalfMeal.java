import java.util.List;

public class HalfMeal extends Meal {
    private Dish main;
    private Dish second;

    public HalfMeal(MealType type, Dish main, Dish second, boolean mealOfTheWeek) {
        super(type, MealSize.HALF, mealOfTheWeek);
        this.main = main;
        this.second = second;
    }

    @Override
    public List<Dish> getDishs() {
        return List.of(main, second);
    }

    public Dish getMain() {
        return main;
    }

    public Dish getSecond() {
        return second;
    }
}

