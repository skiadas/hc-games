package org.example;

import core.Card;
import core.Suit;
import core.locations.FoundationLocation;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class UIFoundation extends JPanel {
    Map<FoundationLocation, UICard> locations = new HashMap<>();
    UIFoundation() {
        for (Suit suit : Suit.values()) {
            FoundationLocation location = new FoundationLocation(suit);
            UICard uiCard = UIFactory.getInstance().createUICard(new Card(1, suit));
            locations.put(location, uiCard);
            uiCard.setLocation(location);
            uiCard.flip();
            add(uiCard);
        }

    }
}
