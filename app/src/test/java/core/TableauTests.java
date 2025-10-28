package core;

import org.junit.jupiter.api.Test;

class TableauTests {
    @Test
    void canCreateEmptyTableau() {
        Tableau tableau = new Tableau();
    }

    @Test
    void canCreateTableauFromStandardDeck() {
        Tableau tableau = new Tableau(Hand.shuffledFullDeck());
    }

    @Test
    void canReturnCardSelectionWithoutRemovingSelectedCards() {

    }

}