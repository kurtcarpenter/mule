package team.map;

public class GameMap implements java.io.Serializable {
  private GameTile[][] grid;

  /**
   * Creates a GameMap object.
   */
  public GameMap() {
    grid = new GameTile[5][9];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = new GameTile(i, j);
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
