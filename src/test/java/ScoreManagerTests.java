import org.junit.Test;

import static org.junit.Assert.assertEquals;
import team.game.containers.Resource;
import team.game.ScoreManager;
import team.config.Player;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Kurt Carpenter
 * @version 1.0
 */
public class ScoreManagerTests {

  ScoreManager sm;
  List<Player> currPlayers;

  @Test
  public void initialScoreCorrect() {
    currPlayers = getSinglePlayer();
    sm = new ScoreManager(currPlayers);
    sm.updateScores();
    assertEquals("Initial score incorrect,", 612, currPlayers.get(0).getScore());
  }

  @Test
  public void scoreWithResourcesCorrect() {
    currPlayers = getSinglePlayer();
    currPlayers.get(0).addResourceQuantity(Resource.SMITHORE, 5);
    sm = new ScoreManager(currPlayers);
    sm.updateScores();
    assertEquals("Initial score incorrect,", 617, currPlayers.get(0).getScore());
  }

  @Test
  public void scoreWithMoneyCorrect() {
    currPlayers = getSinglePlayer();
    currPlayers.get(0).addMoney(1337);
    sm = new ScoreManager(currPlayers);
    sm.updateScores();
    assertEquals("Score incorrect,", 1949, currPlayers.get(0).getScore());
  }

  @Test
  public void scoreWithTilesCorrect() {
    currPlayers = getSinglePlayer();
    currPlayers.get(0).setTilesOwned(2);
    sm = new ScoreManager(currPlayers);
    sm.updateScores();
    assertEquals("Score incorrect,", 1612, currPlayers.get(0).getScore());
  }

  @Test
  public void individualScoreAllResourcesCorrect() {
    currPlayers = getSinglePlayer();
    currPlayers.get(0).addResourceQuantity(Resource.SMITHORE, 5);
    currPlayers.get(0).addMoney(1337);
    currPlayers.get(0).setTilesOwned(2);
    sm = new ScoreManager(currPlayers);
    sm.updateScores();
    assertEquals("Score incorrect,", 2954, currPlayers.get(0).getScore());
  }

  @Test
  public void groupScoreAllResourcesCorrect() {
    currPlayers = getPlayers();
    currPlayers.get(0).addResourceQuantity(Resource.SMITHORE, 5);
    currPlayers.get(1).addMoney(1337);
    currPlayers.get(2).setTilesOwned(2);
    currPlayers.get(3).addResourceQuantity(Resource.SMITHORE, 5);
    currPlayers.get(3).addMoney(1337);
    currPlayers.get(3).setTilesOwned(2);
    sm = new ScoreManager(currPlayers);
    sm.updateScores();
    assertEquals("Score 1 incorrect,", 617, currPlayers.get(0).getScore());
    assertEquals("Score 2 incorrect,", 2949, currPlayers.get(1).getScore());
    assertEquals("Score 3 incorrect,", 1612, currPlayers.get(2).getScore());
    assertEquals("Score 4 incorrect,", 3354, currPlayers.get(3).getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullPlayersListThrowsException() {
    sm = new ScoreManager(null);
  }

  private List<Player> getSinglePlayer() {
    List<Player> players = new ArrayList<Player>();
    Player p1 = new Player("Alice", PlayerRace.HUMAN, PlayerColor.RED);
    players.add(p1);
    return players;
  }

  private List<Player> getPlayers() {
    List<Player> players = new ArrayList<Player>();
    Player p1 = new Player("Alice", PlayerRace.HUMAN, PlayerColor.RED);
    Player p2 = new Player("Bob", PlayerRace.FLAPPER, PlayerColor.YELLOW);
    Player p3 = new Player("Candice", PlayerRace.HUMAN, PlayerColor.BLUE);
    Player p4 = new Player("Destiny", PlayerRace.OTHERS, PlayerColor.GREEN);
    players.add(p1);
    players.add(p2);
    players.add(p3);
    players.add(p4);
    return players;
  }
}
