package core;

import com.google.common.hash.HashCode;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Foundation {
    private Map<Suit, List<Card>> piles;

    public Foundation() {
        piles = new HashMap<>();
        for (Suit suit : Suit.values()) {
            List<Card> cards = new ArrayList<>();
            piles.put(suit, cards);
        }
    }
    public boolean isEmpty(Suit suit) {
        return piles.get(suit).isEmpty();
    }

    public void add(Card card) {
        if (canPlaceCard(card)) {
            piles.get(card.getSuit()).add(card);
        }
    }

    private boolean canPlaceCard(Card card) {
        Suit suit = card.getSuit();
        int rank = card.getRank();
        List<Card> pile = piles.get(suit);
        if (pile.isEmpty()) {
            return rank == 1;
        }
        return false;
    }
}
