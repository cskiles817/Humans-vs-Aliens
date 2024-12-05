package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;

import static org.junit.Assert.assertEquals;

public class TestNoWeaponState {

  private static Environment e;

  @Before
  public void setUp() throws EnvironmentException {
    e = Environment.getEnvironment(12, 12);
    e.clearBoard();
  }

  @Test
  public void testWeaponInCell() throws EnvironmentException {

    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockWeapon w = new MockWeapon();
    e.addWeapon(w, 1, 1);
    e.addLifeForm(l, 1, 1);

    AiContext context = new AiContext(l, e);
    context.execute();
    assertEquals(context.getHasWeaponState(), context.getCurrentState());
  }

  @Test
  public void testNoWeaponInCell() throws EnvironmentException {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    AiContext context = new AiContext(l, e);
    context.execute();
    assertEquals(context.getNoWeaponState(), context.getCurrentState());
  }

  @Test
  public void testIfDead() throws EnvironmentException {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    AiContext context = new AiContext(l, e);
    l.setCurrentLifePoints(0);
    context.execute();
    assertEquals(context.getDeadState(), context.getCurrentState());
  }

}
