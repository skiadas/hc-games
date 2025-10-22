package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    List<Card> cards;

    public Hand(List<Card> cards){
        this.cards = new ArrayList<Card>(cards);
    }

    public Hand() {
        this.cards = new ArrayList<Card>();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    // used for testing won't be used in final game
    public static Hand initFullDeck(){
        return new Hand(Card.fullDeck());
    }


//    public Card drawCard(int numberOfCards){
//        if(cards.isEmpty()){
//            return null;
//        }
//        for (int i = 0; i < numberOfCards; i++) {
//            return cards.remove(0);
//        }
//
//    }

}
