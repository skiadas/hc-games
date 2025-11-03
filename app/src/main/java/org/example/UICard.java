package org.example;

import core.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class UICard extends JPanel {
    static final int TARGET_WIDTH = 100;
    static final int TARGET_HEIGHT = CardImageCache.CARD_HEIGHT * TARGET_WIDTH / CardImageCache.CARD_WIDTH;
    private static final int ARC = TARGET_WIDTH / 10;
    private static final int BORDER_SIZE = 1;

    private final CardImageCache cardImageCache = new CardImageCache();

    private Card card;
    private BufferedImage image;
    private int y = 0;
    private int x = 0;
    private boolean faceUp = true;

    public UICard(Card card) {
        setCard(card);
        setCardBounds();
    }

    public void flip() {
        faceUp = !faceUp;
    }


    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setCardBounds();
    }

    private void setCardBounds() {
        setBounds(x, y, x + TARGET_WIDTH + 2 * BORDER_SIZE, y + TARGET_HEIGHT + 2 * BORDER_SIZE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics g2 = g.create();
        g2.translate(x, y);
        Shape roundedRect = new RoundRectangle2D.Double(BORDER_SIZE, BORDER_SIZE, TARGET_WIDTH + BORDER_SIZE, TARGET_HEIGHT + BORDER_SIZE, ARC, ARC);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, TARGET_WIDTH + 2 * BORDER_SIZE, TARGET_HEIGHT + 2 * BORDER_SIZE, ARC, ARC);
        g2.setClip(roundedRect);
        if (faceUp)
            g2.drawImage(image, 1, 1, this);
        else
            g2.drawImage(cardImageCache.getBackImage(), 1, 1, this);
        g2.dispose();
    }

    void setCard(Card card) {
        this.card = card;
        image = cardImageCache.getResizedImage(this.card);
    }
}
