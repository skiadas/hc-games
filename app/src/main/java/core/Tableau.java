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
        return piles[location.getPile() - 1].look(location.getCard());
    }

    public boolean canAccept(TableauLocation location, List<Card> cards ) {
        return piles[location.getPile() - 1].canAccept(cards);
    }

    public boolean canPickUp(TableauLocation location) {
        return piles[location.getPile() - 1].isValidForPickUp(location.getCard());
    }

    public void pickUpAt(TableauLocation location) {
        piles[location.getPile() - 1].pickUp(location.getCard());
        piles[location.getPile() - 1].revealTopIfNeeded();

    }

    public void dropAt(int pile, List<Card> cards) {
        piles[pile - 1].drop(cards);
    }


}
