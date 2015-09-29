package team.game;

import java.util.List;
import team.config.Player;
import team.game.TurnManager;
import java.util.Random;

public class PubManager {
  private List<Player> players;
  private TurnManager turnManager;
  private Random r;

  private static final int[] ROUND_BONUS = {0, 50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

  public PubManager(List<Player> players, TurnManager turnManager) {
    this.players = players;
    this.turnManager = turnManager;
    r = new Random(System.currentTimeMillis());
  }

  public void gamble() {
    int timeBonus = 60; // Need Timer code here
    int moneyBonus = ROUND_BONUS[turnManager.getCurrentTurn()] * r.nextInt(timeBonus);
    turnManager.getCurrentPlayer().incScore(moneyBonus);
    turnManager.advanceStep();
  }
}
