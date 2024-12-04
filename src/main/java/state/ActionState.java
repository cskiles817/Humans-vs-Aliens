package state;

import environment.Environment;
import lifeform.LifeForm;

/**
 * Abstract class for States
 */
public abstract class ActionState {
  protected AiContext context;
  protected Environment env;
  protected LifeForm life;

  /**
   * Constructor
   * @param context AI Context
   */
  ActionState(AiContext context, LifeForm life, Environment env) {
    this.context = context;
    this.env = context.getEnvironment();
    this.life = context.getLifeForm();
  }

  /**
   * Execute action
   */
  public abstract void executeAction();
}
