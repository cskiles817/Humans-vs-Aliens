package command;

import environment.Environment;
import lifeform.LifeForm;

public class AttackCommand implements Command {

  private Environment environment;


  public AttackCommand(Environment env) {
    environment = env;
  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }

    LifeForm targetLf = environment.getTargetedByLifeForm(lf);

    if (targetLf.getClass().equals(lf.getClass())) {
      return;
    }

    try {
      lf.attack(targetLf, (int)environment.getDistance(lf, targetLf));
    } catch (Exception ex) {
      return;
    }

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
    environment.setLifeForm(targetLf.getRow(), targetLf.getCol(), targetLf);
  }
}
