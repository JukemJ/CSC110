import java.util.Scanner;

public class License{
    String name;
    String address;
    int age;
    int expirationYear;

    public License(String name, String address, int age, int expirationYear){
        name = name;
        address = address;
        age = age;
        expirationYear = expirationYear;
    }

    public void displayLicenseDetails(){
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Age: " + age);
        System.out.println("Expiration Year: " + expirationYear);
    }

    public void renew(){
        expirationYear += 7;
        System.out.println("License renewed!");
    }

    public void birthday(){
        age++;
        System.out.println("Happy Birthday!");
    }

    public static void main(String[] args){
        System.out.println();                   //start on a new line
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = input.nextLine();
        System.out.println("Enter your address: ");
        String address = input.nextLine();
        System.out.println("Enter your age: ");
        int age = input.nextInt();
        System.out.println("Enter the expiration year: ");
        int expirationYear = input.nextInt();
        License license = new License(name, address, age, expirationYear);

    }
}