package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {
  /**
   * @return the LifeForm in this Cell.
   */

  private LifeForm lifeform = null;
  private Weapon[] weapon = {null, null};


  /**
   * Tries to add the LifeForm to the Cell. Will not add if a
   * LifeForm is already present.
   *
   * @param entity the lifeform held in the cell
   * @return true if the LifeForm was added to the Cell, false otherwise.
   */

  public boolean addLifeForm(LifeForm entity) {
    if (lifeform == null) {
      lifeform = entity;
      return true;
    } else {
      return false;
    }
  }


  /**
   * Go through all weapon slots to see if one is available.
   * @param weapon
   * @return True of slot was available
   */
  public boolean addWeapon(Weapon weapon) {
    for (int i = 0; i < this.weapon.length; i++) {
      if (this.weapon[i] == null) {
        //overkill for 2 slots. Makes sure there is not same gun in twice
        for (int j = 0; j < this.weapon.length; j++) {
          if (this.weapon[j] == weapon) {
            return false;
          }
        }

        this.weapon[i] = weapon;
        return true;
      }
    }
    return false;
  }

  public Weapon getWeapon1() {
    return weapon[0];
  }

  public Weapon getWeapon2() {
    return weapon[1];
  }

  /**
   * How many weapons in cell
   * @return Int of Weapons in current cell
   */
  public int getWeaponsCount() {
    int value = 0;
    for (int i = 0; i < this.weapon.length; i++) {
      if (this.weapon[i] != null) {
        value++;
      }
    }

    return value;
  }

  /**
   * Remove weapon, checks slot 0 first then slot 1
   * @param weapon
   * @return Weapon selected
   */
  public Weapon removeWeapon(Weapon weapon) {
    for (int i = 0; i < this.weapon.length; i++) {
      if (this.weapon[i] == weapon) {
        this.weapon[i] = null;

        if (this.weapon[1] != null) {
          System.out.println("WORKING");
          this.weapon[0] = this.weapon[1];
          this.weapon[1] = null;
        }

        return weapon;
      }
    }

    return null;
  }


  /**
   * @return the LifeForm in this Cell.
   */
  public LifeForm getLifeForm() {
    if (lifeform != null) {
      return lifeform;
    } else {
      return null;
    }
  }


  public void removeLifeForm() {
    lifeform = null;
  }

  public boolean isOccupied() {
    return lifeform != null;
  }

}
