package command;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class AcquireCommand implements Command {

  private final Environment environment;


  public AcquireCommand(Environment env) {
    environment = env;
  }

  @Override
  public void execute() {
    LifeForm lf = environment.getLifeForm(environment.focusRow, environment.focusCol);
    if (lf == null) {
      return;
    }

    if (lf.getWeapon() != null) {
      return;
    }

    Weapon[] weapons =  environment.getWeapons(lf.getRow(), lf.getCol());

    for (Weapon weapon : weapons) {
      if (weapon != null) {
        lf.pickUpWeapon(weapon);
        environment.removeWeapon(weapon, lf.getRow(), lf.getCol());
        break;
      }
    }

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }

}
