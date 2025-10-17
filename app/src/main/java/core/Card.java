package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Card {
    public enum Suit { HEARTS, SPADES, DIAMONDS, CLUBS }
    public enum Color { RED, BLACK }
    public static final int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    private final int rank;
    private final Suit suit;
    public Card(int rank, Suit suit) {
        boolean validRank = IntStream.of(ranks).anyMatch(r -> r == rank);

        if (validRank) this.rank = rank;
        else throw new IllegalStateException("Unexpected value for rank: " + rank);

        if (suit == null) throw new IllegalStateException("Unexpected value for suit: " + suit);
        else this.suit = suit;
    }

    // Possibly make this private later, if all color logic is in the Card class
    public Color getColor() {
        return switch (suit) {
            case CLUBS, SPADES -> Color.BLACK;
            case HEARTS, DIAMONDS -> Color.RED;
        };
    }
    public boolean isSameColorAs(Card toCompare) {
        return this.getColor() == toCompare.getColor();
    }

    public boolean isSameRankAs(Card toCompare) {
        return this.rank == toCompare.rank;
    }

    public boolean ranksDirectlyAbove(Card toCompare) {
        return this.rank == toCompare.rank + 1;
    }

    public boolean ranksDirectlyBelow(Card toCompare) {
        return this.rank == toCompare.rank - 1;
    }

    public static List<Card> fullDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int v : ranks) {
                deck.add(new Card(v, suit));
            }
        }
        return deck;
    }

    public boolean equals(Card toCompare) {
        return suit == toCompare.suit && this.isSameRankAs(toCompare);
    }

}
