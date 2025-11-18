package org.example;

import javax.swing.*;

public class UIHand extends JPanel {
    boolean hasCards = false;
    EmptyCard card;

    UIHand() {
        super(null);
        card = UIFactory.getInstance().createEmptyCard("⟳");
        add(card);
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
        updateCard();
    }

    private void updateCard() {

    }


}
