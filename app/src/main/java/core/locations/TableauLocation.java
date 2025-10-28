package core.locations;


public class TableauLocation implements Location {
    private final int pile;
    private final int card;

    public TableauLocation(int column, int row) {
        pile = column;
        card = row;
    }

    public int getPile() {
        return pile;
    }

    public int getCard() {
        return card;
    }
}
