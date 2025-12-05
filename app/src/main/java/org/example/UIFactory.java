package org.example;

import core.Card;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.WeakHashMap;

public class UIFactory {
     private static final UIFactory instance = new UIFactory();

     static UIFactory getInstance() {
         return instance;
     }

    private final WeakHashMap<Updatable, Boolean> updatables = new WeakHashMap<>();
    private final List<CreationListener<Component>> listeners = new ArrayList<>();
    private UIFactory() {
        setSizeFromHeight(200);
    }

    public void addListener(CreationListener<Component> listener) {
        listeners.add(listener);
    }

    public void removeListener(CreationListener<Component> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(Component uiCard) {
        for (CreationListener<Component> listener : listeners) {
            listener.handleCreated(uiCard);
        }
    }

    void setSizeFromHeight(int height) {
        Dimension scaledSize = CardImageCache.computeSizeForHeight(height);
        CardImageCache.getInstance().setScaledSize(scaledSize);
        for (Updatable u : updatables.keySet()) {
            u.setCardSize(scaledSize);
        }
    }

    public UICard createUICard(Card card) {
        UICard uiCard = new UICard(card);
        updatables.put(uiCard, true);
        notifyListeners(uiCard);
        return uiCard;
    }

    public EmptyCard createEmptyCard(String text) {
        EmptyCard emptyCard = new EmptyCard(text);
        updatables.put(emptyCard, true);
        notifyListeners(emptyCard);
        return emptyCard;
    }

    public UIHand createHand() {
        UIHand uiHand = new UIHand();
        updatables.put(uiHand, true);
        return uiHand;
    }
}
