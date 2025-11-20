package core;

import core.locations.Location;
import core.locations.TableauLocation;

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

    public boolean canMoveWasteToHand() {
        return hand.isEmpty();
    }

    public void WasteToHand() {
        if (canMoveWasteToHand()) {
            wastePile.returnToHand();
        }
    }

    public boolean canMoveHandToWaste(){
        return !hand.isEmpty();
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

    public Card getTopTableauCard(int pile){
        return tableauPiles.getTopTableauCard(pile);
    }

    public boolean canPickUpFromTableau(TableauLocation location) {
        return tableauPiles.canPickUp(location);
    }
}
