package core.locations;

public class TableauLocation implements Location {
    // We need to have the cards that are in the index and the tableau index itself
    private int col; // cols are 1 - 7
    private int row; // rows are (i + 1, i + 1 : i starts at 0 and < 7)
}
