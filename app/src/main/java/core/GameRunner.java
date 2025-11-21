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
            Location location = se.getFromLocation();
            if (location instanceof HandLocation) {
                if (game.canMoveWasteToHand()) {
                    //move
                }
            }

            if (currentSelectedLocation == location) {
                currentSelectedLocation = null;
            } else if (currentSelectedLocation == null) {
                attemptSelect(location);
            } else {
                attemptMoveTo(location);
            }
        }
    }

    private void attemptMoveTo(Location location) {
        if (game.canDropAt(location, game.getCardsAt(currentSelectedLocation))) {
            game.dropAt(location, game.pickUpAt(currentSelectedLocation));
        }
    }

    private void attemptSelect(Location location) {
        if (game.canPickUpAt(location)) {
            currentSelectedLocation = location;
        }
    }
}

