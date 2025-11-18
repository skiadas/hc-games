package org.example;

import core.Card;

import java.awt.*;
import java.util.WeakHashMap;

public class UIFactory {
     private static final UIFactory instance = new UIFactory();

     static UIFactory getInstance() {
         return instance;
     }

    private final WeakHashMap<Updatable, Boolean> updatables = new WeakHashMap<>();

    private UIFactory() {
        setSizeFromHeight(200);
    }

    void setSizeFromHeight(int height) {
        Dimension scaledSize = CardImageCache.computeSizeForHeight(height);
        CardImageCache.getInstance().setScaledSize(scaledSize);
        for (Updatable u : updatables.keySet()) {
            u.setSize(scaledSize);
        }
    }

    public UICard createUICard(Card card) {
        UICard uiCard = new UICard(card);
        updatables.put(uiCard, true);
        return uiCard;
    }

    public EmptyCard createEmptyCard(String text) {
        EmptyCard emptyCard = new EmptyCard(text);
        updatables.put(emptyCard, true);
        return emptyCard;
    }
}
