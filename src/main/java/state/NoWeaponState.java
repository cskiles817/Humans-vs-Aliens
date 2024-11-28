package state;

import command.AcquireCommand;

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
    if (l == null) {
      return;
    }
    if (l.getCurrentLifePoints() > 0) {
      if (e.hasWeapon()) {
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
    AcquireCommand a = new AcquireCommand(e);
    a.execute();
    context.setCurrentState(context.HasWeaponState());
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
   l.setRandomDirection();
   if (!e.moveLifeForm(l)) {
     search();
   }
  }
}
