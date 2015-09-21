package team.map;

import team.config.Player;

public class GameTile {
  private final int x;
  private final int y;
  private Player owner;
  private final int cost = 1000;

  public GameTile(int x, int y) {
    this.x = x;
    this.y = y;
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
}
