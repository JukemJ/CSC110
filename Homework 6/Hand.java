//Chris Dixon
import java.util.Scanner;
import java.util.ArrayList;

public class Hand {
    ArrayList<String> cards = new ArrayList<String>();
    
    public Hand() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"S", "C", "H", "D"};
        ArrayList<String> cards = new ArrayList<String>();
        ArrayList<String> deck = new ArrayList<String>();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++) {
                String card = ranks[j] + suits[i];
                deck.add(card);
            }
        }
        for(int i = 0; i < 5; i++) {
            int index = (int)(Math.random() * deck.size());
            cards.add(deck.get(index));
            deck.remove(index);
        }
        this.cards = cards;
    }

    public void display() {
        System.out.print("\n\nYour hand: ");
        for(int i = 0; i < cards.size(); i++) {
            System.out.print(cards.get(i) + " ");
        }
        System.out.println("\n");
    }

    public void play(int index){
        System.out.println("You played " + this.cards.get(index) + " !");
        this.cards.remove(index);
        this.display();

    }

    public void discard(int index){
        System.out.println("You discarded " + this.cards.get(index) + " !");
        this.cards.remove(index);
        this.display();
    }
    
    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hand hand = new Hand();
        hand.display();
        while(true){
            System.out.println("\n1. Play\n2. Discard\n3. Quit");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the index of the card you want to play: ");
                    //int index = ;
                    hand.play(input.nextInt());
                    break;
                case 2:
                    System.out.println("Enter the index of the card you want to discard: ");
                    //int index = ;
                    hand.discard(input.nextInt());
                    break;
                case 3: 
                    System.out.println("\nGoodbye!");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            
        }   
    }
}