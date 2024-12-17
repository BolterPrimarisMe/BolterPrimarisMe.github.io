 
    import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a single card
class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

// Represents the deck of cards
class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

// Main class for the card game
public class CardGame {
    public static void main(String[] args) {
        Deck deck = new Deck();

        int player1Score = 0;
        int player2Score = 0;

        System.out.println("Starting the game of War!");

        while (!deck.isEmpty()) {
            Card player1Card = deck.drawCard();
            Card player2Card = deck.drawCard();

            if (player1Card == null || player2Card == null) {
                break;
            }

            System.out.println("Player 1 draws: " + player1Card);
            System.out.println("Player 2 draws: " + player2Card);

            if (player1Card.getValue() > player2Card.getValue()) {
                System.out.println("Player 1 wins the round!\n");
                player1Score++;
            } else if (player1Card.getValue() < player2Card.getValue()) {
                System.out.println("Player 2 wins the round!\n");
                player2Score++;
            } else {
                System.out.println("It's a tie!\n");
            }
        }

        System.out.println("Game over!");
        System.out.println("Player 1 Score: " + player1Score);
        System.out.println("Player 2 Score: " + player2Score);

        if (player1Score > player2Score) {
            System.out.println("Player 1 wins the game!");
        } else if (player1Score < player2Score) {
            System.out.println("Player 2 wins the game!");
        } else {
            System.out.println("The game is a tie!");
        }
    }
}

