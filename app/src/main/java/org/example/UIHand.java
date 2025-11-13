package org.example;

import core.Card;

public class UIHand extends UICard {
    boolean hasCards = false;

    UIHand() {
        super(null);
    }

    void setCard(Card card) {

    }

    protected void updateIcon() {
        if (hasCards) {
            setIcon(CardImageCache.getInstance().getBackIcon());
            setText(null);
        }
        else {
            setIcon(null);
            setText("Hello");
        }
    }
}
