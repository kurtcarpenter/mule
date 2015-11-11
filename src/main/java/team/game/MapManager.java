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

  public MapManager(TurnManager turnManager, LandSelectManager landSelectManager,
                        MuleManager muleManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.landSelectManager = landSelectManager;
    this.muleManager = muleManager;
    this.gameMap = gameMap;
    passCount = 0;
  }

  public boolean process(int x, int y) {
    if (turnManager.getGameState() == GameState.LAND_SELECT) {
      return landSelectManager.buyLand(x, y);
    } else {
      //other shit maybe view tile in the future

      muleManager.placeMule(x, y);
      return false;
    }
  }

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
            System.out.println("not enough energy");
            return;
          }
        }
      }
    }
  }

  public void calculateProduction(GameTile g, Player p) {
    Terrain t = g.getTerrain();
    Resource r = g.getMule();
    if (r == null) {
      return;
    }
    int amount = 0;
    switch (r) {
      case FOOD:
        System.out.println("Food Mule added resources to production");
        if (t.equals(Terrain.RIVER)) {
          amount = 4;
        } else if (t.equals(Terrain.PLAIN)) {
          amount = 2;
        } else {
          amount = 1;
        }
        break;
      case ENERGY:
        System.out.println("Energy Mule added resources to production");
        if (t.equals(Terrain.RIVER)) {
          amount = 2;
        } else if (t.equals(Terrain.PLAIN)) {
          amount = 3;
        } else {
          amount = 1;
        }
        break;
      case SMITHORE:
        System.out.println("Smithore Mule added resources to production");
        if (t.equals(Terrain.PLAIN)) {
          amount = 1;
        } else if (t.equals(Terrain.M1)) {
          amount = 2;
        } else if (t.equals(Terrain.M2)) {
          amount = 3;
        } else if (t.equals(Terrain.M3)) {
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
    p.setResourceQuantity(r, amount);
  }
}
