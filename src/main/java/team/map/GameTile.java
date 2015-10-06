package team.map;

import team.config.Player;
import team.config.Player.Mule;

public class GameTile {
  private final int x;
  private final int y;
  private Player owner;
  private Mule mule;
  private final int cost = 1000;

  public GameTile(int x, int y) {
    this.x = x;
    this.y = y;
    mule = Mule.NONE;
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

  public void setMule(Mule mule) {
      this.mule = mule;
  }

  public Mule getMule() {
      return mule;
  }
}
