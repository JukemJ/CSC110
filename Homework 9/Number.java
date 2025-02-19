//Chris Dixon

import java.util.Scanner;

public class Number {
    public static void main(String[] args) {
        int randomNumber = (int) (Math.random() * 100) + 1;
        int guessedNumber = 0;
        int tries = 0;
        Scanner input = new Scanner(System.in);

        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");

        while (randomNumber != guessedNumber) {
            System.out.print("Enter your guess: ");
            guessedNumber = input.nextInt();
            if (guessedNumber < randomNumber) System.out.println("\nToo low, try again.");
            if (guessedNumber > randomNumber) System.out.println("\nToo high, try again.");
            if (guessedNumber == randomNumber){
                System.out.println("You guessed it!");
                System.out.printf("It took you %d tries to guess the number.\n", tries);
            }
            tries++;
        }
        
        input.close();
        System.exit(0);
    }
}