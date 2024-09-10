import java.util.Scanner;

class atmMachine {
    private bankAccount account;

    public atmMachine(bankAccount acc) {
        this.account = acc;
    }

    public void interfaceStart() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw Money ");
            System.out.println("2. Deposit Money ");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            int selection = input.nextInt();

            switch (selection) {
                case 1:
                    System.out.print("Amount to withdraw: ₹");
                    double amountWithdraw = input.nextDouble();
                    account.withdraw(amountWithdraw);
                    break;
                case 2:
                    System.out.print("Amount to deposit: ₹");
                    double amountDeposit = input.nextDouble();
                    account.deposit(amountDeposit);
                    break;
                case 3:
                    account.displayBalance();
                    break;
                case 4:
                    System.out.println("Thank you, Please come again.");
                    input.close();
                    return; 
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}

class bankAccount {
    private double balanceAmount;

    public bankAccount(double initialAmount) {
        this.balanceAmount = initialAmount;
    }

    public void deposit(double amt) {
        if (amt > 0) {
            balanceAmount += amt;
            System.out.println("Deposited: ₹" + amt);
        } else {
            System.out.println("invalid deposit amount.");
        }
    }

    public boolean withdraw(double amt) {
        if (amt > 0 && amt <= balanceAmount) {
            balanceAmount -= amt;
            System.out.println("Withdrawn: ₹" + amt);
            return true;
        } else if (amt > balanceAmount) {
            System.out.println("Not enough balance.");
            return false;
        } else {
            System.out.println("Wrong withdrawal amount.");
            return false;
        }
    }

    public void displayBalance() {
        System.out.println("Balance: ₹" + balanceAmount);
    }
}

public class Main {
    public static void main(String[] args) {
        bankAccount userAccount = new bankAccount(500.00);
        atmMachine atm = new atmMachine(userAccount);
        atm.interfaceStart();
    }
}
