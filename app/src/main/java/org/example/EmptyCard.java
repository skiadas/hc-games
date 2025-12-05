package org.example;

import javax.swing.*;
import java.awt.*;

public class EmptyCard extends JLabel implements Updatable {
    private final String text;
    private boolean showBack = false;
    private final double PX_TO_PTS = 0.75;
    private final double TEXT_SCALE = 0.75;


    EmptyCard(String text) {
        super(text);
        this.text = text;
        setBorder(new RoundedBorder(15));
        setHorizontalAlignment(SwingConstants.CENTER);
        updateIcon();
    }

    public void setShowBack(Boolean b) {
        showBack = b;
        updateIcon();
    }

    public void setCardSize(Dimension d) {
        super.setSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setFont(new Font("SansSerif", Font.PLAIN, (int) (d.width * PX_TO_PTS * TEXT_SCALE)));
        updateIcon();
    }

    public void updateIcon() {
        if (showBack) {
            setIcon(CardImageCache.getInstance().getBackIcon());
            setText(null);
        }
        else {
            setIcon(null);
            setText(text);
        }
    }
}
