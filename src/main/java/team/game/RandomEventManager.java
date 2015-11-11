package team.game;

import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

import team.config.Player;
import team.game.containers.Resource;

public class RandomEventManager implements java.io.Serializable {

  private List<Player> players;
  private Random rand;
  private final int[] randFactor = {0, 25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};

  public RandomEventManager(List<Player> players) {
    this.players = players;
    rand = new Random(System.currentTimeMillis());
  }

  /**
   * Method called when a random event has been triggered.
   *
   * @param player the player for which the random event is being applied to.
   * @param round the current round number
   */
  public void triggerEvent(Player player, int round) {
    if (rand.nextInt(100) < 27) {
      System.out.println("Executing a random event!");
      int event = rand.nextInt(7) + 1;
      if (event > 4) {
        Comparator<Player> comp = (p1, p2) -> Integer.compare( p1.getScore(), p2.getScore());
        int minScore = players.stream().min(comp).get().getScore();
        if (player.getScore() == minScore) {
          System.out.println("This player has the lowest score so they can't have a bad event");
          return;
        }
      }

      switch (event) {
        case 1:
          player.setResourceQuantity(Resource.FOOD, player.getFood() + 3);
          player.setResourceQuantity(Resource.ENERGY, player.getFood() + 2);
          System.out.println("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND"
              + " 2 ENERGY UNITS.");
          break;
        case 2:
          player.setOre(player.getOre() + 2);
          System.out.println("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS"
              + " OF ORE.");
          break;
        case 3:
          player.setMoney(randFactor[round] * 8);
          System.out.println("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.");
          break;
        case 4:
          player.setMoney(randFactor[round] * 2);
          System.out.println("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.");
          break;
        case 5:
          player.setMoney(randFactor[round] * -4);
          System.out.println("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.");
          break;
        case 6:
          player.setResourceQuantity(Resource.FOOD, player.getFood() / 2);
          System.out.println("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF"
              + " YOUR FOOD.");
          break;
        case 7:
          player.setMoney(randFactor[round] * -6);
          System.out.println("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO"
              + " CLEAN IT UP.");
          break;
        default:
          System.out.println("Defaulted");
          break;
      }
    }
  }
}
