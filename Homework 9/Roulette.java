//Chris Dixon

public class Roulette {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 37);
        String color = "";
        //if(number < 0 || number > 36) System.out.println("Invalid number");

        if(number == 37 || number == 0) color = "green";
        else if(number <= 10) color = number % 2 == 0 ? "black" : "red";
        else if(number <= 20) color = number % 2 == 0 ? "red" : "black";
        else if(number <= 29) color = number % 2 == 0 ? "black" : "red";
        else if(number <= 36) color = number % 2 == 0 ? "red" : "black";
        
        System.out.printf("Your number is %d so the color is %s\n", number, color);
    }
    
}
