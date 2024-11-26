package command;

import environment.Environment;
import lifeform.LifeForm;

public class TurnWestCommand implements Command {

  private Environment environment;


  public TurnWestCommand(Environment env) {
    environment = env;

  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }

    lf.setDirection("West");

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }
}
