// Chris Dixon

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        String[] suits = { "Spade", "Heart", "Diamond", "Club" };
        String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(suits[i], ranks[j]));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> temp = new ArrayList<Card>();
        for (int i = 0; i < 52; i++) {
            Card card = cards.remove((int) (Math.random() * cards.size()));
            temp.add(card);
        }
        this.cards = temp;
    }

    public Card draw() {
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
