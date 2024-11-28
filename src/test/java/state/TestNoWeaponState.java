package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;

import static org.junit.Assert.assertEquals;

public class TestNoWeaponState {

  private static final Environment e;

  static {
    try {
      e = Environment.getEnvironment(12, 12);
    } catch (EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Before
  public void init() {
    e.clearBoard();
  }

  @Test
  public void testWeaponInCell() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockWeapon w = new MockWeapon();
    e.addWeapon(w, 1, 1);
    e.addLifeForm(l, 1, 1);

    AIContext context = new AIContext(l, e);
    NoWeaponState nws = new NoWeaponState(context);
    nws.executeAction();
    assertEquals(context.getNoWeaponState(), context.getCurrentState());
  }

  @Test
  public void testNoWeaponInCell() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    AIContext context = new AIContext(l, e);
    NoWeaponState nws = new NoWeaponState(context);
    nws.executeAction();
    assertEquals(context.getNoWeaponState(), context.getCurrentState());
  }

  @Test
  public void testIfDead() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    l.setCurrentLifePoints(0);
    AIContext context = new AIContext(l, e);
    NoWeaponState nws = new NoWeaponState(context);
    nws.executeAction();
    assertEquals(context.getDeadState(), context.getCurrentState());
  }

}
