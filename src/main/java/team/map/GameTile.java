package team.map;

import team.config.Player;
import team.game.containers.Resource;
import team.game.containers.Terrain;

public class GameTile implements java.io.Serializable {
  private final int x;
  private final int y;
  private Player owner;
  private Resource muleType;
  private static final int cost = 1000;

  public GameTile(int x, int y) {
    this.x = x;
    this.y = y;
    muleType = null;
  }

  public int getCost() {
    return cost;
  }

  public void setOwner(Player p) {
    this.owner = p;
  }

  public Player getOwner() {
    return owner;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setMule(Resource muleType) {
      this.muleType = muleType;
  }

  public Resource getMule() {
      return muleType;
  }

  public Terrain getTerrain() {
    // TODO: IMPLEMENT THIS
    return Terrain.RIVER;
  }
}
