package org.example;

import core.ActionHandler;
import core.Card;
import core.Presenter;
import core.Suit;
import core.actions.SelectAction;
import core.locations.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.List;

public class UIController implements MouseListener, CreationListener<Component>, Presenter {
    private JFrame frame;
    private ActionHandler actionHandler = (core.actions.Action action) -> {};

    public UIController() {
        UIFactory.getInstance().addListener(this);
    }

    void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        // Find closest locateable
        Locateable l = findClosestLocatable(source);
        if (l != null) {
            System.out.println(l.getGameLocation());
            actionHandler.handle(new SelectAction(l.getGameLocation()));
            frame.repaint();
        }
    }

    private Locateable findClosestLocatable(Object source) {
        if (source == null) return null;
        if (source instanceof Locateable ls) {
            if (ls.getGameLocation() != null) return ls;
        }
        Container parent = ((Component) source).getParent();
        return findClosestLocatable(parent);
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void handleCreated(Component uiCard) {
        uiCard.addMouseListener(this);
    }

    // Presenter part
    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public void setHighlightAt(Location location, Boolean b) {
        Locateable element = findElement(location);
        if (element instanceof JComponent jEl) {
            if (b) {
                jEl.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            } else {
                jEl.setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    private Locateable findElement(Location location) {
       return findElement(location, frame);
    }

    private Locateable findElement(Location location, Component comp) {
        if (comp instanceof Locateable lcomp) {
            if (location.equals(lcomp.getGameLocation())) {
                return lcomp;
            }
        }
        if (comp instanceof Container cont) {
            for (Component component : cont.getComponents()) {
                Locateable element = findElement(location, component);
                if (element != null) return element;
            }
        }
        return null;
    }

    public void exit() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public void showAtFoundation(Suit suit, Card card) {
        Locateable element = findElement(new FoundationLocation(suit));
        UICard uiCard = (UICard) element;
        uiCard.setCard(card);
        uiCard.setVisible();
    }

    public void showAtWaste(List<Card> cards) {
        Locateable element = findElement(new WasteLocation());
        UIWaste waste = (UIWaste) element;
        waste.showCards(cards);
    }

    public void showHandEmpty() {
        Locateable element = findElement(new HandLocation());
        UIHand hand = (UIHand) element;
        hand.setHasCards(false);
    }

    public void showHandFull() {
        Locateable element = findElement(new HandLocation());
        UIHand hand = (UIHand) element;
        hand.setHasCards(true);
    }

    public void removeAt(TableauLocation location) {
        System.out.println("Removing cards at: " + location);
        Locateable element = findElement(location);
        UICard uiCard = (UICard) element;
        Container parent = uiCard.getParent();
        Component[] components = parent.getComponents();
        for (Component component : components) {
            if (component instanceof Locateable lcomp) {
                Location loc = lcomp.getGameLocation();
                if (((TableauLocation) loc).getCard() >= location.getCard()) {
                    parent.remove(component);
                }
            }
        }

    }

    public void addAt(int pile, List<Card> cards) {

    }

    public void addAt(TableauLocation loc, List<Card> cards) {
        System.out.println("Adding cards at: " + loc);
        UICard lastCard = (UICard) findElement(loc);
        UILayeredCards parent = (UILayeredCards) lastCard.getParent();
        parent.addCards(cards);
    }
}
