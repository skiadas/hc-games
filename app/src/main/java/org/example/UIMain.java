package org.example;

import core.Card;
import core.Suit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.GridBagConstraints.BOTH;

public class UIMain extends JPanel {
    private final MouseListener listener;
    private UIWaste waste;
    private JPanel foundation;
    private JPanel tableau;

    UIMain(MouseListener listener) {
        super(null);
        this.listener = listener;
        UIFactory uiFactory = UIFactory.getInstance();
        addMouseListener(listener);
        makeEntries();
        UICard card1 = uiFactory.createUICard(new Card(2, Suit.DIAMONDS));
        card1.addMouseListener(listener);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ctrl A"), "selectAll");

        getActionMap().put("selectAll", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                card1.setCard(new Card(4, Suit.HEARTS));
            }
        });
        UICard card2 = uiFactory.createUICard(new Card(5, Suit.CLUBS));
        card2.addMouseListener(listener);
//        card2.setPosition(30, 30);
        tableau.add(card1);
        waste.addToWaste(card2);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension size = waste.getSize();
                uiFactory.setSizeFromHeight((int) (size.height * 0.8));
            }
        });
    }

    private void makeEntries() {
        waste = new UIWaste();
        foundation = new JPanel();
        tableau = new JPanel();
        arrangeItems();
        // Next three are for testing purposes
        waste.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableau.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        foundation.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void arrangeItems() {
        final double Y_RATIO = 0.25;
        final double X_RATIO = 0.1;
        final int INITIAL_WIDTH = 1200;
        final int INITIAL_HEIGHT = 800;
        waste.setSize((int) (INITIAL_WIDTH * X_RATIO), (int) (INITIAL_HEIGHT * Y_RATIO));
        foundation.setSize((int) (INITIAL_WIDTH * (1-X_RATIO)), (int) (INITIAL_HEIGHT * Y_RATIO));
        tableau.setSize(INITIAL_WIDTH, (int) (INITIAL_HEIGHT * (1 - Y_RATIO)));

        setLayout(new GridBagLayout());
        GridBagConstraints wasteConstraints = makeConstraints(0, 0, X_RATIO, Y_RATIO);
        GridBagConstraints foundationConstraints = makeConstraints(1, 0, 1 - X_RATIO, Y_RATIO);
        GridBagConstraints tableauConstraints = makeConstraints(0, 1, 1, 1 - Y_RATIO);
        tableauConstraints.gridwidth = 2;

        add(waste, wasteConstraints);
        add(foundation, foundationConstraints);
        add(tableau, tableauConstraints);
    }

    private static GridBagConstraints makeConstraints(int xPlace, int yPlace, double xRatio, double yRatio) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = xPlace;
        c.gridy = yPlace;
        c.weightx = xRatio;
        c.weighty = yRatio;
        c.fill = BOTH;
        return c;
    }
}
