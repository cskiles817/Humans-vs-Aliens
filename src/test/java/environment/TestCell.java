package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.MockWeapon;
import weapon.Weapon;
import org.junit.Test;
/**
 * The test cases for the Cell class
 *
 */

public class TestCell {

  /**
   * At initialization, the Cell should be empty and not contain a
   * LifeForm.
   */
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
    assertNull(cell.getWeapon1());
    assertNull(cell.getWeapon2());
  }

  /**
   * Checks to see if we change the LifeForm held by the Cell that
   * getLifeForm properly responds to this change.
   */
  @Test
  public void testAddRemoveLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    // Add  a Lifeform
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob,cell.getLifeForm());

    // Remove Lifeform
    cell.removeLifeForm();
    assertNull(cell.getLifeForm());
  }

  @Test
  public void testLifeFormAlreadyOnCell() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();

    cell.addLifeForm(bob);

    boolean success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob,cell.getLifeForm());
  }

  @Test
  public void testAddRemoveWeapon() {
    Cell cell = new Cell();
    MockWeapon weapon1 = new MockWeapon();
    MockWeapon weapon2 = new MockWeapon();

    // Add a Weapons
    cell.addWeapon(weapon1);
    assertEquals(weapon1, cell.getWeapon1());
    cell.addWeapon(weapon2);
    assertEquals(weapon2, cell.getWeapon2());

    // Remove Weapons
    cell.removeWeapon(weapon1);
    assertNull(cell.getWeapon2());
    cell.removeWeapon(weapon2);
    assertNull(cell.getWeapon1());
  }

  @Test
  public void testCannotPlaceWeaponInFullCell() {
    Cell cell = new Cell();
    MockWeapon weapon1 = new MockWeapon();
    MockWeapon weapon2 = new MockWeapon();
    MockWeapon weapon3 = new MockWeapon();

    assertTrue(cell.addWeapon(weapon1));
    assertTrue(cell.addWeapon(weapon2));
    assertFalse(cell.addWeapon(weapon3));

    assertEquals(weapon1, cell.getWeapon1());
    assertEquals(weapon2, cell.getWeapon2());
  }
}

