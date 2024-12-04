import java.util.Scanner;
import java.util.ArrayList;

public class taskCompass {
    private ArrayList<task> tasks = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private boolean isLoggedIn = false;
    private String currentUser = null;

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

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(tasks.get(i).getTaskName());
            }
        }
    }

    public void createTask(Scanner sc) {
        System.out.println("Enter task name: ");
        String taskName = sc.nextLine();

        task newTask = new task(tasks.size(), taskName, currentUser);

        tasks.add(newTask);

        System.out.println("Task created successfully!");
    }

    // public void editTask(Scanner sc) {
    //     if(){

    //     } else {

    //     }
    // }

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


