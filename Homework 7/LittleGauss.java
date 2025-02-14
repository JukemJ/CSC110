// Chris Dixon 
import java.util.Scanner;

public class LittleGauss {
    public static void main(String[] args) {
        System.out.print("\n\nEnter a number: ");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        if(num < 1) {
            System.out.println("Please enter a number greater than 0.");
            System.exit(0);
        }
        int sum = 0;
        for (int i = 1; i <= num; i++) sum += i;
        System.out.println("Your sum is: " + sum);
    }
}
