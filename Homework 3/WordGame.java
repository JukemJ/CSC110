import java.util.Scanner; 

public class WordGame{
    public static void main(String[] args){
        System.out.println();                   //start on a new line
        String name;
        int age;
        String city;
        String college;
        String profession;
        String animal;
        String petName;
        System.out.println("Enter your name: ");
        name = System.console().readLine();
        System.out.println("Enter your age: ");
        age = Integer.parseInt(System.console().readLine());
        System.out.println("Enter the name of a city: ");
        city = System.console().readLine();
        System.out.println("Enter the name of a college: ");
        college = System.console().readLine();
        System.out.println("Enter a profession: ");
        profession = System.console().readLine();
        System.out.println("Enter a type of animal: ");
        animal = System.console().readLine();
        System.out.println("Enter a pet name: ");
        petName = System.console().readLine();
        System.out.println("There once was a person named " + name + " who lived in " + city + ". At the age of " + age + ", " + name + " went to college at " + college + ". " + name + " graduated and went to work as a " + profession + ". Then, " + name + " adopted a(n) " + animal + " named " + petName + ". They both lived happily ever after!");
    }
}