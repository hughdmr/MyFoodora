import java.util.List;

public class HalfMeal extends Meal {
    private Item main;
    private Item second;

    public HalfMeal(MealType type, String main, String second, boolean mealOfTheWeek) {
        super(type, MealSize.HALF, mealOfTheWeek);
        this.main = main;
        this.second = second;
    }

    @Override
    public List<String> getItems() {
        return List.of(main, second);
    }

    public String getMain() {
        return main;
    }

    public String getSecond() {
        return second;
    }
}

