package command;

import environment.Environment;
import lifeform.LifeForm;

public class TurnEastCommand implements Command {

  private Environment environment;


  public TurnEastCommand(Environment env) {
    environment = env;
  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }

    lf.setDirection("East");

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }
}
