package org.example;

import core.locations.TableauLocation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UITableau extends JPanel {
    List<UILayeredCards> piles;

    UITableau() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        piles = new ArrayList<>();
        piles.add(UILayeredCards.vertical());
        add(piles.get(0));
    }

    void addCards(TableauLocation loc, List<UICard> cards) {
        int pileIndex = loc.getPile();  // 1-indexed
        int card = loc.getCard();  // 1-indexed
        while (piles.size() < pileIndex) {
            UILayeredCards column = UILayeredCards.vertical();
            piles.add(column);
            add(column);  // code duplication
        }
        UILayeredCards pile = piles.get(pileIndex - 1);
        // TODO: Deal with different card levels

        pile.setCards(cards);
    }
}
