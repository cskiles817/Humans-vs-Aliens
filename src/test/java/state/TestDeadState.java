package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;

import static org.junit.Assert.assertEquals;

public class TestDeadState {

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
  public void testWithWeapon() {
    MockLifeForm l = new MockLifeForm("mock", 1);
    MockWeapon w = new MockWeapon();
    e.addLifeForm(l, 1, 1);
    e.addWeapon(w, 1, 1);
    l.pickUpWeapon(w);
    l.setCurrentLifePoints(0);
    AIContext context = new AIContext(l, e);
    context.execute();
    assertEquals(1, l.getCurrentLifePoints());
  }

  @Test
  public void testWithoutWeapon() {
    MockLifeForm l = new MockLifeForm("mock", 1);
    e.addLifeForm(l, 1, 1);
    l.setCurrentLifePoints(0);
    AIContext context = new AIContext(l, e);
    context.execute();
    assertEquals(1, l.getCurrentLifePoints());
  }
}
