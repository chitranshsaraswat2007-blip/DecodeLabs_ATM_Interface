import java.util.Scanner;

class BankAccount {

    private String accountHolder;
    private int accountNumber;
    private double balance;

    public BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit must be greater than 0.");
            return;
        }
        balance = balance + amount;
        System.out.println("Deposit successful! New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Enter a positive value.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance! Your balance is only " + balance);
        } else {
            balance = balance - amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        }
    }
}

class ATM {
    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        int choice;

        System.out.println("===================================");
        System.out.println(" WELCOME " + account.getAccountHolder());
        System.out.println("===================================");

        do {
            System.out.println("\n------ ATM MENU ------");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number (1-4).");
                sc.next();
                System.out.print("Enter your choice: ");
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance: " + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = getValidAmount();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = getValidAmount();
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1-4.");
            }

        } while (choice != 4);
    }

    private double getValidAmount() {
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a number.");
            sc.next();
            System.out.print("Enter amount: ");
        }
        return sc.nextDouble();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Chitransh Saraswat", 10234567, 5000.0);

        ATM atm = new ATM(myAccount);

        atm.start();
    }
}
