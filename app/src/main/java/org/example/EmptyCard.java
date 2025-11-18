package org.example;

import javax.swing.*;
import java.awt.*;

public class EmptyCard extends JLabel implements Updatable {
    private final String text;
    private boolean showBack = true;


    EmptyCard(String text) {
        super(text);
        this.text = text;
        setBorder(new RoundedBorder(25));
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("SansSerif", Font.BOLD, 100));
        updateIcon();
    }

    public void setShowBack(Boolean b) {
        showBack = b;
        updateIcon();
    }

    public void setSize(Dimension d) {
        super.setSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setFont(new Font("SansSerif", Font.PLAIN, (int) (d.width * 0.75 * 0.75)));
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
