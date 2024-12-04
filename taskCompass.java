import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class taskCompass {
    private ArrayList<task> tasks = new ArrayList<>();
    private ArrayList<repeatTask> repeatTasks = new ArrayList<>();
    private ArrayList<partnerTask> partnerTasks = new ArrayList<>();
    // private ArrayList<repeatPartnerTask> repeatPartnerTasks = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private boolean isLoggedIn = false;
    private String currentUser;

    // Menu Option for Login
    public void login(Scanner sc) {
        System.out.print("Enter username to login: ");
        String username = sc.nextLine();
        if (users.contains(username)) {
            currentUser = username;
            isLoggedIn = true;
            System.out.println("Logged in as " + currentUser);
        } else {
            System.out.println("Username not found. Please sign up first.");
        }
    }

    // Menu Option for Signup
    public void signUp(Scanner sc) {
        System.out.print("Enter a new username: ");
        String newUser = sc.nextLine();
        if (users.contains(newUser)) {
            System.out.println("Username already exists. Please try a different username.");
        } else {
            users.add(newUser);
            System.out.println("User registered successfully! You can now log in.");
        }
    }

    // Menu Option for View Tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                task t = tasks.get(i);
                System.out.print((i + 1) + ". " + t.getTaskName());
                if (t instanceof repeatTask) {
                    repeatTask rt = (repeatTask) t;
                    System.out.println(" (Repeats: " + rt.getRepeatInterval() +
                                       ", Ends: " + (rt.getEndDate() == null ? "Never" : rt.getEndDate()) + ")");
                } else {
                    System.out.println();
                }
            }
        }
    }
    

    // Menu Option for Create Task
    public void createTask(Scanner sc) {
        //taskName
        System.out.println("Enter task name: ");
        String taskName = sc.nextLine();
    System.out.println("Enter task name: ");
    String taskName = sc.nextLine();

    System.out.println("Is this a repeating task? (yes/no): ");
    String isRepeating = sc.nextLine().toLowerCase();

    if (isRepeating.equals("yes")) {
        System.out.println("Enter repeat interval (daily, weekly, monthly): ");
        String repeatInterval = sc.nextLine();

        System.out.println("Enter end date (YYYY-MM-DD) or leave blank for no end date: ");
        String endDateInput = sc.nextLine();
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);

        repeatTask newRepeatTask = new repeatTask(tasks.size(), taskName, currentUser, repeatInterval, endDate);
        tasks.add(newRepeatTask);
    } else {
        task newTask = new task(tasks.size(), taskName, currentUser);
        tasks.add(newTask);
    }

    System.out.println("Task created successfully!");
}


    // Menu Option for Edit Task
    public void editTask(Scanner sc) {
    //     if(){

    //     } else {

    //     }
    }

    //Getters
    public boolean getLoggedIn() {
        return isLoggedIn;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public ArrayList<task> getTasks() {
        return tasks;
    }

    //Setters
    public void setLoggedIn(boolean input) {
        this.isLoggedIn = input;
    }

    public void setCurrentUser(String input) {
        this.currentUser = input;
    }
}


