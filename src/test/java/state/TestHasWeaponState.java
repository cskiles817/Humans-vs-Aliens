package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHasWeaponState {

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
  public void testNoTarget() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    AIContext context = new AIContext(l, e);
    HasWeaponState hws = new HasWeaponState(context);
    hws.executeAction();
    //assertEquals();
  }

  @Test
  public void testTargetSameType() {

  }

  @Test
  public void testTargetDifferentType() {

  }

  @Test
  public void testValidOneShotLeft() {

  }

  @Test
  public void testTargetOutOfRange() {

  }

  @Test
  public void testIfDead(){
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    l.setCurrentLifePoints(0);
    AIContext context = new AIContext(l, e);
    HasWeaponState hws = new HasWeaponState(context);
    hws.executeAction();
    assertEquals(context.getDeadState(), context.getCurrentState());
  }
}
