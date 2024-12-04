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
            System.out.println("\t6: Restock Product");
            System.out.println("\t7: Check Inventory Value");
            System.out.println("\t8: Compare Product Prices");
            System.out.println("\t9: Exit");
        }

        sc.close();
    }
}