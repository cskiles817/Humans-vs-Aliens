package gameplay;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;
import state.AiContext;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;
import gui.Gui;


import java.util.Random;

import static gui.Gui.e;


public class Simulator implements TimerObserver {
  private static Environment env;
  private static SimpleTimer timer;
  private static int numHumans;
  private static int numAliens;
  private static final int defaultHumanLifePoints = 15;
  private static final int defaultAlienLifePoints = 10;
  AiContext[] humanContexts;
  AiContext[] alienContexts;

  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) {
    env = e;
    Simulator.timer = timer;
    Simulator.numHumans = numHumans;
    Simulator.numAliens = numAliens;

    timer.addTimeObserver(this);

    // add humans
    humanContexts = new AiContext[numHumans];
    for (int i = 0; i < numHumans; i++) {
      humanContexts[i] = new AiContext(new Human(getRandHumanName(), defaultHumanLifePoints, getRandArmor()), env);
      timer.addTimeObserver(humanContexts[i]);
//      addLifeForm(humanContexts[i].getLifeForm());
//      addWeapon();
    }

    //add aliens
    alienContexts = new AiContext[numAliens];
    for (int j = 0; j < numAliens; j++) {
      alienContexts[j] = new AiContext(new Alien(getRandAlienName(), defaultAlienLifePoints, getRandRecovery()), env);
      timer.addTimeObserver(alienContexts[j]);
//      addLifeForm(alienContexts[j].getLifeForm());
//      addWeapon();
    }
  }

  public static void main(String[] args) throws EnvironmentException {
    try {
      Gui gui = new Gui();
      e.addObserver(gui);
      SimpleTimer timer = new SimpleTimer(1000);
      Simulator sim = new Simulator(e, timer, 5, 5);
      timer.start();
    } catch (RecoveryRateException e) {
      e.printStackTrace();
    }
  }


  private static void addLifeForm(LifeForm l) {
    int lRow, lCol;
    do {
      lRow = getRandRow();
      lCol = getRandCol();
    } while (env.getLifeForm(lRow, lCol) != null);
    env.addLifeForm(l, lRow, lCol);
  }

  private static void addWeapon() {
    int wRow, wCol;
    do {
      wRow = getRandRow();
      wCol = getRandCol();
    } while (!canAddWeapon(wRow, wCol));
    env.addWeapon(getRandWeapon(), wRow, wCol);
  }

  private static final Random r = new Random();
  
  private static boolean canAddWeapon(int row, int col) {
    return env.getWeapons(row, col)[0] == null || env.getWeapons(row, col)[1] == null;
  }

  private static Weapon getRandWeapon() {
    int choice = r.nextInt(3);
    switch (choice) {
      case 0:
        return new Pistol();
      case 1:
        return new ChainGun();
      case 2:
        return new PlasmaCannon();
      default:
        System.out.println("Error in getRandWeapon");
        break;
    }
    return null;
  }
  
  private static RecoveryBehavior getRandRecovery() {
    int choice = r.nextInt(3);
    switch (choice) {
      case 0:
        return new RecoveryNone();
      case 1:
        return new RecoveryLinear(r.nextInt(defaultAlienLifePoints));
      case 2:
        return new RecoveryFractional(r.nextDouble());
      default:
        System.out.println("Error in getRandRecovery");
        break;
    }
    return null;
  }
  
  private static String getRandAlienName() {
    String[] alienNames = {
            "Zorvax", "Xylar", "Quorin", "Veltrix", "Nyxala",
            "Tharvok", "Lumora", "Zephyrax", "Krylon", "Orlith",
            "Varnak", "Elzith", "Axion", "Myrdal", "Phoraxx",
            "Galdrin", "Syntara", "Drexyl", "Xantor", "Obelix"
    };

    // Return random element from list above
    return alienNames[r.nextInt(alienNames.length)];
  }

  private static String getRandHumanName() {
    String[] popularNames = { // Thank you chatgpt
            "Olivia", "Emma", "Sophia", "Isabella", "Mia",
            "Liam", "Noah", "Elijah", "James", "Lucas",
            "Taylor", "Riley", "Jordan", "Avery", "Alex",
            "Muhammad", "Aria", "Sofia", "Diego", "Yuki"
    };
    // Return random element from list above
    return popularNames[r.nextInt(popularNames.length)];
  }

  private static int getRandArmor() {
    return (int) (Math.random() * 9);
  }

  private static int getRandRow() {
    return (int) (Math.random() * env.getNumRows());
  }

  private static int getRandCol() {
    return (int) (Math.random() * env.getNumCols());
  }

  @Override
  public void updateTime(int time) {
    for (AiContext context : humanContexts) {
      context.updateTime(time);
    }
    for (AiContext context : alienContexts) {
      context.updateTime(time);
    }
    // TODO check if someone wins
  }
}
