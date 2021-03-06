package team.config;

public class GameSettings implements java.io.Serializable {

  public enum Difficulty {
    BEGINNER, STANDARD, TOURNAMENT
  }

  public enum Map {
    STANDARD, RANDOM
  }

  private int numPlayers;
  private Difficulty difficulty;
  private Map map;

  public GameSettings() {

  }

  /**
   * Creates a GameSettings object.
   *
   * @param difficulty the difficulty for the game
   * @param map the type of map for the game
   * @param numPlayers the number of players in this game
   */
  public GameSettings(Difficulty difficulty, Map map, int numPlayers) {
    this.difficulty = difficulty;
    this.map = map;
    this.numPlayers = numPlayers;
  }

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public Map getMap() {
    return map;
  }

  public int getNumPlayers() {
    return numPlayers;
  }

  public void setNumPlayers(int players) {
    numPlayers = players;
  }
}