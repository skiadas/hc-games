package org.example;

import core.Card;
import core.Suit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class UIMain extends JPanel {
    private final MouseListener listener;

    UIMain(MouseListener listener) {
        super(null);
        this.listener = listener;
        addMouseListener(listener);
        makeEntries();
        UICard card1 = new UICard(new Card(2, Suit.DIAMONDS));
        card1.addMouseListener(listener);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ctrl A"), "selectAll");

        getActionMap().put("selectAll", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                card1.setCard(new Card(4, Suit.HEARTS));
            }
        });
        UICard card2 = new UICard(new Card(5, Suit.CLUBS));
        card2.addMouseListener(listener);
        card2.setPosition(30, 30);
        add(card1);
        add(card2);

    }

    private void makeEntries() {
        setLayout(new GridBagLayout());
        JPanel waste = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        this.add(waste, c);
    }
}
