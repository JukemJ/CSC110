// Chris Dixon

import java.util.Scanner;

public class FatGramCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter the number of calories: ");
        System.out.print("\nEnter the number of grams of fat: ");
        //one gram of fat has 9 calories
        double calories = input.nextDouble();
        double fatGrams = input.nextDouble();
        if(calories < 0 || fatGrams < 0) {
            System.out.println("Invalid input");
        }
        else if(fatGrams * 9 < calories / 3){
            System.out.println("The food is low in fat");
        }
        input.close();
    }
}