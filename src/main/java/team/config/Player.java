package team.config;

public class Player {

  public enum PlayerRace {
    HUMAN, FLAPPER, OTHERS
  }

  public enum PlayerColor {
    RED, BLUE, GREEN, YELLOW
  }

  private final String name;
  private final PlayerRace race;
  private final PlayerColor color;
  private int money;
  private int score;
  private int tilesOwned;

  public Player(String name, PlayerRace race, PlayerColor color) {
    this.name = name;
    this.race = race;
    this.color = color;
    this.money = 10000;
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

  public int getMoney() {
    return money;
  }

  public void setMoney(int delta) {
    this.money += delta;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public int getTilesOwned() {
    return tilesOwned;
  }

  public void setTilesOwned(int delta) {
    this.tilesOwned += delta;
  }
}
