import core.GameRunner;
import org.example.App;
import org.example.UIController;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        UIController controller = app.getController();
        GameRunner gameRunner = new GameRunner(controller);
        controller.setActionHandler(gameRunner);
        app.run();
    }
}
