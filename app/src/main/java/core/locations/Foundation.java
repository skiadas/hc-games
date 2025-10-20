package core.locations;

import core.Card;

import java.util.List;
import java.util.ArrayList;

public class Foundation implements Location {
    // We will also need a way to store the cards in a foundation and an index of the foundations.
    // Index will behave a little differently
    // instead of an integer we can use suit since each foundation will contain a different suit.
    // This will server as our indexing (clubs, spades, diamonds, hearts)
    private String suit;
    private List<Card> cards;
}
