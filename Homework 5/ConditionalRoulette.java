// Chris Dixon
import java.util.Scanner;

public class ConditionalRoulette {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a number 0-36: ");
        int number = input.nextInt();
        String color = "";
        if(number < 0 || number > 36) System.out.println("Invalid number");
        if(number == 0) color = "green";
        else if(number <= 10) color = number % 2 == 0 ? "black" : "red";
        else if(number <= 20) color = number % 2 == 0 ? "red" : "black";
        else if(number <= 29) color = number % 2 == 0 ? "black" : "red";
        else if(number <= 36) color = number % 2 == 0 ? "red" : "black";
        System.out.println(color);
    }
}