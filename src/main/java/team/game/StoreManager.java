package team.game;

import team.game.exceptions.PlayerTransactionException;
import team.game.exceptions.StoreTransactionException;
import team.config.GameSettings.Difficulty;
import team.game.TurnManager;
import team.config.Player;
import team.game.containers.Resource;

import java.util.Map;
import java.util.HashMap;

public class StoreManager implements java.io.Serializable {
  private HashMap<Resource, Integer> resourceStorage;
  private HashMap<Resource, Integer> prices;
  private TurnManager turnManager;
  private Difficulty difficulty;
  private static final int[] beginnerQuantities = {16, 16, 0, 0, 0, 25};
  private static final int[] stdTrnyQuantities = {8, 8, 8, 0, 0, 14};
  private static final int[] startingPrices = {30, 25, 50, 100, 0, 100};
  private static final int[] muleConfigPrices = {25, 50, 75, 100, 900};

  /**
   * Creates a StoreManager object.
   * 
   * @param difficulty the difficulty associated with this object
   * @param turnManager the TurnManager associated with this object
   */
  public StoreManager(Difficulty difficulty, TurnManager turnManager) {
    this.turnManager = turnManager;
    resourceStorage = new HashMap<Resource, Integer>();
    prices = new HashMap<Resource, Integer>();
    this.difficulty = difficulty;

    for (Resource r : Resource.values()) {
      if (this.difficulty == Difficulty.BEGINNER) {
        resourceStorage.put(r, beginnerQuantities[r.ordinal()]);
      } else {
        resourceStorage.put(r, stdTrnyQuantities[r.ordinal()]);
      }
      prices.put(r, startingPrices[r.ordinal()]);
    }
  }

  public void updateSettings(Difficulty difficulty) {
    this.difficulty = difficulty;
    for (Resource r : Resource.values()) {
      if (difficulty == Difficulty.BEGINNER) {
        resourceStorage.put(r, beginnerQuantities[r.ordinal()]);
      } else {
        resourceStorage.put(r, stdTrnyQuantities[r.ordinal()]);
      }
      prices.put(r, startingPrices[r.ordinal()]);
    }
  }

  /**
   * Method called when the current player wants to buy a resource.
   * 
   * @param resource the type of resource being bought
   * @param quantity amount of resource being bought
   * @throws PlayerTransactionException if this method is called for buying a mule
   */
  public void buyResource(Resource resource, int quantity)
      throws PlayerTransactionException, StoreTransactionException {
    Player player = turnManager.getCurrentPlayer();
    if (resource == Resource.MULE) {
      throw new PlayerTransactionException("Don't use this method to buy Mule");
    }
    verifyPurchase(resource, quantity);
    player.addMoney(-1 * prices.get(resource) * quantity);
    player.addResourceQuantity(resource, quantity);
    resourceStorage.put(resource, resourceStorage.get(resource) - quantity);
  }

  /**
   * Method called when the current player wants to sell a resource.
   * 
   * @param resource the type of resource being sold
   * @param quantity amount of resource being sold
   * @throws PlayerTransactionException if the player tries to sell more than they actually have
   */
  public void sellResource(Resource resource, int quantity)
      throws PlayerTransactionException {
    Player player = turnManager.getCurrentPlayer();

    if (player.getResourceQuantity(resource) < quantity) {
      throw new PlayerTransactionException("Player has insufficient resources to sell");
    }
    player.addMoney(prices.get(resource) * quantity);
    player.addResourceQuantity(resource, -1 * quantity);
    resourceStorage.put(resource, resourceStorage.get(resource) + quantity);
  }

  /**
   * Buys a mule for the current player with the specified type.
   * 
   * @param resource type of resource being bought (will always be Resource.MULE)
   * @param muleType type of mule being bought
   * @param quantity amount of resource being bought (can only be less than one)
   * @throws PlayerTransactionException if the player tries to buy a mule of type mule
   * @throws PlayerTransactionException if quantity is larger than one
   * @throws PlayerTransactionException if the player already has at least one mule
   * @throws PlayerTransactionException if the player does not have enough money to buy a mule
   */
  public void buyMule(Resource resource, Resource muleType, int quantity)
      throws PlayerTransactionException, StoreTransactionException {
    if (muleType == Resource.MULE) {
      throw new PlayerTransactionException("Cannot buy a Mule-type Mule");
    }
    if (quantity > 1) {
      throw new PlayerTransactionException("Cannot buy more than one mule at a time.");
    }
    if (quantity == 0) {
      return;
    }
    Player player = turnManager.getCurrentPlayer();
    if (player.getResourceQuantity(Resource.MULE) >= 1) {
      throw new PlayerTransactionException("Player already has a mule. Place the mule or sell the"
          + " mule to buy another one.");
    }
    verifyPurchase(resource, quantity);
    if (player.getMoney() < (prices.get(resource) + muleConfigPrices[muleType.ordinal()])
        * quantity) {
      throw new PlayerTransactionException("Player does not have enough money");
    }

    player.addMoney(-1 * (prices.get(resource) + muleConfigPrices[muleType.ordinal()])
        * quantity);
    player.receiveMule(muleType);
    resourceStorage.put(resource, resourceStorage.get(resource) - quantity);
  }

  /**
   * Sells a mule for the current player with the specified type.
   * 
   * @param muleType type of mule being sold
   * @throws PlayerTransactionException if the player tries to sell a mule they do not own
   */
  public void sellMule(Resource muleType) throws PlayerTransactionException {
    Player player = turnManager.getCurrentPlayer();

    if (player.getMule() != muleType) {
      throw new PlayerTransactionException("Player cannot sell a mule they do not own");
    }
    player.addMoney(prices.get(Resource.MULE) + muleConfigPrices[muleType.ordinal()]);
    player.addResourceQuantity(Resource.MULE, -1);
    resourceStorage.put(Resource.MULE, resourceStorage.get(Resource.MULE) + 1);
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

  public int getPrice(Resource resource) {
    return prices.get(resource);
  }
}
