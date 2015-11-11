package team.game;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import team.config.Player;
import team.Game.GameState;
import team.game.ScoreManager;
import team.game.MapManager;
import team.game.RandomEventManager;
import team.config.Configuration;
import team.Game;


public class TurnManager implements java.io.Serializable {
  private int step;
  private int turn;
  private List<Player> players;
  private List<Player> currentTurnOrder;
  private int playerCount;
  private GameState currentState;
  private ScoreManager scoreManager;
  private MapManager mapManager;
  private Game game;

  /**
   * Creates a TurnManager object.
   * 
   * @param players the list of players
   * @param currentState the GameState that the game is currently in
   * @param scoreManager the ScoreManager object being associated with this object
   * @param game the Game object being associated with this object
   */
  public TurnManager(List<Player> players, GameState currentState,
      ScoreManager scoreManager, Game game) {
    this.players = players;
    this.turn = 1;
    this.step = 0;
    this.currentState = currentState;
    this.scoreManager = scoreManager;
    this.game = game;

    currentTurnOrder = new ArrayList<Player>();
  }

  public List<Player> getPlayers() {
    return players;
  }

  /**
   *Updates each player's score and turn order for the game.
   */
  public void regenerateList() {
    scoreManager.updateScores();
    currentTurnOrder.clear();
    for (Player p : players) {
      currentTurnOrder.add(p);
    }
    if (currentState != GameState.CONFIGURE) {
      Collections.sort(currentTurnOrder);
    }
  }

  /**
   * Advances step. Call this when a player is done (one player at a time).
   * This is the method you should be using in most cases.
   * Calling this method a bunch of times makes the Turn increment
   */
  public void advanceStep() {
    if (currentState == GameState.MAIN) {
      game.getMapManager().productionMap();
      game.getRandomEventManager().triggerEvent(getCurrentPlayer(), turn);
    }

    this.advanceStep(1);
  }

  /**
   * Advances step by a number. Call this when players are done
   * Calling this method may make the Turn increment
   * @param steps Number of steps to advance
   */
  public void advanceStep(int steps) {
    if ((step + steps) % players.size() == 0) {
      regenerateList();
    }
    step += steps;
    turn = step / players.size() + 1;
  }

  /**
   * Returns the current player and calls regenerateList if there is more than one player left in
   * the current turn.
   */
  public Player getCurrentPlayer() {
    if (currentTurnOrder.size() == 0) {
      regenerateList();
    }
    return currentTurnOrder.get(step % players.size());
  }

  public int getCurrentStep() {
    return step;
  }

  public int getCurrentTurn() {
    return turn;
  }

  public void setState(GameState state) {
    currentState = state;
  }

  public GameState getGameState() {
    return currentState;
  }
}
