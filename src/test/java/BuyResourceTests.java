import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import team.game.ScoreManager;
import team.game.StoreManager;
import team.game.TurnManager;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import team.config.Player;
import team.config.GameSettings.Difficulty;
import team.Game.GameState;
import team.game.containers.Resource;
import team.game.exceptions.PlayerTransactionException;
import team.game.exceptions.StoreTransactionException;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Nathan Dass
 * @version 1.0
 */

public class BuyResourceTests {

  TurnManager turnManager;
  List<Player> players;
  GameState currentState;
  ScoreManager scoreManager;
  StoreManager storeManager;

  /**
   * Instantiates all the objects needed for the tests.
   */
  @Before
  public void setup() {
    players = new ArrayList<>();
    players.add(new Player("Player 1", PlayerRace.HUMAN, PlayerColor.RED));
    currentState = GameState.MAIN;
    scoreManager = new ScoreManager(players);
    turnManager = new TurnManager(players, currentState, scoreManager, null);
    storeManager = new StoreManager(Difficulty.BEGINNER, turnManager);
  }

  @Test
  public void buyMule() {
    boolean playerExcep = false;
    boolean storeExcep = false;
    int prevStoreMule = storeManager.getResourceStock(Resource.MULE);
    try {
      storeManager.buyResource(Resource.MULE, 1);
    } catch (PlayerTransactionException pte) {
      playerExcep = true;
    } catch (StoreTransactionException ste) {
      storeExcep = true;
    }
    assertEquals(storeManager.getResourceStock(Resource.MULE), prevStoreMule);
    assertTrue(playerExcep);
    assertFalse(storeExcep);
  }

  @Test
  public void buyResource() {
    int prevPlayerFood = 0;
    int prevMoney = 0;
    int prevStoreFood = 0;
    boolean playerExcep = false;
    boolean storeExcep = false;
    try {
      prevPlayerFood = turnManager.getCurrentPlayer().getFood();
      prevMoney = turnManager.getCurrentPlayer().getMoney();
      prevStoreFood = storeManager.getResourceStock(Resource.FOOD);
      storeManager.buyResource(Resource.FOOD, 1);
    } catch (PlayerTransactionException pte) {
      playerExcep = true;
    } catch (StoreTransactionException ste) {
      storeExcep = true;
    }
    assertEquals(turnManager.getCurrentPlayer().getFood(), prevPlayerFood + 1);
    assertEquals(turnManager.getCurrentPlayer().getMoney(), prevMoney - storeManager.getPrice(
        Resource.FOOD));
    assertEquals(storeManager.getResourceStock(Resource.FOOD), prevStoreFood - 1);
    assertFalse(playerExcep);
    assertFalse(storeExcep);
  }

  @Test
  public void buyResources() {
    int prevPlayerFood = turnManager.getCurrentPlayer().getFood();
    int prevPlayerEnergy = turnManager.getCurrentPlayer().getEnergy();
    int prevMoney = 0;
    int prevStoreFood = 0;
    int prevStoreEnergy = 0;
    try {
      prevMoney = turnManager.getCurrentPlayer().getMoney();
      prevStoreFood = storeManager.getResourceStock(Resource.FOOD);
      prevStoreEnergy = storeManager.getResourceStock(Resource.ENERGY);
      storeManager.buyResource(Resource.FOOD, 1);
      storeManager.buyResource(Resource.ENERGY, 1);
    } catch (PlayerTransactionException pte) {
      System.out.println(pte.toString());
    } catch (StoreTransactionException ste) {
      System.out.println(ste.toString());
    }
    assertEquals(turnManager.getCurrentPlayer().getFood(), prevPlayerFood + 1);
    assertEquals(turnManager.getCurrentPlayer().getEnergy(), prevPlayerEnergy + 1);
    assertEquals(turnManager.getCurrentPlayer().getMoney(), prevMoney - storeManager.getPrice(
        Resource.FOOD) - storeManager.getPrice(Resource.ENERGY));
    assertEquals(storeManager.getResourceStock(Resource.FOOD), prevStoreFood - 1);
    assertEquals(storeManager.getResourceStock(Resource.ENERGY), prevStoreEnergy - 1);
  }

  @Test
  public void notEnoughMoney() {
    turnManager.getCurrentPlayer().setMoney(-turnManager.getCurrentPlayer().getMoney() + 1);
    boolean playerExcep = false;
    boolean storeExcep = false;
    int prevPlayerFood = 0;
    int prevMoney = 0;
    int prevStoreFood = 0;
    try {
      prevPlayerFood = turnManager.getCurrentPlayer().getFood();
      prevMoney = turnManager.getCurrentPlayer().getMoney();
      prevStoreFood = storeManager.getResourceStock(Resource.FOOD);
      storeManager.buyResource(Resource.FOOD, 1);
    } catch (PlayerTransactionException pte) {
      playerExcep = true;
      storeExcep = false;
    } catch (StoreTransactionException ste) {
      storeExcep = true;
      playerExcep = false;
    }
    assertEquals(turnManager.getCurrentPlayer().getFood(), prevPlayerFood);
    assertEquals(turnManager.getCurrentPlayer().getMoney(), prevMoney);
    assertEquals(storeManager.getResourceStock(Resource.FOOD), prevStoreFood);
    assertTrue(playerExcep);
    assertFalse(storeExcep);
  }

  @Test
  public void notEnoughResources() {
    boolean playerExcep = false;
    boolean storeExcep = false;
    int prevStoreFood = storeManager.getResourceStock(Resource.FOOD);
    try {
      storeManager.buyResource(Resource.FOOD, 1000);
    } catch (PlayerTransactionException pte) {
      playerExcep = true;
    } catch (StoreTransactionException ste) {
      storeExcep = true;
    }
    assertEquals(storeManager.getResourceStock(Resource.FOOD), prevStoreFood);
    assertFalse(playerExcep);
    assertTrue(storeExcep);
  }
}