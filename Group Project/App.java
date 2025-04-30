// Chris Dixon
// Ary Sanchez

import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) throws Exception {
        
        PokerGame pokerGame = new PokerGame();              //starts new game
        
        System.out.println("\n\n" + pokerGame.getHandType());
        
        // SortCards deck = new SortCards();
        // deck.startGame();
    }
}
