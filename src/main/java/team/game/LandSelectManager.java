package team.game;

import team.map.GameMap;

public class LandSelectManager {

  private TurnManager turnManager;
  private GameMap gameMap;

  public LandSelectManager(TurnManager turnManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.gameMap = gameMap;
  }

  public boolean buyLand(int x, int y) {
    if (gameMap.getTile(x, y).getOwner() == null) {
      turnManager.getCurrentPlayer().setTilesOwned(1);
      gameMap.getTile(x, y).setOwner(turnManager.getCurrentPlayer());
      if (turnManager.getCurrentTurn() > 2) {
        turnManager.getCurrentPlayer().setMoney( -1 * gameMap.getTile(x, y).getCost());
      }
      turnManager.advanceStep();
      return true;
    }
    return false;
  }
}
