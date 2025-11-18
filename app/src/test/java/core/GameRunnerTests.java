package core;

import com.sun.jdi.InterfaceType;
import core.actions.SelectAction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import core.locations.*;
import core.actions.*;

public class GameRunnerTests {
    private Presenter presenter;
    private Action action;

    @Test
    void initiateNullSelectedLocation() {
        GameRunner gameRunner = new GameRunner((Presenter) presenter);
        assertEquals(null, gameRunner.getCurrentSelectedLocation());
    }

    @Test
    void currentLocationFoundationAfterSelectedTopCard() {
        GameRunner gamerunner = new GameRunner((Presenter) presenter);
        SelectAction se = (SelectAction) action;
        Location fromLocation = se.getFromLocation();
        assertEquals(fromLocation instanceof FoundationLocation, gamerunner.getCurrentSelectedLocation());
    }
}
