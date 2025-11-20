package core;

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

    public boolean canPlaceCard(Card card) {
        Suit suit = card.getSuit();
        int rank = card.getRank();
        List<Card> pile = piles.get(suit);
        if (pile.isEmpty()) {
            return rank == 1;
        } else {
            return card.ranksDirectlyAbove(pile.get(pile.size() - 1));
        }
    }

    public int getPileSize(Suit suit) {
        return piles.get(suit).size();
    }

    public Card remove(Suit suit) {
        List<Card> pile = piles.get(suit);
        if (canRemoveCard(suit)) {
            Card topCard = pile.remove(pile.size() - 1);
            return topCard;
        }
        throw new RuntimeException("Cannot remove from an empty pile.");
    }

    public boolean canRemoveCard(Suit suit) {
        return !piles.get(suit).isEmpty();
    }

    public Card getTopFoundationCard(Suit suit) {
        List<Card> pile = piles.get(suit);
        if (pile.isEmpty()) {
            throw new RuntimeException("Cannot remove from an empty pile.");
        }
        return pile.remove(pile.size() - 1);
    }
}
