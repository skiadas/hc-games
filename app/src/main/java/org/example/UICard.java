package org.example;

import core.Card;

import javax.swing.*;
import java.awt.*;
import java.util.WeakHashMap;

public class UICard extends JLabel {
    static Dimension TARGET_SIZE = new Dimension(100, 200);
    private static final WeakHashMap<UICard, Boolean> createdCards = new WeakHashMap<>();
    protected static final CardImageCache cardImageCache = new CardImageCache();

    static void setSizeFromHeight(int height) {
        int width = height * CardImageCache.CARD_WIDTH / CardImageCache.CARD_HEIGHT;
        TARGET_SIZE = new Dimension(width,height);
        cardImageCache.reset();
        for (UICard uiCard : createdCards.keySet()) {
            uiCard.setSize(TARGET_SIZE);
        }

    }

    {
         setSizeFromHeight(200);
    }


    private Card card;
    private boolean faceUp = true;

    public UICard(Card card) {
        setCard(card);
        setSize(TARGET_SIZE);
        createdCards.put(this, true);
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
            this.setIcon(cardImageCache.getIcon(this.card));
        else
            this.setIcon(cardImageCache.getBackIcon());
    }

}
