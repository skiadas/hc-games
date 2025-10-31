package core;

import core.locations.Location;

public class Game {

    private Waste waste_pile;
    private Foundation foundation_piles;
    private Tableau tableau_piles;
    private Hand hand;

    public Game() {
    }

    public void initializeGameWithSetHand(){
        hand = Hand.initFullDeck();
        tableau_piles = new Tableau(hand);
        foundation_piles = new  Foundation();
        waste_pile = new Waste();
        Location selectedLocation = null;
    }

    public void initializeGameWithShuffledHand(){
        hand = Hand.shuffledFullDeck();
        tableau_piles = new Tableau(hand);
        foundation_piles = new  Foundation();
        waste_pile = new Waste();
        Location selectedLocation = null;
    }

    public Hand getHand() {
        return hand;
    }
}
