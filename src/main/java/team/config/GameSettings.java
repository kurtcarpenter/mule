package team.config;

public class GameSettings {

  public enum Difficulty {
    BEGINNER
  }

  public enum Map {
    BASIC, LARGE
  }

  private int numPlayers;
  private Difficulty difficulty;
  private Map map;

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
}
