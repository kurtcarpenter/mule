package team.game;

import team.config.Player;
import java.util.List;

public class ScoreManager implements java.io.Serializable {
  private static final int MONEY_MULTIPLIER = 1;
  private static final int TILE_MULTIPLIER = 500;
  private static final int RESOURCE_MULTIPLIER = 1;
  private List<Player> players;

  public ScoreManager(List<Player> players) {
    this.players = players;
  }

  public void updateScores() {
    for (Player p : players) {
      updateScore(p);
    }
  }

  public void updateScore(Player p) {
    p.setScore(calculateScore(p));
  }

  private int calculateScore(Player p) {
    return MONEY_MULTIPLIER * p.getMoney() + TILE_MULTIPLIER * p.getTilesOwned()
        + RESOURCE_MULTIPLIER * p.getScorableResources();
  }
}
