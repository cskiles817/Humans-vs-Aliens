package state;

import command.AcquireCommand;
import command.AttackCommand;
import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.MockWeapon;

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
    MockWeapon w = new MockWeapon();
    e.addLifeForm(l, 1, 1);
    e.addWeapon(w, 1, 1);
    AcquireCommand a = new AcquireCommand(e);
    a.execute();
    AIContext context = new AIContext(l, e);
    context.execute();
    assertEquals(context.getHasWeaponState(), context.getCurrentState());
  }

  @Test
  public void testTargetSameType() {
    Human h1 = new Human("h1", 1, 0);
    Human h2 = new Human("h2", 1, 0);
    MockWeapon w = new MockWeapon();
    e.addLifeForm(h1, 1, 1);
    e.addWeapon(w, 2, 1);
    e.addLifeForm(h2, 2, 1);
    h2.pickUpWeapon(w);
    try {
      h2.attack(h1, (int) e.getDistance(h1, h2));
    } catch (WeaponException | EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
    AIContext context = new AIContext(h1, e);
    context.execute();
    assertEquals(1, h1.getCurrentLifePoints());
  }

  @Test
  public void testTargetDifferentType() {
    Human h = new Human("h1", 1, 0);
    Alien a = new Alien("h2", 1);
    MockWeapon w = new MockWeapon();
    AIContext context = new AIContext(h, e);
    e.addLifeForm(h, 1, 1);
    e.addWeapon(w, 2, 1);
    e.addLifeForm(a, 2, 1);
    a.pickUpWeapon(w);
    try {
      a.attack(h, (int) e.getDistance(a, h));
    } catch (WeaponException | EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
    context.execute();
    assertEquals(0, h.getCurrentLifePoints());
  }

  @Test
  public void testValidOneShotLeft() {
    Human h = new Human("h1", 1, 0);
    Alien a = new Alien("h2", 1);
    MockWeapon w = new MockWeapon();
    AIContext context = new AIContext(h, e);
    w.setCurrentAmmo(1);
    e.addLifeForm(h, 1, 1);
    e.addWeapon(w, 2, 1);
    e.addLifeForm(a, 2, 1);
    a.pickUpWeapon(w);
    try {
      a.attack(h, (int) e.getDistance(h, a));
    } catch (WeaponException | EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
    context.execute();
    assertEquals(0, h.getCurrentLifePoints());
  }

  @Test
  public void testTargetOutOfRange() {
    Human h1 = new Human("h1", 1, 0);
    Human h2 = new Human("h2", 1, 0);
    MockWeapon w = new MockWeapon();
    AIContext context = new AIContext(h1, e);
    e.addLifeForm(h1, 1, 1);
    e.addWeapon(w, 2, 1);
    e.addLifeForm(h2, 11, 1);
    h2.pickUpWeapon(w);
    try {
      h2.attack(h1, (int) e.getDistance(h1, h2));
    } catch (WeaponException | EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
    context.execute();
    assertEquals(1, h1.getCurrentLifePoints());
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
