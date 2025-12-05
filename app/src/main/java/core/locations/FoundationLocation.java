package core.locations;

import core.Suit;

import java.util.Objects;

public class FoundationLocation implements Location {
    public Suit suit;

    public FoundationLocation(Suit suit) {
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FoundationLocation that = (FoundationLocation) o;
        return suit == that.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(suit);
    }
}
