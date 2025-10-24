package core.locations;


import core.Hand;
import core.TableauPile;

public class TableauLocation implements Location {
    private final TableauPile[] piles;

    public TableauLocation(Hand deck) {
        piles = new TableauPile[7];

        for (int pile = 0; pile < 7; pile++) {
            piles[pile] = new TableauPile(deck.drawCards(pile + 1));
        }
    }

    public TableauLocation() {
        this(new Hand());
    }

}
