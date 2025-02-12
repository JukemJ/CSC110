import java.util.Scanner;

public class LicenseDriver {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your address: ");
        String address = input.nextLine();
        System.out.println("Enter your age: ");
        int age = input.nextInt();
        System.out.println("Enter the expiration year: ");
        int expirationYear = input.nextInt();
        License license = new License(name, address, age, expirationYear);
        license.renew();
        license.birthday();
        input.close();
    }
}
