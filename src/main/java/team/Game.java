package team;

import team.config.Configuration;
import team.game.LandSelectManager;
import team.game.TurnManager;
import team.game.MapManager;
import team.game.TimerManager;
import team.game.ScoreManager;
import team.game.PubManager;
import team.map.GameMap;
import team.screens.ScreenMaster;

public class Game {
  private final Configuration configuration;
  private final TurnManager turnManager;
  private final MapManager mapManager;
  private final LandSelectManager landSelectManager;
  private final TimerManager timerManager;
  private final ScoreManager scoreManager;
  private final PubManager pubManager;
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
        scoreManager);
    landSelectManager = new LandSelectManager(turnManager, gameMap);
    mapManager = new MapManager(turnManager, landSelectManager, gameMap);
    timerManager = new TimerManager(turnManager);
    pubManager = new PubManager(configuration.getPlayers(), turnManager, timerManager);
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public TurnManager getTurnManager() {
    return turnManager;
  }

  public PubManager getPubManager() {
    return pubManager;
  }

  public LandSelectManager getLandSelectManager() {
    return landSelectManager;
  }

  public GameState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(GameState s) {
    currentState = s;
  }

  public MapManager getMapManager() {
    return mapManager;
  }

  public TimerManager getTimerManager() {
    return timerManager;
  }

  public void passScreenMaster(ScreenMaster screenMaster) {
    timerManager.passScreenMaster(screenMaster);
  }
}
