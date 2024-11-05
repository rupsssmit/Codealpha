import java.util.Scanner;

public class SimpleBankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000.0; 

        while (true) {
            System.out.println("\nWelcome to Simple Banking Application");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    balance += depositAmount;
                    System.out.println("Deposit successful. New balance: " + balance);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    if (withdrawalAmount > balance) {
                        System.out.println("Insufficient balance.");
                    } else {
                        balance -= withdrawalAmount;
                        System.out.println("Withdrawal successful. New balance: " + balance);
                    }
                    break;
                case 3:
                    System.out.println("Your current balance: " + balance);
                    break;
                case 4:
                    System.out.println("Thank you for using the Simple Banking Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}