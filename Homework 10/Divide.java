// Chris Dixon
import java.util.Scanner;

public class Divide {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        while (!validInput) {
            try {
                // Prompt the user for two numbers
                System.out.print("Enter the numerator: ");
                String numeratorInput = scanner.nextLine();
                int numerator = Integer.parseInt(numeratorInput);

                System.out.print("Enter the denominator: ");
                String denominatorInput = scanner.nextLine();
                int denominator = Integer.parseInt(denominatorInput);

                // Perform division
                int result = numerator / denominator;

                // Display result
                System.out.println("Result: " + result);
                validInput = true;

            } catch (ArithmeticException e) {
                System.out.println("Error: Division by zero is not allowed. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number.");
            }
        }

        scanner.close();
    }
}
