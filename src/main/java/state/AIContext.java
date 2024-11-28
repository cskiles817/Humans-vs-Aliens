package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AIContext implements TimerObserver {

  private LifeForm lf;
  private Environment e;
  private ActionState currentState;
  private final DeadState deadState = new DeadState(this);
  private final HasWeaponState hasWeaponState = new HasWeaponState(this);
  private final NoWeaponState noWeaponState = new NoWeaponState(this);
  private final OutOfAmmoState outOfAmmoState = new OutOfAmmoState(this);

  AIContext(LifeForm lf, Environment e) {
    this.lf = lf;
    this.e = e;
    if (lf.getCurrentLifePoints() == 0) {
      currentState = deadState;
    } else if (!lf.hasWeapon()){
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
  void execute() {
    currentState.executeAction();
  }

  ActionState getCurrentState() {
    return currentState;
  }

  DeadState getDeadState() {
    return deadState;
  }

  public Environment getEnvironment() {
    return e;
  }

  public HasWeaponState getHasWeaponState() {
    return hasWeaponState;
  }

  public LifeForm getLifeForm() {
    return lf;
  }

  public NoWeaponState getNoWeaponState() {
    return noWeaponState;
  }

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

  @Override
  public void updateTime(int time) {

  }
}
