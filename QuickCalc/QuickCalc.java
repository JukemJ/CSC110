/**
* @authors Chris Dixon, Sarah Munsell
*
*
*/
import java.util.Scanner;

public class QuickCalc {
    
    private boolean divisibleByTwo(String number){
        String evenNumbers = "02468";
        if(evenNumbers.contains(number.substring(number.length()-1))) return true;
        return false;
    }
    
    private boolean divisibleByThree(String number){
        int sum = 0;
        for (int i = 0; i < number.length(); i++){
            sum += Integer.parseInt(number.substring(i, i+1));
        }
        String sumString = Integer.toString(sum);
        
        return "369".contains(sumString.substring(number.length()-1));
    }

    private boolean divisibleByFour(String number){
        String binaryString = Integer.toBinaryString(Integer.parseInt(number));
        return "00".equals(binaryString.substring(binaryString.length()-2));
    }

    private boolean divisibleByFive(String number){
        return "05".contains(number.substring(number.length()-1));
    }

    private boolean divisibleBySix(String number){
        if(divisibleByTwo(number) && divisibleByThree(number)){
            return true;
        }
        return false;
    }

    private boolean divisibleByEight(String number){
        String binaryString = Integer.toBinaryString(Integer.parseInt(number));
        return "000".equals(binaryString.substring(binaryString.length()-3));
    }

    private boolean divisibleByNine(String number){
        int sum = 0;
        for (int i = 0; i < number.length(); i++){
            sum += Integer.parseInt(number.substring(i, i+1));
        }
        String sumString = Integer.toString(sum);
        return "09".contains(sumString.substring(number.length()-1));
    }
    
    
    
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        QuickCalc calc = new QuickCalc();
        System.out.print("\n\n\n\n\rEnter a number(Enter 'n' to exit): ");
        String yourNumber = input.nextLine();
        if(yourNumber.equals("n")){
            System.out.println("Goodbye!");
            System.exit(0);
        }
        System.out.println();
        System.out.println("Your number is " + (calc.divisibleByTwo(yourNumber) ? "" : "not") + " divisible by 2");
        System.out.println("Your number is " + (calc.divisibleByThree(yourNumber) ? "" : "not") + " divisible by 3");
        System.out.println("Your number is " + (calc.divisibleByFour(yourNumber) ? "" : "not") + " divisible by 4");
        System.out.println("Your number is " + (calc.divisibleByFive(yourNumber) ? "" : "not") + " divisible by 5");
        System.out.println("Your number is " + (calc.divisibleBySix(yourNumber) ? "" : "not") + " divisible by 6");
        System.out.println("Your number is " + (calc.divisibleByEight(yourNumber) ? "" : "not") + " divisible by 8");
        System.out.println("Your number is " + (calc.divisibleByNine(yourNumber) ? "" : "not") + " divisible by 9");
        input.close();

    }
            
}
