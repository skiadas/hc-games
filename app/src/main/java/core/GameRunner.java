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

    public Location getCurrentSelectedLocation() {
        return currentSelectedLocation;
    }
    public void play() {

    }

    @Override
    public void handle(Action action) {
        if (action instanceof SelectAction) {
            SelectAction se = (SelectAction) action;
            // Hopefully use state and visitor patterns
            Location fromLocation = se.getFromLocation();
            if (currentSelectedLocation == null) {
                if (fromLocation instanceof FoundationLocation) {
                    FoundationLocation fl = (FoundationLocation) fromLocation;
                    if (game.canPickUpFromFoundation(fl.suit)) {
                        currentSelectedLocation = fl;
                        // Tell the presenter to tell ui to show the card
                    }
                } else if (fromLocation instanceof Tableau) {
                    TableauLocation tl = (TableauLocation) fromLocation;
                    if (game.canPickUpFromTableau(tl)) {
                        currentSelectedLocation = tl;
                        // Tell the presenter to tell ui to show the card
                    }
                } else if (fromLocation instanceof WasteLocation) {
                    WasteLocation wl = (WasteLocation) fromLocation;
                    if (game.canPickUpFromWaste()) {
                        currentSelectedLocation = wl;
                        // Tell the presenter to tell ui to show the card
                    }
                } else if (fromLocation instanceof HandLocation) {
                    HandLocation hl = (HandLocation) fromLocation;
                    if (game.canMoveHandToWaste()) {
                        currentSelectedLocation = hl;
                    }
                    // Tell the presenter to tell ui to show shuffle and return to hand
                }
            }

            if (currentSelectedLocation == fromLocation) {
                currentSelectedLocation = null;
            } else if (currentSelectedLocation instanceof FoundationLocation) {
                if (fromLocation instanceof FoundationLocation) {
                    FoundationLocation fl = (FoundationLocation) fromLocation;
                    if (game.canPickUpFromFoundation(fl.suit)) {
                    }
                } else if (fromLocation instanceof Tableau) {
                    TableauLocation tl = (TableauLocation) fromLocation;
                    if (game.canPickUpFromTableau(tl)) { // Need more functions in game
                    }
                } else if (fromLocation instanceof WasteLocation) {
                    WasteLocation wl = (WasteLocation) fromLocation;
                    if (game.canPickUpFromWaste()) {
                        currentSelectedLocation = wl;
                        // Tell the presenter to tell ui to show the card
                    }
                } else if (fromLocation instanceof HandLocation) {
                    HandLocation hl = (HandLocation) fromLocation;
                    if (game.canMoveHandToWaste()) {
                        currentSelectedLocation = hl;
                    }
                    // Tell the presenter to tell ui to show shuffle and return to hand

                }
            } else if (currentSelectedLocation instanceof TableauLocation) {

            } else if (currentSelectedLocation instanceof WasteLocation) {

            } else if (currentSelectedLocation instanceof HandLocation) {

            }
        }
    }


}
