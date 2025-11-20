package org.example;

import core.Card;

import javax.swing.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class UIWaste extends JPanel {
    List<UICard> uiCards = new ArrayList<>();
    JLayeredPane cardPane = new JLayeredPane();

    public UIWaste() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(cardPane);
    }

    public void showCards(List<Card> cards) {
        for (Card card : cards) {
            uiCards.add(UIFactory.getInstance().createUICard(card));
        }
        updateCards();
    }

    private void updateCards() {
    }
}
