package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTests {
    @Test
    public void checkForPremadeDeckBeingEqual() {
        Hand hand1 = Hand.initFullDeck();
        Hand hand2 = Hand.initFullDeck();
        assertEquals(hand1.cards, hand2.cards);
    }

    @Test
    public void checkForShuffleMethodWorking() {
        Hand hand1 = Hand.initFullDeck();
        Hand hand2 = Hand.initFullDeck();
        hand2.shuffle();
        assertNotEquals(hand1.cards, hand2.cards);
    }

}
