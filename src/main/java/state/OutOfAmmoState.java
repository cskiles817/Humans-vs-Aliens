package state;

import exceptions.WeaponException;

/**
 * Lifeform does not have any ammo
 */
public class OutOfAmmoState extends ActionState
{


  /**
   * Constructor
   *
   * @param context AI context
   */
  OutOfAmmoState(AIContext context)
  {
    super(context);
  }

  /**
   * Evaluation: If not dead, call reload method. Otherwise call dead method.
   * Dead: Move to Dead state
   * Reload: Reload the weapon, change to Has Weapon state.
   */
  @Override
  public void executeAction()
  {
    if (l.getCurrentLifePoints > 0)
    {
      aquireWeapon();
    } else
    {
      dead();
    }
  }

  /**
   * Move to dead state
   */
  private void dead()
  {
    context.setCurrentState(context.getDeadState());
  }

  /**
   * Reload the weapon, changes to Has Weapon state
   */
  private void reload()
  {
    try
    {
      context.getLifeform.getweapon.reload();
      context.setCurrentState(context.getHasWeaponState());
    } catch (WeaponException e)
    {
      System.err.println("Couldn't reload the weapon: " + e.getMessage());
    }
  }
}