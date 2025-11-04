package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertEquals(game.getHand().size(), 24);
    }

}
