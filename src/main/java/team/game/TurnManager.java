package team.game;

import java.util.List;
import team.config.Player;
import team.Game.GameState;

public class TurnManager {
  private int step;
  private int turn;
  private List<Player> players;
  private int playerCount;
  private GameState currentState;

  public TurnManager(List<Player> players, GameState currentState) {
    this.players = players;
    this.playerCount = players.size();
    this.turn = 1;
    this.step = 0;
    this.currentState = currentState;
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
    step += steps;
    turn = step / playerCount + 1;
  }

  /**
   * Advances turn. Should not usually be called, unless all players end and
   * you do not advance steps.
   */
  public void advanceTurn() {
    this.advanceTurn(1);
  }

  /**
   * Advances turn by a number. Should not usually be called.
   * Used for skipping a bunch of time into the future.
   */
  public void advanceTurn(int turns) {
    step += playerCount * turns;
    turn += turns;
  }

  public Player getCurrentPlayer() {
    return players.get(step % playerCount);
  }

  public int getCurrentStep() {
    return step;
  }

  public int getCurrentTurn() {
    return turn;
  }

  public GameState getGameState() {
    return currentState;
  }
}
