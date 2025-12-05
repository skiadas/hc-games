package org.example;

import core.Card;
import core.Game;
import core.Suit;
import core.Tableau;
import core.locations.TableauLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.GridBagConstraints.BOTH;

public class UIMain extends JPanel {
    private final MouseListener listener;
    private UIWasteHand waste;
    private UIFoundation foundation;
    private UITableau tableau;

    UIMain(MouseListener listener) {
        super(null);
        this.listener = listener;
        UIFactory uiFactory = UIFactory.getInstance();
        makeEntries();
        UICard card1 = uiFactory.createUICard(new Card(2, Suit.DIAMONDS));
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ctrl A"), "selectAll");

        getActionMap().put("selectAll", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                card1.setCard(new Card(4, Suit.HEARTS));
            }
        });
        Card card1Card = new Card(5, Suit.CLUBS);
        Game game = new Game();
        game.initializeGameWithShuffledHand();
        for (int i = 0; i < 7; i++) { // Don't hardcode 7?
            TableauLocation loc = new TableauLocation(i + 1, 1);
            Tableau tableauPiles = game.getTableauPiles();
            List<Card> cards = tableauPiles.lookAt(loc);
            UIFactory factory = UIFactory.getInstance();
            List<UICard> uiCards = new ArrayList<>();
            for (int j = 0; j < cards.size(); j++) {
                TableauLocation location = new TableauLocation(i + 1, j + 1);
                UICard uiCard = factory.createUICard(cards.get(i));
                uiCard.setLocation(location);
                if (!tableauPiles.canPickUp(location)) {
                    uiCard.flip();
                }
                uiCards.add(uiCard);
            }
            tableau.addCards(loc, uiCards);
        }
        waste.showInWaste(List.of(card1Card));
    }

    private void makeEntries() {
        waste = new UIWasteHand();
        foundation = new UIFoundation();
        tableau = new UITableau();
        arrangeItems();
        // Next three are for testing purposes
        waste.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tableau.setBorder(BorderFactory.createLineBorder(Color.PINK));
        foundation.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    private void arrangeItems() {
        final double Y_RATIO = 0.1;
        final double X_RATIO = 0.3;
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
