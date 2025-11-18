package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UIWaste extends JPanel {
    JPanel waste = new JPanel();
    JPanel hand = new UIHand();

    UIWaste() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Component leftPad = Box.createHorizontalStrut(10);
        Component midPad = Box.createHorizontalStrut(15);
        waste.setLayout(new BoxLayout(waste, BoxLayout.X_AXIS));
        waste.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        hand.setLayout(new BoxLayout(hand, BoxLayout.X_AXIS));
//        hand.setBorder(BorderFactory.createLineBorder(Color.RED));

        add(leftPad);
        add(hand);
        add(midPad);
        add(waste);
    }

    void addToWaste(UICard card) {
        waste.add(card);
    }
}
