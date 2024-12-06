import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.time.LocalDate;


class Menu {

    public static void main(String[] args) {
        taskCompass tc = new taskCompass();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
    
        while (isRunning) {
            try {
                if (!tc.getLoggedIn()) {
                    System.out.println("Main Menu:");
                    System.out.println("\t1. Login");
                    System.out.println("\t2. Sign Up");
                    System.out.println("\t3. Exit");
                    System.out.print("\nEnter your choice: ");

                    String input = scanner.nextLine().trim();

                    if (input.isEmpty()) {
                        System.out.println("\nYou must choose an option. Please try again.\n");
                        continue;
                    }
                    
                    if (!input.matches("\\d+")) {
                        System.out.println("\nInvalid input. Please enter a valid number.\n");
                        continue;
                    }
                    
                    int choice = Integer.parseInt(input);
                    
                    switch (choice) {
                        case 1:
                            if (tc.getUsers().isEmpty()) {
                                System.out.println("\nNo user accounts, please create an account first.\n");
                            } else {
                                tc.login(scanner);
                            }
                            break;
                        case 2:
                            tc.signUp(scanner);
                            break;
                        case 3:
                            isRunning = false;
                            System.out.println("\nExiting...");
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.\n");
                    }
                } else {
                    System.out.println("\nLogged In Menu:");
                    System.out.println("\t1. View/Edit Tasks");
                    System.out.println("\t2. Create Task");
                    System.out.println("\t3. View Notifications");
                    System.out.println("\t4. Change User");
                    System.out.println("\t5. Exit\n");
                    System.out.print("Enter your choice: ");
    
                    if (!scanner.hasNextInt()) {
                        System.out.println("\nInvalid input. Please enter a number.\n");
                        scanner.next();
                        continue;
                    }
    
                    int choice = scanner.nextInt();
                    scanner.nextLine();
    
                    switch (choice) {
                        case 1:
                            tc.viewTasks();
                            // tc.editTask(scanner);
                            break;
                        case 2:
                            tc.createTask(scanner);
                            break;
                        case 3:
                            viewNotifications(tc);
                            break;
                        case 4:
                            tc.setLoggedIn(false);
                            tc.setCurrentUser(null);
                            System.out.println("\nLogged out. Returning to Main Menu...\n");
                            break;
                        case 5:
                            isRunning = false;
                            System.out.println("\nExiting...");
                            break;

                // TEST CASES - DELETE
                        case 69:
                            System.out.println("Regular: " + tc.getTasks());
                            System.out.println(" Repeat: " + tc.getRepeatTasks());
                            System.out.println("Partner: " + tc.getPartnerTasks());
                            System.out.println("  Combo: " + tc.getComboTasks());
                            break;
                        case 420:
                            if (tc.getRepeatTasks().isEmpty()) {
                                System.out.println("\nError running test, create repeat task first...");
                            } else {
                                System.out.println("Name: " + tc.getRepeatTasks().get(0).getTaskName() +
                                        "\nUser: " + tc.getRepeatTasks().get(0).getTaskUser() +
                                        "\nDescription: " + tc.getRepeatTasks().get(0).getTaskDescription() +
                                        "\nPriority: " + tc.getRepeatTasks().get(0).getTaskPriority() +
                                        "\tInterval: " + tc.getRepeatTasks().get(0).getRepeatInterval() +
                                        "\tEnd Date: " + tc.getRepeatTasks().get(0).getEndDate() +
                                        "\tType: " + tc.getRepeatTasks().get(0).getTaskType());
                            }
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.\n");
                    }
                }
            } catch (Exception e) {
                System.out.println("\nAn error occurred. Please try again.\n");
                scanner.nextLine();
            }
        }
    
        scanner.close();
    }
    

    private static void viewNotifications(taskCompass tc) {
        // Combine all tasks from all lists into a single list
        List<Object> allTasks = new ArrayList<>();
        allTasks.addAll(tc.getTasks());
        allTasks.addAll(tc.getRepeatTasks());
        allTasks.addAll(tc.getPartnerTasks());
        allTasks.addAll(tc.getComboTasks());
    
        // Sort tasks by priority (descending) and due date (ascending)
        List<Object> sortedTasks = allTasks.stream()
                .sorted((task1, task2) -> {
                    int priority1 = tc.getTaskPriorityValue(task1);
                    int priority2 = tc.getTaskPriorityValue(task2);
                    if (priority1 != priority2) {
                        return Integer.compare(priority2, priority1);
                    }
    
                    LocalDate endDate1 = tc.getTaskEndDate(task1);
                    LocalDate endDate2 = tc.getTaskEndDate(task2);
    
                    if (endDate1 == null && endDate2 == null) return 0;
                    if (endDate1 == null) return 1;
                    if (endDate2 == null) return -1;
                    return endDate1.compareTo(endDate2);
                })
                .collect(Collectors.toList());
    
        // Display the sorted tasks
        System.out.println("\nUpcoming Tasks:");
        if (sortedTasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Object task : sortedTasks) {
                String taskName = tc.getTaskName(task);
                LocalDate endDate = tc.getTaskEndDate(task);
                String endDateDisplay = (endDate == null) ? "No End Date" : endDate.toString();
                String priority = tc.getTaskPriority(task);
                System.out.println("- " + taskName + " | Priority: " + priority + " | End Date: " + endDateDisplay);
            }
        }
    }
        
}