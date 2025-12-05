package org.example;

import core.locations.HandLocation;
import core.locations.Location;

import javax.swing.*;
import java.awt.*;

public class UIHand extends JPanel implements Locateable, Updatable {
    boolean hasCards;
    EmptyCard card;
    private final Location location = new HandLocation();

    UIHand() {
        super();
        setLayout(new BoxLayout(this,  BoxLayout.X_AXIS));
        card = UIFactory.getInstance().createEmptyCard("⟳");
        add(card);
        setHasCards(false);
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
        card.setShowBack(this.hasCards);
    }


    public Location getGameLocation() {
        return location;
    }

    public void setCardSize(Dimension d) {
        setSize(d);
        setMinimumSize(d);
    }

    public void updateIcon() {

    }
}
