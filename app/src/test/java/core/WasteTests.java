package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.HeaderTransformer;

import java.util.ArrayList;
import java.util.List;

import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

public class WasteTests {
    @Test
    void WastePileInitializedToEmpty() {
        Waste waste = new Waste();
        assertTrue(waste.isEmpty());
    }

    @Test
    void canAddCardsFromHand() {
        Waste waste = new Waste();
        List<Card> handCard = new ArrayList<>();
        handCard.add(new Card(1, HEARTS));
        handCard.add(new Card(2, HEARTS));
        Hand hand = new Hand(handCard);
        waste.add(hand.drawCard());
        assertEquals(1, waste.getPileSize());
    }

    @Test
    void removeTopCard() {
        Waste waste = new Waste();
        Card ace = new Card(1, HEARTS);
        waste.add(ace);
        Card topCard = waste.remove();
        assertTrue(waste.isEmpty());
    }

    @Test
    void returnProperTopCard() {
        Waste waste = new Waste();
        Card ace = new Card(1, HEARTS);
        waste.add(ace);
        Card topCard = waste.remove();
        assertEquals(ace, topCard);
    }

    @Test
    void RuntimeExceptionCheck() {
        Waste waste = new Waste();
        Card ace = new Card(1, HEARTS);
        RuntimeException exception = assertThrows(RuntimeException.class, waste::remove);
        assertEquals("Cannot remove from an empty pile.", exception.getMessage());
    }

    @Test
    void emptyWastePileAndReturnReversedList() {
        Waste waste = new Waste();
        Card aceHeart = new Card(1, HEARTS);
        waste.add(aceHeart);
        Card twoHeart = new Card(2, HEARTS);
        waste.add(twoHeart);
        List<Card> returnPile = waste.returnToHand();
        assertTrue(waste.isEmpty());
        List<Card> reversePile = new ArrayList<>();
        reversePile.add(twoHeart);
        reversePile.add(aceHeart);
        assertEquals(reversePile, returnPile);
    }
}
