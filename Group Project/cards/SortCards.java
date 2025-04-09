
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SortCards {

    Deck deck = new Deck(52);

    Hand hand = new Hand(13); // bridge hand

    //window
    int boardWidth = 1900;
    int boardHeight = 600;

    int cardWidth = 138; //ratio should 1/1.4
    int cardHeight = 220;

    JFrame frame = new JFrame("Black Jack");
    JPanel gamePanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                //draw player's hand
                for (int i = 0; i < hand.last; i++) {
                    Card card = hand.getCard(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 120, cardWidth, cardHeight, null);
                }

//                g.setFont(new Font("Arial", Font.PLAIN, 30));
//                g.setColor(Color.white);
//                g.drawString(message, 220, 250);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton sortButton = new JButton("Deal");
    JButton shuffleHandButton = new JButton("Shuffle Hand");
    JButton shuffleDeckButton = new JButton("Shuffle Deck");

    SortCards() {

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        sortButton.setFocusable(false);
        buttonPanel.add(sortButton);
        shuffleHandButton.setFocusable(false);
        buttonPanel.add(shuffleHandButton);
        shuffleDeckButton.setFocusable(false);
        buttonPanel.add(shuffleDeckButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        sortButton.addActionListener((ActionEvent e) -> {
            // THIS IS WRONG. ADD CALL TO APPROPRIATE SORT
            System.out.println("sortButton.() needs to be edited");
            dealHand();
            gamePanel.repaint();
        });

        shuffleHandButton.addActionListener(e -> {
            shuffle(hand);
        });

        shuffleDeckButton.addActionListener(e -> {
            hand = null;              // get rid of old hand
            hand = new Hand(13); // create new empty hand
            shuffle(deck);
            dealHand();
        });

    }

    public void startGame() {
        deck.buildDeck();
        shuffle(deck); // used for debugging
        dealHand();
//        gamePanel.repaint(); 
        shuffle(hand); // used for debugging

        System.out.println("PLAYER: ");
        System.out.println(hand);
    }

    public void dealHand() { // This takes the 1st 13 cards from deck and puts it in hand.
        for (int i = 0; i < 13; i++) {
            Card card = deck.dealCard();
            hand.addCard(card);
        }
        gamePanel.repaint();
    }

    public void shuffle(Deck d) {
        d.shuffle();
        gamePanel.repaint();
    }
}
