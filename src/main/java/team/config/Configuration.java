package team.config;

import java.util.List;
import java.util.ArrayList;
import team.config.GameSettings;

public class Configuration {

  private List<Player> players;
  private GameSettings settings;

  public Configuration() {
    players = new ArrayList<Player>();
    settings = new GameSettings();
  }

  public Configuration(GameSettings settings) {
    this.settings = settings;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }

  public GameSettings getSettings() {
    return settings;
  }

  public void setSettings(GameSettings settings) {
    this.settings = settings;
  }
}
