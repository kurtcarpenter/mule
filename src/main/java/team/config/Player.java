package team.config;

import java.lang.Comparable;
import team.game.containers.Resource;
import team.config.GameSettings.Difficulty;

public class Player implements Comparable<Player>, java.io.Serializable  {

  public enum PlayerRace {
    FLAPPER, BONZOID, UGAITE, BUZZITE, HUMAN
  }

  public enum PlayerColor {
    RED, BLUE, GREEN, YELLOW
  }

  private final String name;
  private final PlayerRace race;
  private final PlayerColor color;
  private final Difficulty difficulty;
  private int money;
  private int score;
  private int land;
  private int tilesOwned;
  private int mulesOwned;
  private int food;
  private int energy;
  private int smithore;
  private int crystite;
  private Resource mule;

  /**
   * Creates a Player object.
   *
   * @param name the name of the Player
   * @param race the race of the Player
   * @param color the color of the Player
   */
  public Player(String name, PlayerRace race, PlayerColor color, Difficulty difficulty) {
    this.name = name;
    this.race = race;
    this.color = color;
    this.difficulty = difficulty;
    setStartingMoney();
    tilesOwned = 0;
    mulesOwned = 0;
    if (this.difficulty == Difficulty.BEGINNER) {
      food = 8;
      energy = 4;
      smithore = 0;
    } else {
      food = 4;
      energy = 2;
      smithore = 0;
    }
    mule = null;
  }

  private void setStartingMoney() {
    switch (race) {
      case FLAPPER:
        money = 5000;
        break;
      case BONZOID:
        money = 3000;
        break;
      case UGAITE:
        money = 1500;
        break;
      case BUZZITE:
        money = 1000;
        break;
      default:
        money = 0;
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

  public void addMoney(int delta) {
    this.money += delta;
  }

  public int getFood() {
    return food;
  }

  public int getEnergy() {
    return energy;
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
  public int compareTo(Player player) {
    return this.getScore() - player.getScore();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj instanceof Player) {
      Player temp = (Player) obj;
      return temp.getName().equals(getName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return getName().hashCode();
  }

  public void incScore(int delta) {
    score += delta;
  }

  /**
   * Returns the amount of a resource.
   *
   * @param resource the type or resource being checked
   * @return the amount of the resource
   */
  public int getResourceQuantity(Resource resource) {
    switch (resource) {
      case FOOD:
        return food;
      case ENERGY:
        return energy;
      case SMITHORE:
        return smithore;
      case CRYSTITE:
        return crystite;
      case MULE:
        if (mule == null) {
          return 0;
        } else {
          return 1;
        }
      default:
        return -1;
    }
  }

  /**
   * Adds a specified quantity of the resource to the current amount of
   * resources.
   *
   * @param resource the type of resource being set
   * @param quantity the amount of resources to be added to the current amount
   */
  public void addResourceQuantity(Resource resource, int quantity) {
    switch (resource) {
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
        mule = null;
        break;
      default:
        break;
    }
  }

  public void receiveMule(Resource type, boolean success) {
    if (success)
      mulesOwned++;
    mule = type;
  }

  public Resource getMule() {
    return mule;
  }

  public int getMulesOnwed() {
    return mulesOwned;
  }
}