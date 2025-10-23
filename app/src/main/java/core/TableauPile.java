package core;

import java.util.ArrayList;
import java.util.List;

public class TableauPile {
    private final List<Card> pile;
    private int lowestVisibleIndex;

    public TableauPile(List<Card> cards) {
        pile = new ArrayList<>();
        pile.add(null); // sentinel element
        pile.addAll(List.copyOf(cards));
        lowestVisibleIndex = numberOfCardsInPile();
    }

    public TableauPile() {
        this(new ArrayList<>());
    }

    public boolean isValidForPickUp(int index) {
        if (index > numberOfCardsInPile() || index <= 0) return false;
        return index >= lowestVisibleIndex;
    }

    private int numberOfCardsInPile() {
        return pile.size() - 1;
    }

    public void revealTopCard() {
        if (lowestVisibleIndex <= numberOfCardsInPile()) {
            throw new RuntimeException("revealTopCard() shouldn't be called when there are still revealed cards on the TableauPile.");
        }
        lowestVisibleIndex = numberOfCardsInPile();
    }

    List<Card> getCards() {
        List<Card> cards = new ArrayList<>(pile);
        cards.remove(0);
        return cards;
    }

    public List<Card> pickUp(int index) {
        if (numberOfCardsInPile() <= 1) throw new RuntimeException("pickUp(index) should not be called on an empty TableauPile");

        List<Card> subList = pile.subList(index, pile.size());
        List<Card> picked = new ArrayList<>(subList);
        subList.clear();
        return picked;
    }

    public void drop(List<Card> cards) {
        if (cards.isEmpty()) throw new RuntimeException("cannot drop 0 cards onto a TableauPile");
        pile.addAll(cards);
    }

    public boolean canAccept(List<Card> cards) {
        if (cards.isEmpty()) return false;
        if (numberOfCardsInPile() == 0) return cards.get(0).getRank() == 13;
        return canStackCards(cards);
    }

    private boolean canStackCards(List<Card> cards) {

        Card toBeAdded = cards.get(0);
        Card topCard = pile.get(pile.size() - 1);
        return toBeAdded.ranksDirectlyBelow(topCard) && !toBeAdded.isSameColorAs(topCard);
    }
}
