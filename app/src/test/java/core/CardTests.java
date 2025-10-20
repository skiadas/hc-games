package core;


import org.junit.jupiter.api.Test;

import java.util.List;
import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

public class CardTests {
    @Test
    void canOnlyCreateCardsWithValidSuitAndRank() {
        assertDoesNotThrow(() -> new Card(10, HEARTS));
        assertCardConstructorThrows(14, HEARTS);
        assertCardConstructorThrows(0, null);
        assertCardConstructorThrows(20, SPADES);
        assertCardConstructorThrows(13, null);
    }

    private void assertCardConstructorThrows(int rank, Suit suit) {
        assertThrows(IllegalStateException.class, () -> new Card(rank, suit));
    }

    @Test
    void canGenerateAListOfAllPossibleCards() {
        List<Card> deck = Card.fullDeck();
        for (int i = 0; i < deck.size(); i++) {
            for (int j = i + 1; j < deck.size(); j++) {
                assertNotEquals(deck.get(i), deck.get(j));
            }
        }
        assertEquals(52, deck.size());
    }

    @Test
    void colorComparisonsAreCorrect() {
        Card hearts = new Card(1, HEARTS);
        Card spades = new Card(1, SPADES);
        Card clubs = new Card(1, CLUBS);
        Card diamonds = new Card(1, DIAMONDS);

        assertTrue(hearts.isSameColorAs(diamonds));
        assertTrue(spades.isSameColorAs(clubs));
        assertFalse(spades.isSameColorAs(diamonds));
        assertFalse(hearts.isSameColorAs(clubs));
    }

    @Test
    void canEvaluateDirectlyOutranking() {
        Card four = new Card(4, HEARTS);
        Card three = new Card(3, SPADES);
        assertTrue(four.ranksDirectlyAbove(three));
        assertTrue(three.ranksDirectlyBelow(four));

        Card five = new Card(5, CLUBS);
        assertFalse(five.ranksDirectlyAbove(three));
        assertFalse(three.ranksDirectlyBelow(five));
    }

    @Test
    void equalsMethodWorks() {
        Card card1 = new Card(5, HEARTS);
        Card card2 = new Card(5, HEARTS);
        Card card3 = new Card(12, SPADES);
        Card card4 = new Card(12, SPADES);
        assertTrue(card1.equals(card2));
        assertTrue(card3.equals(card4));
        assertFalse(card1.equals(card3));
        assertFalse(card2.equals(card4));
    }
}
