package core;

import core.locations.TableauLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Spliterator;

import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

class TableauTests {

    private Tableau tableau;

    @BeforeEach
    void setUp() {
        List<Card> cards = List.of(
                new Card(1, HEARTS),
                new Card(10, SPADES), new Card(6, DIAMONDS),
                new Card(1, HEARTS), new Card(1, HEARTS), new Card(5, HEARTS),
                new Card(8, HEARTS), new Card(2, HEARTS), new Card(3, HEARTS), new Card(5, CLUBS),
                new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS),
                new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS),
                new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS), new Card(1, HEARTS)
        );
        tableau = new Tableau(new Hand(cards));
    }

    @Test
    void canCreateEmptyTableau() {
        Tableau tableau = new Tableau();
    }

    @Test
    void canCreateTableauFromStandardDeck() {
        Tableau tableau = new Tableau(Hand.shuffledFullDeck());
    }

    @Test
    void canGetCardsAtAnIndexFromASpecificPile() {
        assertEquals(List.of(new Card(5, HEARTS)), tableau.lookAt(new TableauLocation(3, 3)));
    }

    @Test
    void canCheckThatAMoveIsValid() {
        List<Card> cards = tableau.lookAt(new TableauLocation(3, 3));
        assertFalse(tableau.canAccept(new TableauLocation(2, 0), cards));
        cards = tableau.lookAt(new TableauLocation(4, 4));
        assertTrue(tableau.canAccept(new TableauLocation(2, 0), cards));
    }

    @Test
    void canCheckIfCardsCanBePickedUp() {
        assertFalse(tableau.canPickUp(new TableauLocation(2, 3)));
        assertFalse(tableau.canPickUp(new TableauLocation(2, 1)));
        assertTrue(tableau.canPickUp(new TableauLocation(2, 2)));
    }

    @Test
    void canPickUpCards() {
        List<Card> cards = tableau.pickUpAt(new TableauLocation(4, 4));
        assertEquals(List.of(new Card(5, CLUBS)), cards);
        assertEquals(List.of(new Card(8, HEARTS), new Card(2, HEARTS), new Card(3, HEARTS)), tableau.lookAt(new TableauLocation(4, 1)));
    }

    @Test
    void canDropCards() {
        List<Card> cards = List.of(new Card(5, SPADES), new Card(4, HEARTS));
        tableau.dropAt(new TableauLocation(2, 2), cards);
        assertEquals(List.of(new Card(10, SPADES),
                    new Card(6, DIAMONDS),
                    new Card(5, SPADES),
                    new Card(4, HEARTS)),
                tableau.lookAt(new TableauLocation(2, 1)));
    }

}