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
  private final int chance;

  /**
   * Creates a RandomEventManager object.
   *
   * @param players the list of players
   * @param chance the chance for the random event
   */
  public RandomEventManager(List<Player> players, int chance) {
    this.players = players;
    rand = new Random(System.currentTimeMillis());
    this.chance = chance;
  }

  public void updateSettings(List<Player> players) {
    this.players = players;
  }

  /**
   * Method called when a random event has been triggered.
   *
   * @param player the player for which the random event is being applied to.
   * @param round the current round number
   */
  public void triggerEvent(Player player, int round) {
    if (rand.nextInt(100) < 27 || chance >= 1) {
      int event = rand.nextInt(7) + 1;
      if (chance >= 1) {
        event = chance;
      } else if (event > 4) {
        Comparator<Player> comp = (p1, p2) -> Integer.compare( p1.getScore(), p2.getScore());
        int minScore = players.stream().min(comp).get().getScore();
        if (player.getScore() == minScore) {
          return;
        }
      }


      switch (event) {
        case 1:
          player.addResourceQuantity(Resource.FOOD, 3);
          player.addResourceQuantity(Resource.ENERGY, 2);
          System.out.println("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND"
              + " 2 ENERGY UNITS.");
          break;
        case 2:
          player.addResourceQuantity(Resource.SMITHORE, 2);
          System.out.println("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS"
              + " OF ORE.");
          break;
        case 3:
          player.addMoney(randFactor[round] * 8);
          System.out.println("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $"
              + randFactor[round] * 8 + ".");
          break;
        case 4:
          player.addMoney(randFactor[round] * 2);
          System.out.println("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $"
              + randFactor[round] * 2 + ".");
          break;
        case 5:
          player.addMoney(randFactor[round] * -4);
          System.out.println("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $"
              + randFactor[round] * 4 + ".");
          break;
        case 6:
          player.addResourceQuantity(Resource.FOOD, -1 * player.getFood() / 2);
          System.out.println("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF"
              + " YOUR FOOD.");
          break;
        case 7:
          player.addMoney(randFactor[round] * -6);
          System.out.println("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $"
              + randFactor[round] * -6 + " CLEAN IT UP.");
          break;
        case 8:
          player.addResourceQuantity(Resource.FOOD, 10);
          System.out.println("You have been given 10 units of food, cuz hungry");
          break;
        case 9:
          player.addMoney(10000);
          System.out.println("You have won the lottery! $10000 :)");
          break;
        default:
          System.out.println("Defaulted");
          break;
      }
    }
  }
}
