import org.junit.Test;
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
import team.config.Game.GameState;
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
		storeManager.buyResource(Resource.MULE, 1);
	}

	@Test
	public void buyResource() {
		int prevPlayerOre = turnManager.getCurrentPlayer().getOre();
		int prevMoney = turnManager.getCurrentPlayer().getMoney();
		int prevStoreOre = storeManager.getResource(Resource.ORE);
		storeManager.buyResource(Resource.ORE, 1);
		assertEquals(turnManager.getCurrentPlayer().getOre(), prevPlayerOre + 1);
		assertEquals(turnManager.getCurrentPlayer.getMoney, prevMoney - storeManager.getPrice(Resource.ORE));
		assertEquals(storeManager.getResource(Resource.ORE), prevStoreOre - 1);
	}

	@Test
	public void buyResources() {
		int prevPlayerOre = turnManager.getCurrentPlayer().getOre();
		int prevPlayerEnergy = turnManager.getCurrentPlayer().getEnergy();
		int prevMoney = turnManager.getCurrentPlayer().getMoney();
		int prevStoreOre = storeManager.getResource(Resource.ORE);
		int prevStoreEnergy = storeManager.getResource(Resource.ENERGY);
		storeManager.buyResource(Resource.ORE, 1);
		storeManager.buyResource(Resource.ENERGY, 1);
		assertEquals(turnManager.getCurrentPlayer().getOre(), prevPlayerOre + 1);
		assertEquals(turnManager.getCurrentPlayer().getEnergy(), prevPlayerEnergy + 1);
		assertEquals(turnManager.getCurrentPlayer.getMoney, prevMoney - storeManager.getPrice(Resource.ORE) - storeManager.getPrice(Resource.ENERGY));
		assertEquals(storeManager.getResource(Resource.ORE), prevStoreOre - 1);
		assertEquals(storeManager.getResource(Resource.ENERGY), prevStoreEnergy - 1);
	}

	@Test(expected = PlayerTransactionException.class)
	public void notEnoughMoney() {
		turnManager.getCurrentPlayer().setMoney(turnManager.getCurrentPlayer().getMoney - 1);
		storeManager.buyResource(Resource.ORE, 1);
	}

	@Test(expected = StoreTransactionException.class)
	public void notEnoughResources() {
		storeManager.buyResource(Resource.ORE, 500);
	}
}