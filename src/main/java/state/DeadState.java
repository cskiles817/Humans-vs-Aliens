package state;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * The lifeform is dead
 */
public class DeadState extends ActionState {
  private boolean hasLf;
  private int numWeapons;
  private int row;
  private int col;

  /**
   * Constructor
   * @param context
   */
  DeadState(AIContext context) {
    super(context);
  }

  /**
   *  If the LifeForm had a weapon then remove that weapon and place it in a random Cell in the Environment (that has space).
   */
  public void executeAction() {
    // Move weapon to random cell that has space
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    if (l.hasWeapon()) {
      Weapon removed = l.dropWeapon();
      getNewCell();
      checkNumWeapons();
      e.addWeapon(removed, row, col);
    }
    // Respawn
    getNewCell();
    checkHasLifeForm();
    l.setMaxLifePoints();
    e.addLifeForm(l, row, col);
  }

  /**
   * Check if the current cell can get another weapon
   * if it cant, get a new cell until one is found
   */
  private void checkNumWeapons() {
    while (numWeapons == 2) {
      getNewCell();
    }
  }

  /**
   * Check if the current cell can get a lifeform
   * if it cant, get a new cell until one is found
   */
  private void checkHasLifeForm() {
    while (hasLf) {
      getNewCell();
    }
  }

  /**
   * Get a new random cell, and set variables accordingly
   */
  private void getNewCell() {
    Environment e = context.getEnvironment();
    Object[] newCell = e.getRandomCell();
    hasLf = (boolean) newCell[0];
    numWeapons = (int) newCell[1];
    row = (int) newCell[2];
    col = (int) newCell[3];
  }

}
