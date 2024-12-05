package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * Abstract class for States
 */
public abstract class ActionState {
  protected AiContext context;
  protected Environment e;
  protected LifeForm l;

  /**
   * Constructor
   * @param context AI Context
   */
  ActionState(AiContext context, LifeForm life, Environment env) {
    this.context = context;
    this.e = env;
    this.l = life;
  }

  /**
   * Execute action
   */
  public abstract void executeAction();
}
