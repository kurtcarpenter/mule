package team.map;

public class GameMap {
  GameTile[][] grid;

  public GameMap() {
    grid = new GameTile[5][9];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        grid[i][j] = new GameTile(i, j);
      }
    }
  }

  public GameTile[][] getGrid() {
    return grid;
  }

  public GameTile getTile(int x, int y) {
    return grid[x][y];
  }
}
