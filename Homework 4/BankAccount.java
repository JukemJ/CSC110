/**
 * @author: Chris Dixon
 * @date: 02/4/2025
 * @description: This program creates a bank account object with a name, account number, and balance.
 */

import java.util.Scanner; 

public class BankAccount{
    private String accountHolderName;
    private int accountNumber;
    private double balance;

    public BankAccount (String accountHolderName, int accountNumber, double balance){
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
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
        System.out.println();                   //start on a new line
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the account holder name: ");
        String accountHolderName = input.nextLine();
        System.out.println("Enter the account number: ");
        int accountNumber = input.nextInt();
        System.out.println("Enter the initial balance: ");
        double balance = input.nextDouble();
        System.out.println();
                           
        BankAccount account = new BankAccount(accountHolderName, accountNumber, balance);
        account.deposit(500);
        account.withdraw(100);
        account.displayAccountDetails();
        input.close();
    }
}