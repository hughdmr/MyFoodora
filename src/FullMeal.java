import java.util.List;

public class FullMeal extends Meal {
    private Dish starter;
    private Dish main;
    private Dish dessert;

    public FullMeal(String name, MealType type, boolean mealOfTheWeek) {
        super(name, type, MealSize.FULL, mealOfTheWeek);
    }

    public void addDish(Dish dish) {
        // Add dish to Meal according to the category
        Dish.DishCategory dishCategory = dish.getDishCategory();
        switch (dishCategory) {
            case STARTER -> this.starter = dish;
            case MAIN -> this.main = dish;
            case DESSERT -> this.dessert = dish;
        }
        // Check Meal Type status
        if (this.starter != null && this.main != null && this.dessert != null
        && getMealType(this.starter.getDishType()).equals(MealType.VEGETARIAN)
        && getMealType(this.main.getDishType()).equals(MealType.VEGETARIAN)
        && getMealType(this.dessert.getDishType()).equals(MealType.VEGETARIAN)) {
            super.setMealType(MealType.VEGETARIAN);
        }
        else if (this.starter != null && this.main != null && this.dessert != null
        && getMealType(this.starter.getDishType()).equals(MealType.GLUTEN_FREE)
        && getMealType(this.main.getDishType()).equals(MealType.GLUTEN_FREE)
        && getMealType(this.dessert.getDishType()).equals(MealType.GLUTEN_FREE)) {
            super.setMealType(MealType.GLUTEN_FREE);
        }
        else if (this.starter == null || this.main == null || this.dessert == null) {
            System.out.println("The full menu is not complete, don't forget to add starter and main and dessert");
        }
    }

    public void setMealofTheWeek(boolean mealOfTheWeek) {
        super.setMealOfTheWeek(mealOfTheWeek);
    }

//    @Override
//    public List<Dish> getDishs() {
//        return List.of(starter, main, dessert);
//    }

    public Dish getStarter() {
        return starter;
    }

    public Dish getMain() {
        return main;
    }

    public Dish getDessert() {
        return dessert;
    }

    public String toString() {
        return "Full Meal: "
                + super.toString()
                + "\nStarter: " + starter
                + "\nMain: " + main
                + "\nDessert: " + dessert;
    }
}

