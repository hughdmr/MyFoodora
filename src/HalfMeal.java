import java.util.List;

public class HalfMeal extends Meal {
    private Item main;
    private Item second;

    public HalfMeal(MealType type, Item main, Item second, boolean mealOfTheWeek) {
        super(type, MealSize.HALF, mealOfTheWeek);
        this.main = main;
        this.second = second;
    }

    @Override
    public List<Item> getItems() {
        return List.of(main, second);
    }

    public Item getMain() {
        return main;
    }

    public Item getSecond() {
        return second;
    }
}

