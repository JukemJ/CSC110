// Chris Dixon

import java.util.Scanner;

public class RomanNumerals {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a number between 1 and 10 (inclusive): ");
        int number = scanner.nextInt();
        String[] romanNumerals ={"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        if(number < 1 || number > 10) System.out.println("Invalid number");
        else System.out.println("Roman numeral: " + romanNumerals[number - 1]);
    }
}

