import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class taskCompass {
    private ArrayList<task> tasks = new ArrayList<>();
    private ArrayList<repeatTask> repeatTasks = new ArrayList<>();
    private ArrayList<partnerTask> partnerTasks = new ArrayList<>();
    private ArrayList<comboTask> comboTasks = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private boolean isLoggedIn = false;
    private String currentUser;

    //START Login method
    public void login(Scanner sc) {
        System.out.print("Enter username to login: ");
        String username = sc.nextLine();
        if (users.contains(username)) {
            currentUser = username;
            isLoggedIn = true;
            System.out.println("Logged in as " + currentUser);
        } else {
            System.out.println("Username not found. Please sign up first.\n");
        }
    } //END Login method

    // START Signup method
    public void signUp(Scanner sc) {
        System.out.print("Enter a new username: ");
        String newUser = sc.nextLine();
        if (users.contains(newUser)) {
            System.out.println("Username already exists. Please try a different username.");
        } else {
            users.add(newUser);
            System.out.println("User registered successfully! You can now log in.\n");
        }
    } //END Signup method

    //START View tasks method
    public void viewTasks() {
        if (tasks.isEmpty() && repeatTasks.isEmpty() && partnerTasks.isEmpty() && comboTasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Your associated tasks:");
    // REGULAR TASK
            for (int i = 0; i < tasks.size(); i++) {
                if(currentUser.equals(tasks.get(i).getTaskUser())) {
                    System.out.println("\nREGULAR TASKS: " + "\n\tTitle: " + getTasks().get(0).getTaskName() + 
                    "\n\tDescription: " + getTasks().get(0).getTaskDescription() + 
                    "\n\tPriority: " + getTasks().get(0).getTaskPriority());
                }
            }
    // REPEATING TASK
            for (int i = 0; i < repeatTasks.size(); i++) {
                if(currentUser.equals(repeatTasks.get(i).getTaskUser())) {
                    System.out.println("\nREPEATING TASKS: " + "\n\tTitle: " + getRepeatTasks().get(0).getTaskName() + 
                    "\n\tDescription: " + getRepeatTasks().get(0).getTaskDescription() + 
                    "\n\tPriority: " + getRepeatTasks().get(0).getTaskPriority() + 
                    "\n\tRepetition: " + getRepeatTasks().get(0).getRepeatInterval() + 
                    "\n\tEnd Date: " + getRepeatTasks().get(0).getEndDate());                }
            }
    // PARTNER TASK
            for (int i = 0; i < partnerTasks.size(); i++) {
                if(currentUser.equals(partnerTasks.get(i).getTaskUser()) || currentUser.equals(partnerTasks.get(i).getPartnerName())) {
                    System.out.println("\nPARTNER TASKS: " + "\n\tTitle: " + getPartnerTasks().get(0).getTaskName() + 
                    "\n\tDescription: " + getPartnerTasks().get(0).getTaskDescription() + 
                    "\n\tPartner Name: " + getComboTasks().get(0).getPartnerName() +
                    "\n\tPriority: " + getPartnerTasks().get(0).getTaskPriority());                }
            }
    // COMBO TASK
            for (int i = 0; i < comboTasks.size(); i++) {
                if(currentUser.equals(comboTasks.get(i).getTaskUser()) || currentUser.equals(comboTasks.get(i).getPartnerName())) {
                    System.out.println("\nCOMBO TASKS: " + "\n\tTitle: " + getComboTasks().get(0).getTaskName() + 
                    "\n\tDescription: " + getComboTasks().get(0).getTaskDescription() +
                    "\n\tPartner Name: " + getComboTasks().get(0).getPartnerName() + 
                    "\n\tPriority: " + getComboTasks().get(0).getTaskPriority() + 
                    "\n\tRepetition: " + getComboTasks().get(0).getRepeatInterval() + 
                    "\n\tEnd Date: " + getComboTasks().get(0).getEndDate());                }
            }
        }
    } //END View tasks method

    // START Create task method
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

        //REPEATING TASK CREATION
        System.out.println("Is this a repeating task? (yes/no): ");
        boolean checkRepeating = false;
        String isRepeatingTask = sc.nextLine().toLowerCase();

        String repeatInterval = "Error";
        LocalDate endDate = LocalDate.now();


        if (isRepeatingTask.equals("yes") || isRepeatingTask.equals("y")) {
            checkRepeating = true;
            System.out.println("Enter repeat interval (daily, weekly, monthly): ");
            repeatInterval = sc.nextLine();
            
            System.out.println("Enter end date (YYYY-MM-DD) or leave blank for no end date: ");
            String endDateInput = sc.nextLine();
            endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
        }

        //PARTNER TASK CREATION
        System.out.println("Did you want this to be a partner task? (yes/no): ");
        boolean checkPartner = false;
        String isPartnerTask = sc.nextLine().toLowerCase();
        String partnerName = "Error";

        if (isPartnerTask.equals("yes") || isPartnerTask.equals("y")) {
            if (users.size() < 2) {
                System.out.println("Not enough registered users, reverting to non-partner task.");
            } else {
                checkPartner = true;
                boolean selectingPartner = true;

                while(selectingPartner) {
                    System.out.println("Available partners:");
                    for (int i = 0; i < users.size(); i++ ) {
                        if (!users.get(i).equals(getCurrentUser())) {
                            System.out.print( users.get(i) + " ");
                        }
                        System.out.println("\n");
                    }

                    System.out.println("Enter user to partner with: ");
                    String partnerSearch = sc.nextLine();
                    if (users.contains(partnerSearch)) {
                        if (partnerSearch.equals(getCurrentUser())) {
                            System.out.println("You cannot partner with yourself!\nPlease try again...");
                        } else {
                            selectingPartner = false;
                            partnerName = partnerSearch;
                        }
                    }
                }
                
            }
        }

        //Setup TaskType enum for constructor calls
        task.TaskType type;

        //Constructor calls for event type
        if (checkRepeating == false && checkPartner == false) {
            type = task.TaskType.BASE;
            task newTask = new task(tasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type);
            tasks.add(newTask);
        } else if (checkRepeating == true && checkPartner == false) {
            type = task.TaskType.REPEAT;
            repeatTask newRepeatTask = new repeatTask(repeatTasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type, repeatInterval, endDate);
            repeatTasks.add(newRepeatTask);
        } else if (checkRepeating == false && checkPartner == true) {
            type = task.TaskType.PARTNER;
            partnerTask newPartnerTask = new partnerTask(partnerTasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type, partnerName);
            partnerTasks.add(newPartnerTask);
        } else if (checkRepeating == true && checkPartner == true) {
            type = task.TaskType.COMBO;
            comboTask newComboTask = new comboTask(comboTasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type, repeatInterval, endDate, partnerName);
            comboTasks.add(newComboTask);
        }
        
        System.out.println("Task created successfully!");
    } //END Create task method

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

    public ArrayList<repeatTask> getRepeatTasks() {
        return repeatTasks;
    }

    public ArrayList<partnerTask> getPartnerTasks() {
        return partnerTasks;
    }

    public ArrayList<comboTask> getComboTasks() {
        return comboTasks;
    }

    //Setters
    public void setLoggedIn(boolean input) {
        this.isLoggedIn = input;
    }

    public void setCurrentUser(String input) {
        this.currentUser = input;
    }
}
