package org.example;

import core.Card;

import javax.swing.*;
import java.awt.Component;
import java.awt.Color;
import java.util.List;


public class UIWasteHand extends JPanel {
    UIWaste waste = new UIWaste();
    JPanel hand = UIFactory.getInstance().createHand();

    UIWasteHand() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Component leftPad = Box.createHorizontalStrut(10);
        Component midPad = Box.createHorizontalStrut(15);

        // FOR TESTING PURPOSES
        hand.setBorder(BorderFactory.createLineBorder(Color.RED));
        waste.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        add(leftPad);
        add(hand);
        add(midPad);
        add(waste);
    }

    void showInWaste(List<Card> cards) {
        waste.showCards(cards);
    }
}
