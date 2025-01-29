// Chris Dixon

import java.util.Scanner; 

public class Bill2
{
   public static void main(String[] args)
   {
      System.out.println();
      Scanner input = new Scanner(System.in);
      System.out.println("Enter the cost of the meal:");
      double mealCost = input.nextDouble();
      double taxRate = 8.6;
      double tipPercentage = 20;
      System.out.println("Meal Charge: " + mealCost);
      System.out.println("Tax: " + (mealCost * taxRate / 100));
      System.out.println("Tip: " + (mealCost * tipPercentage / 100));
      System.out.println("Total: $" + ((mealCost + (mealCost * taxRate / 100) + (mealCost * tipPercentage / 100))));
      input.close();
   }

}