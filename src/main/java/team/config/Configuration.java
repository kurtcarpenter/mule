package team.config;

import java.util.List;

public class Configuration {

  private List<Player> players;
  private final GameSettings settings;

  public Configuration(GameSettings settings) {
    this.settings = settings;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }
}