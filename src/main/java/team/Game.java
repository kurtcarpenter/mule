package team;

import team.config.Configuration;
import team.game.LandSelectManager;
import team.game.TurnManager;
import team.game.MapManager;
import team.game.TimerManager;
import team.map.GameMap;
import team.screens.ScreenMaster;

public class Game {
  private final Configuration configuration;
  private final TurnManager turnManager;
  private final MapManager mapManager;
  private final LandSelectManager landSelectManager;
  private final TimerManager timerManager;
  private GameState currentState;
  private GameMap gameMap;

  public enum GameState {
    MAIN, LAND_SELECT
  }

  public Game(Configuration config) {
    currentState = GameState.LAND_SELECT;
    configuration = config;
    gameMap = new GameMap();
    turnManager = new TurnManager(configuration.getPlayers(), currentState);
    landSelectManager = new LandSelectManager(turnManager, gameMap);
    mapManager = new MapManager(turnManager, landSelectManager, gameMap);
    timerManager = new TimerManager(turnManager);
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public TurnManager getTurnManager() {
    return turnManager;
  }

  public LandSelectManager getLandSelectManager() {
    return landSelectManager;
  }

  public GameState getCurrentState() {
    return currentState;
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
