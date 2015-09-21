package team;

import team.config.Configuration;
import team.game.TurnManager;

public class Game {
  private final Configuration configuration;
  private final TurnManager turnManager;

  public Game(Configuration config) {
    configuration = config;
    turnManager = new TurnManager(configuration.getPlayers());
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public TurnManager getTurnManager() {
    return turnManager;
  }
}
