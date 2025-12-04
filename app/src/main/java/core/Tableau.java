package core;

import core.locations.TableauLocation;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static Tableau from(InputStream stream) {
        Scanner input = new Scanner(stream);
        Tableau tableau = new Tableau();
        int visibilityMarker = 0;

        for (int pile = 0; pile < 7 && input.hasNext(); pile++) {
            String pileString = input.nextLine();
            String[] cardStrings = pileString.split(" ");
            List<Card> cards = new ArrayList<>();
            for (int i = 0; i < cardStrings.length; i++) {
                if (cardStrings[i].contains("*")) {
                    visibilityMarker = i + 1;
                    cardStrings[i] = cardStrings[i].substring(1);
                }
                cards.add(Card.from(cardStrings[i]));
            }
            tableau.piles[pile] = new TableauPile(cards, (cards.size() - visibilityMarker) + 1);
        }

        return tableau;
    }

    public void writeTo(PrintStream stream) {
        for (int i = 0; i < 7; i++) {
            piles[i].writeTo(stream);
            if (i != 6) {
                stream.print("\n");
            }
        }
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
