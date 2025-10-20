package core.locations;
// add the needed imports for the arraylist of cards that we are storing in the piles also import card
import core.Card;
import java.util.List;

public class Tableau implements Location {
    // We need to have the cards that are in the index and the tableau index itself
    private int index; // which tableau (1 - 7)
    private List<Card> cards; // The cards in specific tableau pile
}
