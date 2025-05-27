import java.util.List;

public class HalfMeal extends Meal {
    private Dish main;
    private Dish second;

    public HalfMeal(String name, MealType type, boolean mealOfTheWeek) {
        super(name, type, MealSize.HALF, mealOfTheWeek);
    }

    public void addDish(Dish dish) {
        // Add dish to Meal according to the category
        Dish.DishCategory dishCategory = dish.getDishCategory();
        switch (dishCategory) {
            case STARTER, DESSERT -> this.second = dish;
            case MAIN -> this.main = dish;
        }
        System.out.println("Added dish: " + dish + " to meal: " + this);

        // Check Meal Type status
        if (this.main != null && this.second != null
        && getMealType(this.main.getDishType()).equals(MealType.VEGETARIAN)
        && getMealType(this.second.getDishType()).equals(MealType.VEGETARIAN)) {
            super.setMealType(MealType.VEGETARIAN);
        }
        else if (this.main != null && this.second != null
        && getMealType(this.main.getDishType()).equals(MealType.GLUTEN_FREE)
        && getMealType(this.second.getDishType()).equals(MealType.GLUTEN_FREE)) {
            super.setMealType(MealType.GLUTEN_FREE);
        }
        else if (this.main == null || this.second == null) {
            System.out.println("The half menu is not complete, don't forget to add main and second dish");
        }
    }


    public void setMealofTheWeek(boolean mealOfTheWeek) {
        super.setMealOfTheWeek(mealOfTheWeek);
    }

//    @Override
//    public List<Dish> getDishs() {
//        return List.of(main, second);
//    }

    public Dish getMain() {
        return main;
    }

    public Dish getSecond() {
        return second;
    }

    public String toString() {
        return "Half Meal: "
                + super.toString()
                + "\nMain: " + main
                + "\nSecond: " + second;
    }
}

