package command;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class DropCommand implements Command {

  private Environment environment;


  public DropCommand(Environment env) {
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
    System.out.println("DROP: " + lf.getWeapon());

    if (environment.addWeapon(lf.getWeapon(), lf.getRow(), lf.getCol())) {
      lf.dropWeapon();
      environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
    }
  }
}
