package command;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class AcquireCommand implements Command {

  private Environment environment;


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

    for (int i = 0; i < weapons.length; i++) {
      if (weapons[i] != null) {
        lf.pickUpWeapon(weapons[i]);
        environment.removeWeapon(weapons[i], lf.getRow(), lf.getCol());
        break;
      }
    }

    environment.setLifeForm(environment.focusRow, environment.focusCol, lf);
  }

}
