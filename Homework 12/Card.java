//Chris Dixon

import java.util.ArrayList;

public class Card {
    private String suit;
    private char rank;
    static int cardCount = 0;

    public Card(String suit, String rank) {

        ArrayList<String> validSuits = new ArrayList<String>(java.util.Arrays.asList("Spades", "Hearts", "Diamonds", "Clubs"));
        if(validSuits.contains(suit)){
                this.suit = suit;
        }
        else {
            throw new IllegalArgumentException("Invalid suit: " + suit);
        }

        ArrayList<String> validRanks = new ArrayList<String>(java.util.Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"));
        if (validRanks.contains(rank)) {
            this.rank = rank.charAt(0);
        } else {
            throw new IllegalArgumentException("Invalid rank: " + rank);
        }
    }


    public String getSuit() {
        return suit;
    }


    public String getRank() {
        switch (rank) {
            case '2':
                return "2";
            case '3':
                return "3";
            case '4':
                return "4";
            case '5':
                return "5";
            case '6':
                return "6";
            case '7':
                return "7";
            case '8':
                return "8";
            case '9':
                return "9";
            case '1':
                return "10";
            case 'J':
                return "Jack";
            case 'Q':
                return "Queen";
            case 'K':
                return "King";
            case 'A':
                return "Ace";
            default:
                return null;
        }
    }

    public static int getCardCount() {
        return cardCount;
    }

    @Override
    public String toString() {
        return "Suit: " + suit + ", Rank: " + rank;
    }

    public int compareTo(Card other) {
        ArrayList<String> rankOrder = new ArrayList<>(java.util.Arrays.asList(
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"
        ));


        int thisRankIndex = rankOrder.indexOf(this.getRank());
        int otherRankIndex = rankOrder.indexOf(other.getRank());

        if(thisRankIndex == otherRankIndex) return 0;
        return thisRankIndex < otherRankIndex ? 1 : -1;
    }
}