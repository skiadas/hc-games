package core;

import core.locations.Location;

public class Game {

    private Waste wastePile;
    private Foundation foundationPiles;
    private Tableau tableauPiles;
    private Hand hand;

    public Game() {
    }

    public void initializeGameWithSetHand(){
        hand = Hand.initFullDeck();
        tableauPiles = new Tableau(hand);
        foundationPiles = new  Foundation();
        wastePile = new Waste();
        Location selectedLocation = null;
    }

    public void initializeGameWithShuffledHand(){
        hand = Hand.shuffledFullDeck();
        tableauPiles = new Tableau(hand);
        foundationPiles = new  Foundation();
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
}
