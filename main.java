import java.util.Scanner;

class test {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        boolean menuStop = false;
        
        //while loop to run until exit is selected
        while(menuStop == false) {
            //printing options menu
            System.out.println("Welcome to the bookstore, please select an option below:");
            System.out.println("\t1: Check Inventory");
            System.out.println("\t2: Add Member");
            System.out.println("\t3: Make Purchase");
            System.out.println("\t4: Check Customer's Total Spent");
            System.out.println("\t5: Check Premium Fee Payment");
            System.out.println("\t9: Exit");

        //prompting user for selection and storing it
        System.out.println("Please enter a selection from the list. input 1-8");
        String menuInput = sc.next();

            //executes desired method based on user input, also warns of erroneous input
            switch(menuInput) {
                case "1":
                    tomsBooks.checkInventory();
                    break;
                case "2":
                    tomsBooks.addMember();
                    break;
                case "3":
                    tomsBooks.makePurchase();
                    break;
                case "4":
                    tomsBooks.checkAmountSpent();
                    break;
                case "5":
                    tomsBooks.checkFeePayment();
                    break;
                case "9":
                    menuStop = true;
                    break;
                default:
                    System.out.println("ERROR: Invalid input, please try again.\n");
                    break;
            }
            
        sc.close();
        }
    }
}