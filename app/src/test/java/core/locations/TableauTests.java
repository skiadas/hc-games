package core.locations;

import core.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableauTests {
    @Test
    void canCreateEmptyTableau() {
        TableauLocation tableau = new TableauLocation();
    }

    @Test
    void canCreateTableauFromStandardDeck() {
        TableauLocation tableau = new TableauLocation(Hand.shuffledFullDeck());
    }

    @Test
    void canReturnCardSelectionWithoutRemovingSelectedCards() {

    }

}