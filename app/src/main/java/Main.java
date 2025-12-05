import core.Game;
import core.GameRunner;
import org.example.App;
import org.example.UIController;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        Game game = new Game();
        game.initializeGameWithShuffledHand();
        UIController controller = app.getController();
        GameRunner gameRunner = new GameRunner(controller, game);
        controller.setActionHandler(gameRunner);
        app.run(game);
    }
}
