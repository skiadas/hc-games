package core;

import com.google.common.collect.Table;
import core.locations.TableauLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static core.Suit.*;
import static org.junit.jupiter.api.Assertions.*;

class TableauTests {

    private Tableau tableau;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        InputStream stream = TableauTests.class.getClassLoader().getResourceAsStream("TableauTestsTableau.tbl");
        tableau = Tableau.from(stream);
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
        assertEquals(List.of(new Card(13, DIAMONDS)), tableau.lookAt(new TableauLocation(3, 3)));
    }

    @Test
    void canCheckThatAMoveIsValid() {
        List<Card> cards = tableau.lookAt(new TableauLocation(5, 5));
        assertFalse(tableau.canAccept(new TableauLocation(6, 6), cards));
        cards = tableau.lookAt(new TableauLocation(7, 7));
        assertTrue(tableau.canAccept(new TableauLocation(6, 6), cards));
    }

    @Test
    void canCheckIfCardsCanBePickedUp() {
        assertFalse(tableau.canPickUp(new TableauLocation(2, 3)));
        assertFalse(tableau.canPickUp(new TableauLocation(2, 1)));
        assertTrue(tableau.canPickUp(new TableauLocation(2, 2)));
    }

    @Test
    void PickingUpCardsRemovesThemFromTheirPile() {
        List<Card> cards = tableau.pickUpAt(new TableauLocation(4, 4));
        assertEquals(List.of(new Card(11, DIAMONDS)), cards);
        assertEquals(List.of(new Card(6, HEARTS), new Card(7, SPADES), new Card(12, HEARTS)), tableau.lookAt(new TableauLocation(4, 1)));
    }

    @Test
    void canDropCards() {
        List<Card> cards = List.of(new Card(5, DIAMONDS), new Card(4, CLUBS));
        tableau.dropAt(new TableauLocation(6, 6), cards);
        assertEquals(List.of(new Card(5, HEARTS),
                    new Card(1, DIAMONDS),
                    new Card(13, CLUBS),
                    new Card(10, SPADES),
                    new Card(1, CLUBS),
                    new Card(6, CLUBS),
                    new Card(5, DIAMONDS),
                    new Card(4, CLUBS)),
                tableau.lookAt(new TableauLocation(6, 1)));
    }

    @Test
    void canWriteATableauToAFile() {
        String tableauString = "*AS\n" +
                "4H *3D\n" +
                "8C 2D *KD\n" +
                "6H 7S QH *JD\n" +
                "6D JH JC 10C *5C\n" +
                "5H AD KC 10S AC *6C\n" +
                "9H 6S QC 10H 5S 8D *5H";
//        Tableau tableau = Tableau.from(new ByteArrayInputStream(tableauString.getBytes()));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        tableau.writeTo(stream);
        assertEquals(tableauString, output.toString());
    }
}