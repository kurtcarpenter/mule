package team.map;

import team.config.Player;
import team.game.containers.Resource;
import team.game.containers.Terrain;

public class GameTile implements java.io.Serializable {
  private final int myX;
  private final int myY;
  private Player owner;
  private Resource muleType;
  private Terrain terrain;
  private static final int cost = 1000;

  /**
   * Creates a GameTile object at a specified position.
   *
   * @param myX the x position of the object
   * @param myY the y position of the object
   */
  public GameTile(int myX, int myY, Terrain myTerrain) {
    this.myX = myX;
    this.myY = myY;
    terrain = myTerrain;
    muleType = null;
  }

  public int getCost() {
    return cost;
  }

  public void setOwner(Player player) {
    this.owner = player;
  }

  public Player getOwner() {
    return owner;
  }

  public int getX() {
    return myX;
  }

  public int getY() {
    return myY;
  }

  public void setMule(Resource muleType) {
    this.muleType = muleType;
  }

  public Resource getMule() {
    return muleType;
  }

  public Terrain getTerrain() {
    return terrain;
  }
}
