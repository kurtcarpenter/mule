package team;

import team.config.Configuration;
import team.game.LandSelectManager;
import team.game.TurnManager;
import team.game.MapManager;
import team.game.ScoreManager;
import team.map.GameMap;

public class Game {
  private final Configuration configuration;
  private final TurnManager turnManager;
  private final MapManager mapManager;
  private final LandSelectManager landSelectManager;
  private final ScoreManager scoreManager;
  private GameState currentState;
  private GameMap gameMap;

  public enum GameState {
    MAIN, LAND_SELECT
  }

  public Game(Configuration config) {
    currentState = GameState.LAND_SELECT;
    configuration = config;
    gameMap = new GameMap();
    scoreManager = new ScoreManager(configuration.getPlayers());
    turnManager = new TurnManager(configuration.getPlayers(), currentState,
        scoreManager);
    landSelectManager = new LandSelectManager(turnManager, gameMap);
    mapManager = new MapManager(turnManager, landSelectManager, gameMap);
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
}
