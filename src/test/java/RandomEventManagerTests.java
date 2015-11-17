import org.junit.Test;

import static org.junit.Assert.assertEquals;
import team.game.RandomEventManager;
import team.config.Player;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import team.game.containers.Resource;
import java.util.List;
import java.util.ArrayList;
import team.Game.GameState;

public class RandomEventManagerTests {

  @Test
  public void constructorWorksAndInitialValuesCorrect1() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 1);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 food must be 19", 19, players.get(0).getResourceQuantity(Resource.FOOD));
  }

  @Test
  public void constructorWorksAndInitialValuesCorrect2() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 2);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 ore must be 2", 2, players.get(0).getOre());
  }

  @Test
  public void constructorWorksAndInitialValuesCorrect3() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 3);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 money must be 800", 800, players.get(0).getMoney());
  }

  @Test
  public void constructorWorksAndInitialValuesCorrect4() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 4);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 money must be 650", 650, players.get(0).getMoney());
  }

  @Test
  public void constructorWorksAndInitialValuesCorrect5() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 5);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 money must be 500", 500, players.get(0).getMoney());
  }

  @Test
  public void constructorWorksAndInitialValuesCorrect6() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 6);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 food must be 12", 12, players.get(0).getResourceQuantity(Resource.FOOD));
  }

  @Test
  public void constructorWorksAndInitialValuesCorrect7() {
    List<Player> players = getPlayers();
    RandomEventManager rn = new RandomEventManager(players, 7);
    rn.triggerEvent(players.get(0), 1);
    assertEquals("P1 money must be 450", 450, players.get(0).getMoney());
  }

  private List<Player> getPlayers() {
    List<Player> players = new ArrayList<Player>();
    players.add(new Player("Alice", PlayerRace.HUMAN, PlayerColor.RED));
    players.add(new Player("Bob", PlayerRace.FLAPPER, PlayerColor.YELLOW));
    players.add(new Player("Candice", PlayerRace.HUMAN, PlayerColor.BLUE));
    return players;
  }
}
