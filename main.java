import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static ArrayList<String> users = new ArrayList<>();
    private static boolean isLoggedIn = false;
    private static String currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            if (!isLoggedIn) {
                System.out.println("Main Menu:");
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        login(scanner);
                        break;
                    case 2:
                        signUp(scanner);
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("\nLogged In Menu:");
                System.out.println("1. View Tasks");
                System.out.println("2. Create Task");
                System.out.println("3. View Notifications");
                System.out.println("4. Change User");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewTasks();
                        break;
                    case 2:
                        createTask(scanner);
                        break;
                    case 3:
                        viewNotifications();
                        break;
                    case 4:
                        isLoggedIn = false;
                        currentUser = null;
                        System.out.println("Logged out. Returning to Main Menu...");
                        break;
                    case 5:
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        scanner.close();
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username to login: ");
        String username = scanner.nextLine();
        if (users.contains(username)) {
            currentUser = username;
            isLoggedIn = true;
            System.out.println("Logged in as " + currentUser);
        } else {
            System.out.println("Username not found. Please sign up first.");
        }
    }

    private static void signUp(Scanner scanner) {
        System.out.print("Enter a new username: ");
        String newUser = scanner.nextLine();
        if (users.contains(newUser)) {
            System.out.println("Username already exists. Please try a different username.");
        } else {
            users.add(newUser);
            System.out.println("User registered successfully! You can now log in.");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task created successfully!");
    }

    private static void viewNotifications() {
        System.out.println("You have no new notifications."); // Placeholder for notifications feature
    }
}

class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}