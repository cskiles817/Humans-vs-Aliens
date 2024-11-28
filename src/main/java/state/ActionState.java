package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  protected AIContext context;
  protected Environment e;
  protected LifeForm l;

  ActionState(AIContext context) {
    this.context = context;
    this.e = context.getEnvironment();
    this.l = context.getLifeForm();
  }

  public abstract void executeAction();
}
