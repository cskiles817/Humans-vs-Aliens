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
    if (life == null) {
      return;
    }
    if (life.getCurrentLifePoints() > 0) {
      if (env.getTargetedByLifeForm(life) != null) {
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
    AttackCommand a = new AttackCommand(env);
    a.execute();
    if (life.getWeapon() != null && life.getWeapon().getCurrentAmmo() == 0) {
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
    life.setRandomDirection();
    boolean move = ((int) (Math.random() * 2) == 0);
    if (move) {
      MoveCommand mv = new MoveCommand(env);
      mv.execute();
    }
  }

}
