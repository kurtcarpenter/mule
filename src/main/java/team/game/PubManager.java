package team.game;

import java.util.List;
import team.config.Player;
import team.game.TurnManager;
import team.game.TimerManager;
import java.util.Random;

public class PubManager {
  private List<Player> players;
  private TurnManager turnManager;
  private TimerManager timerManager;
  private Random r;

  private static final int[] ROUND_BONUS = {0, 50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150, 200};

  public PubManager(List<Player> players, TurnManager turnManager, TimerManager timerManager) {
    this.players = players;
    this.turnManager = turnManager;
    this.timerManager = timerManager;
    r = new Random(System.currentTimeMillis());
  }

  public void gamble() {
    int timeBonus = timerManager.getTime();
    int moneyBonus = ROUND_BONUS[turnManager.getCurrentTurn()] + r.nextInt(timeBonus);
    moneyBonus = moneyBonus > 250 ? 250 : moneyBonus;
    turnManager.getCurrentPlayer().setMoney(moneyBonus);
    timerManager.resetTimer();
  }
}
