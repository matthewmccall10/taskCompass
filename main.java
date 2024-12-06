import java.util.Scanner;

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
                        System.out.println("\nInvalid input. Please enter a number.\n");
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
                    System.out.println("\t1. View Tasks");
                    System.out.println("\t2. Create Task");
                    System.out.println("\t3. Edit Task");
                    System.out.println("\t4. View Notifications");
                    System.out.println("\t5. Change User");
                    System.out.println("\t6. Exit\n");
                    System.out.print("Enter your choice: \n");
    
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
                            break;
                        case 2:
                            tc.createTask(scanner);
                            break;
                        case 3:
                            // tc.editTask(scanner);
                            break;
                        case 4:
                            viewNotifications();
                            break;
                        case 5:
                            tc.setLoggedIn(false);
                            tc.setCurrentUser(null);
                            System.out.println("\nLogged out. Returning to Main Menu...\n");
                            break;
                        case 6:
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
    

    private static void viewNotifications() {
        System.out.println("You have no new notifications.");
    }
}
