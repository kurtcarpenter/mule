package team.map;

import team.game.containers.Terrain;

public class GameMap implements java.io.Serializable {
  private GameTile[][] grid;

  private String[][] mapLayout = { {"P","P","M1","P","R","P","M3","P","P"},
      {"P","M1","P","P","R","P","P","P","M3"}, {"M3","P","P","P","Town","P","P","P","M1"},
      {"P","M2","P","P","R","P","M2","P","P"}, {"P","P","M2","P","R","P","P","P","M2"} };

  /**
   * Creates a GameMap object.
   */
  public GameMap() {
    grid = new GameTile[5][9];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        Terrain terrain = Terrain.NONE;
        switch (mapLayout[i][j]) {
          case "P":
            terrain = Terrain.PLAIN;
            break;
          case "R":
            terrain = Terrain.RIVER;
            break;
          case "Town":
            terrain = Terrain.NONE;
            break;
          case "M1":
            terrain = Terrain.M1;
            break;
          case "M2":
            terrain = Terrain.M2;
            break;
          case "M3":
            terrain = Terrain.M3;
            break;
          default:
            terrain = Terrain.NONE;
            break;
        }
        grid[i][j] = new GameTile(i, j, terrain);
      }
    }
  }

  public GameTile[][] getGrid() {
    return grid.clone();
  }

  public GameTile getTile(int myX, int myY) {
    return grid[myX][myY];
  }
}