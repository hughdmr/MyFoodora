import java.util.ArrayList;

public class Restaurant extends User {
    private String name;
    private ArrayList<Double> position;
    private ArrayList<Item> menu = new ArrayList<>();
    private ArrayList<Meal> meal = new ArrayList<>();

    public Restaurant(String username, String password, String name, ArrayList<Double> address) {
        super(username, password);
        this.name = name;
        this.position = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Double> position) {
        this.position = position;
    }

    public void addMeal(Meal meal) {
        this.meal.add(meal);
    }

    public void addItem(Item item) {
        this.menu.add(item);
    }

    public void removeMeal(Meal meal) {
        this.meal.remove(meal);
    }

    public void removeItem(Item item) {
        this.menu.remove(item);
    }
}
