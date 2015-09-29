package team.game;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import team.config.Player;
import team.Game.GameState;
import team.game.ScoreManager;

public class TurnManager {
  private int step;
  private int turn;
  private List<Player> players;
  private List<Player> currentTurnOrder;
  private int playerCount;
  private GameState currentState;
  private ScoreManager scoreManager;

  public TurnManager(List<Player> players, GameState currentState,
      ScoreManager scoreManager) {
    this.players = players;
    this.turn = 1;
    this.step = 0;
    this.currentState = currentState;
    this.scoreManager = scoreManager;

    currentTurnOrder = new ArrayList<Player>();
    regenerateList();
  }

  public List<Player> getPlayers() {
    return players;
  }

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
    this.advanceStep(1);
  }

  /**
   * Advances step by a number. Call this when players are done
   * Calling this method may make the Turn increment
   * @param steps Number of steps to advance
   */
  public void advanceStep(int steps) {
    if (step % players.size() == 0 && step != 0 && step != 1) {
      regenerateList();
    }
    step += steps;
    turn = step / players.size() + 1;
  }

  /**
   * Advances turn. Should not usually be called, unless all players end and
   * you do not advance steps.
   */
  public void advanceTurn() {
    if (step % players.size() == 0) {
      regenerateList();
    }
    this.advanceTurn(1);
  }

  /**
   * Advances turn by a number. Should not usually be called.
   * Used for skipping a bunch of time into the future.
   */
  public void advanceTurn(int turns) {
    if (step % players.size() == 0) {
      regenerateList();
    }
    step += players.size() * turns;
    turn += turns;
  }

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
