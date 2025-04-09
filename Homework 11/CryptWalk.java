// Chris Dixon

//This program reads a file called secret.txt decodes it by shifting each character by -1 and prints the output to the terminal.

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class CryptWalk {
    public static void main(String[] args) {

        String encodedString = "";
        String decodedString = "";

        System.out.println();

        // Read the file
        try {
            File file = new File("./Homework 11/secret.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                encodedString = myReader.nextLine();
                System.out.printf("Encoded Text: %s\n", encodedString);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    
        // Decode the string
        for (char c : encodedString.toCharArray()) {
            if (((int) c >= 65) && ((int) c <= 90) || ((int) c >= 97) && ((int) c <= 122)) {
                decodedString += (char) (c - 1);
            } else {
                decodedString += c;
            }

        }
        System.out.printf("Decoded Text: %s\n", decodedString);
    }
}
