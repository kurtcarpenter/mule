package team.game;

import team.map.GameMap;
import team.Game.GameState;
import team.config.Player;

public class MapManager {

  private final TurnManager turnManager;
  private final LandSelectManager landSelectManager;
  private final GameMap gameMap;
  private int passCount;

  public MapManager(TurnManager turnManager, LandSelectManager landSelectManager, GameMap gameMap) {
    this.turnManager = turnManager;
    this.landSelectManager = landSelectManager;
    this.gameMap = gameMap;
    passCount = 0;
  }

  public void process(int x, int y) {
    if (turnManager.getGameState() == GameState.LAND_SELECT) {
      landSelectManager.buyLand(x, y);
    } else {
      //other shit maybe view tile in the future
    }
  }

  public void pass() {
    passCount++;
    if (passCount == turnManager.getPlayers().size()) {
      turnManager.setState(GameState.MAIN);
    }
    turnManager.advanceStep();
  }
}
