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
  NoWeaponState(AiContext context, LifeForm lifeform, Environment environment) {
    super(context, lifeform, environment);
  }

  /**
   * Check if I'm dead first.
   * If weapon in cell call acquireWeapon method.
   * If no weapon call search method.
   */
  @Override
  public void executeAction() {
    if (life == null) {
      return;
    }
    if (life.getCurrentLifePoints() > 0) {
      if (env.hasWeapon(life.getRow(), life.getCol())) {
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
    AcquireCommand a = new AcquireCommand(env);
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
    life.setRandomDirection();
    if (!env.moveLifeForm(life)) {
      search();
    }
  }
}
