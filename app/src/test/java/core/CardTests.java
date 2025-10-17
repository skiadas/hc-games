package core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardTests {
    @Test
    void canOnlyCreateCardsWithValidSuitAndRank() {
        assertDoesNotThrow(() -> new Card(10, Card.Suit.HEARTS));
        assertThrows(IllegalStateException.class, () -> new Card(14, Card.Suit.HEARTS));
        assertThrows(IllegalStateException.class, () -> new Card(0, null));
        assertThrows(IllegalStateException.class, () -> new Card(20, Card.Suit.SPADES));
        assertThrows(IllegalStateException.class, () -> new Card(13, null));
    }

    @Test
    void canGenerateAListOfAllPossibleCards() {
        List<Card> deck = Card.fullDeck();
        for (int i = 0; i < deck.size(); i++) {
            for (int j = i + 1; j < deck.size(); j++) {
                assertNotSame(deck.get(i), deck.get(j));
            }
        }
        assertEquals(52, deck.size());
    }

    @Test
    void getColorReturnsCorrectColors() {
        Card hearts = new Card(1, Card.Suit.HEARTS);
        Card spades = new Card(1, Card.Suit.SPADES);
        Card clubs = new Card(1, Card.Suit.CLUBS);
        Card diamonds = new Card(1, Card.Suit.DIAMONDS);

        assertEquals(Card.Color.RED, diamonds.getColor());
        assertEquals(Card.Color.BLACK, clubs.getColor());
        assertEquals(Card.Color.RED, hearts.getColor());
        assertEquals(Card.Color.BLACK, spades.getColor());
    }

    @Test
    void colorComparisonsAreCorrect() {
        Card hearts = new Card(1, Card.Suit.HEARTS);
        Card spades = new Card(1, Card.Suit.SPADES);
        Card clubs = new Card(1, Card.Suit.CLUBS);
        Card diamonds = new Card(1, Card.Suit.DIAMONDS);

        assertTrue(hearts.isSameColorAs(diamonds));
        assertTrue(spades.isSameColorAs(clubs));
        assertFalse(spades.isSameColorAs(diamonds));
        assertFalse(hearts.isSameColorAs(clubs));
    }

    @Test
    void canEvaluateRankEquality() {
        for (int rank : Card.ranks) {
            Card card1 = new Card(rank, Card.Suit.DIAMONDS);
            Card card2 = new Card(rank, Card.Suit.SPADES);
            assertTrue(card1.isSameRankAs(card2));
        }
    }

    @Test
    void canEvaluateDirectlyOutranking() {
        Card four = new Card(4, Card.Suit.HEARTS);
        Card three = new Card(3, Card.Suit.SPADES);
        assertTrue(four.ranksDirectlyAbove(three));
        assertTrue(three.ranksDirectlyBelow(four));

        Card five = new Card(5, Card.Suit.CLUBS);
        assertFalse(five.ranksDirectlyAbove(three));
        assertFalse(three.ranksDirectlyBelow(five));
    }
}
