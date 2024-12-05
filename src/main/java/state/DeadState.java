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
  DeadState(AiContext context, LifeForm lifeform, Environment environment) {
    super(context, lifeform, environment);
  }

  /**
   *  If the LifeForm had a weapon then remove that weapon and
   *  place it in a random Cell in the Environment (that has space).
   */
  public void executeAction() {
    // Move weapon to random cell that has space
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    if (l.hasWeapon()) {
      Weapon removed = l.dropWeapon();
      do {
        getNewCell();
      } while (numWeapons == 2);
      e.addWeapon(removed, row, col);
    }
    // Respawn
    do {
      getNewCell();
    } while (hasLf);
    //l.setMaxLifePoints(0); //FIX
    e.addLifeForm(l, row, col);
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
