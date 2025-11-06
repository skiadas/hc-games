package core;

/**
 * Class responsible for running a game. Eventually
 * should hold some Game instance
 */
public class GameRunner {
    private final Game game;
    Presenter presenter;
    public GameRunner(Presenter presenter) {
        this.presenter = presenter;
        this.game = new Game();
    }

    public void play() {

    }
}
