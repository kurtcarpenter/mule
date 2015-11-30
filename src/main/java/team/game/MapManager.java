package team.game;

import team.map.GameMap;
import team.Game.GameState;
import team.config.Player;
import team.game.containers.Resource;
import team.map.GameTile;
import team.game.containers.Terrain;

public class MapManager implements java.io.Serializable {

  private final TurnManager turnManager;
  private final LandSelectManager landSelectManager;
  private final MuleManager muleManager;
  private final GameMap gameMap;
  private int passCount;

  /**
   * Creates a MapManager object.
   * 
   * @param turnManager the TurnManager object being associated with this object
   * @param landSelectManager the LandSelectManager object being associated with this object
   * @param muleManager the MuleManager object being associated with this object
   * @param gameMap the GameMap object being associated with this object
   */
  public MapManager(TurnManager turnManager, LandSelectManager landSelectManager, 
      MuleManager muleManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.landSelectManager = landSelectManager;
    this.muleManager = muleManager;
    this.gameMap = gameMap;
    passCount = 0;
  }

  /**
   * Method used to buy land during the land selection phase or place a mule on land.
   * 
   * @param myX the x position of the tile being processed
   * @param myY the y position of the tile being processed
   */
  public boolean process(int myX, int myY) {
    if (turnManager.getGameState() == GameState.LAND_SELECT) {
      return landSelectManager.buyLand(myX, myY);
    } else {
      return muleManager.placeMule(myX, myY);
    }
  }

  /**
   * Method used to register that the user has passed their turn.
   */
  public int pass() {
    if (turnManager.getGameState() == GameState.LAND_SELECT) {
      passCount++;
      if (passCount == turnManager.getPlayers().size()) {
        turnManager.setState(GameState.MAIN);
      }
      turnManager.advanceStep();
    }
    return passCount;
  }

  /**
   * Calculates the production of the entire map and updates the score of each player's inventory.
   */
  public void productionMap() {
    GameTile[][] grid = gameMap.getGrid();
    Player currentPlayer = turnManager.getCurrentPlayer();
    int availableEnergy = currentPlayer.getResourceQuantity(Resource.ENERGY);
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        GameTile currentTile = grid[i][j];
        if (currentTile.getOwner() != null && currentTile.getOwner().equals(currentPlayer)) {
          if (availableEnergy > 0) {
            calculateProduction(currentTile, currentPlayer);
            availableEnergy--;
          } else {
            System.out.println("Not enough energy");
            return;
          }
        }
      }
    }
  }

  /**
   * Calculates the production granted to a player for a tile that they own.
   * 
   * @param gameTile the tile whose production is being calculated
   * @param player the player who owns the tile
   */
  public void calculateProduction(GameTile gameTile, Player player) {
    Terrain terrain = gameTile.getTerrain();
    Resource resource = gameTile.getMule();
    if (resource == null) {
      return;
    }
    int amount = 0;
    switch (resource) {
      case FOOD:
        System.out.println("Food Mule added resources to production");
        if (terrain.equals(Terrain.RIVER)) {
          amount = 4;
        } else if (terrain.equals(Terrain.PLAIN)) {
          amount = 2;
        } else {
          amount = 1;
        }
        break;
      case ENERGY:
        System.out.println("Energy Mule added resources to production");
        if (terrain.equals(Terrain.RIVER)) {
          amount = 2;
        } else if (terrain.equals(Terrain.PLAIN)) {
          amount = 3;
        } else {
          amount = 1;
        }
        break;
      case SMITHORE:
        System.out.println("Smithore Mule added resources to production");
        if (terrain.equals(Terrain.PLAIN)) {
          amount = 1;
        } else if (terrain.equals(Terrain.M1)) {
          amount = 2;
        } else if (terrain.equals(Terrain.M2)) {
          amount = 3;
        } else if (terrain.equals(Terrain.M3)) {
          amount = 4;
        }
        break;
      case CRYSTITE:
        System.out.println("Crystite Mule added resources to production");
        amount = new java.util.Random().nextInt(5);
        break;
      default:
        System.out.println("Defaulted");
        break;
    }
    player.setResourceQuantity(resource, amount);
  }
}
