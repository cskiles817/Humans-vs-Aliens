package state;

import command.AttackCommand;
import command.MoveCommand;
import environment.Environment;
import lifeform.LifeForm;

/**
 * LifeForm has a weapon
 */
public class HasWeaponState extends ActionState {

  /**
   * Constructor
   * @param context AI Context
   */
  HasWeaponState(AiContext context, LifeForm lifeform, Environment environment) {
    super(context, lifeform, environment);
  }

  /**
   * If target call attackTarget. If no target within range, call
   * search.
   */
  @Override
  public void executeAction() {
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    if (l == null) {
      return;
    }
    if (l.getCurrentLifePoints() > 0) {
      if (e.getTargetedByLifeForm(l) != null) {
        attackTarget();
      } else {
        search();
      }
    } else {
      dead();
    }
  }

  /**
   * Fire the weapon. If out of ammo move to Out of Ammo state
   */
  private void attackTarget() {
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    AttackCommand a = new AttackCommand(e);
    a.execute();
    if (l.getWeapon().getCurrentAmmo() == 0) {
      context.setCurrentState(context.getOutOfAmmoState());
    }
  }

  /**
   * Move to dead state
   */
  private void dead() {
    context.setCurrentState(context.getDeadState());
  }

  /**
   * Turn random, different direction and 50% of time move to new Cell.
   */
  private void search() {
    LifeForm l = context.getLifeForm();
    Environment e = context.getEnvironment();
    l.setRandomDirection();
    boolean move = ((int) (Math.random() * 2) == 0);
    if (move) {
      MoveCommand mv = new MoveCommand(e);
      mv.execute();
    }
  }

}
