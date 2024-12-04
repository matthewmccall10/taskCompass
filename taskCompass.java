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
            System.out.println("User registered successfully! You can now log in.\n");
        }
    }

    // Menu Option for View Tasks
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                if(currentUser.equals(tasks.get(i).getTaskUser())) {
                    System.out.println(tasks.get(i).getTaskName());
                    
                    //Testing line
                    // System.out.println(tasks.get(i).getTaskName() + " " + tasks.get(i).getTaskUser() + " " + tasks.get(i).getTaskDescription()
                    // + " " + tasks.get(i).getTaskPriority() + " " + tasks.get(i).getTaskStatus());

                //REPEATING TASK VIEW OPTION

                // task t = tasks.get(i);
                // System.out.print((i + 1) + ". " + t.getTaskName());
                // if (t instanceof repeatTask) {
                //     repeatTask rt = (repeatTask) t;
                //     System.out.println(" (Repeats: " + rt.getRepeatInterval() +
                //                        ", Ends: " + (rt.getEndDate() == null ? "Never" : rt.getEndDate()) + ")");
                // } else {
                //     System.out.println();
                }
            }
        }
    }
    

    // Menu Option for Create Task
    public void createTask(Scanner sc) {
        //taskName
        System.out.println("Enter task name: ");
        String taskName = sc.nextLine();

        //taskDescription
        System.out.println("Enter a description: ");
        String taskDescription = sc.nextLine();

        //taskPriority
        boolean validPriority = false;
        System.out.println("What is the priority? 3:High, 2:Medium, 1:Low : ");
        String taskPriority = "";
        while(!validPriority) {
            String priorityValue = sc.nextLine();
            switch (priorityValue) {
                case "3":
                case "High":
                    taskPriority = "High";
                    validPriority = true;
                    break;
                case "2":
                case "Medium":
                    taskPriority = "Medium";
                    validPriority = true;
                    break;
                case "1":
                case "Low":
                    taskPriority = "Low";
                    validPriority = true;
                    break;
                default:
                    System.out.println("Invalid selection. Enter 3 for High priority,/n2 for Medium priority or 1 for Low priority: ");
                    break;
            }
        }
        
        //START MATTHEW'S CODE
        System.out.println("Is this a repeating task? (yes/no): ");
        boolean checkRepeating = false;
        String isRepeating = sc.nextLine().toLowerCase();

        if (isRepeating.equals("yes") || isRepeating.equals("y")) {
            checkRepeating = true;
            System.out.println("Enter repeat interval (daily, weekly, monthly): ");
            String repeatInterval = sc.nextLine();
            
            System.out.println("Enter end date (YYYY-MM-DD) or leave blank for no end date: ");
            String endDateInput = sc.nextLine();
            LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
        }

        //ADD MULTIPLE TASK CREATION CALLS

        //Regular task  call
        task newTask = new task(tasks.size(), taskName, currentUser, taskDescription, taskPriority, false);

        //Repeated task call
        // repeatTask newRepeatTask = new repeatTask(tasks.size(), taskName, currentUser, repeatInterval, endDate);
        //     tasks.add(newRepeatTask);
        
        System.out.println("Task created successfully!");
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
