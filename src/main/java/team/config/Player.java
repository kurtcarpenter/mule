package team.config;

public class Player {

  public enum PlayerRace {
    HUMAN, ALIEN
  }

  public enum PlayerColor {
    RED, BLUE, GREEN, YELLOW
  }

  private final String name;
  private final PlayerRace race;
  private final PlayerColor color;

  public Player(String name, PlayerRace race, PlayerColor color) {
    this.name = name;
    this.race = race;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public PlayerRace getRace() {
    return race;
  }

  public PlayerColor getColor() {
    return color;
  }

}
