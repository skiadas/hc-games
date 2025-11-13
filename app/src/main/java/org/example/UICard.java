package org.example;

import core.Card;

import javax.swing.*;
import java.awt.*;

public class UICard extends JLabel {

    private Card card;
    private boolean faceUp = true;

    UICard(Card card) {
        setCard(card);
    }

    public void setSize(Dimension d) {
        super.setSize(d);
        updateIcon();
    }

    public void flip() {
        faceUp = !faceUp;
        updateIcon();
    }

    void setCard(Card card) {
        this.card = card;
        updateIcon();
    }

    protected void updateIcon() {
        if (faceUp)
            this.setIcon(CardImageCache.getInstance().getIcon(this.card));
        else
            this.setIcon(CardImageCache.getInstance().getBackIcon());
    }

}
