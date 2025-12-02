package core;

import core.actions.Action;
import core.actions.DeselectAction;
import core.actions.ExitAction;
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
            Location location = se.getFromLocation();

            if (currentSelectedLocation == location) {
                resetSelection();
                return;
            }
            if (location instanceof HandLocation) {
                attemptWasteToHand();
                attemptHandToWaste();
                resetSelection();
                return;
            }
            if (currentSelectedLocation == null) {
                attemptSelect(location);
            } else {
                attemptMoveTo(location);
            }
        } else if (action instanceof DeselectAction) {
            resetSelection();
        } else if (action instanceof ExitAction) {
            // presenter instructions to EXIT
        }
    }

    private void resetSelection() {
        currentSelectedLocation = null;
        // presenter instructions

    }

    private void attemptHandToWaste() {
        if (game.canMoveHandToWaste()) {
            game.handToWaste();
            // presenter instructions
        }
    }

    private void attemptWasteToHand() {
        if (game.canMoveWasteToHand()) {
            game.WasteToHand();
            // presenter instructions
        }
    }

    private void attemptMoveTo(Location location) {
        if (game.canDropAt(location, game.getCardsAt(currentSelectedLocation))) {
            game.dropAt(location, game.pickUpAt(currentSelectedLocation));
            // presenter instructions

        }
    }

    private void attemptSelect(Location location) {
        if (game.canPickUpAt(location)) {
            currentSelectedLocation = location;
            // presenter instructions
        }
    }
}

