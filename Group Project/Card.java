
public class Card implements Comparable<Card> {

    String rank;
    String suit;
    int pokerValue; // int value; // 2-10 = 2-10, J=11, Q=12, K=13, A=14

    
    Card(String suit, String rank) {
        this.rank = rank;
        this.suit = suit;
        
        switch (rank) {     // 2-10, J, Q, K, A       //just for pokerGame -CD      
            case "A":
                this.pokerValue = 14;
                break;
            case "K":
                this.pokerValue = 13;
                break;
            case "Q":
                this.pokerValue = 12;
                break;
            case "J":
                this.pokerValue = 11;
                break;
            default:
                //System.out.println(Integer.parseInt(rank));
                pokerValue = Integer.parseInt(rank);
        }
    }

    @Override
    public String toString() {
        return rank + "-" + suit;
    }

    public int getPokerValue() {
        return pokerValue;
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

    public String getSuit() {
        return suit;
    }

    public boolean isAce() {
        return rank.equals("A");
    }

    public String getImagePath() {
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
