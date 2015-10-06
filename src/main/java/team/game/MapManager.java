package team.game;

import team.map.GameMap;
import team.Game.GameState;
import team.config.Player;

public class MapManager {

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

  public void pass() {
    if (turnManager.getGameState() == GameState.LAND_SELECT) {
      passCount++;
      if (passCount == turnManager.getPlayers().size()) {
        turnManager.setState(GameState.MAIN);
      }
      turnManager.advanceStep();
    }
  }
}
