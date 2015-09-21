package team;

import team.config.Configuration;
import team.game.LandSelectManager;
import team.game.TurnManager;

public class Game {
  private final Configuration configuration;
  private final TurnManager turnManager;
  private final LandSelectManager landSelectManager;

  public Game(Configuration config) {
    configuration = config;
    turnManager = new TurnManager(configuration.getPlayers());
    landSelectManager = new LandSelectManager(turnManager);
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
}
