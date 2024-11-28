package state;

public class DeadState extends ActionState {

  DeadState(AIContext context) {
    super(context);
  }

  /**
   *  If the LifeForm had a weapon then remove that weapon and place it in a random Cell in the Environment (that has space).
   */
  public void executeAction() {
  }

}
