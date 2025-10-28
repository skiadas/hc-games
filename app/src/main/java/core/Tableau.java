package core;

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
}
