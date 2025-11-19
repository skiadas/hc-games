package org.example;

import javax.swing.*;

public class UIHand extends JPanel {
    boolean hasCards = false;
    EmptyCard card;

    UIHand() {
        super(null);
        card = UIFactory.getInstance().createEmptyCard("⟳");
        add(card);
        setHasCards(true);
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
        card.setShowBack(this.hasCards);
    }


}
