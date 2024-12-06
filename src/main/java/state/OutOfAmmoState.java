package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * Lifeform does not have any ammo
 */
public class OutOfAmmoState extends ActionState {


  /**
   * Constructor
   *
   * @param context AI context
   */
  OutOfAmmoState(AiContext context, LifeForm lifeform, Environment environment) {
    super(context, lifeform, environment);
  }

  /**
   * Evaluation: If not dead, call reload method. Otherwise call dead method.
   * Dead: Move to Dead state
   * Reload: Reload the weapon, change to Has Weapon state.
   */
  @Override
  public void executeAction() {
    if (life.getCurrentLifePoints() > 0) {
      reload();
    } else {
      dead();
    }
  }

  /**
   * Move to dead state
   */
  private void dead() {
    context.setCurrentState(context.getDeadState());
  }

  /**
   * Reload the weapon, changes to Has Weapon state
   */
  private void reload() {
    life.getWeapon().reload();
    context.setCurrentState(context.getHasWeaponState());
  }
}