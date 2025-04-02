// Chris Dixon

public class Card {

    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        switch (rank) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "Jack":
                return 11;
            case "Queen":
                return 12;
            case "King":
                return 13;
            case "Ace":
                return 14;
        }
        return 0;
    }

    public String drawCard(Card card) {
        String cardString = "";
        char rank = card.getRank().charAt(0);
        char suit = card.getSuit().charAt(0);

        cardString += " ----- \n";
        cardString += "|";
        cardString += rank;

        if (rank == '1')
            cardString += "0   |\n"; // fix for 10 not displaying correctly
        else
            cardString += "    |\n";

        cardString += "|  ";
        cardString += suit;
        cardString += "  |\n";

        if (rank == '1')
            cardString += "|   10"; // fix for 10 not displaying correctly
        else {
            cardString += "|    ";
            cardString += rank;
        }

        cardString += "|";
        cardString += "\n ----- ";

        return cardString;
    }

    public String toString() {
        return rank + " of " + suit + "s";
    }
}