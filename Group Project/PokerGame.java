// Chris Dixon

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokerGame {

    ArrayList<Card> communityCards = new ArrayList<Card>();

    Deck deck = new Deck(52);
    ArrayList<Card> hand = new ArrayList<Card>();
    boolean[] selectedCards = { false, false, false, false, false }; // keeps track of which cards are selected to keep
    String[] handTypes = { "High Card", "Pair", "Two Pair", "Three of a Kind", "Straight", "Flush", "Full House",
            "Four of a Kind", "Straight Flush" , "Royal Flush"};
    int[] handTypeScores = {0, 1, 2, 3, 4, 6, 9, 25, 50, 250};
    int playerScore = 0;
    String currentHandType = "None";
    Font gameFont = new Font("Arial", Font.BOLD, 20);
    int turnsRemaining = 3; // number of turns remaining
    boolean gameOver = false; // flag to check if game is over

    // colors
    Color backgroundColor = new Color(53, 101, 77);

    // setting window variables
    int boardWidth = 1200;
    int boardHeight = 600;

    int cardWidth = 138; // ratio should 1/1.4
    int cardHeight = 220;

    JFrame frame = new JFrame("Video Poker!");

    JPanel scoreListPanel = new JPanel(); // score list panel on the right side of screen

    JPanel scorePanel = new JPanel(); // score panel top of screen
    JLabel scoreLabel = new JLabel("Score: 0");
    JLabel handTypeLabel = new JLabel("Current Hand: None");
    JLabel cardsRemainingLabel = new JLabel("Cards Remaining: " + deck.getRemainingCards());
    JLabel handsRemainingLabel = new JLabel("Hands Remaining: " + turnsRemaining);

    JPanel gamePanel = new JPanel() { // game panel in the middle of screen
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                // draw player's hand
                for (int i = 0; i < 5; i++) {
                    Card card = hand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    int yPosition = selectedCards[i] ? 100 : 120; // Move up by 20 pixels if selected
                    g.drawImage(cardImg, 20 + (cardWidth + 5) * i, yPosition, cardWidth, cardHeight, null);
                    //g.drawImage(cardImg, 20 + (cardWidth + 5) * i, yPosition, 10, 14, null);
                }

                // g.setFont(new Font("Arial", Font.PLAIN, 30));
                // g.setColor(Color.white);
                // g.drawString("TEST", 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    JPanel buttonPanel = new JPanel(); // button panel at the bottom of screen
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

        // updateScore();
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

        String message = "Welcome to Poker!\n"
                + "You will intially be dealt a hand of 5 cards.\n"
                + "The goal is to get the best hand possible in " + turnsRemaining + " hands\n"
                + "After your hand is dealt, you can choose which cards to keep and press the Deal button.\n"
                + "Your other cards will be replaced.\n"
                + "Good luck!";

        // Display the message in a JOptionPane
        JOptionPane.showMessageDialog(frame, message, "Welcome", JOptionPane.INFORMATION_MESSAGE);

    }

    public void dealHand() {
        if (hand.size() == 0) {
            for (int i = 0; i < 5; i++) {
                hand.add(deck.dealCard());
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (!selectedCards[i]) {
                    hand.set(i, deck.dealCard());
                }
                else selectedCards[i] = false;                  // reset selected cards for next hand
            }
        }
                                                        // reset toggle buttons for next hand
        keepCard1.setSelected(false);
        keepCard2.setSelected(false);
        keepCard3.setSelected(false);
        keepCard4.setSelected(false);   
        keepCard5.setSelected(false);
        turnsRemaining--;
        if(turnsRemaining == 0) {
            gameOver = true;
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

    public int getHandScore() {

        currentHandType = getHandType();

        // Award points based on hand type
        switch (currentHandType) {
            case "Straight Flush":
                return 50;
                
            case "Four of a Kind":
                return 25;
            case "Full House":
                return 9;
            case "Flush":
                return 6;
            case "Straight":
                return 4;
            case "Three of a Kind":
                return 3;
            case "Two Pair":
                return 2;
            case "Pair":
                return 1;
            default:
                return 0; // No points for high card
        }
    }

    public void gameOver() {
        playerScore += getHandScore();
        scoreLabel.setText("Score: " + playerScore);
        String message = "You scored: " + getHandScore() + "\n"
                + "Your final hand was: " + getHandType() + "\n"
                + "Play Again?";
        int newGameChoice = JOptionPane.showConfirmDialog(frame, message, "Game Over", JOptionPane.YES_NO_OPTION);
        if(newGameChoice == JOptionPane.YES_OPTION) {
            deck = new Deck(52);
            deck.buildDeck();
            deck.shuffle();
            hand.clear();
            dealHand();
            turnsRemaining = 3;
            gameOver = false;
        } else {
            System.exit(0); // Close the application
        }
        //System.exit(0); // Close the application
    }

    // drawing the game window
    void drawWindow() {

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawScorePanel();
        frame.add(scorePanel, BorderLayout.NORTH);

        drawScoreListPanel();
        frame.add(scoreListPanel, BorderLayout.EAST);

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

        // GAME CONTROLS BUTTONS

        dealButton.addActionListener((ActionEvent e) -> {
            dealHand();
            handTypeLabel.setText("Hand: " + getHandType());
            cardsRemainingLabel.setText("Cards Remaining: " + deck.getRemainingCards());
            handsRemainingLabel.setText("Hands Remaining: " + turnsRemaining);
            if(gameOver) gameOver();
            gamePanel.repaint();
        });

        keepCard1.addActionListener((ActionEvent e) -> {
            selectedCards[0] = keepCard1.isSelected();
            gamePanel.repaint();
            // System.out.println("Card 1 selected: " + selectedCards[0]);
        });

        keepCard2.addActionListener((ActionEvent e) -> {
            selectedCards[1] = keepCard2.isSelected();
            gamePanel.repaint();
            // System.out.println("Card 2 selected: " + selectedCards[1]);
        });

        keepCard3.addActionListener((ActionEvent e) -> {
            selectedCards[2] = keepCard3.isSelected();
            gamePanel.repaint();
            // System.out.println("Card 3 selected: " + selectedCards[2]);
        });

        keepCard4.addActionListener((ActionEvent e) -> {
            selectedCards[3] = keepCard4.isSelected();
            gamePanel.repaint();
            // System.out.println("Card 4 selected: " + selectedCards[3]);
        });

        keepCard5.addActionListener((ActionEvent e) -> {
            selectedCards[4] = keepCard5.isSelected();
            gamePanel.repaint();
            // System.out.println("Card 5 selected: " + selectedCards[4]);
        });

    }

    void drawScoreListPanel() {
        scoreListPanel.setBackground(new Color(40, 80, 60));
        scoreListPanel.setPreferredSize(new Dimension(300, boardHeight));

        // Set the layout to BoxLayout for vertical alignment
        scoreListPanel.setLayout(new BoxLayout(scoreListPanel, BoxLayout.Y_AXIS));

        for (int i = handTypes.length - 1; i >= 0; i--) {
            JLabel handTypeLabel = new JLabel(handTypes[i] + ": " + handTypeScores[i]);
            handTypeLabel.setFont(gameFont);
            handTypeLabel.setForeground(Color.WHITE);
            handTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align labels to the left
            scoreListPanel.add(handTypeLabel);
            scoreListPanel.add(Box.createVerticalStrut(10)); // Add spacing between labels
        }
    }

    void drawScorePanel() {
        scorePanel.setBackground(new Color(40, 80, 60));
        scorePanel.setPreferredSize(new Dimension(boardWidth, 50));
        scorePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        scoreLabel.setFont(gameFont);
        scoreLabel.setForeground(Color.WHITE);
        cardsRemainingLabel.setFont(gameFont);
        cardsRemainingLabel.setForeground(Color.WHITE);
        handsRemainingLabel.setFont(gameFont);
        handsRemainingLabel.setForeground(Color.WHITE);
        handTypeLabel.setFont(gameFont);
        handTypeLabel.setForeground(Color.WHITE);

        scorePanel.add(scoreLabel);
        scorePanel.add(handTypeLabel);
        scorePanel.add(handsRemainingLabel);
        scorePanel.add(cardsRemainingLabel);
    }

}
