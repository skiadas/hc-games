package core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import core.actions.Action;
import core.actions.DeselectAction;
import core.actions.ExitAction;
import core.actions.SelectAction;
import core.locations.HandLocation;
import core.locations.Location;
import core.locations.TableauLocation;
import core.locations.WasteLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;


public class GameRunnerTests {


    private Presenter presenterMock;
    private Game gameMock;
    private GameRunner gameRunner;

    @BeforeEach
    void setUp() {
        presenterMock = Mockito.mock(Presenter.class);
        gameMock = Mockito.mock(Game.class);
        gameRunner = new GameRunner(presenterMock, gameMock);

    }

    @Test
    void canCreateGameRunnerWithPresenterOnly() {
        gameRunner = new GameRunner(presenterMock);
    }

    @Test
    void canCreateGameRunnerWithPresenterAndGame() {
        gameRunner = new GameRunner(presenterMock, gameMock);
    }

    @Test
    void exitActionClosesGame() {
        gameRunner.handle(new ExitAction());
    }

    @Test
    void canSelectValidCardWhenNoCurrentSelection() {
        Location location = new TableauLocation(3, 3);
        Action selectAction = new SelectAction(location);
        when(gameMock.canPickUpAt(location)).thenReturn(true);
        gameRunner.handle(selectAction);

        verify(gameMock).canPickUpAt(location);
        verify(presenterMock).setHighlightAt(location, true);
        assertEquals(location, gameRunner.getCurrentSelectedLocation());
    }

    @Test
    void cannotSelectInvalidCardWhenNoCurrentSelection() {
        Location location = new TableauLocation(3, 1);
        Action selectAction = new SelectAction(location);
        when(gameMock.canPickUpAt(location)).thenReturn(false);
        gameRunner.handle(selectAction);

        verify(gameMock).canPickUpAt(location);
        verify(presenterMock, never()).setHighlightAt(location, true);
        assertNotSame(location, gameRunner.getCurrentSelectedLocation());
    }

    @Test
    void canDeselectSelectedCardThroughDeselectAction() {
        Location location = new TableauLocation(5, 5);
        Action selectAction = new SelectAction(location);
        when(gameMock.canPickUpAt(location)).thenReturn(true);
        gameRunner.handle(selectAction);

        Action deselectAction = new DeselectAction();
        gameRunner.handle(deselectAction);

        verify(presenterMock).setHighlightAt(location, false);
        assertNull(gameRunner.getCurrentSelectedLocation());
    }

    @Test
    void canDeselectSelectedCardThroughReselection() {
        Location location = new TableauLocation(5, 5);
        Action selectAction = new SelectAction(location);
        when(gameMock.canPickUpAt(location)).thenReturn(true);
        gameRunner.handle(selectAction);

        Action selectAction2 = new SelectAction(location);
        gameRunner.handle(selectAction2);

        verify(presenterMock).setHighlightAt(location, false);
        assertNull(gameRunner.getCurrentSelectedLocation());
    }

    @Test
    void selectingHandDrawsFromHand() {
        Location location = new HandLocation();
        Action selectAction = new SelectAction(location);
        when(gameMock.canMoveWasteToHand()).thenReturn(false);
        when(gameMock.canMoveHandToWaste()).thenReturn(true);
        when(gameMock.handToWaste()).thenReturn(new Card(1, Suit.HEARTS));
        gameRunner.handle(selectAction);

        verify(gameMock).canMoveWasteToHand();
        verify(gameMock).canMoveHandToWaste();
        verify(gameMock).handToWaste();
        verify(presenterMock).showAt(new WasteLocation(), List.of(new Card(1, Suit.HEARTS)));
    }

    @Test
    void selectingEmptyHandBringsWasteToHand() {

    }
}
