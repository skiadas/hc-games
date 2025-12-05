package core;

import core.locations.Location;
import core.locations.TableauLocation;
import core.locations.WasteLocation;

import java.util.List;

public interface Presenter {
    void setHighlightAt(Location location, Boolean b);

    void exit();

    void showAtFoundation(Suit suit, Card card);
    // NULL CARD IS EMPTY

    void showAtWaste(List<Card> cards);

    void showHandEmpty();

    void showHandFull();

    void removeAt(TableauLocation location);

    void addAt(int pile, List<Card> cards);

    void addAt(TableauLocation location, List<Card> cards);
}
