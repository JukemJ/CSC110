// Chris Dixon

import java.util.ArrayList;

public class PokerGame {

    ArrayList<Card> communityCards = new ArrayList<Card>();
    //Player user = new Player();
    //Player computer = new Player();
    Deck deck = new Deck(5);

    public PokerGame() {
        deck.shuffle();
        // for(int i = 0; i < 5; i++){
        //     user.drawCard(deck.draw());
        // }
    }

    public void displayWelcomeMessage(){
        System.out.println("Welcome to Poker!");
        System.out.println("You will be dealt a hand of 5 cards.");
        System.out.println("The goal is to get the best hand possible.");
        System.out.println("After your hand is dealt you can choose which cards to keep. Your other cards will be replaced.");
        System.out.println("Good luck!");
        System.out.println();
        
    }

    //Hand type checks

    private boolean pair(ArrayList<Card> hand){
        hand.sort((a,b) -> a.getValue() - b.getValue());
        for(int i = 0; i < hand.size() - 1; i++){
            if(hand.get(i).getValue() == hand.get(i + 1).getValue()){
                return true;
            }
        }
        return false;
    }

    private boolean twoPair(ArrayList<Card> hand){
        hand.sort((a,b) -> a.getValue() - b.getValue());
        int pairCount = 0;
        for(int i = 0; i < hand.size() - 1; i++){
            if(hand.get(i).getValue() == hand.get(i + 1).getValue()){
                pairCount++;
            }
        }
        if(pairCount == 2){
            return true;
        }
        return false;
    }

    private boolean fullHouse(ArrayList<Card> hand){
        hand.sort((a,b) -> a.getValue() - b.getValue());
        if(hand.get(0).getValue() == hand.get(1).getValue() && hand.get(0).getValue() == hand.get(2).getValue() && hand.get(3).getValue() == hand.get(4).getValue()){
            return true;
        }
        if(hand.get(0).getValue() == hand.get(1).getValue() && hand.get(2).getValue() == hand.get(3).getValue() && hand.get(2).getValue() == hand.get(4).getValue()){
            return true;
        }
        return false;
    }

    private boolean fourOfAKind(ArrayList<Card> hand){
        hand.sort((a,b) -> a.getValue() - b.getValue());
        for(int i = 0; i < hand.size() - 3; i++){
            if(hand.get(i).getValue() == hand.get(i + 1).getValue() && hand.get(i).getValue() == hand.get(i + 2).getValue() && hand.get(i).getValue() == hand.get(i + 3).getValue()){
                return true;
            }
        }
        return false;
    }

    private boolean threeOfAKind(ArrayList<Card> hand){
        hand.sort((a,b) -> a.getValue() - b.getValue());
        for(int i = 0; i < hand.size() - 2; i++){
            if(hand.get(i).getValue() == hand.get(i + 1).getValue() && hand.get(i).getValue() == hand.get(i + 2).getValue()){
                return true;
            }
        }
        return false;
    }

    private boolean straight(ArrayList<Card> hand){
        hand.sort((a,b) -> a.getValue() - b.getValue());
        for(int i = 0; i < hand.size() - 1; i++){
            if(hand.get(i).getValue() + 1 != hand.get(i + 1).getValue()){
                return false;
            }
        }
        return true;
    }

    private boolean flush(ArrayList<Card> hand){
        String suit = hand.get(0).getSuit();
        for(int i = 1; i < hand.size(); i++){
            if(hand.get(i).getSuit() != suit){
                return false;
            }
        }
        return true;
    }

    private boolean straightFlush(ArrayList<Card> hand){
        if(flush(hand) && straight(hand)){
            return true;
        }
        return false;
    }

    public String getHandType(){
        if(straightFlush(user.getHand())){
            return "Straight Flush";
        } else if(fourOfAKind(user.getHand())){
            return "Four of a Kind";
        } else if(fullHouse(user.getHand())){
            return "Full House";
        } else if(flush(user.getHand())){
            return "Flush";
        } else if(straight(user.getHand())){
            return "Straight";
        } else if(threeOfAKind(user.getHand())){
            return "Three of a Kind";
        } else if(twoPair(user.getHand())){
            return "Two Pair";
        } else if(pair(user.getHand())){
            return "Pair";
        } else {
            return "High Card";
        }
    }
}
