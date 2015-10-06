package team.config;

import java.lang.Comparable;
import team.game.containers.Resource;

public class Player implements Comparable<Player> {

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
  private int tilesOwned;
  private int food;
  private int energy;
  private int smithore;
  private int crystite;
  private Resource mule;

  public Player(String name, PlayerRace race, PlayerColor color) {
    this.name = name;
    this.race = race;
    this.color = color;
    setStartingMoney();
    // Set food to 8 for Beginner Level
    food = 8;
    mule = null;
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

  public int getFood() {
    return food;
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

  public int getScorableResources() {
    return energy + smithore + crystite + food;
  }

  @Override
  public int compareTo(Player p) {
    return this.getScore() - p.getScore();
  }

  public void incScore(int delta) {
    score += delta;
  }

  public int getResourceQuantity(Resource r) {
    // TODO: implement
    return 42;
  }

  public void setResourceQuantity(Resource r, int quantity) {
    // TODO: implement
  }

  public void receiveMule(Resource type) {
    // TODO: implement
    mule = type;
  }

  public Resource getMule() {
      return mule;
  }
}
