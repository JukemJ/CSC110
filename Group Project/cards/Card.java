
public class Card implements Comparable<Card> {

    String rank;
    String suit;

    Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + "-" + suit;
    }

    public int getRank() {
        if ("AJQK".contains(rank)) { //A J Q K
            if (rank.equals("A")) {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(rank); //2-10
    }

    public boolean isAce() {
        return rank.equals("A");
    }

    public String getImagePath() {
        return "./cards/" + toString() + ".png";
    }

    @Override
    public int compareTo(Card c) {
        if (this.getRank() < c.getRank()) {
            return -1;
        } else if (this.getRank() > c.getRank()) {
            return 1;
        } else {
            return 0;
        }
    }

}
