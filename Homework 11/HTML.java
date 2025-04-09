// Chris Dixon

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class HTML {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        
        System.out.print("Enter your age: ");
        int age = input.nextInt();
        input.nextLine(); // Consume the newline character
        
        System.out.print("Enter your occupation: ");
        String occupation = input.nextLine();

        System.out.print("Enter your salary: ");
        double salary = input.nextDouble();
        input.nextLine(); // Consume the newline character

        System.out.print("Enter a description about yourself: ");
        String description = input.nextLine();

        // create profile.html
        try {
            File file = new File("profile.html");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        // write to profile.html
        try {
            FileWriter myWriter = new FileWriter("profile.html");
            myWriter.write("<html>\n");
            myWriter.write("<head>\n");
            myWriter.write("</head>\n");
            myWriter.write("<body>\n");
            myWriter.write("<h1>" + name + "</h1>\n");
            myWriter.write("<p> Name: " + name + " </p>\n");
            myWriter.write("<p> Age: " + age + " </p>\n");
            myWriter.write("<p> Occupation: " + occupation + " </p>\n");
            myWriter.write("<p> Salary: " + salary + " </p>\n");
            myWriter.write("<p> " + description + "</p>\n");
            myWriter.write("</body>\n");
            myWriter.write("</html>");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        input.close();
    }
    

}
