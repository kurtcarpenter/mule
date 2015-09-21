package team.map;

public class GameMap {
  GameTile[][] grid;

  public GameMap() {
    grid = new GameTile[5][9];
    for (int i = 0; i < tileArray.length; i++)
      for (int j = 0; i < tileArray[0].length; j++) {
        tileArray[i][j] = new GameTile(i, j);
      }
    }
  }

  public GameTile[][] getGrid() {
    return grid;
  }

  public Tile getTile(int x, int y) {
    return grid[x][y];
  }
}
