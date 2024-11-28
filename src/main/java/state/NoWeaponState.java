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
   * Evaluation: Check if I'm dead first.
   * If weapon in cell call acquireWeapon method.
   * If no weapon call search method.
   * Acquire Weapon: Pickup weapon and change to Has Weapon state.
   * Dead: Move to Dead state
   * Search: Turn random direction and move to new Cell.
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
    int dir = (int) (Math.random() * 4);
    switch (dir) {
      case 0:
        l.setDirection("North");
        break;
      case 1:
        l.setDirection("East");
        break;
      case 2:
        l.setDirection("South");
        break;
      case 3:
        l.setDirection("West");
        break;
      default:
        break;
    }
   if (!e.moveLifeForm(l)) {
     search();
   }
  }
}
