package command;

import environment.Environment;
import lifeform.LifeForm;

public class TurnSouthCommand implements Command {

  private Environment environment;

  public TurnSouthCommand(Environment env) {
    environment = env;
  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }

    lf.setDirection("South");

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }
}
