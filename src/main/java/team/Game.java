package team;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import team.config.Configuration;
import team.game.LandSelectManager;
import team.game.MapManager;
import team.game.MuleManager;
import team.game.PubManager;
import team.game.RandomEventManager;
import team.game.ScoreManager;
import team.game.StoreManager;
import team.game.TimerManager;
import team.game.TurnManager;
import team.map.GameMap;
import team.screens.ScreenMaster;
import team.screens.instances.MainMapController;

public class Game implements Serializable {
  private Configuration configuration;
  private ScoreManager scoreManager;
  private TurnManager turnManager;
  private MuleManager muleManager;
  private TimerManager timerManager;
  private LandSelectManager landSelectManager;
  private MapManager mapManager;
  private PubManager pubManager;
  private StoreManager storeManager;
  private RandomEventManager randomEventManager;
  private GameState currentState;
  private GameMap gameMap;
  private ScreenMaster main;

  public enum GameState {
    MAIN, LAND_SELECT, CONFIGURE
  }

  /**
   * Sets up game instance that persists through playthrough.
   *
   * @param config congig object to instantiate Game
   */
  public Game(Configuration config) {
    currentState = GameState.CONFIGURE;
    configuration = config;
    gameMap = new GameMap();
    scoreManager = new ScoreManager(configuration.getPlayers());
    turnManager = new TurnManager(configuration.getPlayers(), currentState,
        scoreManager, this);
    timerManager = new TimerManager(turnManager);
    landSelectManager = new LandSelectManager(turnManager, gameMap);
    muleManager = new MuleManager(turnManager, gameMap);
    mapManager = new MapManager(turnManager, landSelectManager, muleManager, gameMap);
    pubManager = new PubManager(configuration.getPlayers(), turnManager, timerManager);
    storeManager = new StoreManager(configuration.getSettings().getDifficulty(), turnManager);
    randomEventManager = new RandomEventManager(configuration.getPlayers(), 0);
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public ScoreManager getScoreManager() {
    return scoreManager;
  }

  public TurnManager getTurnManager() {
    return turnManager;
  }

  public TimerManager getTimerManager() {
    return timerManager;
  }

  public LandSelectManager getLandSelectManager() {
    return landSelectManager;
  }

  public MapManager getMapManager() {
    return mapManager;
  }

  public PubManager getPubManager() {
    return pubManager;
  }

  public StoreManager getStoreManager() {
    return storeManager;
  }

  public RandomEventManager getRandomEventManager() {
    return randomEventManager;
  }

  public GameState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(GameState state) {
    currentState = state;
  }

  public void passScreenMaster(ScreenMaster screenMaster) {
    main = screenMaster;
    timerManager.passScreenMaster(screenMaster);
  }

  /**
   * Loads the game file and sets the map buttons.
   *
   * @return the current Game object if the file is found, otherwise null
   * @throws FileNotFoundException if the game file is not found
   * @throws Exception if for any other exception
   */
  public Game loadGame() {
    try {
      readObject(new ObjectInputStream(new FileInputStream("game.ser")));
      ((MainMapController) main.getMainMapController()).setMapButtons(gameMap);
    } catch (FileNotFoundException f) {
      System.out.println("Load Game File Not Found");
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return this;
  }

  /**
   * Saves the current game file for future use.
   *
   * @return true if the game file was successfully saved, false otherwise
   * @throws Exception if an exception occurs
   */
  public boolean saveGame() {
    try {
      writeObject(new ObjectOutputStream(new FileOutputStream("game.ser")));
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.writeObject(configuration);
    out.writeObject(scoreManager);
    out.writeObject(turnManager);
    out.writeObject(muleManager);
    out.writeObject(landSelectManager);
    out.writeObject(mapManager);
    out.writeObject(pubManager);
    out.writeObject(storeManager);
    out.writeObject(randomEventManager);
    out.writeObject(currentState);
    out.writeObject(gameMap);
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    configuration = (Configuration) in.readObject();
    scoreManager = (ScoreManager) in.readObject();
    turnManager = (TurnManager) in.readObject();
    muleManager = (MuleManager) in.readObject();
    landSelectManager = (LandSelectManager) in.readObject();
    mapManager = (MapManager) in.readObject();
    pubManager = (PubManager) in.readObject();
    storeManager = (StoreManager) in.readObject();
    randomEventManager = (RandomEventManager) in.readObject();
    currentState = (GameState) in.readObject();
    gameMap = (GameMap) in.readObject();

    timerManager = new TimerManager(turnManager);
    timerManager.passScreenMaster(main);
    pubManager.setTimerManager(timerManager);
  }
}
