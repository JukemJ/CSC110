import java.util.ArrayList;
import java.util.Scanner;

public class Deck {

    ArrayList<String> cards = new ArrayList<String>();

    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};

        ArrayList<String> cards = new ArrayList<String>();

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++) {
                String card = ranks[j] + " of " + suits[i];
                cards.add(card);
            }
        }
        this.cards = cards;
        //this.shuffle();
    }

    public void shuffle(){
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < 52; i++) {
            String card = cards.remove((int)(Math.random() * cards.size()));
            temp.add(card);
        }
        this.cards = temp;
    }

    public String drawCard(){
        String card = cards.remove(0);
        System.out.println("\nYou drew: " + card);
        return card;
    }

    public String toString(){
        String cardsInDeck = "\n";
        for(int i = 0; i < cards.size(); i++) {
            cardsInDeck += cards.get(i) + ", ";
        }
        return String.format("\nYour deck has: %d cards\nThey are: %s\n", cards.size(), cardsInDeck);
    }


    public static void main(String[] args) {
        Deck deck = new Deck();
        Scanner input = new Scanner(System.in);

        while(true){
            System.out.println("\n\n1. Draw Card\n2. Shuffle Deck\n3. View Deck\n4. New Deck\n5. Quit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            switch (choice){
                case 1:
                    deck.drawCard();
                    break;
                case 2:
                    deck.shuffle();
                    break;
                case 3:
                    System.out.println(deck.toString());
                    break;
                case 4:
                    deck = new Deck();
                    System.out.println("\nNew deck created!");
                    break;
                case 5: 
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
