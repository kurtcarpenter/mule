package team.game;

import java.util.List;
import team.config.Player;
import team.game.TurnManager;
import team.game.TimerManager;
import java.util.Random;

public class PubManager implements java.io.Serializable {
  private List<Player> players;
  private TurnManager turnManager;
  private transient TimerManager timerManager;
  private Random rand;
  private static final long serialVersionUID = 42L; 

  private static final int[] ROUND_BONUS = {0, 50, 50, 50, 100, 100, 100, 100, 150, 150, 150, 150,
      200};

  /**
   * Creats a PubManager object.
   * 
   * @param players the list of players
   * @param turnManager the TurnManager object being associated with this object
   * @param timerManager the TimerManager object being associated with this object
   */
  public PubManager(List<Player> players, TurnManager turnManager, TimerManager timerManager) {
    this.players = players;
    this.turnManager = turnManager;
    this.timerManager = timerManager;
    rand = new Random(System.currentTimeMillis());
  }

  /**
   * Method called when a player decides to gamble in the pub.
   */
  public void gamble() {
    int timeBonus = timerManager.getTime();
    int moneyBonus = ROUND_BONUS[turnManager.getCurrentTurn()] + rand.nextInt(timeBonus);
    moneyBonus = moneyBonus > 250 ? 250 : moneyBonus;
    turnManager.getCurrentPlayer().setMoney(moneyBonus);
    timerManager.resetTimer();
  }

  public void setTimerManager(TimerManager tm) {
    timerManager = tm;
  }
}
