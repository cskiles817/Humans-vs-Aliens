package command;

import environment.Environment;
import lifeform.LifeForm;

public class TurnNorthCommand implements Command {

  private Environment environment;

  public TurnNorthCommand(Environment env) {
    environment = env;
  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }

    lf.setDirection("North");

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }
}
