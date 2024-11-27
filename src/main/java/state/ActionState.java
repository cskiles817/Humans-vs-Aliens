package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState {
  protected AIContext context;
  protected Environment e;
  protected LifeForm lf;

  ActionState(AIContext context) {
    this.context = context;
  }

  public abstract void executeAction();
}
