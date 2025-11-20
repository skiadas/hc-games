package org.example;

import javax.swing.*;

public class UIHand extends JPanel {
    boolean hasCards;
    EmptyCard card;

    UIHand() {
        super();
        setLayout(new BoxLayout(this,  BoxLayout.X_AXIS));
        card = UIFactory.getInstance().createEmptyCard("⟳");
        add(card);
        setHasCards(true);
    }

    public void setHasCards(boolean hasCards) {
        this.hasCards = hasCards;
        card.setShowBack(this.hasCards);
    }


}
