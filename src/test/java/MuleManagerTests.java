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
    gameMap = new GameMap();
    muleManager = new MuleManager(turnManager, gameMap);
  }

  /**
   * Setup Stream to test STDOUT.
   */
  @Before
  public void setUpStreams() throws UnsupportedEncodingException {
    System.setOut(new PrintStream(outContent, false,
        Charset.defaultCharset().displayName()));
  }

  /**
   * Flush STDOUT Stream after tests.
   */
  @After
  public void cleanUpStreams() {
    System.setOut(null);
  }

  @Test
  public void placeValidMule() {
    int myX = 2;
    int myY = 2;
    Player curPlayer = turnManager.getCurrentPlayer();
    curPlayer.receiveMule(Resource.FOOD);
    gameMap.getTile(myX, myY).setOwner(curPlayer);
    muleManager.placeMule(myX, myY);
    assertEquals("Mule incorrectly assigned,", Resource.FOOD,
        gameMap.getTile(myX, myY).getMule());
    assertNull("Player still has mule,", curPlayer.getMule());
    String outString = "Placed " + Resource.FOOD.toString() + "mule at ("
        + myX + ", " + myY + ")\n";
    assertEquals("Incorrect output,", outString, outContent.toString());
  }

  @Test
  public void placeMuleWithoutMule() {
    Player curPlayer = turnManager.getCurrentPlayer();
    gameMap.getTile(4, 8).setOwner(curPlayer);
    muleManager.placeMule(4, 8);
    assertNull("Game Tile Mule not null,", gameMap.getTile(4, 8).getMule());
    String outString = "You don't own a mule to place here.\n";
    assertEquals("Incorrect output,", outString, outContent.toString());
  }

  @Test
  public void placeMuleWithMuleWithoutOwningTile() {
    Player curPlayer = turnManager.getCurrentPlayer();
    curPlayer.receiveMule(Resource.ENERGY);
    muleManager.placeMule(4, 0);
    assertNull("Game Tile Mule not null,", gameMap.getTile(4, 0).getMule());
    assertNull("Player Mule not null,", curPlayer.getMule());
    String outString = "You don't own this tile!\n";
    assertEquals("Incorrect output,", outString, outContent.toString());
  }

  @Test
  public void placeMuleWithoutOwningTile() {
    Player curPlayer = turnManager.getCurrentPlayer();
    muleManager.placeMule(1, 0);
    assertNull("Game Tile Mule not null,", gameMap.getTile(4, 0).getMule());
    assertNull("Player Mule not null,", curPlayer.getMule());
    String outString = "You don't own this tile!\n";
    assertEquals("Incorrect output,", outString, outContent.toString());
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
