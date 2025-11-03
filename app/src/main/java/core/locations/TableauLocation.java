package core.locations;


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
}
