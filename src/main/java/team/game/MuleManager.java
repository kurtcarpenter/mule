package team.game;

import team.map.GameMap;
import team.Game.GameState;
import team.config.Player;
import team.game.containers.Resource;

public class MuleManager implements java.io.Serializable {

  private TurnManager turnManager;
  private GameMap gameMap;

  public MuleManager(TurnManager turnManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.gameMap = gameMap;
  }

  public void placeMule(int x, int y) {
      Player curPlayer = turnManager.getCurrentPlayer();
      if (gameMap.getTile(x, y).getOwner() == curPlayer) {
          if (curPlayer.getMule() != null) {
              gameMap.getTile(x, y).setMule(curPlayer.getMule());
              System.out.println("Placed " + curPlayer.getMule().toString() + "mule at (" + x + ", " + y + ")");
              curPlayer.receiveMule(null);
          } else {
              System.out.println("You don't own a mule to place here.");
          }
      } else {
          curPlayer.receiveMule(null);
          System.out.println("You don't own this tile!");
      }
  }

}
