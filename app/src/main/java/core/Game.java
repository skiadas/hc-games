package core;

import core.locations.*;

import java.util.Collections;
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
        wastePile.returnToHand();
    }

    public void handToWaste(){
        wastePile.returnToHand();
    }


    public List<Card> getTopWasteCard(){
        return Collections.singletonList(wastePile.getTopCard());
    }

    public Card removeTopWasteCard(){
        return wastePile.remove();
    }

//    public boolean canPlaceOntoFoundationFromWaste(){
//        return foundationPiles.canPlaceCard(getTopWasteCard());
//    }

    public List<Card> getTopFoundationCard(Suit suit){
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
            throw new RuntimeException("this should never happen");
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public boolean canDropAt(Location l, List<Card> cards) {
        if (l instanceof FoundationLocation) {
            return foundationPiles.canPlaceCard((Card) cards);
        } else if (l instanceof TableauLocation) {
            TableauLocation tl = (TableauLocation) l;
            return tableauPiles.canAccept(tl,cards);
        } else if (l instanceof WasteLocation) {
            return false;
        } else if (l instanceof HandLocation) {
            return false;
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public List<Card> pickUpAt(Location l) {
        if (l instanceof FoundationLocation) {
            FoundationLocation fl = (FoundationLocation) l;
            return getTopFoundationCard(fl.suit);
        } else if (l instanceof TableauLocation) {
            TableauLocation tl = (TableauLocation) l;
            return tableauPiles.pickUpAt(tl);
        } else if (l instanceof WasteLocation) {
            return getTopWasteCard();
        } else if (l instanceof HandLocation) {
            throw new RuntimeException("Can't pickup from hand.");
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }

    public void dropAt(Location l, List<Card> cards) {
        if (l instanceof FoundationLocation) {
            FoundationLocation fl = (FoundationLocation) l;
            foundationPiles.add((Card) cards);
        } else if (l instanceof TableauLocation) {
            TableauLocation tl = (TableauLocation) l;
            tableauPiles.dropAt(tl,cards);
        } else if (l instanceof WasteLocation) {
            throw new RuntimeException("Should never drop cards onto waste.");
        } else if (l instanceof HandLocation) {
            throw new RuntimeException("Can't put cards in hand.");
        } else {
            throw new RuntimeException("Not supposed to happen.");
        }
    }

    public List<Card> getCardsAt(Location l) {
        if (l instanceof FoundationLocation) {
            FoundationLocation fl = (FoundationLocation) l;
            return foundationPiles.getTopFoundationCard(fl.suit);
        } else if (l instanceof TableauLocation) {
            TableauLocation tl = (TableauLocation) l;
            return tableauPiles.lookAt(tl);
        } else if (l instanceof WasteLocation) {
            return Collections.singletonList(wastePile.getTopCard());
        } else if (l instanceof HandLocation) {
            throw new RuntimeException("Hand Cards should always be hidden.");
        } else {
            throw new RuntimeException("Not supposed to happen");
        }
    }


}
