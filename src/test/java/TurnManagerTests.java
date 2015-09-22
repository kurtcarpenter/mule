import org.junit.Test;
import static org.junit.Assert.assertEquals;

import team.game.TurnManager;
import team.config.Player;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import java.util.List;
import java.util.ArrayList;
import team.Game.GameState;

public class TurnManagerTests {

  @Test
  public void constructorWorksAndInitialValuesCorrect() {
    TurnManager tm = new TurnManager(getPlayers(), GameState.MAIN);

    assertEquals("Initial turn must be 1", 1, tm.getCurrentTurn());
    assertEquals("Initial step must be 0", 0, tm.getCurrentStep());
    assertEquals("Initial player must be Alice", "Alice",
        tm.getCurrentPlayer().getName());
  }

  @Test
  public void advanceStepCorrectlyAdvancesTurn() {
    TurnManager tm = new TurnManager(getPlayers(), GameState.MAIN);
    tm.advanceStep();
    assertEquals("Turn must be 1", 1, tm.getCurrentTurn());
    assertEquals("Step must be 1", 1, tm.getCurrentStep());
    assertEquals("Player must be Bob", "Bob",
        tm.getCurrentPlayer().getName());
  }

  private List<Player> getPlayers() {
    List<Player> players = new ArrayList<Player>();
    players.add(new Player("Alice", PlayerRace.HUMAN, PlayerColor.RED));
    players.add(new Player("Bob", PlayerRace.FLAPPER, PlayerColor.YELLOW));
    players.add(new Player("Candice", PlayerRace.HUMAN, PlayerColor.BLUE));
    return players;
  }
}
