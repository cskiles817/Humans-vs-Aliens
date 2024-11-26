package command;

import environment.Environment;
import lifeform.LifeForm;

public class ReloadCommand implements Command {

  private Environment environment;


  public ReloadCommand(Environment env) {
    environment = env;

  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }
    if (lf.getWeapon() == null) {
      return;
    }
    lf.getWeapon().reload();
    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }
}
