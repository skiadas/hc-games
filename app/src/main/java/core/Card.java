package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Card {
    private static final int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    private final int rank;
    private final Suit suit;

    public Card(int rank, Suit suit) {
        boolean validRank = IntStream.of(ranks).anyMatch(r -> r == rank);

        if (validRank) this.rank = rank;
        else throw new IllegalStateException("Unexpected value for rank: " + rank);

        if (suit == null) throw new IllegalStateException("Unexpected value for suit: " + suit);
        else this.suit = suit;
    }

    public boolean isSameColorAs(Card toCompare) {
        return this.suit.getColor() == toCompare.suit.getColor();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                '}';
    }
}
