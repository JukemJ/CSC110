
public class Hand extends Deck {

    int last = 0;

    public Hand(int size) { // this size changes depending on game rules
        super(size);
        last = 0;
    }

    public void addCard(Card c) {
        if (last < deck.length) {
            deck[last++] = c;
        } else {
            System.out.println("Error: too many cards in hand");
        }
    }

    public Card getCard(int i) {
        return deck[i];
    }
}
