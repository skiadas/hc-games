package core;

import core.actions.Action;
import core.actions.SelectAction;
import core.locations.*;

/**
 * Class responsible for running a game. Eventually
 * should hold some Game instance
 */
public class GameRunner implements ActionHandler {
    private final Game game;
    Presenter presenter;
    private Location currentSelectedLocation;
    private Suit suit;

    public GameRunner(Presenter presenter) {
        this.presenter = presenter;
        this.game = new Game();
        this.currentSelectedLocation = null;
    }

    public void play() {

    }

    @Override
    public void handle(Action action) {
        if (action instanceof SelectAction) {
            SelectAction se = (SelectAction) action;
            if (currentSelectedLocation == null) {
                Location fromLocation = se.getFromLocation();
                if (fromLocation instanceof FoundationLocation) {
                    FoundationLocation fl = (FoundationLocation) fromLocation;
                    if (!game.getFoundationPiles().isEmpty(fl.suit)) {
                        game.getFoundationPiles().getTopCard(fl.suit);
                    }
                } else if (fromLocation instanceof Tableau) {
                    TableauLocation tl = (TableauLocation) fromLocation;
                    if (game.getTableauPiles().canPickUp(tl)) {
                        game.getTableauPiles().pickUpAt(tl);
                    }
                } else if (fromLocation instanceof WasteLocation) {
                    if (!game.getWastePile().isEmpty()) {
                        game.getWastePile().getTopCard();
                    }
                } else if (fromLocation instanceof HandLocation) {
                    if (game.getHand().isEmpty()) {
                        game.getWastePile().returnToHand();
                    } else {
                        game.getHand().drawCard();
                    }
                }
            } else {
                Location fromLocation = se.getFromLocation();
                if (fromLocation instanceof FoundationLocation) {

                } else if (fromLocation instanceof TableauLocation) {

                } else if (fromLocation instanceof WasteLocation) {

                } else if (fromLocation instanceof HandLocation) {

                }
            }
        }
    }
}
