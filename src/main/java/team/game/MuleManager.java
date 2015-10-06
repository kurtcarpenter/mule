package team.game;

import team.map.GameMap;
import team.Game.GameState;
import team.config.Player;
import team.game.containers.Resource;

public class MuleManager {

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
              curPlayer.receiveMule(null);
              System.out.println("Placed mule at (" + x + ", " + y + ")");
          } else {
              System.out.println("You don't own a mule to place here.");
          }
      } else {
          curPlayer.receiveMule(null);
          System.out.println("You don't own this tile!");
      }
  }

}
