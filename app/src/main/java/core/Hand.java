package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static core.Card.fullDeck;

public class Hand {
    private List<Card> cards;

    public Hand(List<Card> cards){
        this.cards = new ArrayList<Card>();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    // mainly for testing won't be used in final game
    public List<Card> getSetDeck(){
        return fullDeck();
    }

    public Card drawCard(){
        if(cards.isEmpty()){
            return null;
        }
        return cards.remove(0);
    }


}
