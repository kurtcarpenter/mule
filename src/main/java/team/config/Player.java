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
  private int land;
  private int food;
  private int energy;
  private int smithore;
  private int crystite;
  private int mule;

  public Player(String name, PlayerRace race, PlayerColor color) {
    this.name = name;
    this.race = race;
    this.color = color;
    setStartingMoney();
  }

  private void setStartingMoney() {
    switch (race) {
      case HUMAN:
          money = 600;
          break;
      case FLAPPER:
          money = 1600;
          break;
      case OTHERS:
          money = 1000;
          break;
    }
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

  public int getScore() {
    return money + 500 * land + 30 * food + 50 * smithore + 100 * crystite + 100 * mule;
  }

  public int getFood() {
    return food;
  }
}
