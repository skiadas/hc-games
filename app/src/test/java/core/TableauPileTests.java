package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

public class TableauPileTests {

    private TableauPile pile;

    @BeforeEach
    void Setup() {
        List<Card> cards = new ArrayList<>(List.of(new Card(5, HEARTS),
                new Card(9, SPADES),
                new Card(1, HEARTS),
                new Card(7, CLUBS)));
        pile = new TableauPile(cards);
    }

    @Test
    void canCreateEmptyPileOfCards() {
        pile = new TableauPile();
    }

    @Test
    void canInitializeTableauPileFromListOfCards() {
        assertEquals(List.of(new Card(5, HEARTS),
                        new Card(9, SPADES),
                        new Card(1, HEARTS),
                        new Card(7, CLUBS))
                ,pile.look(1));
    }

    @Test
    void onlyTopCardIsVisibleOnCreationOfTableauPile() {
        assertTrue(pile.isValidForPickUp(4));
        assertFalse(pile.isValidForPickUp(2));

        pile = new TableauPile(List.of(new Card(5, HEARTS),
                new Card(9, SPADES),
                new Card(1, HEARTS),
                new Card(7, CLUBS),
                new Card(8, CLUBS)));


        assertTrue(pile.isValidForPickUp(5));
        assertFalse(pile.isValidForPickUp(4));
    }

    @Test
    void isValidForPickUpReturnsFalseOnEmptyPile() {
        pile = new TableauPile();
        assertFalse(pile.isValidForPickUp(0));
        assertFalse(pile.isValidForPickUp(1));
    }

    @Test
    void pickUpReturnsCorrectListOfCards() {
        assertEquals(List.of(new Card(7, CLUBS)), pile.pickUp(4));
    }

    @Test
    void pickedUpCardsAreRemovedFromPile() {
        pile.pickUp(4);
        assertEquals(List.of(new Card(5, HEARTS),
                             new Card(9, SPADES),
                             new Card(1, HEARTS))
                             ,pile.look(1));
    }

    @Test
    void pickedUpCardsAreReturnedCorrectly() {
        assertEquals(List.of(new Card(7, CLUBS)), pile.pickUp(4));
    }

    @Test
    void pickUpCannotBeCalledOnAnEmptyPile() {
        pile = new TableauPile();
        for (int i = 0; i < 7; i++) {
            int finalI = i;
            assertThrows(RuntimeException.class, () -> pile.pickUp(finalI));
        }
    }

    @Test
    void revealingTopCardMakesTopCardVisible() {
        assertTrue(pile.isValidForPickUp(4));
        pile.pickUp(4);
        assertFalse(pile.isValidForPickUp(3));
        pile.revealTopIfNeeded();
        assertFalse(pile.isValidForPickUp(4));
        assertTrue(pile.isValidForPickUp(3));
    }

    @Test
    void dropAddsCardsToThePile() {
        pile.drop(List.of(new Card(6, HEARTS), new Card(5, SPADES)));
        assertEquals(List.of(new Card(5, HEARTS),
                new Card(9, SPADES),
                new Card(1, HEARTS),
                new Card(7, CLUBS),
                new Card(6, HEARTS),
                new Card(5, SPADES)), pile.look(1));
    }

    @Test
    void dropThrowsWhenDroppingEmptyListOfCards() {
        assertThrows(RuntimeException.class, () -> pile.drop(new ArrayList<>()));
    }

    @Test
    void canAcceptCorrectlyFollowsSolitaireRules() {
        assertTrue(pile.canAccept(List.of(new Card(6, HEARTS))));
        assertFalse(pile.canAccept(List.of(new Card(6, SPADES))));
        assertFalse(pile.canAccept(List.of(new Card(5, DIAMONDS))));
        assertFalse(pile.canAccept(List.of(new Card(7, CLUBS))));
    }

}
