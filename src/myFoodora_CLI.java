import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class myFoodora_CLI {
    public static void main(String[] args) {
        if (args.length > 0) {
            runCommandsFromFile(args[0]);
        } else {
            runInteractiveCLI();
        }
    }

    private static void runCommandsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                System.out.println("> " + line);
                processCommand(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void runInteractiveCLI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("This is a Command Line Interface");
        printHelp();
        System.out.println("Enter your commands below:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("STOP")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (input.isEmpty()) continue;

            processCommand(input);
        }
        scanner.close();
    }

    private static void processCommand(String input) {
        String[] parts = input.split("\\s+");
        String command = parts[0];
        String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

        switch (command.toLowerCase()) {
            case "help":
                printHelp();
                break;
            case "login":
                login(arguments);
                break;
            case "logout":
                logout();
                break;
            case "registerrestaurant":
                registerRestaurant(arguments);
                break;
            case "registercustomer":
                registerCustomer(arguments);
                break;
            case "registercourier":
                registerCourier(arguments);
                break;
            case "adddishrestaurantmenu":
                addDishRestaurantMenu(arguments);
                break;
            case "createmeal":
                createMeal(arguments);
                break;
            case "adddish2meal":
                try { addDish2Meal(arguments); }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "showmeal":
                try { showMeal(arguments); }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "setspecialoffer":
                try { setSpecialOffer(arguments); }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "showrestaurants":
                showRestaurants();
                break;
            case "showcouriers":
                showCouriers();
                break;
            case "showmanagers":
                showManagers();
                break;
            case "showcustomers":
                showCustomers();
                break;
            case "associatecard":
                associateCard(arguments);
                break;
            case "stop":
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Type HELP for available commands");
        }
    }

    private static ArrayList<Customer> customers = new ArrayList<>();

    private static ArrayList<Restaurant> restaurants = new ArrayList<>();

    private static ArrayList<Courier> couriers = new ArrayList<>();

    private static ArrayList<Manager> managers = new ArrayList<>();

    private static ArrayList<User> users = new ArrayList<>();

    private static User currentLoggedInUser = null;

    static {
        Manager manager = new Manager("ceo", "123456789", "ceo", "admin");
        managers.add(manager);
        users.add(manager);
        Restaurant jojorestaurant = new Restaurant("jojo", "mdp12345", "ChezJOJO", new ArrayList<Double>(Arrays.asList(3.45,2.44)));
        users.add(jojorestaurant);
        restaurants.add(jojorestaurant);
        Dish poulet = new Dish("poulet", Dish.DishCategory.MAIN, Dish.DishType.STANDARD, 24);
        Dish tartare = new Dish("tartare_saumon", Dish.DishCategory.STARTER, Dish.DishType.VEGETARIAN, 12);
        jojorestaurant.addDish(poulet);
        jojorestaurant.addDish(tartare);
        FullMeal fullmeal = new FullMeal("menu_jour", Meal.MealType.STANDARD, false);
        HalfMeal halfmeal = new HalfMeal("menu_soir", Meal.MealType.STANDARD, false);
        jojorestaurant.addMeal(fullmeal);
        jojorestaurant.addMeal(halfmeal);
    }

    private static void login(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: login <username> <password>");
            return;
        }

        String username = args[0];
        String password = args[1];

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentLoggedInUser = user;
                System.out.println("Login successful. Welcome, " + currentLoggedInUser.getUsername() + "!");
                return;
            }
        }

        System.out.println("Login failed. Invalid username or password.");
    }

    private static void logout() {
        if (currentLoggedInUser != null) {
            System.out.println("Logout successful. Goodbye, " + currentLoggedInUser.getUsername() + "!");
            currentLoggedInUser = null;
        } else {
            System.out.println("Logout failed. No user is currently logged in.");
        }
    }


    private static void registerCustomer(String[] args) {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can register a new customer.");
            return;
        }

        if (args.length != 7) {
            System.out.println("  registerCustomer <firstName> <lastName> <username> <address> <password> <email> <phoneNumber>");
            return;
        }

        try {
        String firstName = args[0];
        String lastName = args[1];
        String username = args[2];
        String rawAddress = args[3].replace("(", "").replace(")", "");
        String[] coords = rawAddress.split(",");
        ArrayList<Double> address = new ArrayList<>();
        address.add(Double.parseDouble(coords[0]));
        address.add(Double.parseDouble(coords[1]));
        String password = args[4];
        String email = args[5];
        String phoneNumber = args[6];

        Customer customer = new Customer(username, password, firstName, lastName, address, email, phoneNumber);
        customers.add(customer);
        users.add(customer);

        System.out.println("Customer registered: " + firstName + " " + lastName);
    } catch (NumberFormatException e) {
        System.out.println("Error: x and y must be valid numbers.");
    }
    }

    private static void registerRestaurant(String[] args) {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can register a new restaurant.");
            return;
        }

        if (args.length != 4) {
            System.out.println("Usage: registerRestaurant <name> <username> <password> <address(x,y)>");
            return;
        }

        try {
            String name = args[0];
            String username = args[1];
            String password = args[2];
            String rawAddress = args[3].replace("(", "").replace(")", "");
            String[] coords = rawAddress.split(",");
            ArrayList<Double> position = new ArrayList<>();
            position.add(Double.parseDouble(coords[0]));
            position.add(Double.parseDouble(coords[1]));

            Restaurant restaurant = new Restaurant(username, password, name, position);
            restaurants.add(restaurant);
            users.add(restaurant);

            System.out.println("Restaurant registered: " + name);
        } catch (NumberFormatException e) {
            System.out.println("Error: x and y must be valid numbers.");
        }
    }

    private static void registerCourier(String[] args) {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can register a new courier.");
            return;
        }
        if (args.length != 6) {
            System.out.println("Usage: registerCourier <firstName> <lastName> <username> <position> <password> <phoneNumber>");
            return;
        }
        try {
            String firstName = args[0];
            String lastName = args[1];
            String username = args[2];
            String rawPosition = args[3].replace("(", "").replace(")", "");
            String[] coords = rawPosition.split(",");
            ArrayList<Double> position = new ArrayList<>();
            position.add(Double.parseDouble(coords[0]));
            position.add(Double.parseDouble(coords[1]));
            String password = args[4];
            String phoneNumber = args[5];

            Courier courier = new Courier(username, password, firstName, lastName, position, phoneNumber);
            couriers.add(courier);
            users.add(courier);

            System.out.println("Courier registered: " + firstName + " " + lastName);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: x and y must be valid numbers.");
        }

    }

    private static void showCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("List of customers:");
        int count = 1;
        for (Customer c : customers) {
            System.out.printf("%d: %s %s (%s)%n", count++, c.getFirstName(), c.getLastName(), c.getUsername());
            c.viewAccountInfo();
        }
    }

    private static void showManagers() {
        if (managers.isEmpty()) {
            System.out.println("No managers found.");
            return;
        }

        System.out.println("List of managers:");
        int count = 1;
        for (Manager m : managers) {
            System.out.printf("%d: %s %s (%s)%n", count++, m.getFirstName(), m.getLastName(), m.getUsername());
        }
    }

    public static void showRestaurants() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
            return;
        }
        System.out.println("List of restaurants:");
        int count = 1;
        for (Restaurant r : restaurants) {
            System.out.printf("%d: %s (%s)%n", count++, r.getName(), r.getUsername());
            System.out.println("Menu:" + r.getMenu());
        }
    }

    public static void showCouriers() {
        if (couriers.isEmpty()) {
            System.out.println("No couriers found.");
            return;
        }
        System.out.println("List of couriers:");
        int count = 1;
        for (Courier c : couriers) {
            System.out.printf("%d: %s (%s)%n", count++, c.getLastName(), c.getUsername());
        }
    }

    public static void addDishRestaurantMenu(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: addDishRestaurantMenu <dishName> <dishCategory> <dishType> <unitPrice>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to the menu.");
            return;
        }
        Restaurant restaurant = (Restaurant) currentLoggedInUser;
        String dishName = args[0];
        Dish.DishCategory dishCategory = Dish.DishCategory.valueOf(args[1].toUpperCase());
        Dish.DishType foodCategory = Dish.DishType.valueOf(args[2].toUpperCase());
        double unitPrice = Double.parseDouble(args[3]);
        Dish dish = new Dish(dishName, dishCategory, foodCategory, unitPrice);

        restaurant.addDish(dish);
        System.out.println("Dish added to restaurant menu.");
    }

    public static void createMeal(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: createMeal <mealName> <mealType> <mealSize>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on restaurant can create a meal.");
            return;
        }
        Restaurant restaurant = (Restaurant) currentLoggedInUser;
        String mealName = args[0];
        Meal.MealType mealType = Meal.MealType.valueOf(args[1].toUpperCase());
        Meal.MealSize mealSize = Meal.MealSize.valueOf(args[2].toUpperCase());
        if (mealSize == Meal.MealSize.FULL) {
            Meal meal = new FullMeal(mealName, mealType, false);
            restaurant.addMeal(meal);
        }
        else if (mealSize == Meal.MealSize.HALF) {
            Meal meal = new HalfMeal(mealName, mealType, false);
            restaurant.addMeal(meal);
        }
        else {
            System.out.println("The meal type should be either FULL or HALF.");
            return;
        }
        System.out.println("Meal added to restaurant.");
    }

    public static void addDish2Meal(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: addDish2Meal <dishName> <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to a meal.");
            return;
        }
        Restaurant restaurant = (Restaurant) currentLoggedInUser;
        String dishName = args[0];
        String mealName = args[1];

        Meal meal = restaurant.getMeals()
                .stream()
                .filter(m -> m.getName().equals(mealName))
                .findFirst()
                .orElseThrow(() -> new Exception("Meal not found: " + mealName));


        Dish dish = restaurant.getMenu()
                .stream()
                .filter(m -> m.getName().equals(dishName))
                .findFirst()
                .orElseThrow(() -> new Exception("Dish not found: " + dishName));

        meal.addDish(dish);
        System.out.println("Dish added to restaurant.");
    }

    public static void showMeal(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: showMeal <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to a meal.");
            return;
        }
        Restaurant restaurant = (Restaurant) currentLoggedInUser;
        String mealName = args[0];

        Meal meal = (Meal) restaurant.getMeals()
                .stream()
                .filter(m -> m.getName().equals(mealName))
                .findFirst()
                .orElseThrow(() -> new Exception("Meal not found: " + mealName));

        System.out.println("The following meal exists and is constituted as below: " + meal);
    }

    public static void setSpecialOffer(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: setSpecialOffer <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to a meal.");
            return;
        }
        Restaurant restaurant = (Restaurant) currentLoggedInUser;
        String mealName = args[0];

        Meal meal = (Meal) restaurant.getMeals()
                .stream()
                .filter(m -> m.getName().equals(mealName))
                .findFirst()
                .orElseThrow(() -> new Exception("Meal not found: " + mealName));

        meal.setMealOfTheWeek(true);
        System.out.println("The following meal has been added to special offer: " + meal);
    }

    public static void associateCard(String[] args) {
        if (args.length != 2) {
            System.out.println("  associateCard <userName> <cardType>");
            return;
        }
        String username = args[0];
        String cardType = args[1];
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        switch (cardType.toLowerCase()) {
            case "basic":
                customer.unregisterFidelityCard();
                customer.registerFidelityCard(new BasicFidelityCard());
                break;
            case "point":
                customer.unregisterFidelityCard();
                customer.registerFidelityCard(new PointFidelityCard());
                break;
            case "lottery":
                customer.unregisterFidelityCard();
                customer.registerFidelityCard(new LotteryFidelityCard());
                break;
            default:
                System.out.println("Unknown card type. Use 'basic' or 'point' or 'lottery'.");
                return;
        }

        System.out.println("Fidelity card associated to customer " + customer.getUsername());
    }


    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("  HELP - Show this help message");
        System.out.println("  login <username> <password>");
        System.out.println("  logout");
        System.out.println("  registerCustomer <firstName> <lastName> <username> <address> <password> <email> <phoneNumber>");
        System.out.println("  registerRestaurant <name> <username> <password> <address(x,y)>");
        System.out.println("  registerCourier <firstName> <lastName> <username> <position> <password> <phoneNumber>");
        System.out.println("  createMeal <mealName> <mealType> <mealSize>");
        System.out.println("  addDish2Meal <dishName> <mealName>");
        System.out.println("  showMeal <mealName>");
        System.out.println("  setSpecialOffer <mealName>");
        System.out.println("  showRestaurants");
        System.out.println("  showCouriers");
        System.out.println("  showManagers");
        System.out.println("  showCustomers");
        System.out.println("  addDishRestaurantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
        System.out.println("  associateCard <userName> <cardType>");
        System.out.println("  STOP - Exit the program");
    }
}