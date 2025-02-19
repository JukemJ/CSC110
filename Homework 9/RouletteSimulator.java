//Chris Dixon

public class RouletteSimulator {

    public static String roulette(int number){
        String color = "";

        if(number == 37 || number == 0) color = "green";
        else if(number <= 10) color = number % 2 == 0 ? "black" : "red";
        else if(number <= 20) color = number % 2 == 0 ? "red" : "black";
        else if(number <= 29) color = number % 2 == 0 ? "black" : "red";
        else if(number <= 36) color = number % 2 == 0 ? "red" : "black";

        return color;
    }
    
    public static void main(String[] args) {
        int[] colorCount = {0,0,0};              //[red, black, green]
        int maxIndex = 0;

        for(int i = 1; i <= 100; i++){
            int randomNumber = (int) (Math.random() * 37);
            if(roulette(randomNumber).equals("red")) colorCount[0]++;
            if(roulette(randomNumber).equals("black")) colorCount[1]++;
            if(roulette(randomNumber).equals("green")) colorCount[2]++;
        }

        for(int i = 0; i < colorCount.length; i++){
            if(colorCount[i] > colorCount[maxIndex]) maxIndex = i;
        }

        System.out.printf("Red: %d\nBlack: %d\nGreen: %d\n", colorCount[0], colorCount[1], colorCount[2]);
        System.out.printf("The color that appeared the most was %s\n", maxIndex == 0 ? "red" : maxIndex == 1 ? "black" : "green");
    }
}
