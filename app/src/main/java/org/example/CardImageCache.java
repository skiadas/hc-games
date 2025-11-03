package org.example;

import core.Card;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CardImageCache implements Serializable {
    private final BufferedImage deckImage;
    private final BufferedImage backImage;
    private final Map<Card, BufferedImage> map;
    static final int CARD_WIDTH = 475;
    static final int CARD_HEIGHT = 675;
    static final int X_OFFSET = 306;
    static final int Y_OFFSET = 182;
    static final int X_GAP = 39;
    static final int Y_GAP = 47;

    public CardImageCache() {
        map = new HashMap<>();
        try {
            InputStream imgStream = UICard.class.getClassLoader().getResourceAsStream("images/carddeck.png");
            deckImage = ImageIO.read(imgStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to locate image files");
        }
        backImage = createBackImage();
    }

    private BufferedImage createBackImage() {
        return resizeImage(getImageAtOffset(13,2));
    }

    BufferedImage getBackImage() {
        return backImage;
    }

    BufferedImage getResizedImage(Card card) {
        return map.computeIfAbsent(card, this::createCardImage);
    }

    private BufferedImage createCardImage(Card card) {
        return resizeImage(getCardImage(card));
    }

    BufferedImage resizeImage(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(UICard.TARGET_WIDTH, UICard.TARGET_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, UICard.TARGET_WIDTH, UICard.TARGET_HEIGHT, null);
        g.dispose();
        return resizedImage;
    }

    public BufferedImage getCardImage(Card card) {
        int xOffset = rankOffset(card);
        int yOffset = suitOffset(card);
        return getImageAtOffset(xOffset, yOffset);
    }

    private BufferedImage getImageAtOffset(int xOffset, int yOffset) {
        return deckImage.getSubimage(X_OFFSET + xOffset * (CARD_WIDTH + X_GAP), Y_OFFSET + yOffset * (CARD_HEIGHT + Y_GAP), CARD_WIDTH, CARD_HEIGHT);
    }

    int suitOffset(Card card) {
        return switch (card.getSuit()) {
            case DIAMONDS -> 0;
            case CLUBS -> 1;
            case HEARTS -> 2;
            case SPADES -> 3;
        };
    }

    int rankOffset(Card card) {
        // Cards start at 2 in image, so offset 0 is rank 2
        int rank = card.getRank();
        if (rank == 1) return 12;
        return rank - 2;
    }
}