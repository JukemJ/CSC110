//Chris Dixon

import java.util.Scanner;

public class Multiply {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n\nEnter the first number: ");
        int num1 = input.nextInt();
        System.out.print("\nEnter the second number: ");
        int num2 = input.nextInt();
        int prod = 0;

        while(num2 > 0){
            prod += num1;
            num2--;
        }
        
        System.out.println("Your answer is: " + prod);
    }
}
