package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

/**
 * Keep track of states
 */
public class AiContext implements TimerObserver {

  private LifeForm life;
  private Environment env;
  private static ActionState currentState;
  private final DeadState deadState = new DeadState(this);
  private final HasWeaponState hasWeaponState = new HasWeaponState(this);
  private final NoWeaponState noWeaponState = new NoWeaponState(this);
  private final OutOfAmmoState outOfAmmoState = new OutOfAmmoState(this);

  /**
   * Constructor
   * @param lf lifeform
   * @param e  environment
   */
  public AiContext(LifeForm lf, Environment e) {
    this.life = lf;
    this.env = e;
    if (lf.getCurrentLifePoints() == 0) {
      currentState = deadState;
    } else if (!lf.hasWeapon()) {
      currentState = noWeaponState;
    } else {
      if (lf.getWeapon().getCurrentAmmo() == 0) {
        currentState = outOfAmmoState;
      } else {
        currentState = hasWeaponState;
      }
    }
  }

  /**
   * execute the current state's action
   */
  public void execute() {
    currentState.executeAction();
  }

  /**
   * Get the current state
   * @return currentState
   */
  ActionState getCurrentState() {
    return currentState;
  }

  /**
   * Get the dead state
   * @return deadState
   */
  DeadState getDeadState() {
    return deadState;
  }

  /**
   * Get the environment
   * @return e
   */
  public Environment getEnvironment() {
    return env;
  }

  /**
   * Get has weapon state
   * @return hasWeaponState
   */
  public HasWeaponState getHasWeaponState() {
    return hasWeaponState;
  }

  /**
   * Get the lifeform
   * @return lf
   */
  public LifeForm getLifeForm() {
    return life;
  }

  /**
   * Get no weapon state
   * @return noWeaponState
   */
  public NoWeaponState getNoWeaponState() {
    return noWeaponState;
  }

  /**
   * Get out of ammo state
   * @return
   */
  public OutOfAmmoState getOutOfAmmoState() {
    return outOfAmmoState;
  }

  /**
   * Change this context's state
   * @param state the state to change to
   */
  public void setCurrentState(ActionState state) {
    currentState = state;
  }

  /**
   * Go to next round
   */
  @Override
  public void updateTime(int time) {
    execute();
  }
}
