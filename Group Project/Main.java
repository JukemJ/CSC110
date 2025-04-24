// Chris Dixon

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println(); // line break

        // Variables

        Scanner input = new Scanner(System.in);
        PokerGame game = new PokerGame();
        Player user = game.user;
        ArrayList<Card> hand = new ArrayList<Card>();
        int numberOfRounds = 3;

        // Game menu

        game.displayWelcomeMessage();
        System.out.println("1. New Game");
        System.out.println("2. Exit");
        System.out.print("Please choose: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 2:
                input.close();
                System.exit(0);
                break;

            default:
                break;
        }

        System.out.println();

        // Game loop

        for (int i = 0; i < numberOfRounds; i++) {
            while (hand.size() < 5) {
                hand.add(game.deck.draw());
            }
            user.setHand(hand);
            user.showHand();

            System.out.println(user.drawHand());

            if (i == numberOfRounds - 1) {
                System.out.print("Your final hand: " + game.getHandType());
                break;
            }

            System.out.println("You currently have: " + game.getHandType());
            System.out.print("Enter the numbers of the cards you want to keep. Example: 1 2 3: ");
            String keep = input.nextLine();

            ArrayList<Card> keepCards = new ArrayList<Card>();
            for (char c : keep.toCharArray()) {
                if (c == ' ') {
                    continue;
                } else
                    keepCards.add(hand.get((int) c - 49));
            }
            user.setHand(keepCards);
            hand = user.getHand();

        }

        input.close();

    }
}
