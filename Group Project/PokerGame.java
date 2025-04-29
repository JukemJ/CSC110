// Chris Dixon

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokerGame {

    ArrayList<Card> communityCards = new ArrayList<Card>();

    Deck deck = new Deck(52);
    ArrayList<Card> hand = new ArrayList<Card>();
    boolean[] selectedCards = {false,false,false,false,false};   //keeps track of which cards are selected to keep
    int playerScore = 0;
    String currentHandType = "None";

    //  colors
    Color backgroundColor = new Color(53, 101, 77); 

    // setting window variables
    int boardWidth = 1200;
    int boardHeight = 600;

    int cardWidth = 138; // ratio should 1/1.4
    int cardHeight = 220;

    JFrame frame = new JFrame("Poker!");

    JPanel scoreListPanel = new JPanel();                     // score list panel on the right side of screen
    
    JPanel scorePanel = new JPanel();                           //score panel top of screen
    JLabel scoreLabel = new JLabel("Score: 0");
    JLabel handTypeLabel = new JLabel("Current Hand: None");

    JPanel gamePanel = new JPanel() {                           // game panel in the middle of screen           
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                // draw player's hand
                for (int i = 0; i < 5; i++) {
                    Card card = hand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 120, cardWidth, cardHeight, null);
                }

                //  g.setFont(new Font("Arial", Font.PLAIN, 30));
                //  g.setColor(Color.white);
                //  g.drawString("TEST", 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    JPanel buttonPanel = new JPanel();                      // button panel at the bottom of screen                     
    JButton dealButton = new JButton("Deal");
    JToggleButton keepCard1 = new JToggleButton("Keep Card 1");
    JToggleButton keepCard2 = new JToggleButton("Keep Card 2");
    JToggleButton keepCard3 = new JToggleButton("Keep Card 3");
    JToggleButton keepCard4 = new JToggleButton("Keep Card 4");
    JToggleButton keepCard5 = new JToggleButton("Keep Card 5");
    // JButton shuffleHandButton = new JButton("Shuffle Hand");
    // JButton shuffleDeckButton = new JButton("Shuffle Deck");

    public PokerGame() {
        deck.buildDeck();
        deck.shuffle();
        dealHand();
        displayWelcomeMessage();
        drawWindow();
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome to Poker!");
        System.out.println("You will be dealt a hand of 5 cards.");
        System.out.println("The goal is to get the best hand possible.");
        System.out.println(
                "After your hand is dealt you can choose which cards to keep. Your other cards will be replaced.");
        System.out.println("Good luck!");
        System.out.println();

    }

    public void dealHand() {
        if(hand.size() == 0){
            for (int i = 0; i < 5; i++) {
                hand.add(deck.dealCard());
            }
        }
        else {
            for (int i = 0; i < 5; i++) {
                if (!selectedCards[i]) {
                    hand.set(i, deck.dealCard());
                }
            }
        }
        
        gamePanel.repaint();
    }

    // Hand type checks

    private boolean pair(ArrayList<Card> hand) {
        hand.sort((a, b) -> a.getPokerValue() - b.getPokerValue());
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getPokerValue() == hand.get(i + 1).getPokerValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean twoPair(ArrayList<Card> hand) {
        hand.sort((a, b) -> a.getPokerValue() - b.getPokerValue());
        int pairCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getPokerValue() == hand.get(i + 1).getPokerValue()) {
                pairCount++;
            }
        }
        if (pairCount == 2) {
            return true;
        }
        return false;
    }

    private boolean fullHouse(ArrayList<Card> hand) {
        hand.sort((a, b) -> a.getPokerValue() - b.getPokerValue());
        if (hand.get(0).getPokerValue() == hand.get(1).getPokerValue()
                && hand.get(0).getPokerValue() == hand.get(2).getPokerValue()
                && hand.get(3).getPokerValue() == hand.get(4).getPokerValue()) {
            return true;
        }
        if (hand.get(0).getPokerValue() == hand.get(1).getPokerValue()
                && hand.get(2).getPokerValue() == hand.get(3).getPokerValue()
                && hand.get(2).getPokerValue() == hand.get(4).getPokerValue()) {
            return true;
        }
        return false;
    }

    private boolean fourOfAKind(ArrayList<Card> hand) {
        hand.sort((a, b) -> a.getPokerValue() - b.getPokerValue());
        for (int i = 0; i < hand.size() - 3; i++) {
            if (hand.get(i).getPokerValue() == hand.get(i + 1).getPokerValue()
                    && hand.get(i).getPokerValue() == hand.get(i + 2).getPokerValue()
                    && hand.get(i).getPokerValue() == hand.get(i + 3).getPokerValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean threeOfAKind(ArrayList<Card> hand) {
        hand.sort((a, b) -> a.getPokerValue() - b.getPokerValue());
        for (int i = 0; i < hand.size() - 2; i++) {
            if (hand.get(i).getPokerValue() == hand.get(i + 1).getPokerValue()
                    && hand.get(i).getPokerValue() == hand.get(i + 2).getPokerValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean straight(ArrayList<Card> hand) {
        hand.sort((a, b) -> a.getPokerValue() - b.getPokerValue());
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getPokerValue() + 1 != hand.get(i + 1).getPokerValue()) {
                return false;
            }
        }
        return true;
    }

    private boolean flush(ArrayList<Card> hand) {
        String suit = hand.get(0).getSuit();
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    private boolean straightFlush(ArrayList<Card> hand) {
        if (flush(hand) && straight(hand)) {
            return true;
        }
        return false;
    }

    public String getHandType() {
        if (straightFlush(hand)) {
            return "Straight Flush";
        } else if (fourOfAKind(hand)) {
            return "Four of a Kind";
        } else if (fullHouse(hand)) {
            return "Full House";
        } else if (flush(hand)) {
            return "Flush";
        } else if (straight(hand)) {
            return "Straight";
        } else if (threeOfAKind(hand)) {
            return "Three of a Kind";
        } else if (twoPair(hand)) {
            return "Two Pair";
        } else if (pair(hand)) {
            return "Pair";
        } else {
            return "High Card";
        }
    }

    public void updateScore() {
        currentHandType = getHandType();
        
        // Award points based on hand type
        switch(currentHandType) {
            case "Straight Flush": playerScore += 50; break;
            case "Four of a Kind": playerScore += 25; break;
            case "Full House": playerScore += 15; break;
            case "Flush": playerScore += 10; break;
            case "Straight": playerScore += 8; break;
            case "Three of a Kind": playerScore += 5; break;
            case "Two Pair": playerScore += 3; break;
            case "Pair": playerScore += 1; break;
            default: break; // No points for high card
        }
        
        // Update the score display
        scoreLabel.setText("Score: " + playerScore);
        handTypeLabel.setText("Hand: " + currentHandType);
    }

    // drawing the game window
    // window

    void drawWindow() {

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scorePanel.setBackground(new Color(40, 80, 60));
        scorePanel.setPreferredSize(new Dimension(boardWidth, 50));
        scorePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        handTypeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        handTypeLabel.setForeground(Color.WHITE);

        scorePanel.add(scoreLabel);
        scorePanel.add(handTypeLabel);

        frame.add(scorePanel, BorderLayout.NORTH);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(backgroundColor);
        frame.add(gamePanel);

        dealButton.setFocusable(false);
        buttonPanel.add(dealButton);
        // shuffleHandButton.setFocusable(false);
        // buttonPanel.add(shuffleHandButton);
        // shuffleDeckButton.setFocusable(false);
        // buttonPanel.add(shuffleDeckButton);
        keepCard1.setFocusable(false);
        buttonPanel.add(keepCard1);
        keepCard2.setFocusable(false);
        buttonPanel.add(keepCard2);
        keepCard3.setFocusable(false);
        buttonPanel.add(keepCard3);
        keepCard4.setFocusable(false);
        buttonPanel.add(keepCard4);
        keepCard5.setFocusable(false);
        buttonPanel.add(keepCard5);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        dealButton.addActionListener((ActionEvent e) -> {
            dealHand();
            updateScore();
            gamePanel.repaint();
        });

        keepCard1.addActionListener((ActionEvent e) -> {
            selectedCards[0] = keepCard1.isSelected();
            System.out.println("Card 1 selected: " + selectedCards[0]);
        });

        keepCard2.addActionListener((ActionEvent e) -> {
            selectedCards[1] = keepCard2.isSelected();
            System.out.println("Card 2 selected: " + selectedCards[1]);
        });

        keepCard3.addActionListener((ActionEvent e) -> {
            selectedCards[2] = keepCard3.isSelected();
            System.out.println("Card 3 selected: " + selectedCards[2]);
        });

        keepCard4.addActionListener((ActionEvent e) -> {
            selectedCards[3] = keepCard4.isSelected();
            System.out.println("Card 4 selected: " + selectedCards[3]);
        });

        keepCard5.addActionListener((ActionEvent e) -> {
            selectedCards[4] = keepCard5.isSelected();
            System.out.println("Card 5 selected: " + selectedCards[4]);
        });

        // //these buttons should be added for the games that need them -CD
        // shuffleHandButton.addActionListener(e -> {
        //     shuffle(hand);
        // });

        // shuffleDeckButton.addActionListener(e -> {
        //     hand = null;              // get rid of old hand
        //     hand = new Hand(13); // create new empty hand
        //     shuffle(deck);
        //     dealHand();
        // });
    }
}
