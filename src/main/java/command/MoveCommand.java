package command;

import environment.Environment;
import lifeform.LifeForm;

public class MoveCommand implements Command {

  private Environment environment;


  public MoveCommand(Environment env) {
    environment = env;

  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow,environment.focusCol);
    if (lf == null) {
      return;
    }

    environment.moveLifeForm(lf);
  }
}
