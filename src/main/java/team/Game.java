package team;

import team.config.Configuration;
import team.game.LandSelectManager;
import team.game.TurnManager;
import team.map.GameMap;

public class Game {
  private final Configuration configuration;
  private final TurnManager turnManager;
  private final LandSelectManager landSelectManager;
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
}
