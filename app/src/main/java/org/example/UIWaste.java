package org.example;

import core.Card;
import core.locations.Location;
import core.locations.WasteLocation;

import javax.swing.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class UIWaste extends JPanel implements Locateable {
    UILayeredCards uiCards = UILayeredCards.horizontal();
    private final Location location = new WasteLocation();

    public UIWaste() {
        super(null);
//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(uiCards);
    }

    public void showCards(List<Card> cards) {
        UIFactory factory = UIFactory.getInstance();
        uiCards.setCards(cards.stream().map(factory::createUICard).toList());
        updateCards();
    }

    private void updateCards() {
    }

    public Location getGameLocation() {
        return location;
    }
}
