package team.game;

import team.map.GameMap;
import team.Game.GameState;

public class MapManager {

  private final TurnManager turnManager;
  private final LandSelectManager landSelectManager;
  private final GameMap gameMap;

  public MapManager(TurnManager turnManager, LandSelectManager landSelectManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.landSelectManager = landSelectManager;
    this.gameMap = gameMap;
  }

  public void process(int x, int y) {
    if (turnManager.getGameState() == GameState.LAND_SELECT) {
      landSelectManager.buyLand(x, y);
    } else {
      //other shit maybe view tile in the future
    }
  }
}
