import java.util.Scanner; 

public class BankAccount{
    String accountHolderName;
    int accountNumber;
    double balance;

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -= amount;
    }

    public double getBalance(){
        return balance;
    }

    public void displayAccountDetails(){
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }

    public static void main(String[] args){
        BankAccount account = new BankAccount();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the account holder name: ");
        account.accountHolderName = input.nextLine();
        System.out.println("Enter the account number: ");
        account.accountNumber = input.nextInt();
        System.out.println("Enter the initial balance: ");
        account.balance = input.nextDouble();
        account.deposit(500);
        account.withdraw(100);
        account.displayAccountDetails();
    }
}