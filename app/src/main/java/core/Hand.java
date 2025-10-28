package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards){
        this.cards = new ArrayList<Card>(cards);
    }

    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(cards);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    // used for testing won't be used in final game
    public static Hand initFullDeck(){
        return new Hand(Card.fullDeck());
    }

    public static Hand shuffledFullDeck(){
        Hand hand = Hand.initFullDeck();
        hand.shuffle();
        return hand;
    }

    public List<Card> drawCards(int numberOfCards) {
        List<Card> drawnCards = new ArrayList<>(numberOfCards);

        for (int i = 0; i < numberOfCards && !cards.isEmpty(); i++) {
            drawnCards.add(cards.remove(0));
        }
        return drawnCards;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
