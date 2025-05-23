import java.util.List;

public class FullMeal extends Meal {
    private Item starter;
    private Item main;
    private Item dessert;

    public FullMeal(MealType type, Item starter, Item main, Item dessert, boolean mealOfTheWeek) {
        super(type, MealSize.FULL, mealOfTheWeek);
        this.starter = starter;
        this.main = main;
        this.dessert = dessert;
    }

    @Override
    public List<Item> getItems() {
        return List.of(starter, main, dessert);
    }

    public Item getStarter() {
        return starter;
    }

    public Item getMain() {
        return main;
    }

    public Item getDessert() {
        return dessert;
    }
}

