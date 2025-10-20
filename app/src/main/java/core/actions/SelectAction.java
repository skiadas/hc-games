package core.actions;
import core.Card; // Card class import
import core.locations.Location; // import the interface so that we can use the different locations

public class SelectAction implements Action {
    // We need the card they have selected and a location where that they have selected from
    private Card selectedCard;
    private Location fromLocation;
}
