import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
  List<Player> curPlayers;

  @Test
  public void initialScoreCorrect() {
    curPlayers = getSinglePlayer();
    sm = new ScoreManager(curPlayers);
    sm.updateScores();
    assertEquals("Initial score incorrect,", 612, curPlayers.get(0).getScore());
  }

  @Test
  public void scoreWithResourcesCorrect() {
    curPlayers = getSinglePlayer();
    curPlayers.get(0).setOre(5);
    sm = new ScoreManager(curPlayers);
    sm.updateScores();
    assertEquals("Initial score incorrect,", 612, curPlayers.get(0).getScore());
  }

  @Test
  public void scoreWithMoneyCorrect() {
    curPlayers = getSinglePlayer();
    curPlayers.get(0).setMoney(1337);
    sm = new ScoreManager(curPlayers);
    sm.updateScores();
    assertEquals("Score incorrect,", 1949, curPlayers.get(0).getScore());
  }

  @Test
  public void scoreWithTilesCorrect() {
    curPlayers = getSinglePlayer();
    curPlayers.get(0).setTilesOwned(2);
    sm = new ScoreManager(curPlayers);
    sm.updateScores();
    assertEquals("Score incorrect,", 1612, curPlayers.get(0).getScore());
  }

  @Test
  public void individualScoreAllResourcesCorrect() {
    curPlayers = getSinglePlayer();
    curPlayers.get(0).setOre(5);
    curPlayers.get(0).setMoney(1337);
    curPlayers.get(0).setTilesOwned(2);
    sm = new ScoreManager(curPlayers);
    sm.updateScores();
    assertEquals("Score incorrect,", 2949, curPlayers.get(0).getScore());
  }

  @Test
  public void groupScoreAllResourcesCorrect() {
    curPlayers = getPlayers();
    curPlayers.get(0).setOre(5);
    curPlayers.get(1).setMoney(1337);
    curPlayers.get(2).setTilesOwned(2);
    curPlayers.get(3).setOre(5);
    curPlayers.get(3).setMoney(1337);
    curPlayers.get(3).setTilesOwned(2);
    sm = new ScoreManager(curPlayers);
    sm.updateScores();
    assertEquals("Score 1 incorrect,", 612, curPlayers.get(0).getScore());
    assertEquals("Score 2 incorrect,", 2949, curPlayers.get(1).getScore());
    assertEquals("Score 3 incorrect,", 1612, curPlayers.get(2).getScore());
    assertEquals("Score 4 incorrect,", 3349, curPlayers.get(3).getScore());
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
