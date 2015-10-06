package team.game;

import team.game.exceptions.PlayerTransactionException;
import team.game.exceptions.StoreTransactionException;
import team.config.GameSettings.Difficulty;
import team.game.TurnManager;
import team.config.Player;
import team.game.containers.Resource;

import java.util.Map;
import java.util.HashMap;

public class StoreManager {
  private HashMap<Resource, Integer> resourceStorage;
  private HashMap<Resource, Integer> prices;
  private TurnManager turnManager;
  private static final int[] beginnerQuantities = {16, 16, 0, 0, 25};
  private static final int[] stdTrnyQuantities = {8, 8, 8, 0, 14};
  private static final int[] startingPrices = {30, 25, 50, 100, 100};
  private static final int[] muleConfigPrices = {25, 50, 75, 100};

  public StoreManager(Difficulty difficulty, TurnManager turnManager) {
    this.turnManager = turnManager;
    resourceStorage = new HashMap<Resource, Integer>();
    prices = new HashMap<Resource, Integer>();

    for (Resource r : Resource.values()) {
      if (difficulty == Difficulty.BEGINNER) {
        resourceStorage.put(r, beginnerQuantities[r.ordinal()]);
      } else {
        resourceStorage.put(r, stdTrnyQuantities[r.ordinal()]);
      }

      prices.put(r, startingPrices[r.ordinal()]);
    }
  }

  public void buyResource(Resource resource, int quantity)
      throws PlayerTransactionException, StoreTransactionException {
    Player player = turnManager.getCurrentPlayer();
    if (resource == Resource.MULE) {
      throw new PlayerTransactionException("Don't use this method to buy Mule");
    }
    verifyPurchase(resource, quantity);
    player.setMoney(-1 * prices.get(resource) * quantity);
    player.setResourceQuantity(resource, quantity);
    resourceStorage.put(resource, resourceStorage.get(resource) - quantity);
  }

  public void sellResource(Resource resource, int quantity)
      throws PlayerTransactionException, StoreTransactionException {
    Player player = turnManager.getCurrentPlayer();

    if (player.getResourceQuantity(resource) < quantity) {
      throw new PlayerTransactionException("Player has insufficient resources to sell");
    }
    player.setMoney(prices.get(resource) * quantity);
    player.setResourceQuantity(resource, -1 * quantity);
    resourceStorage.put(resource, resourceStorage.get(resource) + quantity);
  }

  public void buyMule(Resource resource, Resource muleType, int quantity)
      throws PlayerTransactionException, StoreTransactionException {
    if (muleType == Resource.MULE)
      throw new PlayerTransactionException("Cannot buy a Mule-type Mule");
    if (quantity > 1)
      throw new PlayerTransactionException("Cannot buy more than one mule at a time.");
    if (quantity == 0)
        return;
    Player player = turnManager.getCurrentPlayer();
    if (player.getResourceQuantity(Resource.MULE) >= 1)
      throw new PlayerTransactionException("Player already has a mule. Place the mule or sell the mule to buy another one.");
    verifyPurchase(resource, quantity);
    player.setMoney(-1 * prices.get(resource) * quantity);
    player.receiveMule(muleType);
    resourceStorage.put(resource, resourceStorage.get(resource) - quantity);
  }

  private void verifyPurchase(Resource resource, int quantity)
      throws PlayerTransactionException, StoreTransactionException {
    Player player = turnManager.getCurrentPlayer();

    int currentAmount = resourceStorage.get(resource);
    int currentPrice = prices.get(resource);

    if (quantity > currentAmount) {
      throw new StoreTransactionException("Store does not have enough resources");
    }
    if (player.getMoney() < currentPrice * quantity) {
      throw new PlayerTransactionException("Player does not have enough money");
    }
  }

  public int getResourceStock(Resource resource) {
    return resourceStorage.get(resource);
  }
}
