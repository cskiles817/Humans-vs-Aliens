package state;

import command.AcquireCommand;
import environment.Environment;
import lifeform.LifeForm;

/**
 * LifeForm does not have a weapon
 */
public class NoWeaponState extends ActionState {


  /**
   * Constructor
   * @param context AI context
   */
  NoWeaponState(AIContext context) {
    super(context);
  }

  /**
   * Check if I'm dead first.
   * If weapon in cell call acquireWeapon method.
   * If no weapon call search method.
   */
  @Override
  public void executeAction() {
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    if (l == null) {
      return;
    }
    if (l.getCurrentLifePoints() > 0) {
      if (e.hasWeapon(l.getRow(), l.getCol())) {
        acquireWeapon();
      } else {
        search();
      }
    } else {
      dead();
    }
  }

  /**
   * Pickup weapon and change to Has Weapon state
   */
  private void acquireWeapon() {
    context.setCurrentState(context.getHasWeaponState());
    Environment e = context.getEnvironment();
    AcquireCommand a = new AcquireCommand(e);
    a.execute();
  }

  /**
   * Move to dead state
   */
  private void dead() {
    context.setCurrentState(context.getDeadState());
  }

  /**
   * Turn random direction and move to new Cell
   */
  private void search() {
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    l.setRandomDirection();
    if (!e.moveLifeForm(l)) {
      search();
    }
  }
}
