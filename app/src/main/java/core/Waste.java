package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Waste {
    private List<Card> pile;

    public Waste() {
        pile = new ArrayList<>();
    }

    public boolean isEmpty() {
        return pile.isEmpty();
    }

    public void add(Card card) {
        pile.add(card);
    }

    public int getPileSize() {
        return pile.size();
    }

    public Card remove() {
        if (!pile.isEmpty()) {
            return pile.get(pile.size() - 1);
        }
        throw new RuntimeException("Cannot remove from an empty pile.");
    }

    public List<Card> returnToHand() {
        List<Card> pileCopy = new ArrayList<>(pile);
        reversePile(pileCopy);
        pile.clear();
        return pileCopy;
    }

    private void reversePile(List<Card> copyPile) {
        Collections.reverse(copyPile);
    }
}
