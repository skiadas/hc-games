package core.locations;


import java.util.Objects;

public class TableauLocation implements Location {
    private final int pile;
    private final int card;

    public TableauLocation(int pile, int card) {
        this.pile = pile;
        this.card = card;
    }

    public int getPile() {
        return pile;
    }

    public int getCard() {
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TableauLocation that = (TableauLocation) o;
        return pile == that.pile && card == that.card;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pile, card);
    }
}
