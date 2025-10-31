package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameTests {

    @Test
    public void startGamewithSetDeck() {
        Game game = new Game();
        game.initializeGameWithSetHand();
        Game game2 = new Game();
    }
}
