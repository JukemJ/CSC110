
public class Card implements Comparable<Card> {

    String rank;
    String suit;

    Card(String suit, String rank) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit + "-" + rank;
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
        //return "C:\\Users\\Chris\\Documents\\skool\\CSC110\\Group Project\\images\\2-C.png";
        return "images/" + toString() + ".png";
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

    // public String drawCard(Card card) {
    //     String cardString = "";
    //     char rank = card.getRank().charAt(0);
    //     char suit = card.getSuit().charAt(0);

    //     cardString += " ----- \n";
    //     cardString += "|";
    //     cardString += rank;

    //     if (rank == '1')
    //         cardString += "0   |\n"; // fix for 10 not displaying correctly
    //     else
    //         cardString += "    |\n";

    //     cardString += "|  ";
    //     cardString += suit;
    //     cardString += "  |\n";

    //     if (rank == '1')
    //         cardString += "|   10"; // fix for 10 not displaying correctly
    //     else {
    //         cardString += "|    ";
    //         cardString += rank;
    //     }

    //     cardString += "|";
    //     cardString += "\n ----- ";

    //     return cardString;
    // }

}
