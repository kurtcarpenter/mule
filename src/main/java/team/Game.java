package team;

import java.io.*;

import team.config.Configuration;
import team.game.TurnManager;
import team.game.TimerManager;
import team.game.ScoreManager;
import team.game.LandSelectManager;
import team.game.MapManager;
import team.game.PubManager;
import team.game.MuleManager;
import team.game.StoreManager;
import team.game.RandomEventManager;
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
    randomEventManager = new RandomEventManager(configuration.getPlayers());
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

  public void setCurrentState(GameState s) {
    currentState = s;
  }

  public void passScreenMaster(ScreenMaster screenMaster) {
      main = screenMaster;
      timerManager.passScreenMaster(screenMaster);
  }

  public Game loadGame() {
      try {
          readObject(new ObjectInputStream(new FileInputStream("game.ser")));
          ((MainMapController) main.getMainMapController()).setMapButtons(gameMap);
      } catch(Exception e) {
          e.printStackTrace();
          return null;
      }
      return this;
  }

  public boolean saveGame() {
      try {
        writeObject(new ObjectOutputStream(new FileOutputStream("game.ser")));
      } catch(Exception e) {
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
