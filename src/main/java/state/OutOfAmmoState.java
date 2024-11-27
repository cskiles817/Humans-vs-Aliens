package state;

import exceptions.WeaponException;

public class OutOfAmmoState extends ActionState {

  OutOfAmmoState(AIContext context) {
    super(context);
  }

  /**
   * Evaluation: If not dead, call reload method. Otherwise call dead method.
   * Dead: Move to Dead state
   * Reload: Reload the weapon, change to Has Weapon state.
   */
  @Override
  public void executeAction() {

  }
}
