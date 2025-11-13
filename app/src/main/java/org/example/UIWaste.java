package org.example;

import javax.swing.*;
import java.awt.*;

public class UIWaste extends JPanel {
    JPanel waste = new JPanel();
    UIHand hand = new UIHand();

    UIWaste() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        Component leftPad = Box.createHorizontalStrut(10);
        Component midPad = Box.createHorizontalStrut(15);
        waste.setLayout(new BoxLayout(waste, BoxLayout.X_AXIS));
        waste.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        add(leftPad);
        add(hand);
        add(midPad);
        add(waste);
    }

    void addToWaste(UICard card) {
        waste.add(card);
    }
}
