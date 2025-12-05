package org.example;

import core.Card;
import core.locations.Location;

import javax.swing.*;
import java.awt.*;

public class UICard extends JLabel implements Updatable, Locateable {

    private Card card;
    private boolean faceUp = true;
    private Location location = null;

    UICard(Card card) {
        setCard(card);
    }

    void setLocation(Location location) {
        this.location = location;
    }

    public void setCardSize(Dimension d) {
        super.setSize(d);
        updateIcon();
    }

    public void flip() {
        faceUp = !faceUp;
        updateIcon();
    }

    public void setVisible() {
        faceUp = true;
        updateIcon();
    }

    public void setHidden() {
        faceUp = true;
        updateIcon();
    }
    void setCard(Card card) {
        this.card = card;
        updateIcon();
    }

    public void updateIcon() {
        if (faceUp)
            this.setIcon(CardImageCache.getInstance().getIcon(this.card));
        else
            this.setIcon(CardImageCache.getInstance().getBackIcon());
    }

    public Location getGameLocation() {
        return location;
    }
}
