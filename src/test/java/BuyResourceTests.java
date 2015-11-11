import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

import team.game.ScoreManager;
import team.game.StoreManager;
import team.game.TurnManager;
import team.game.containers.*;
import team.game.exceptions.*;
import team.config.Player.PlayerRace;
import team.config.Player.PlayerColor;
import team.config.Player;
import team.config.GameSettings.Difficulty;
import team.Game.GameState;
import team.game.containers.Resource;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Nathan Dass
 * @version 1.0
 */

public class BuyResourceTests {

	TurnManager turnManager;
	List<Player> players;
	GameState currentState;
    ScoreManager scoreManager;
    StoreManager storeManager;

	@Before
	public void setup() {
		players = new ArrayList<>();
		players.add(new Player("Player 1", PlayerRace.HUMAN, PlayerColor.RED));
		players.add(new Player("Player 2", PlayerRace.HUMAN, PlayerColor.BLUE));
		currentState = GameState.MAIN;
		scoreManager = new ScoreManager(players);
		turnManager = new TurnManager(players, currentState, scoreManager, null);
		storeManager = new StoreManager(Difficulty.BEGINNER, turnManager);
	}

	@Test(expected = PlayerTransactionException.class)
	public void throwsPlayerTransactionException() {
		try {
			storeManager.buyResource(Resource.MULE, 1);
		} catch (PlayerTransactionException pte) {
		} catch (StoreTransactionException ste) {
		}
	}

	@Test
	public void buyResource() {
		int prevPlayerOre = turnManager.getCurrentPlayer().getOre();
		int prevMoney = turnManager.getCurrentPlayer().getMoney();
		int prevStoreOre = storeManager.getResourceStock(Resource.SMITHORE);
		try {
			storeManager.buyResource(Resource.SMITHORE, 1);
		} catch (PlayerTransactionException pte) {
		} catch (StoreTransactionException ste) {
		}
		assertEquals(turnManager.getCurrentPlayer().getOre(), prevPlayerOre + 1);
		assertEquals(turnManager.getCurrentPlayer().getMoney(), prevMoney - storeManager.getPrice(Resource.SMITHORE));
		assertEquals(storeManager.getResourceStock(Resource.SMITHORE), prevStoreOre - 1);
	}

	@Test
	public void buyResources() {
		int prevPlayerOre = turnManager.getCurrentPlayer().getOre();
		int prevPlayerEnergy = turnManager.getCurrentPlayer().getEnergy();
		int prevMoney = turnManager.getCurrentPlayer().getMoney();
		int prevStoreOre = storeManager.getResourceStock(Resource.SMITHORE);
		int prevStoreEnergy = storeManager.getResourceStock(Resource.ENERGY);
		try {
			storeManager.buyResource(Resource.SMITHORE, 1);
			storeManager.buyResource(Resource.ENERGY, 1);
		} catch (PlayerTransactionException pte) {
		} catch (StoreTransactionException ste) {
		}
		assertEquals(turnManager.getCurrentPlayer().getOre(), prevPlayerOre + 1);
		assertEquals(turnManager.getCurrentPlayer().getEnergy(), prevPlayerEnergy + 1);
		assertEquals(turnManager.getCurrentPlayer().getMoney(), prevMoney - storeManager.getPrice(Resource.SMITHORE) - storeManager.getPrice(Resource.ENERGY));
		assertEquals(storeManager.getResourceStock(Resource.SMITHORE), prevStoreOre - 1);
		assertEquals(storeManager.getResourceStock(Resource.ENERGY), prevStoreEnergy - 1);
	}

	@Test(expected=PlayerTransactionException.class)
	public void notEnoughMoney() {
		turnManager.getCurrentPlayer().setMoney(turnManager.getCurrentPlayer().getMoney() - 1);
		try {
			storeManager.buyResource(Resource.SMITHORE, 1);
		} catch (PlayerTransactionException pte) {
		} catch (StoreTransactionException ste) {
		}
	}

	@Test(expected=StoreTransactionException.class)
	public void notEnoughResources() {
		try {
			storeManager.buyResource(Resource.SMITHORE, 500);
		} catch (PlayerTransactionException pte) {
		} catch (StoreTransactionException ste) {
		}
	}
}