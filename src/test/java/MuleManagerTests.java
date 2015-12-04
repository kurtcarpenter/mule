import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import team.game.ScoreManager;
import team.game.MuleManager;
import team.game.TurnManager;
import team.config.Player;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import team.Game.GameState;
import team.map.GameMap;
import team.config.GameSettings.Map;
import team.config.GameSettings.Difficulty;
import team.game.containers.Resource;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.io.UnsupportedEncodingException;

/**
 * @author Srivas Sundararajan
 * @version 1.0
 */
public class MuleManagerTests {

  MuleManager muleManager;
  List<Player> players;
  GameState currentState;
  ScoreManager scoreManager;
  TurnManager turnManager;
  GameMap gameMap;
  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  String defaultCharset = Charset.defaultCharset().displayName();

  /**
   * Setup test variable for use.
   */
  @Before
  public void setup() {
    players = getPlayers();
    currentState = GameState.MAIN;
    scoreManager = new ScoreManager(players);
    turnManager = new TurnManager(players, currentState,
      scoreManager, null);
    gameMap = new GameMap(Map.STANDARD);
    muleManager = new MuleManager(turnManager, gameMap);
  }

  /**
   * Setup Stream to test STDOUT.
   */
  @Before
  public void setUpStreams() throws UnsupportedEncodingException {
    System.setOut(new PrintStream(outContent, false, defaultCharset));
  }

  /**
   * Flush STDOUT Stream after tests.
   */
  @After
  public void cleanUpStreams() {
    System.out.flush();
  }

  @Test
  public void placeValidMule() throws UnsupportedEncodingException {
    int myX = 2;
    int myY = 2;
    Player currPlayer = turnManager.getCurrentPlayer();
    currPlayer.receiveMule(Resource.FOOD, false);
    gameMap.getTile(myX, myY).setOwner(currPlayer);
    muleManager.placeMule(myX, myY);
    assertEquals("Mule incorrectly assigned,", Resource.FOOD,
        gameMap.getTile(myX, myY).getMule());
    assertNull("Player still has mule,", currPlayer.getMule());
    String outString = "Placed " + Resource.FOOD.toString() + "mule at ("
        + myX + ", " + myY + ")";
    assertEquals("Incorrect output,", outString,
        outContent.toString(defaultCharset).trim());
  }

  @Test
  public void placeMuleWithoutMule() throws UnsupportedEncodingException {
    Player currPlayer = turnManager.getCurrentPlayer();
    gameMap.getTile(4, 8).setOwner(currPlayer);
    muleManager.placeMule(4, 8);
    assertNull("Game Tile Mule not null,", gameMap.getTile(4, 8).getMule());
    String outString = "You don't own a mule to place here.";
    assertEquals("Incorrect output,", outString,
        outContent.toString(defaultCharset).trim());
  }

  @Test
  public void placeMuleWithMuleWithoutOwningTile()
        throws UnsupportedEncodingException {
    Player currPlayer = turnManager.getCurrentPlayer();
    currPlayer.receiveMule(Resource.ENERGY, false);
    muleManager.placeMule(4, 0);
    assertNull("Game Tile Mule not null,", gameMap.getTile(4, 0).getMule());
    assertNull("Player Mule not null,", currPlayer.getMule());
    String outString = "You don't own this tile! You lost your mule.";
    assertEquals("Incorrect output,", outString,
        outContent.toString(defaultCharset).trim());
  }

  @Test
  public void placeMuleWithoutOwningTile() throws UnsupportedEncodingException {
    Player currPlayer = turnManager.getCurrentPlayer();
    muleManager.placeMule(1, 0);
    assertNull("Game Tile Mule not null,", gameMap.getTile(4, 0).getMule());
    assertNull("Player Mule not null,", currPlayer.getMule());
    String outString = "You don't own this tile! You lost your mule.";
    assertEquals("Incorrect output,", outString,
        outContent.toString(defaultCharset).trim());
  }

  private List<Player> getPlayers() {
    List<Player> players = new ArrayList<Player>();
    Player p1 = new Player("Alice", PlayerRace.HUMAN, PlayerColor.RED, Difficulty.BEGINNER);
    Player p2 = new Player("Bob", PlayerRace.FLAPPER, PlayerColor.YELLOW, Difficulty.BEGINNER);
    Player p3 = new Player("Candice", PlayerRace.HUMAN, PlayerColor.BLUE, Difficulty.BEGINNER);
    Player p4 = new Player("Destiny", PlayerRace.BONZOID, PlayerColor.GREEN, Difficulty.BEGINNER);
    players.add(p1);
    players.add(p2);
    players.add(p3);
    players.add(p4);
    return players;
  }
}
