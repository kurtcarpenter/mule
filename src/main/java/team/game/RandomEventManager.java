package team.game;

import java.util.List;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

import team.config.Player;
import team.game.containers.Resource;

public class RandomEventManager {

  private List<Player> players;
  private Random rand;
  private final int[] randFactor = {0, 25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};

  public RandomEventManager(List<Player> players) {
    this.players = players;
    rand = new Random(System.currentTimeMillis());
  }

  public void triggerEvent(Player p, int round) {
    if (rand.nextInt(100) < 27) {
      System.out.println("Executing a random event!");
      int event = rand.nextInt(7) + 1;
      if (event > 4) {
        Comparator<Player> comp = (p1, p2) -> Integer.compare( p1.getScore(), p2.getScore());
        int minScore = players.stream().min(comp).get().getScore();
        if (p.getScore() == minScore) {
          System.out.println("This player has the lowest score so they can't have a bad event");
          return;
        }
      }
      switch (event) {
           case 1:  p.setResourceQuantity(Resource.FOOD, p.getFood() + 3);
                    p.setResourceQuantity(Resource.ENERGY, p.getFood() + 2);
                    break;
           case 2:  p.setOre(p.getOre() + 2);
                    break;
           case 3:  p.setMoney(randFactor[round] * 8);
                    break;
           case 4:  p.setMoney(randFactor[round] * 2);
                    break;
           case 5:  p.setMoney(randFactor[round] * -4);
                    break;
           case 6:  p.setResourceQuantity(Resource.FOOD, p.getFood()/2);
                    break;
           case 7:  p.setMoney(randFactor[round] * -6);
                    break;
       }
    }
  }
}
