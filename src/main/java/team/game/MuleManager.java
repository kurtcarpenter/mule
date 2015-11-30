package team.game;

import team.map.GameMap;
import team.Game.GameState;
import team.config.Player;
import team.game.containers.Resource;
import team.game.containers.Terrain;

public class MuleManager implements java.io.Serializable {

  private TurnManager turnManager;
  private GameMap gameMap;

  public MuleManager(TurnManager turnManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.gameMap = gameMap;
  }

  /**
   * Method called to place a mule on a piece of land if the player owns that piece of land.
   * 
   * @param myX the x position of the tile
   * @param myY the y position of the tile
   * @return true if a mule was placed, false otherwise
   */
  public boolean placeMule(int myX, int myY) {
    Player currPlayer = turnManager.getCurrentPlayer();
    if (gameMap.getTile(myX, myY).getOwner() == currPlayer) {
      if (gameMap.getTile(myX, myY).getMule() != null) {
        System.out.println("There is already a mule on this tile. You lost your mule.");
        currPlayer.receiveMule(null);
      }
      if (currPlayer.getMule() != null) {
        if (!((currPlayer.getMule() == Resource.SMITHORE || currPlayer.getMule()
            == Resource.CRYSTITE) && gameMap.getTile(myX, myY).getTerrain() == Terrain.RIVER)) {
          gameMap.getTile(myX, myY).setMule(currPlayer.getMule());
          System.out.println("Placed " + currPlayer.getMule().toString() + "mule at (" + myX + ", "
              + myY + ")");
          currPlayer.receiveMule(null);
          return true;
        } else {
          System.out.println("You cannot place a smithore or crystite mule on a river tile.");
          return false;
        }
      } else {
        System.out.println("You don't own a mule to place here.");
        return false;
      }
    } else {
      currPlayer.receiveMule(null);
      System.out.println("You don't own this tile! You lost your mule.");
      return false;
    }
  }
}