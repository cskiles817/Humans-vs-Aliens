package gameplay;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import gui.Gui;
import lifeform.Human;
import lifeform.Alien;
import lifeform.LifeForm;
import recovery.RecoveryBehavior;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;
import recovery.RecoveryFractional;
import state.AiContext;
import weapon.Pistol;
import weapon.ChainGun;
import weapon.PlasmaCannon;
import weapon.Weapon;

import java.util.Random;

public class Simulator implements TimerObserver {
  private Environment environment;
  private SimpleTimer timer;
  private int numHumans;
  private int numAliens;
  private AiContext[] humanContexts;
  private AiContext[] alienContexts;

  /**
   * Creates the GUI, and the SimpleTimer
   * @param args arguments
   */
  public static void main(String[] args) {
    try {
      Gui gui = new Gui();
      Environment env = Gui.e;
      env.addObserver(gui);

      SimpleTimer timer = new SimpleTimer(1000);
      Simulator simulator = new Simulator(env, timer, 10, 10);
      timer.addTimeObserver(simulator);

      timer.run();

    } catch (RecoveryRateException | EnvironmentException e) {
      e.printStackTrace();
    }
  }

  /**
   * This populates the environment and everything in the environment
   * @param env environment
   * @param timer game timer
   * @param numHumans number of humans on the game board
   * @param numAliens number of aliens on the game board
   */
  public Simulator(Environment env, SimpleTimer timer, int numHumans, int numAliens) {
    this.environment = env;
    this.timer = timer;
    this.numHumans = numHumans;
    this.numAliens = numAliens;

    this.humanContexts = new AiContext[numHumans];
    this.alienContexts = new AiContext[numAliens];

    initializeHumans();
    initializeAliens();
    placeWeapons();
  }

  private void initializeHumans() {
    Random random = new Random();
    for (int i = 0; i < numHumans; i++) {
      LifeForm human = new Human("Human " + i, 15, random.nextInt(10)); // Random armor 0-9
      placeLifeForm(human);
      humanContexts[i] = new AiContext(human, environment);
      timer.addTimeObserver(humanContexts[i]);
    }
  }

  private void initializeAliens() {
    Random random = new Random();
    RecoveryBehavior recovery;
    for (int i = 0; i < numAliens; i++) {
      int choice = random.nextInt(3);
      switch (choice) {
        case 0:
          recovery = new RecoveryNone();
          break;
        case 1:
          recovery = new RecoveryLinear(5);
          break;
        case 2:
          recovery = new RecoveryFractional(0.5);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + choice);
      }
      LifeForm alien = new Alien("Alien " + i, 10, recovery);
      placeLifeForm(alien);
      alienContexts[i] = new AiContext(alien, environment);
      timer.addTimeObserver(alienContexts[i]);
    }
  }

  private void placeLifeForm(LifeForm lifeForm) {
    Random random = new Random();
    int row;
    int col;
    do {
      row = random.nextInt(environment.getNumRows() - 1);
      col = random.nextInt(environment.getNumCols() - 1);
    } while (environment.getLifeForm(row, col) != null);
    environment.addLifeForm(lifeForm, row, col);
  }

  private void placeWeapons() {
    Random random = new Random();
    for (int i = 0; i < numHumans + numAliens; i++) {
      Weapon weapon;
      int choice = random.nextInt(3);
      switch (choice) {
        case 0:
          weapon = new Pistol();
          break;
        case 1:
          weapon = new ChainGun();
          break;
        case 2:
          weapon = new PlasmaCannon();
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + choice);
      }
      int row;
      int col;
      do {
        row = random.nextInt(environment.getNumRows());
        col = random.nextInt(environment.getNumCols());
      } while (environment.hasWeapon(row, col));
      environment.addWeapon(weapon, row, col);
    }
  }

  @Override
  public void updateTime(int time) {
    // Called on each timer tick to allow human and alien AIContexts to act
    for (AiContext c : humanContexts) {
      c.updateTime(time);
    }
    for (AiContext c : alienContexts) {
      c.updateTime(time);
    }
    System.out.println("\nRound " + timer.getRound());
  }
}
