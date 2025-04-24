// Chris Dixon

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PokerGame {

    ArrayList<Card> communityCards = new ArrayList<Card>();

    Deck deck = new Deck(52);
    ArrayList<Card> hand = new ArrayList<Card>();

    // setting window variables
    int boardWidth = 1200;
    int boardHeight = 600;

    int cardWidth = 138; // ratio should 1/1.4
    int cardHeight = 220;

    JFrame frame = new JFrame("Poker!");
    JPanel gamePanel = new JPanel() {
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

                // g.setFont(new Font("Arial", Font.PLAIN, 30));
                // g.setColor(Color.white);
                // g.drawString(message, 220, 250);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton dealButton = new JButton("Deal");
    JButton shuffleHandButton = new JButton("Shuffle Hand");
    JButton shuffleDeckButton = new JButton("Shuffle Deck");

    public PokerGame() {
        deck.buildDeck();
        deck.shuffle();
        dealHand();
        //displayWelcomeMessage();
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
        hand.clear();
        for (int i = 0; i < 5; i++) {
            hand.add(deck.dealCard());
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

    // drawing the game window
    // window

    void drawWindow() {

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        dealButton.setFocusable(false);
        buttonPanel.add(dealButton);
        shuffleHandButton.setFocusable(false);
        buttonPanel.add(shuffleHandButton);
        shuffleDeckButton.setFocusable(false);
        buttonPanel.add(shuffleDeckButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        dealButton.addActionListener((ActionEvent e) -> {
            // THIS IS WRONG. ADD CALL TO APPROPRIATE SORT
            //System.out.println("sortButton.() needs to be edited");
            dealHand();
            System.out.println(getHandType());
            gamePanel.repaint();
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
