import java.util.Scanner; 

public class Oreos{
    public static void main(String[] args) {
        double caloriesPerCookie = 53.333;
        Scanner input = new Scanner(System.in);
        System.out.println("How many cookies did you eat?");
        int cookies = input.nextInt();
        double calories = cookies * caloriesPerCookie;
        System.out.println("You consumed " + calories + " .");
  }
}