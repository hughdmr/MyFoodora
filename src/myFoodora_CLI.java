import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class myFoodora_CLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("This is a  Command Line Interface");
        System.out.println("Available commands:");
        System.out.println("  HELP - Show this help message");
        System.out.println("  login <username> <password>");
        System.out.println("  logout <>");
        System.out.println("  createCustomer <firstName> <lastName> <username> <address> <password> <email> <phoneNumber>");
        System.out.println("  showCustomers");
        System.out.println("  associateCard <userName> <cardType>");
        System.out.println("  STOP - Exit the program");
        System.out.println("Enter your commands below:");

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("STOP")) {
                System.out.println("Exiting the program...");
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            // Split input into command and arguments
            String[] parts = input.split("\\s+");
            String command = parts[0];
            String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

            switch (command) {
                case "HELP":
                    printHelp();
                    break;
                case "login":
                    login(arguments);
                    break;
                case "logout":
                    logout();
                    break;
                case "createCustomer":
                    createCustomer(arguments);
                    break;
                case "showCustomers":
                    showCustomers();
                    break;
                case "associateCard":
                    associateCard(arguments);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    System.out.println("Type HELP for available commands");
            }
        }

        scanner.close();
    }

    private static ArrayList<Customer> customers = new ArrayList<>();

    private static ArrayList<Restaurant> restaurants = new ArrayList<>();

    private static ArrayList<User> users = new ArrayList<>();

    private static User currentLoggedInUser = null;

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




    private static void createCustomer(String[] args) {
        if (args.length != 7) {
            System.out.println("  createCustomer <firstName> <lastName> <username> <address> <password> <email> <phoneNumber>");
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
        System.out.println("  createCustomer <firstName> <lastName> <username> <address> <password> <email> <phoneNumber>");
        System.out.println("  STOP - Exit the program");
    }

}