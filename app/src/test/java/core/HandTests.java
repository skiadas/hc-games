package core;

import org.junit.jupiter.api.Test;

import java.util.List;

import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

public class HandTests {
    @Test
    public void checkForPremadeDeckBeingEqual() {
        Hand hand1 = Hand.initFullDeck();
        Hand hand2 = Hand.initFullDeck();
        assertEquals(hand1.getCards(), hand2.getCards());
    }

    @Test
    public void checkForShuffleMethodWorking() {
        Hand hand1 = Hand.initFullDeck();
        Hand hand2 = Hand.initFullDeck();
        hand2.shuffle();
        assertNotEquals(hand1.getCards(), hand2.getCards());
    }
    @Test
    public void checkForShuffledDeck(){
        Hand hand1 = Hand.shuffledFullDeck();
        Hand hand2 = Hand.initFullDeck();
        assertNotEquals(hand1.getCards(), hand2.getCards());
    }
    @Test
    public void checkDrawCard() {
        List<Card> someCards = shortListOfCards();
        Hand hand = new Hand(someCards);
        assertEquals(new Card(1, Suit.HEARTS), hand.drawCard());
    }

    @Test
    public void checkDrawCards() {
        Hand hand = new Hand(shortListOfCards());
        Hand hand2 = new Hand(shortListOfCards().subList(0,4));
        assertEquals(hand2.getCards(), hand.drawCards(4));
    }

    private static List<Card> shortListOfCards() {
        return List.of(
            new Card(1, HEARTS),
            new Card(5, SPADES),
            new Card(7, DIAMONDS),
            new Card(3, SPADES),
            new Card(2, HEARTS));
    }


}
