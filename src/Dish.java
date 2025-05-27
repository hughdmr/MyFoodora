public class Dish {
    public enum DishCategory {
        STARTER,
        MAIN,
        DESSERT
    }

    public enum DishType {
        STANDARD,
        VEGETARIAN,
        GLUTEN_FREE,
    }

    private String name;
    private DishCategory dishCategory;
    private DishType dishType;
    private double dishPrice;

    public Dish(String name, DishCategory dishCategory, DishType foodCategory, double dishPrice) {
        this.name = name;
        this.dishCategory = dishCategory;
        this.dishType = foodCategory;
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

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", category=" + dishCategory +
                ", foodCategory='" + dishType + '\'' +
                ", unitPrice=" + dishPrice +
                '}';
    }
}
