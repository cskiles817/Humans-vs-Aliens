package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;

import static org.junit.Assert.assertEquals;

public class TestDeadState {

  private static Environment e;
  private MockLifeForm l;

  @Before
  public void init() throws EnvironmentException {
    e = Environment.getEnvironment(12, 12);
    e.clearBoard();
    l = new MockLifeForm("mock", 1);
  }

  @Test
  public void testWithWeapon() {
    MockWeapon w = new MockWeapon();
    e.addLifeForm(l, 1, 1);
    e.addWeapon(w, 1, 1);
    l.pickUpWeapon(w);
    l.setCurrentLifePoints(0);
    AiContext context = new AiContext(l, e);
    context.setCurrentState(context.getDeadState());
    context.execute();
    assertEquals(1, l.getCurrentLifePoints());
  }

  @Test
  public void testWithoutWeapon() {
    MockLifeForm l = new MockLifeForm("mock", 1);
    e.addLifeForm(l, 1, 1);
    l.setCurrentLifePoints(0);
    AiContext context = new AiContext(l, e);
    context.execute();
    assertEquals(1, l.getCurrentLifePoints());
  }
}
