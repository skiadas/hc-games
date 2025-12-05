package core;

import core.actions.Action;
import core.actions.DeselectAction;
import core.actions.ExitAction;
import core.actions.SelectAction;
import core.locations.*;

import java.util.List;

/**
 * Class responsible for running a game. Eventually
 * should hold some Game instance
 */
public class GameRunner implements ActionHandler {
    private final Game game;
    Presenter presenter;
    private Location currentSelectedLocation = null;

    public GameRunner(Presenter presenter) {
        //TESTED
        this(presenter, new Game());
    }

    public GameRunner(Presenter presenter, Game game) {
        //TESTED
        this.presenter = presenter;
        this.game = game;
        // Send game state to presenter
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
            Location location = se.getLocation();

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
            presenter.exit();
        }
    }

    private void resetSelection() {
        presenter.setHighlightAt(currentSelectedLocation, false);
        currentSelectedLocation = null;


    }

    private void attemptHandToWaste() {
        if (game.canMoveHandToWaste()) {
           presenter.showAtWaste(game.getTopWasteCard());
            game.handToWaste();
           if (game.canMoveWasteToHand()) presenter.showHandEmpty();

        }
    }

    private void attemptWasteToHand() {
        if (game.canMoveWasteToHand()) {
            game.wasteToHand();
            presenter.showAtWaste(null);
            presenter.showHandFull();
        }
    }

    private void attemptMoveTo(Location location) {
        if (game.canDropAt(location, game.getCardsAt(currentSelectedLocation))) {

            List<Card> pickedCards = game.pickUpAt(currentSelectedLocation);
            game.dropAt(location, pickedCards);

            if (currentSelectedLocation instanceof WasteLocation) {
                presenter.showAtWaste(null); // NEED A FUNCTION IN GAME FOR LOOKING AT REVEALED WASTE CARD

            } else if (currentSelectedLocation instanceof FoundationLocation) {
                int cardRank = pickedCards.get(0).getRank();
                Suit cardSuit = pickedCards.get(0).getSuit();

                if (cardRank - 1 >= 1) {
                    presenter.showAtFoundation(cardSuit, new Card(cardRank - 1, cardSuit));
                } else {
                    presenter.showAtFoundation(cardSuit, null);
                }

            } else if (currentSelectedLocation instanceof TableauLocation) {
                presenter.removeAt((TableauLocation) currentSelectedLocation);
            }

            if (location instanceof FoundationLocation) {
                presenter.showAtFoundation(pickedCards.get(0).getSuit(), pickedCards.get(0));
            } else if (location instanceof TableauLocation) {
                presenter.addAt((TableauLocation) location, pickedCards);
            }
            resetSelection();
        }
    }

    private void attemptSelect(Location location) {
        if (game.canPickUpAt(location)) {
            presenter.setHighlightAt(location, true);
            currentSelectedLocation = location;
        }
    }
}

