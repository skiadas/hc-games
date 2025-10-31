package core;

public class Game {

    public Game() {
    }

    public void newGameWithSetHand(){
        Hand hand = Hand.initFullDeck();
        new Tableau(hand);
    }

    public void newGameWithShuffledHand(){
        Hand hand = Hand.shuffledFullDeck();
        new Tableau(hand);
    }
}
