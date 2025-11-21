package core;

import core.locations.*;

import java.util.List;

public class Game {

    private Waste wastePile;
    private Foundation foundationPiles;
    private Tableau tableauPiles;
    private Hand hand;

    public Game() {
    }

    public void initializeGameWithSetHand() {
        hand = Hand.initFullDeck();
        tableauPiles = new Tableau(hand);
        foundationPiles = new Foundation();
        wastePile = new Waste();
        Location selectedLocation = null;
    }

    public void initializeGameWithShuffledHand() {
        hand = Hand.shuffledFullDeck();
        tableauPiles = new Tableau(hand);
        foundationPiles = new Foundation();
        wastePile = new Waste();
        Location selectedLocation = null;
    }

    public Hand getHand() {
        return hand;
    }

    public Tableau getTableauPiles() {
        return tableauPiles;
    }

    public Foundation getFoundationPiles() {
        return foundationPiles;
    }

    public Waste getWastePile() {
        return wastePile;
    }

    public void WasteToHand() {
        if (canMoveWasteToHand()) {
            wastePile.returnToHand();
        }
    }

    public void handToWaste(){
        wastePile.returnToHand();
    }


    public Card getTopWasteCard(){
        return wastePile.getTopCard();
    }

    public Card removeTopWasteCard(){
        return wastePile.remove();
    }

    public boolean canPlaceOntoFoundationFromWaste(){
        return foundationPiles.canPlaceCard(getTopWasteCard());
    }

    public Card getTopFoundationCard(Suit suit){
        return foundationPiles.getTopFoundationCard(suit);
    }

    boolean canMoveHandToWaste(){
        return !hand.isEmpty();
    }

    boolean canMoveWasteToHand() {
        return hand.isEmpty();
    }

    private boolean canPickUpFromTableau(TableauLocation location) {
        return tableauPiles.canPickUp(location);
    }

    private boolean canPickUpFromFoundation(Suit suit) {
        return foundationPiles.canRemoveCard(suit);
    }

    private boolean canPickUpFromWaste() {
        return !wastePile.isEmpty();
    }

    public boolean canPickUpAt(Location l) {
        if (l instanceof FoundationLocation) {
            return canPickUpFromFoundation(((FoundationLocation) l).suit);
        } else if (l instanceof TableauLocation) {
            return canPickUpFromTableau((TableauLocation) l);
        } else if (l instanceof WasteLocation) {
            return canPickUpFromWaste();
        } else if (l instanceof HandLocation) {
            return canMoveHandToWaste();
            // More needs to happen in this special case
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public boolean canDropAt(Location l, List<Card> cards) {
        if (l instanceof FoundationLocation) {
            return true;
        } else if (l instanceof TableauLocation) {
            return true;
        } else if (l instanceof WasteLocation) {
            return true;
        } else if (l instanceof HandLocation) {
            return true;
            // More needs to happen in this special case
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public List<Card> pickUpAt(Location l) {
        if (l instanceof FoundationLocation) {
            return true;
        } else if (l instanceof TableauLocation) {
            return true;
        } else if (l instanceof WasteLocation) {
            return true;
        } else if (l instanceof HandLocation) {
            return true;
            // More needs to happen in this special case
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public void dropAt(Location l, List<Card> cards) {
        if (l instanceof FoundationLocation) {
            return true;
        } else if (l instanceof TableauLocation) {
            return true;
        } else if (l instanceof WasteLocation) {
            return true;
        } else if (l instanceof HandLocation) {
            return true;
            // More needs to happen in this special case
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public List<Card> getCardsAt(Location l) {
        if (l instanceof FoundationLocation) {
            return true;
        } else if (l instanceof TableauLocation) {
            return true;
        } else if (l instanceof WasteLocation) {
            return true;
        } else if (l instanceof HandLocation) {
            return true;
            // More needs to happen in this special case
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }


}
