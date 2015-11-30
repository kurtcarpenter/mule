package team.game;

import team.map.GameMap;

public class LandSelectManager implements java.io.Serializable {

  private TurnManager turnManager;
  private GameMap gameMap;

  public LandSelectManager(TurnManager turnManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.gameMap = gameMap;
  }

  /**
   * Method used for the current player to buy a piece of land.
   * 
   * @param myX x position of the land being bought
   * @param myY y position of the land being bought
   * @return true if the land was successfully bought, false otherwise
   */
  public boolean buyLand(int myX, int myY) {
    if (gameMap.getTile(myX, myY).getOwner() == null) {
      if (turnManager.getCurrentPlayer().getMoney() >= gameMap.getTile(myX, myY).getCost()) {
        turnManager.getCurrentPlayer().setTilesOwned(1);
        gameMap.getTile(myX, myY).setOwner(turnManager.getCurrentPlayer());
        if (turnManager.getCurrentTurn() > 2) {
          turnManager.getCurrentPlayer().addMoney(-1 * gameMap.getTile(myX, myY).getCost());
        }
        turnManager.advanceStep();
        return true;
      } else {
        System.out.println("You do not have enough money to buy this piece of land!");
        return false;
      }
    }
    return false;
  }
}