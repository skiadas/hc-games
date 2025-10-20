package core;

public enum Suit {
    HEARTS,
    SPADES,
    DIAMONDS,
    CLUBS;

    public Color getColor() {
        return switch (this) {
            case CLUBS, SPADES -> Color.BLACK;
            case HEARTS, DIAMONDS -> Color.RED;
        };
    }
}

