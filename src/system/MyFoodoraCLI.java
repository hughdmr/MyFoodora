package system;

import fidelity.*;
import users.*;
import food.*;
import policies.delivery.*;
import policies.profit.*;
import policies.ordersorting.*;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class MyFoodoraCLI {
    private static User currentLoggedInUser = null;
    private final static MyFoodoraSystem myFoodoraSystem = new MyFoodoraSystem();

    public MyFoodoraCLI() {}

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in MyFoodora CLI");
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
            case "help" -> printHelp();
            case "login" -> login(arguments);
            case "logout" -> logout();
            case "registerrestaurant" -> registerRestaurant(arguments);
            case "registercustomer" -> registerCustomer(arguments);
            case "registercourier" -> registerCourier(arguments);
            case "adddishrestaurantmenu" -> addDishRestaurantMenu(arguments);
            case "createmeal" -> createMeal(arguments);
            case "adddish2meal" -> addDish2Meal(arguments);
            case "showmeal" -> showMeal(arguments);
            case "setspecialoffer" -> setSpecialOffer(arguments);
            case "removefromspecialoffer" -> removeFromSpecialOffer(arguments);
            case "createorder" -> createOrder(arguments);
            case "additem2order" -> addItem2Order(arguments);
            case "endorder" -> endOrder(arguments);
            case "onduty" -> onDuty(arguments);
            case "offduty" -> offDuty(arguments);
            case "finddeliverer" -> findDeliverer(arguments);
            case "setdeliverypolicy" -> setDeliveryPolicy(arguments);
            case "setprofitpolicy" -> setProfitPolicy(arguments);
            case "showrestauranttop" -> showRestaurantTop();
            case "showcourierdeliveries" -> showCourierDeliveries();
            case "showcustomers" -> showCustomers();
            case "showmenuitem" -> showMenuItem(arguments);
            case "showmanagers" -> showManagers();
            case "showtotalprofit" -> showTotalProfit(arguments);
            case "associatecard" -> associateCard(arguments);
            case "sortshippedorders" -> sortShippedOrders(arguments);
            case "runtest" -> runCommandsFromFile(arguments);
            case "stop" -> {
                System.out.println("Exiting the program...");
                System.exit(0);
            }
            default -> {
                System.out.println("Unknown command: " + command);
                System.out.println("Type HELP for available commands");
            }
        }
    }

    private static void runCommandsFromFile(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: runTest <testScenario-file>");
            return;
        }
        String fileName = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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

    private static void login(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: login <username> <password>");
            return;
        }

        String username = args[0];
        String password = args[1];

        for (User user : myFoodoraSystem.getUsers()) {
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
            Customer customer = parseCustomer(args);

            myFoodoraSystem.addCustomer(customer);
            System.out.println("myfoodora.Customer registered: " + customer.getUsername());
        } catch (NumberFormatException e) {
            System.out.println("Error: x and y must be valid numbers.");
        }
    }

    private static Customer parseCustomer(String[] args) {
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

        return new Customer(username, password, firstName, lastName, address, email, phoneNumber);
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
            myFoodoraSystem.addRestaurant(restaurant);

            System.out.println("myfoodora.Restaurant registered: " + name);
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

            Courier courier = parseCourier(args);
            myFoodoraSystem.addCourier(courier);

            System.out.println("myfoodora.Courier registered: " + courier.getUsername());
        }
        catch (NumberFormatException e) {
            System.out.println("Error: x and y must be valid numbers.");
        }

    }

    private static Courier parseCourier(String[] args) {
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

        return new Courier(username, password, firstName, lastName, position, phoneNumber);
    }

    private static void showCustomers() {
        ArrayList<Customer> customers = myFoodoraSystem.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        System.out.println("List of customers:");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    private static void showManagers() {
        ArrayList<Manager> managers = myFoodoraSystem.getManagers();
        if (managers.isEmpty()) {
            System.out.println("No managers found.");
            return;
        }

        System.out.println("List of managers:");
        for (Manager m : managers) {
            System.out.println(m);
        }
    }

    public static void showRestaurantTop() {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can show courier.");
            return;
        }
        ArrayList<Restaurant> sortedRestaurantsByDeliveries = myFoodoraSystem.getRestaurantsSortedByDeliveries();
        System.out.println("Top 3 restaurants with the most deliveries:");
        int limit = Math.min(3, sortedRestaurantsByDeliveries.size());
        for (int i = 0; i < limit; i++) {
            Restaurant r = sortedRestaurantsByDeliveries.get(i);
            System.out.println(r);
        }
    }

    public static void showCourierDeliveries() {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can show courier.");
            return;
        }

        ArrayList<Courier> sortedCouriersByDeliveries = myFoodoraSystem.getCourierSortedByDeliveries();
        System.out.println("List of couriers:");
        for (Courier c : sortedCouriersByDeliveries) {
            System.out.println(c);
        }
    }

    public static void showMenuItem(String[] args) {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can show menu items.");
            return;
        }
        if (args.length != 1) {
            System.out.println("Usage: showMenuItem <restaurantName>");
            return;
        }
        String restaurantName = args[0];
        try {
            Restaurant restaurant = myFoodoraSystem.getRestaurant(restaurantName);
            System.out.println("Menu of " + restaurantName + ":" + restaurant.getMenu());
        } catch (Exception e) {
            System.out.println("Error fetching menu for restaurant " + restaurantName + ": " + e.getMessage());
        }
    }

    public static void showTotalProfit(String[] args) {
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can show total profit.");
            return;
        }

        if (args.length == 0) {
            double totalProfit = myFoodoraSystem.getTotalProfit();
            System.out.println("Total profit of the platform: " + totalProfit);
        } else if (args.length == 2) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date startDate = sdf.parse(args[0]);
                Date endDate = sdf.parse(args[1]);

                double profitInRange = myFoodoraSystem.getTotalProfitBetween(startDate, endDate);
                System.out.printf("Total profit of the platform between %s and %s: %.2f%n", args[0], args[1], profitInRange);
            } catch (Exception e) {
                System.out.println("Error parsing dates. Please use format: dd/MM/yyyy");
            }
        } else {
            System.out.println("Usage: showTotalProfit OR showTotalProfit <startDate> <endDate>");
        }
    }

    public static void addDishRestaurantMenu(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: addDishRestaurantMenu <dishName> <dishCategory> <dishType> <unitPrice>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to the menu.");
            return;
        }
        String dishName = args[0];
        Dish.DishCategory dishCategory = Dish.DishCategory.valueOf(args[1].toUpperCase());
        Dish.DishType foodCategory = Dish.DishType.valueOf(args[2].toUpperCase());
        double unitPrice = Double.parseDouble(args[3]);
        Dish dish = new Dish(dishName, dishCategory, foodCategory, unitPrice);

        restaurant.addDish(dish);
        System.out.println("myfoodora.Dish added to restaurant menu.");
    }

    public static void createMeal(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: createMeal <mealName> <mealType> <mealSize>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant restaurant)) {
            System.out.println("Only a logged on restaurant can create a meal.");
            return;
        }
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
        System.out.println("myfoodora.Meal added to restaurant.");
    }

    public static void addDish2Meal(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: addDish2Meal <dishName> <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to a meal.");
            return;
        }
        String dishName = args[0];
        String mealName = args[1];

        try {
            Meal meal = restaurant.getMeal(mealName);
            Dish dish = restaurant.getDish(dishName);

            meal.addDish(dish);
            System.out.println("Added dish [" + dish.getName() + "] to meal [" + meal.getName() + "]");
            System.out.println("myfoodora.Dish added to restaurant.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showMeal(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: showMeal <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant restaurant)) {
            System.out.println("Only a logged on restaurant can add a dish to a meal.");
            return;
        }
        String mealName = args[0];

        try {
            Meal meal = restaurant.getMeal(mealName);
            System.out.println("The following meal exists and is constituted as below: " + meal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setSpecialOffer(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: setSpecialOffer <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant restaurant)) {
            System.out.println("Only a logged on restaurant can set a special offer.");
            return;
        }
        String mealName = args[0];

        try {
            Meal meal = restaurant.getMeal(mealName);
            meal.setMealOfTheWeek(true);
            System.out.println("myfoodora.Meal [" + meal.getName() + "] has been added to special offer: ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeFromSpecialOffer(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: removeFromSpecialOffer <mealName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant restaurant)) {
            System.out.println("Only a logged on restaurant can remove a special offer.");
            return;
        }
        String mealName = args[0];

        try {
            Meal meal = restaurant.getMeal(mealName);
            meal.setMealOfTheWeek(false);
            System.out.println("myfoodora.Meal [" + meal.getName() + "] has been removed from special offer: ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createOrder(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: createOrder <restaurantName> <orderName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Customer customer)) {
            System.out.println("Only a logged on customer can create an order.");
            return;
        }
        try {
            String restaurantName = args[0];
            Restaurant restaurant = myFoodoraSystem.getRestaurant(restaurantName);
            String orderName = args[1];

            Order order = new Order(orderName, restaurant, customer);
            myFoodoraSystem.addOrder(order);
            System.out.println("myfoodora.System.Order [" + order.getName() + "] has been created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addItem2Order(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: addItem2Order <orderName> <itemName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Customer)) {
            System.out.println("Only a logged on customer can create an order.");
            return;
        }

        String orderName = args[0];
        try {
            Order order = myFoodoraSystem.getProgressOrder(orderName);
            Restaurant restaurant = order.getRestaurant();

            String itemName = args[1];
            try {
                Dish dish = restaurant.getDish(itemName);
                order.addSingleDish(dish);
                System.out.println("myfoodora.Dish [" + itemName + "] added to order [" + order.getName() + "]");
            } catch (Exception e) {
                System.out.println("myfoodora.Dish [" + itemName + "] not found. Trying meal");
                try {
                    Meal meal = restaurant.getMeal(itemName);
                    order.addMenu(meal);
                    System.out.println("myfoodora.Meal [" + itemName + "] added to order [" + order.getName() + "]");
                } catch (Exception e1) {
                    System.out.println("myfoodora.Meal [" + itemName + "] not found...");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void endOrder(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: endOrder <orderName> <date>");
            return;
        }
        if (!(currentLoggedInUser instanceof Customer)) {
            System.out.println("Only a logged on customer can end an order.");
            return;
        }
        try {
            String orderName = args[0];
            Order order = myFoodoraSystem.getProgressOrder(orderName);

            String date = args[1];
            myFoodoraSystem.completeOrder(order, date);
            System.out.println("myfoodora.System.Order [" + order.getName() + "] has been completed on [" + order.getDate() + "] for [" + order.getPrice() + "]€");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sortShippedOrders(String[] args) {
        if (!(currentLoggedInUser instanceof Manager || currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on manager or restaurant can sort shipped orders.");
            return;
        }

        if (args.length != 1) {
            System.out.println("Usage: sortShippedOrders <policy-type>");
            System.out.println("Available policy types: mostordereddish, leastordereddish, mostorderedhalfmeal, leastorderedhalfmeal, mostorderedfullmeal, leastorderedfullmeal");
            return;
        }

        String policyType = args[0].toLowerCase();
        ShippedOrderSortingPolicy policy;

        switch (policyType) {
            case "mostordereddish":
                policy = new OrderedDishPolicy(true, "Most ordered dishes");
                break;
            case "leastordereddish":
                policy = new OrderedDishPolicy(false, "Least ordered dishes");
                break;
            case "mostorderedhalfmeal":
                policy = new OrderedMealPolicy<>(HalfMeal.class, true, "Most ordered half-meals");
                break;
            case "leastorderedhalfmeal":
                policy = new OrderedMealPolicy<>(HalfMeal.class, false, "Least ordered half-meals");
                break;
            case "mostorderedfullmeal":
                policy = new OrderedMealPolicy<>(FullMeal.class, true, "Most ordered full-meals");
                break;
            case "leastorderedfullmeal":
                policy = new OrderedMealPolicy<>(FullMeal.class, false, "Least ordered full-meals");
                break;
            default:
                System.out.println("Unknown policy type: " + policyType);
                return;
        }

        ArrayList<Order> completedOrders = myFoodoraSystem.getCompletedOrders();
        policy.sort(completedOrders);
    }

    public static void onDuty(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: onDuty <username>");
            return;
        }
        if (!(currentLoggedInUser instanceof Courier) && !(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on courier or manager can set his state as on duty.");
            return;
        }
        else if (currentLoggedInUser instanceof Courier) {
            if (!currentLoggedInUser.getUsername().equals(args[0])) {
                System.out.println("Incorrect username. Couriers can only change their own state.");
            }
        }
        try {
            Courier courier = myFoodoraSystem.getCourier(args[0]);
            courier.setOnDuty(true);
            System.out.println("myfoodora.Courier [" + courier + "] has been set to on duty.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void offDuty(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: offDuty <username>");
            return;
        }
        if (!(currentLoggedInUser instanceof Courier) && !(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on courier or manager can set his state as off duty.");
            return;
        }
        else if (currentLoggedInUser instanceof Courier) {
            if (!currentLoggedInUser.getUsername().equals(args[0])) {
                System.out.println("Incorrect username. Couriers can only change their own state.");
            }
        }
        try {
            Courier courier = myFoodoraSystem.getCourier(args[0]);
            courier.setOnDuty(false);
            System.out.println("myfoodora.Courier [" + courier + "] has been set to off duty.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findDeliverer(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: findDeliverer <orderName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Restaurant)) {
            System.out.println("Only a logged on restaurant can find deliver.");
            return;
        }
        try {
            Order order = myFoodoraSystem.getOrder(args[0]);
            Courier bestCourier = myFoodoraSystem.getBestCourier(order);

            System.out.println("The best available courier is [" + bestCourier.getUsername() + "|" + bestCourier.getPhoneNumber() + "]");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setDeliveryPolicy(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: setDeliveryPolicy <delPolicyName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can change the delivery policy.");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "fastest": myFoodoraSystem.setDeliveryPolicy(new FastestDeliveryPolicy()); break;
            case "fairoccupation": myFoodoraSystem.setDeliveryPolicy(new FairOccupationDeliveryPolicy()); break;
            default:
                System.out.println("Unknown card type. Use 'fastest or 'fairoccupation'.");
                return;
        }
        System.out.println("System.MyFoodoraSystem set delivery policy to [" + myFoodoraSystem.getDeliveryPolicy() + "]");
    }

    public static void setProfitPolicy(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: setProfitPolicy <ProfitPolicyName>");
            return;
        }
        if (!(currentLoggedInUser instanceof Manager)) {
            System.out.println("Only a logged on manager can change the profit policy.");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "deliverycost": myFoodoraSystem.setProfitPolicy(new DeliveryCostProfitPolicy()); break;
            case "markupprofit": myFoodoraSystem.setProfitPolicy(new MarkupProfitPolicy()); break;
            case "servicefee" : myFoodoraSystem.setProfitPolicy(new ServiceFeeProfitPolicy()); break;
            default:
                System.out.println("Unknown card type. Use 'deliverycost' or 'markupprofit' or 'servicefee'.");
                return;
        }
        System.out.println("System.MyFoodoraSystem set profit policy to [" + myFoodoraSystem.getProfitPolicy() + "]");
    }

    public static void associateCard(String[] args) {
        if (args.length != 2) {
            System.out.println("  associateCard <userName> <cardType>");
            return;
        }
        String username = args[0];
        String cardType = args[1];
        Customer customer = null;
        ArrayList<Customer> customers = myFoodoraSystem.getCustomers();

        for (Customer c : customers) {
            if (c.getUsername().equals(username)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("myfoodora.Customer not found.");
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
        System.out.println("  registerRestaurant <name> <username> <password> <address(x,y)>");
        System.out.println("  registerCustomer <firstName> <lastName> <username> <address> <password> <email> <phoneNumber>");
        System.out.println("  registerCourier <firstName> <lastName> <username> <position> <password> <phoneNumber>");
        System.out.println("  addDishRestaurantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
        System.out.println("  createMeal <mealName> <mealType> <mealSize>");
        System.out.println("  addDish2Meal <dishName> <mealName>");
        System.out.println("  showMeal <mealName>");
        System.out.println("  setSpecialOffer <mealName>");
        System.out.println("  removeFromSpecialOffer <mealName>");
        System.out.println("  createOrder <restaurantName> <orderName>");
        System.out.println("  addItem2Order <orderName> <itemName>");
        System.out.println("  endOrder <orderName> < date>");
        System.out.println("  onDuty <username>");
        System.out.println("  offDuty <username>");
        System.out.println("  findDeliverer <orderName>");
        System.out.println("  setDeliveryPolicy <delPolicyName>");
        System.out.println("  setProfitPolicy <ProfitPolicyName>");
        System.out.println("  associateCard <userName> <cardType>");
        System.out.println("  showCourierDeliveries");
        System.out.println("  showRestaurantTop");
        System.out.println("  showCustomers");
        System.out.println("  showMenuItem <restaurantName>");
        System.out.println("  showTotalProfit");
        System.out.println("  showTotalProfit <startDate> <endDate>");
        System.out.println("  sortShippedOrders <policy-type>");
        System.out.println("  runTest <testScenario-file>");
        System.out.println("  showManagers");
        System.out.println("  STOP - Exit the program");
    }
}