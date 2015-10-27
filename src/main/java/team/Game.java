package team;

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

public class Game {
  private final Configuration configuration;
  private final ScoreManager scoreManager;
  private final TurnManager turnManager;
  private final MuleManager muleManager;
  private final TimerManager timerManager;
  private final LandSelectManager landSelectManager;
  private final MapManager mapManager;
  private final PubManager pubManager;
  private final StoreManager storeManager;
  private final RandomEventManager randomEventManager;
  private GameState currentState;
  private GameMap gameMap;

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
    timerManager.passScreenMaster(screenMaster);
  }
}
