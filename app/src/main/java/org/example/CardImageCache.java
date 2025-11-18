package org.example;

import core.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CardImageCache implements Serializable {
    private static final CardImageCache instance = new CardImageCache();
    private static final Color BORDER_COLOR = Color.BLACK;
    private final BufferedImage deckImage;
    private BufferedImage backImage;
    private final Map<Card, BufferedImage> map;
    private final Map<Card, Icon> icons;
    private static final int CARD_WIDTH = 475;
    private static final int CARD_HEIGHT = 675;
    private static final int X_OFFSET = 306;
    private static final int Y_OFFSET = 182;
    private static final int X_GAP = 39;
    private static final int Y_GAP = 47;
    private static final int BORDER_SIZE = 1;
    private Dimension scaledSize;

    private CardImageCache() {
        map = new HashMap<>();
        icons = new HashMap<>();
        try {
            InputStream imgStream = UICard.class.getClassLoader().getResourceAsStream("images/carddeck.png");
            if (imgStream == null) {
                throw new RuntimeException("Image path not found");
            }
            deckImage = ImageIO.read(imgStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to locate image files");
        }
        scaledSize = new Dimension(100, 200);
        backImage = createBackImage();
    }

    public static CardImageCache getInstance() {
        return instance;
    }

    static Dimension computeSizeForHeight(int height) {
        int width = height * CARD_WIDTH / CARD_HEIGHT;
        return new Dimension(width, height);
    }

    private BufferedImage createBackImage() {
        return resizeImage(getImageAtOffset(13,2), scaledSize);
    }

    BufferedImage getResizedImage(Card card) {
        return map.computeIfAbsent(card, card1 -> resizeImage(getCardImage(card1), scaledSize));
    }

    BufferedImage resizeImage(BufferedImage originalImage, Dimension size) {
        BufferedImage resizedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, size.width, size.height, null);
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

    ImageIcon getIcon(Card card) {
        BufferedImage resizedImage = getResizedImage(card);
        return new ImageIcon(clip(resizedImage));
    }

    private BufferedImage clip(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int cornerRadius = scaledSize.height / 10;
        BufferedImage maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = maskedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(BORDER_COLOR);
        g2d.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(BORDER_SIZE, BORDER_SIZE, width - 2 * BORDER_SIZE, height - 2 * BORDER_SIZE, cornerRadius, cornerRadius);
        g2d.setClip(roundedRect);

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return maskedImage;
    }

    Icon getBackIcon() {
        return new ImageIcon(clip(backImage));
    }

    void reset() {
        map.clear();
        backImage = createBackImage();
    }

    public void setScaledSize(Dimension targetSize) {
        this.scaledSize = targetSize;
        reset();
    }
}