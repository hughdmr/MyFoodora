package food;

/**
 *
 * @author gravlax,hugues
 * A class to represent a Dish from a Menu, which can be added
 * in a meal by a restaurant and ordered by a Customer
 * through an Order
 *
 */
public class Dish {
    public enum Category {
        STARTER, MAIN, DESSERT
    }

    public enum Type {
        STANDARD, VEGETARIAN, GLUTEN_FREE,
    }

    private String name;
    private Category category;
    private Type type;
    private double price;

    public Dish(String name, Category category, Type type, double price) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public Category getCategory() { return category; }
    public Type getType() {
        return type;
    }

    public void setName(String name) { this.name = name; }
    public void setPrice(double dishPrice) { this.price = dishPrice; }
    public void setCategory(Category category) { this.category = category; }
    public void setType(Type type) { this.type = type; }

    // Display
    @Override
    public String toString() {
        return "[(DISH) - | name: " + name + " | category: " + category
                + " | type: " + type + " | price: " + price + " ]";
    }
}
