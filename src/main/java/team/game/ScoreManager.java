package team.game;

import team.config.Player;
import java.util.List;

public class ScoreManager implements java.io.Serializable {
  private static final int MONEY_MULTIPLIER = 1;
  private static final int TILE_MULTIPLIER = 500;
  private static final int RESOURCE_MULTIPLIER = 1;
  private List<Player> players;


  /**
   * Creates a ScoreManager object.
   *
   * @param players the list of players
   */
  public ScoreManager(List<Player> players) {
    if (players == null) {
      throw new IllegalArgumentException("Players list was null");
    }
    this.players = players;
  }

  /**
   * Method called to update the scores of all players.
   */
  public void updateScores() {
    for (Player player : players) {
      updateScore(player);
    }
  }

  public void updateScore(Player player) {
    player.setScore(calculateScore(player));
  }

  private int calculateScore(Player player) {
    return MONEY_MULTIPLIER * player.getMoney() + TILE_MULTIPLIER * player.getTilesOwned()
        + RESOURCE_MULTIPLIER * player.getScorableResources();
  }
}
