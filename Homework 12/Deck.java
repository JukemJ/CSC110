//Chris Dixon

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] ranks = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
        // System.out.println(Card.getCardCount());
    }

    public static void main(String[] args) {
        System.out.println();

        Card card1 = new Card("Hearts", "5");
        Card card2 = new Card("Spades", "10");
        Card card3 = new Card("Diamonds", "5");

        System.out.println(card1);
        System.out.println(card2);
        System.out.println(card3+"\n");
        

        System.out.println(card1.compareTo(card2));
        System.out.println(card1.compareTo(card3));

        Deck deck = new Deck();
        System.out.println(Card.getCardCount());
    }
}
