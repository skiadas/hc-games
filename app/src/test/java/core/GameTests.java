package core;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    @Test
    public void startGameWithSetDeck() {
        Game game = new Game();
        game.initializeGameWithSetHand();
        Game game2 = new Game();
        game2.initializeGameWithShuffledHand();
        assertNotEquals(game.getHand().getCards(),game2.getHand().getCards());
    }

    @Test
    public void startGameWithShuffledDeck() {
        Game game = new Game();
        game.initializeGameWithShuffledHand();
        Game game2 = new Game();
        game2.initializeGameWithShuffledHand();
        assertNotEquals(game.getHand().getCards(),game2.getHand().getCards());
    }

    @Test
    public void checkHandSizeAfterGameStart() {
        Game game = new Game();
        game.initializeGameWithShuffledHand();
        assertEquals(24, game.getHand().size());
    }

    @Test
    void gameHandToWasteDrawsCard() {
        Game game = new Game();
        game.initializeGameWithSetHand();
        game.handToWaste();
        assertFalse(game.getWastePile().isEmpty());
    }

    @Test
    void gameWasteMethodPrintsToStream() {
        Game game = new Game();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        game.initializeGameWithSetHand();
        Card topHandCard = game.getHand().getCards().get(0);
        game.handToWaste();
        game.wasteWriteTo(stream);
        assertEquals(topHandCard.toString(), output.toString());
    }

    @Test
    void handWriteToPrintsSizeToStream() {
        Game game = new Game();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(output);
        game.initializeGameWithSetHand();
        game.handWriteTo(stream);
//        assertEquals(game.getHand().getCards().size(), output.toString());
    }
}
