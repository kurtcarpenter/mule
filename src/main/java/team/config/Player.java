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
  private int mule;
  private Resource muleType;

  public Player(String name, PlayerRace race, PlayerColor color) {
    this.name = name;
    this.race = race;
    this.color = color;
    setStartingMoney();
    // Set food to 8 for Beginner Level
    food = 8;
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
    switch (r) {
      case FOOD:
        return food;
      case ENERGY:
        return energy;
      case SMITHORE:
        return smithore;
      case CRYSTITE:
        return crystite;
      case MULE:
        return mule;
    }
    return -1;
  }

  public void setResourceQuantity(Resource r, int quantity) {
    switch (r) {
      case FOOD:
        food += quantity;
        break;
      case ENERGY:
        energy += quantity;
        break;
      case SMITHORE:
        smithore += quantity;
        break;
      case CRYSTITE:
        crystite += quantity;
        break;
      case MULE:
        mule += quantity;
        break;
    }
  }

  public void receiveMule(Resource type) {
    muleType = type;
    mule++;
  }

  public String getMuleType() {
    if (mule == 0)
      return "None";
    switch (muleType) {
      case FOOD:
        return "Food";
      case ENERGY:
        return "Energy";
      case SMITHORE:
        return "Smithore";
      case CRYSTITE:
        return "Crystite";
    }
    return "None";
  }
}
