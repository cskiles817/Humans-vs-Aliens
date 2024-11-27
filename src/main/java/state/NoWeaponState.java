package state;

public class NoWeaponState extends ActionState {

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

  }
}
