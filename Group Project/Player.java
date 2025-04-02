import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private int score = 0;

    public Player() {
        // Your code here
    }

    public int getScore() {
        return this.score;
    }

    public void drawCard(Card card) {
        this.hand.add(card);
    }

    public void sortHand() {
        this.hand.sort((a, b) -> a.getValue() - b.getValue());
    }

    public ArrayList<Card> getHand() {
        sortHand();
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void showHand() {
        sortHand();
        System.out.println("Your hand: ");
        for (Card card : hand) {
            System.out.print(hand.indexOf(card) + 1);
            System.out.print(". " + card);
            System.out.print(", ");
        }
        System.out.println();
    }

    public String drawHand() {
        String handString = "";
        for (Card card : hand) {
            handString += card.drawCard(card);
            handString += "\n";
        }
        return handString;
    }

}
