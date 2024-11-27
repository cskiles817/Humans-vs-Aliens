package state;

import environment.Environment;
import gameplay.TimerObserver;
import lifeform.LifeForm;

public class AIContext implements TimerObserver {

  private LifeForm lf;
  private Environment e;

  AIContext(LifeForm lf, Environment e) {
    this.lf = lf;
    this.e = e;
  }

  /**
   * execute the current state's action
   */
  void execute() {

  }

  ActionState getCurrentState() {
    return null;
  }

  DeadState getDeadState() {
    return null;
  }

  public Environment getEnvironment() {
    return e;
  }

  public HasWeaponState HasWeaponState() {
    return null;
  }

  public LifeForm getLifeForm() {
    return null;
  }

  public NoWeaponState getNoWeaponState() {
    return null;
  }

  public OutOfAmmoState getOutOfAmmoState() {
    return null;
  }

  /**
   * Change this context's state
   * @param state
   */
  public void setCurrentState(ActionState state) {

  }

  @Override
  public void updateTime(int time) {

  }
}
