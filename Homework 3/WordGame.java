// Chris Dixon

import java.util.Scanner; 

public class WordGame{
    public static void main(String[] args){
        String name;
        int age;
        String city;
        String college;
        String profession;
        String animal;
        String petName;
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter your name: ");
        name = input.nextLine();
        System.out.println("Enter your age: ");
        age = input.nextInt();
        input.nextLine();
        System.out.println("Enter the name of a city: ");
        city = input.nextLine();
        System.out.println("Enter the name of a college: ");
        college = input.nextLine();
        System.out.println("Enter a profession: ");
        profession = input.nextLine();
        System.out.println("Enter a type of animal: ");
        animal = input.nextLine();
        System.out.println("Enter a pet name: ");
        petName = input.nextLine();
        input.close();
        System.out.println("There once was a person named " + name + " who lived in " + city + ". At the age of " + age + ", " + name + " went to college at " + college + ". " + name + " graduated and went to work as a " + profession + ". Then, " + name + " adopted a(n) " + animal + " named " + petName + ". They both lived happily ever after!");
    }
}