package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Card {
    private static final int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private static final Pattern regex = Pattern.compile("(\\d{1,2}|[AJQK])([HDSC♡♥♢♦♧♣♤♠])");

    private final int rank;
    private final Suit suit;

    public Card(int rank, Suit suit) {
        boolean validRank = IntStream.of(ranks).anyMatch(r -> r == rank);

        if (validRank) this.rank = rank;
        else throw new IllegalStateException("Unexpected value for rank: " + rank);

        if (suit == null) throw new IllegalStateException("Unexpected value for suit: " + suit);
        else this.suit = suit;
    }

    public static Card from(String string) {
        if (string == null ) throw new RuntimeException("Cannot create a Card from a null string");

        Matcher matcher = regex.matcher(string);
        if(!matcher.matches()) throw new RuntimeException(string + " does not constitute a valid card");


        String rankGroup = matcher.group(1);
        int rank = switch (rankGroup) {
            case "A" -> 1;
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            default -> Integer.parseInt(rankGroup);
        };

        String suitGroup = matcher.group(2);
        Suit suit = switch (suitGroup) {
            case "H", "♡", "♥" -> Suit.HEARTS;
            case "D", "♢", "♦" -> Suit.DIAMONDS;
            case "C", "♧", "♣" -> Suit.CLUBS;
            case "S", "♤", "♠" -> Suit.SPADES;
            default -> throw new RuntimeException("Unknown suit" + suitGroup);
        };

        return new Card(rank, suit);

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

    public Suit getSuit() {
        return suit;
    }
    public int getRank() {
        return rank;
    }
}
