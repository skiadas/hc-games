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

    @Test
    void getRankMethodWorks() {
        Card card1 = new Card(5, HEARTS);
        assertEquals(5, card1.getRank());
        Card card2 = new Card(12, SPADES);
        assertEquals(12, card2.getRank());
    }

    @Test
    void canCreateCardsFromStrings() {
        assertCardEqualsString(5, HEARTS, "5H");
        assertCardEqualsString(13, CLUBS, "13C");
        assertCardEqualsString(13, CLUBS, "KC");
        assertCardEqualsString(6, SPADES, "6S");
        assertCardEqualsString(2, CLUBS, "2C");
        assertCardEqualsString(8, SPADES, "8S");
        assertCardEqualsString(9, SPADES, "9S");
        assertCardEqualsString(10, HEARTS, "10H");
        assertCardEqualsString(12, DIAMONDS, "QD");
        assertCardEqualsString(3, HEARTS, "3H");
        assertCardEqualsString(11, DIAMONDS, "11D");
        assertCardEqualsString(11, HEARTS, "JH");
        assertCardEqualsString(11, SPADES, "11S");
        assertCardEqualsString(11, CLUBS, "11C");
        assertCardEqualsString(11, CLUBS, "JC");
        assertCardEqualsString(1, DIAMONDS, "1D");
        assertCardEqualsString(1, CLUBS, "A♧");
        assertCardEqualsString(12, DIAMONDS, "12♢");
        assertCardEqualsString(6, HEARTS, "6♡");
        assertCardEqualsString(10, SPADES, "10♤");
        assertCardEqualsString(10, CLUBS, "10♣");
        assertCardEqualsString(11, DIAMONDS, "11♦");
        assertCardEqualsString(12, HEARTS, "Q♥");
        assertCardEqualsString(13, SPADES, "K♠");

    }

    private void assertCardEqualsString(int rank, Suit suit, String string) {
        assertEquals(new Card(rank, suit), Card.from(string));
    }
}
