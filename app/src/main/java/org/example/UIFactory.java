package org.example;

import core.Card;

import java.awt.*;
import java.util.WeakHashMap;

public class UIFactory {
     private static final UIFactory instance = new UIFactory();

     static UIFactory getInstance() {
         return instance;
     }

    private final WeakHashMap<UICard, Boolean> createdCards = new WeakHashMap<>();

    private UIFactory() {
        setSizeFromHeight(200);
    }

    void setSizeFromHeight(int height) {
        Dimension targetSize = CardImageCache.computeSizeForHeight(height);
        CardImageCache.getInstance().setSize(targetSize);
        for (UICard uiCard : createdCards.keySet()) {
            uiCard.setSize(targetSize);
        }
    }

    public UICard createUICard(Card card) {
        UICard uiCard = new UICard(card);
        createdCards.put(uiCard, true);
        return uiCard;
    }
}
