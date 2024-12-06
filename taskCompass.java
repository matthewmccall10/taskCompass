import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        System.out.print("\nEnter username to login: ");
        String username = sc.nextLine();
        if (users.contains(username)) {
            currentUser = username;
            isLoggedIn = true;
            System.out.println("Logged in as " + currentUser);
        } else {
            System.out.println("\nUsername not found. Please sign up first.\n");
        }
    }
    //END Login method

    // START Signup method
    public void signUp(Scanner sc) {
        System.out.print("\nEnter a new username: ");
        String newUser = sc.nextLine();
        if (users.contains(newUser)) {
            System.out.println("\nUsername already exists. Please try a different username.");
        } else {
            users.add(newUser);
            System.out.println("User registered successfully! You can now log in.\n");
        }
    }
    //END Signup method

    //START View tasks method
    public void viewTasks(Scanner sc) {
        if (tasks.isEmpty() && repeatTasks.isEmpty() && partnerTasks.isEmpty() && comboTasks.isEmpty()) {
            System.out.println("\nNo tasks available.");
        } else {
            ArrayList<taskEditInfo> taskInfoList = new ArrayList<>();

            System.out.println("\nYour associated tasks:");

            // REGULAR TASK
            System.out.println("\nREGULAR TASKS: ");
                for (int i = 0; i < tasks.size(); i++) {
                    if(currentUser.equals(tasks.get(i).getTaskUser())) {
                        taskInfoList.add(new taskEditInfo(tasks.get(i).getTaskName(), tasks.get(i).getTaskID(), tasks.get(i).getTaskType()));
                        
                        System.out.println( 
                            "\n\tTitle: " + getTasks().get(i).getTaskName() + 
                            "\n\tDescription: " + getTasks().get(i).getTaskDescription() + 
                            "\n\tPriority: " + getTasks().get(i).getTaskPriority());
                    }
                }

            // REPEATING TASK
            System.out.println("\nREPEATING TASKS: ");
                for (int i = 0; i < repeatTasks.size(); i++) {
                    if(currentUser.equals(repeatTasks.get(i).getTaskUser())) {
                        taskInfoList.add(new taskEditInfo(repeatTasks.get(i).getTaskName(), repeatTasks.get(i).getTaskID(), repeatTasks.get(i).getTaskType()));

                        System.out.println( 
                            "\n\tTitle: " + getRepeatTasks().get(i).getTaskName() + 
                            "\n\tDescription: " + getRepeatTasks().get(i).getTaskDescription() + 
                            "\n\tPriority: " + getRepeatTasks().get(i).getTaskPriority() + 
                            "\n\tRepetition: " + getRepeatTasks().get(i).getRepeatInterval() + 
                            "\n\tEnd Date: " + getRepeatTasks().get(i).getEndDate());
                    }
                }
            

            // PARTNER TASK
            System.out.println("\nPARTNER TASKS: ");
                for (int i = 0; i < partnerTasks.size(); i++) {
                    if(currentUser.equals(partnerTasks.get(i).getTaskUser()) || currentUser.equals(partnerTasks.get(i).getPartnerName())) {
                        taskInfoList.add(new taskEditInfo(partnerTasks.get(i).getTaskName(), partnerTasks.get(i).getTaskID(), partnerTasks.get(i).getTaskType()));
                        
                        System.out.println( 
                            "\n\tTitle: " + getPartnerTasks().get(i).getTaskName() + 
                            "\n\tDescription: " + getPartnerTasks().get(i).getTaskDescription() + 
                            "\n\tPartner Name: " + getPartnerTasks().get(i).getPartnerName() +
                            "\n\tPriority: " + getPartnerTasks().get(i).getTaskPriority());
                    }
                }

            // COMBO TASK
            System.out.println("\nCOMBO TASKS: ");
                for (int i = 0; i < comboTasks.size(); i++) {
                    if(currentUser.equals(comboTasks.get(i).getTaskUser()) || currentUser.equals(comboTasks.get(i).getPartnerName())) {
                        taskInfoList.add(new taskEditInfo(comboTasks.get(i).getTaskName(), comboTasks.get(i).getTaskID(), comboTasks.get(i).getTaskType()));

                        System.out.println( 
                            "\n\tTitle: " + getComboTasks().get(i).getTaskName() + 
                            "\n\tDescription: " + getComboTasks().get(i).getTaskDescription() +
                            "\n\tPartner Name: " + getComboTasks().get(i).getPartnerName() + 
                            "\n\tPriority: " + getComboTasks().get(i).getTaskPriority() + 
                            "\n\tRepetition: " + getComboTasks().get(i).getRepeatInterval() + 
                            "\n\tEnd Date: " + getComboTasks().get(i).getEndDate());
                    }
                }

            System.out.println("\nEnter a task's title to edit that task, or enter nothing to return to the main menu.");
            String userSelection = sc.nextLine();
            if(!userSelection.isEmpty()) {

                editTask(taskInfoList, userSelection, sc);

                viewTasks(sc);
            }
        }
    } //END View tasks method

    // START View Notifications method 
        // Helper method to get the end date of any task
        public LocalDate getTaskEndDate(Object task) {
            if (task instanceof repeatTask) {
                return ((repeatTask) task).getEndDate();
            } else if (task instanceof comboTask) {
                return ((comboTask) task).getEndDate();
            }
            return null; // Tasks like regular or partner do not have an end date
        }
        
        public String getTaskName(Object task) {
            if (task instanceof task) {
                return ((task) task).getTaskName();
            } else if (task instanceof repeatTask) {
                return ((repeatTask) task).getTaskName();
            } else if (task instanceof partnerTask) {
                return ((partnerTask) task).getTaskName();
            } else if (task instanceof comboTask) {
                return ((comboTask) task).getTaskName();
            }
            return "Unknown Task";
        }
        public int getTaskPriorityValue(Object task) {
            String priority = "";
            if (task instanceof task) {
                priority = ((task) task).getTaskPriority();
            } else if (task instanceof repeatTask) {
                priority = ((repeatTask) task).getTaskPriority();
            } else if (task instanceof partnerTask) {
                priority = ((partnerTask) task).getTaskPriority();
            } else if (task instanceof comboTask) {
                priority = ((comboTask) task).getTaskPriority();
            }
        
            switch (priority) {
                case "High":
                    return 3;
                case "Medium":
                    return 2;
                case "Low":
                    return 1;
                default:
                    return 0; // Unknown priority
            }
        }
        public String getTaskPriority(Object task) {
            if (task instanceof task) {
                return ((task) task).getTaskPriority();
            } else if (task instanceof repeatTask) {
                return ((repeatTask) task).getTaskPriority();
            } else if (task instanceof partnerTask) {
                return ((partnerTask) task).getTaskPriority();
            } else if (task instanceof comboTask) {
                return ((comboTask) task).getTaskPriority();
            }
            return "Unknown";
        }
        
        
        

    // END View Notifications method

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
                    System.out.println("Invalid selection. Enter 3 for High Priority, 2 for Medium Priority, or 1 for Low Priority: ");
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
        
            // Validate repeat interval
            while (true) {
                System.out.println("Enter repeat interval (daily, weekly, monthly) or use abbreviations (d, w, m): ");
                repeatInterval = sc.nextLine().toLowerCase();
        
                if (repeatInterval.equals("daily") || repeatInterval.equals("d")) {
                    repeatInterval = "daily";
                    break;
                } else if (repeatInterval.equals("weekly") || repeatInterval.equals("w")) {
                    repeatInterval = "weekly";
                    break;
                } else if (repeatInterval.equals("monthly") || repeatInterval.equals("m")) {
                    repeatInterval = "monthly";
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'daily', 'weekly', 'monthly' or their abbreviations ('d', 'w', 'm').");
                }
            }
        
            // Validate end date
            while (true) {
                System.out.println("Enter end date (YYYY-MM-DD) or leave blank for no end date: ");
                String endDateInput = sc.nextLine();
        
                if (endDateInput.isEmpty()) {
                    endDate = null;
                    break;
                }
        
                try {
                    endDate = LocalDate.parse(endDateInput);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("\nInvalid date format. Please enter the date in YYYY-MM-DD format.\n");
                }
            }
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
            if (taskName.isEmpty()) {
                taskName = "BaseTask#" + tasks.size();
            }
            task newTask = new task(tasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type);
            tasks.add(newTask);
        } else if (checkRepeating == true && checkPartner == false) {
            type = task.TaskType.REPEAT;
            if (taskName.isEmpty()) {
                taskName = "RepeatTask#" + tasks.size();
            }
            repeatTask newRepeatTask = new repeatTask(repeatTasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type, repeatInterval, endDate);
            repeatTasks.add(newRepeatTask);
        } else if (checkRepeating == false && checkPartner == true) {
            type = task.TaskType.PARTNER;
            if (taskName.isEmpty()) {
                taskName = "PartnerTask#" + tasks.size();
            }
            partnerTask newPartnerTask = new partnerTask(partnerTasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type, partnerName);
            partnerTasks.add(newPartnerTask);
        } else if (checkRepeating == true && checkPartner == true) {
            type = task.TaskType.COMBO;
            if (taskName.isEmpty()) {
                taskName = "ComboTask#" + tasks.size();
            }
            comboTask newComboTask = new comboTask(comboTasks.size(), taskName, currentUser, taskDescription, taskPriority, false, type, repeatInterval, endDate, partnerName);
            comboTasks.add(newComboTask);
        }
        
        System.out.println("Task created successfully!");
    }
    //END Create task method

    //START Edit task method
    public void editTask(ArrayList<taskEditInfo> foundTasks , String searchTerm, Scanner sc) {
        int matchingTasks = 0;
        int matchedIndex = -1;
        task.TaskType matchedType = task.TaskType.ERROR;

        for (int i = 0; i < foundTasks.size(); i++) {

            if(searchTerm.equals(foundTasks.get(i).getEditName())) {
                matchingTasks++;
                matchedIndex = foundTasks.get(i).getEditID();
                matchedType = foundTasks.get(i).getEditType();
            }
        }

        if(matchingTasks == 1) {
            if(matchedIndex == -1 || matchedType == task.TaskType.ERROR) {
                System.out.println("Error updating search criteria! This shouldn't happen...");
            } else {
                if(matchedType == task.TaskType.BASE) {
                    System.out.println( 
                            "\n\tTitle: " + getTasks().get(matchedIndex).getTaskName() + 
                            "\n\tDescription: " + getTasks().get(matchedIndex).getTaskDescription() + 
                            "\n\tPriority: " + getTasks().get(matchedIndex).getTaskPriority());
                } else if (matchedType == task.TaskType.REPEAT) {
                    System.out.println( 
                            "\n\tTitle: " + getRepeatTasks().get(matchedIndex).getTaskName() + 
                            "\n\tDescription: " + getRepeatTasks().get(matchedIndex).getTaskDescription() + 
                            "\n\tPriority: " + getRepeatTasks().get(matchedIndex).getTaskPriority() + 
                            "\n\tRepetition: " + getRepeatTasks().get(matchedIndex).getRepeatInterval() + 
                            "\n\tEnd Date: " + getRepeatTasks().get(matchedIndex).getEndDate());  
                } else if (matchedType == task.TaskType.PARTNER) {
                    System.out.println( 
                            "\n\tTitle: " + getPartnerTasks().get(matchedIndex).getTaskName() + 
                            "\n\tDescription: " + getPartnerTasks().get(matchedIndex).getTaskDescription() + 
                            "\n\tPartner Name: " + getPartnerTasks().get(matchedIndex).getPartnerName() +
                            "\n\tPriority: " + getPartnerTasks().get(matchedIndex).getTaskPriority());
                } else if (matchedType == task.TaskType.COMBO) {
                    System.out.println( 
                            "\n\tTitle: " + getComboTasks().get(matchedIndex).getTaskName() + 
                            "\n\tDescription: " + getComboTasks().get(matchedIndex).getTaskDescription() +
                            "\n\tPartner Name: " + getComboTasks().get(matchedIndex).getPartnerName() + 
                            "\n\tPriority: " + getComboTasks().get(matchedIndex).getTaskPriority() + 
                            "\n\tRepetition: " + getComboTasks().get(matchedIndex).getRepeatInterval() + 
                            "\n\tEnd Date: " + getComboTasks().get(matchedIndex).getEndDate());
                } else {
                    System.out.println("Error comparing task.TaskType enum! This shouldn't happen...");
                }
            }
        } else {
            System.out.println("Multiple tasks found with same title, listing options:");
        }
    }
    //END Edit task method

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
