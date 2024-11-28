package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * Abstract class for States
 */
public abstract class ActionState {
  protected AIContext context;
  protected Environment e;
  protected LifeForm l;

  /**
   * Constructor
   * @param context AI Context
   */
  ActionState(AIContext context) {
    this.context = context;
    this.e = context.getEnvironment();
    this.l = context.getLifeForm();
  }

  /**
   * Execute action
   */
  public abstract void executeAction();
}
