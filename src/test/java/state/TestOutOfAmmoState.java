package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestOutOfAmmoState {

  private static Environment e;

  @Before
  public void setUp() throws EnvironmentException {
    e = Environment.getEnvironment(12, 12);
    e.clearBoard();
  }

  @Test
  public void testInitialization(){
    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockWeapon w = new MockWeapon();
    w.setCurrentAmmo(0);
    AiContext context = new AiContext(l, e);
    OutOfAmmoState o = new OutOfAmmoState(context, l, e);
    assertNotNull(o);
  }

  @Test
  public void testReload() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockWeapon w = new MockWeapon();
    e.addLifeForm(l, 0, 0);
    e.addWeapon(w, 0, 0);
    l.pickUpWeapon(w);
    w.setCurrentAmmo(0);
    AiContext context = new AiContext(l, e);
    context.execute();
    assertEquals(w.getMaxAmmo(), w.getCurrentAmmo());
  }

  @Test
  public void testMoveToCorrectState() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockWeapon w = new MockWeapon();
    e.addLifeForm(l, 0, 0);
    e.addWeapon(w, 0, 0);
    l.pickUpWeapon(w);
    w.setCurrentAmmo(0);
    AiContext context = new AiContext(l, e);
    context.execute();
    assertEquals(context.getHasWeaponState(), context.getCurrentState());
  }

  @Test
  public void testIfDead() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockWeapon w = new MockWeapon();
    e.addLifeForm(l, 0, 0);
    e.addWeapon(w, 0, 0);
    l.pickUpWeapon(w);
    w.setCurrentAmmo(0);
    l.setCurrentLifePoints(0);
    AiContext context = new AiContext(l, e);
    context.execute();
    assertEquals(context.getDeadState(), context.getCurrentState());
  }
}
