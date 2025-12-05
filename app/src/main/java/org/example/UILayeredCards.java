package org.example;

import org.jspecify.annotations.NonNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of cards layered either in a vertical or
 * a horizontal direction
 */
public class UILayeredCards extends JLayeredPane implements Updatable {
    private enum LayeringDirection {Horizontal, Vertical}
    private static final double SKIP_PCT = 0.15;
    private Dimension cardSize = new Dimension(200, 200); // Initial dummy values
    private Dimension skip;
    private final LayeringDirection direction;
    private List<UICard> cards;

    static UILayeredCards vertical() {
        return new UILayeredCards(LayeringDirection.Vertical);
    }

    static UILayeredCards horizontal() {
        return new UILayeredCards(LayeringDirection.Horizontal);
    }

    UILayeredCards(LayeringDirection direction) {
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setAlignmentY(Component.TOP_ALIGNMENT);
        this.direction = direction;
        cards = new ArrayList<>();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    void setCards(List<UICard> newCards) {
        cards.clear();
        removeAll();
        int i = 0;
        for (UICard card : newCards) {
            cards.add(card);
            i += 1;
            add(card, Integer.valueOf(i));
        }
        determineBounds();
    }

    private void determineBounds() {
        int count = cards.size();
        // Each new card should have a "skip" percentage
        // that it adds to the size of the component
        int widthSkip = isHorizontal() ? (int) (cardSize.width * SKIP_PCT) : 0;
        int heightSkip   = isHorizontal() ? 0 : (int) (cardSize.height * SKIP_PCT);
        skip = new Dimension(widthSkip, heightSkip);
        Dimension d = DimMath.add(cardSize, DimMath.times(count, skip));
        setSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        repositionCards();
    }

    private boolean isHorizontal() {
        return direction == LayeringDirection.Horizontal;
    }

    private void repositionCards() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setLocation(i * skip.width, i * skip.height);
        }
    }

    public void setCardSize(@NonNull Dimension d) {
        cardSize = d;
        determineBounds();
    }

    public void updateIcon() {

    }
}
