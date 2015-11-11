import org.junit.Test;

import static org.junit.Assert.assertEquals;
import team.game.RandomEventManager;
import team.config.Player;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import java.util.List;
import java.util.ArrayList;
import team.Game.GameState;

public class RandomEventManagerTests {

  @Test
  public void constructorWorksAndInitialValuesCorrect() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 1);
    rn.triggerEvent(players.get(0), 1);
    rn.triggerEvent(players.get(1), 1);
    rn.triggerEvent(players.get(2), 1);
    assertEquals("P1 ore must be 2", 2, players.get(0).getOre());
    assertEquals("P2 score must be 0", 0, players.get(1).getScore());
    assertEquals("P3 ore must be 2", 2, players.get(2).getOre());
  }


  private List<Player> getPlayers() {
    List<Player> players = new ArrayList<Player>();
    players.add(new Player("Alice", PlayerRace.HUMAN, PlayerColor.RED));
    players.add(new Player("Bob", PlayerRace.FLAPPER, PlayerColor.YELLOW));
    players.add(new Player("Candice", PlayerRace.HUMAN, PlayerColor.BLUE));
    return players;
  }
}
