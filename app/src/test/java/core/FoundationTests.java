package core;

import org.junit.jupiter.api.Test;

import java.util.List;
import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

public class FoundationTests {
    @Test
    void initialFoundationPilesAreEmpty() {
        Foundation emptyFoundation = new Foundation();
        assertEquals(true, emptyFoundation.isEmpty(HEARTS));
        assertEquals(true, emptyFoundation.isEmpty(SPADES));
        assertEquals(true, emptyFoundation.isEmpty(DIAMONDS));
        assertEquals(true, emptyFoundation.isEmpty(CLUBS));
    }

    @Test
    void canAddAces() {
        Foundation foundation = new Foundation();
        Card card = new Card(1, HEARTS);
        foundation.add(card);
        assertEquals(false, foundation.isEmpty(HEARTS));
        assertEquals(true, foundation.isEmpty(SPADES));
        assertEquals(true, foundation.isEmpty(DIAMONDS));
        assertEquals(true, foundation.isEmpty(CLUBS));
    }

    @Test
    void canOnlyAddAcesToEmptyPiles() {
        Foundation foundation = new Foundation();
        Card card = new Card(2, HEARTS);
        foundation.add(card);
        assertEquals(true, foundation.isEmpty(HEARTS));
    }
}
