public class Dish {
    public enum DishCategory {
        STARTER,
        MAIN,
        DESSERT
    }

    public enum FoodCategory {
        STANDARD,
        VEGETARIAN,
        GLUTEN_FREE,
    }

    private String name;
    private DishCategory dishCategory;
    private FoodCategory foodCategory;
    private double dishPrice;

    public Dish(String name, DishCategory dishCategory, FoodCategory foodCategory, double dishPrice) {
        this.name = name;
        this.dishCategory = dishCategory;
        this.foodCategory = foodCategory;
        this.dishPrice = dishPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return dishPrice;
    }

    public void setPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", category=" + dishCategory +
                ", foodCategory='" + foodCategory + '\'' +
                ", unitPrice=" + dishPrice +
                '}';
    }
}
