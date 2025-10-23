package core;

import org.junit.jupiter.api.Test;

import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

public class FoundationTests {
    private Card heartAce = new Card(1, HEARTS);

    @Test
    void initialFoundationPilesAreEmpty() {
        Foundation emptyFoundation = new Foundation();
        arePilesEmpty(emptyFoundation);
    }

    private static void arePilesEmpty(Foundation emptyFoundation) {
        assertTrue(emptyFoundation.isEmpty(HEARTS));
        assertTrue(emptyFoundation.isEmpty(SPADES));
        assertTrue(emptyFoundation.isEmpty(DIAMONDS));
        assertTrue(emptyFoundation.isEmpty(CLUBS));
    }

    @Test
    void canAddAces() {
        Foundation foundation = new Foundation();
        foundation.add(heartAce);
        assertFalse(foundation.isEmpty(HEARTS));
        assertTrue(foundation.isEmpty(SPADES));
        assertTrue(foundation.isEmpty(DIAMONDS));
        assertTrue(foundation.isEmpty(CLUBS));
    }

    @Test
    void canOnlyAddAcesToEmptyPiles() {
        Foundation foundation = new Foundation();
        foundation.add(new Card(2, HEARTS));
        assertTrue(foundation.isEmpty(HEARTS));
        foundation.add(heartAce);
        assertFalse(foundation.isEmpty(HEARTS));
    }

    @Test
    void getCardCountInPile() {
        Foundation foundation = new Foundation();
        assertEquals(0, foundation.getPileSize(HEARTS));
        foundation.add(heartAce);
        assertEquals(1, foundation.getPileSize(HEARTS));
    }

    @Test
    void canOnlyAddNextAscendingCard() {
        Foundation foundation = new Foundation();
        foundation.add(heartAce);
        assertEquals(1, foundation.getPileSize(HEARTS));
        foundation.add(new Card(3, HEARTS));
        assertEquals(1, foundation.getPileSize(HEARTS));
        foundation.add(new Card(2, HEARTS));
        assertEquals(2, foundation.getPileSize(HEARTS));
        foundation.add(new Card(3, HEARTS));
        assertEquals(3, foundation.getPileSize(HEARTS));
    }

    @Test
    void cannotAddAnotherSuitOntoAnotherSuitsPile() {
        Foundation foundation = new Foundation();
        foundation.add(heartAce);
        foundation.add(new Card(2, DIAMONDS));
        assertEquals(1, foundation.getPileSize(HEARTS));
    }

    @Test
    void canRemoveTopCardFromPile() {
        Foundation foundation = new Foundation();
        foundation.add(heartAce);
        foundation.add(new Card(2, HEARTS));
        foundation.remove(HEARTS);
        assertEquals(1, foundation.getPileSize(HEARTS));
    }

    @Test
    void returnProperCardFromRemove() {
        Foundation foundation = new Foundation();
        Card twoOfHearts = new Card(2, HEARTS);
        foundation.add(heartAce);
        foundation.add(twoOfHearts);
        Card removedCard = foundation.remove(HEARTS);
        assertEquals(twoOfHearts, removedCard);
    }
}
