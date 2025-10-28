package core;

import core.locations.TableauLocation;

import java.util.List;

public class Tableau {
    private final TableauPile[] piles;

    public Tableau(Hand deck) {
        piles = new TableauPile[7];

        for (int pile = 0; pile < 7; pile++) {
            piles[pile] = new TableauPile(deck.drawCards(pile + 1));
        }
    }

    public Tableau() {
        this(new Hand());
    }

    public List<Card> lookAt(TableauLocation location) {
        return piles[getPileAt(location)].look(location.getCard());
    }

    private int getPileAt(TableauLocation location) {
        return location.getPile() - 1;
    }

    public boolean canAccept(TableauLocation location, List<Card> cards ) {
        return piles[getPileAt(location)].canAccept(cards);
    }

    public boolean canPickUp(TableauLocation location) {
        return piles[getPileAt(location)].isValidForPickUp(location.getCard());
    }

    public List<Card> pickUpAt(TableauLocation location) {
        List<Card> cards = piles[getPileAt(location)].pickUp(location.getCard());
        piles[getPileAt(location)].revealTopIfNeeded();
        return cards;
    }

    public void dropAt(TableauLocation tableauLocation, List<Card> cards) {
        piles[getPileAt(tableauLocation)].drop(cards);
    }


}
