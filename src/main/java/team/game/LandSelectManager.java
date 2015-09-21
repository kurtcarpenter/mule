package team.game;

import team.map.GameMap;

public class LandSelectManager {

  private TurnManager turnManager;
  private GameMap gameMap;

  public LandSelectManager(TurnManager turnManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.gameMap = gameMap;
  }

  public void buyLand(int x, int y) {
    gameMap.getTile(x, y).setOwner(turnManager.getCurrentPlayer());
    if (turnManager.getCurrentTurn() > 2) {
      turnManager.getCurrentPlayer().setMoney( -1 * gameMap.getTile(x, y).getCost());
    }
    turnManager.advanceStep();
  }
}
