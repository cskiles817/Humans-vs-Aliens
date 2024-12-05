package environment;

import exceptions.EnvironmentException;
import gameplay.EnvironmentObserver;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;
import weapon.Pistol;
import weapon.Weapon;

import static org.junit.Assert.*;

public class TestEnvironment {

  private EnvironmentObserver observer;
  private static Environment e;

  @Before
  public void resetEnv() throws EnvironmentException {
    e = Environment.getEnvironment(12, 12);
    e.clearBoard();
  }

  @Test
  public void testMove() {

    MockLifeForm l = new MockLifeForm("Mock", 1);
    MockLifeForm l2 = new MockLifeForm("Mock", 1);
    MockLifeForm l3 = new MockLifeForm("Mock", 1);
    MockLifeForm l4 = new MockLifeForm("Mock", 1);
    MockLifeForm l5 = new MockLifeForm("Mock", 1);

    l.setDirection("South");

    assertTrue(e.addLifeForm(l, 1, 5));
    assertTrue(e.addLifeForm(l2, 6, 5));
    e.moveLifeForm(l);
    assertEquals(5, l.getRow());
  }


  @Test
  public void testEnvironmentInit() throws EnvironmentException {
    assertNull(e.getLifeForm(0,0));

    Environment env2 = Environment.getEnvironment(12,12);
    assertEquals(e, env2);
  }


  //Lab 5 tests
  @Test
  public void testAddWeaponToLocation() throws EnvironmentException {
    Weapon w = new Pistol();

    e.addWeapon(w, 0,0);

    assertEquals(w, e.getWeapons(0,0)[0]);
  }

  @Test
  public void testRemoveWeaponToLocation() throws EnvironmentException {
    Weapon w = new Pistol();

    e.addWeapon(w, 0,0);

    e.removeWeapon(w,0,0);

    assertNull(e.getWeapons(0,0)[0]);
  }

  @Test
  public void testDistSameRow() throws EnvironmentException {
    assertEquals(15.0, e.getDistance(0, 0, 0, 3), 0.01);
  }

  @Test
  public void testDistSameCol() throws EnvironmentException {
    assertEquals(20.0, e.getDistance(4, 0, 0, 0), 0.01);
  }

  @Test
  public void testDistDifRowCol() throws EnvironmentException {
    assertEquals(14.14, e.getDistance(4, 1, 2, 3), 0.01);
  }



  @Test
  public void testEnvironmentAddLf() throws EnvironmentException {
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = e.addLifeForm(lf, 0, 1);
    assertTrue(success);
    assertEquals(lf, e.getLifeForm(0,1));
  }


  @Test
  public void testEnvBorderCaseA() throws EnvironmentException {
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = e.addLifeForm(lf, 0, 0);
    assertTrue(success);
    assertEquals(lf, e.getLifeForm(0,0));
  }

  @Test
  public void testEnvBorderCaseB() throws EnvironmentException {
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = e.addLifeForm(lf, 4, 4);
    assertTrue(success);
    assertEquals(lf, e.getLifeForm(4,4));
  }


  @Test
  public void environmentRemoveLifeForm() throws EnvironmentException {
    LifeForm lf = new MockLifeForm("Bob", 40);

    e.addLifeForm(lf, 0, 1);
    e.removeLifeForm(0,1);

    assertNull(e.getLifeForm(0,1));
  }

  @Test
  public void moveNorthWtOrWotObs() throws EnvironmentException {
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    e.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("North");
    e.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face North", "North", lifeForm.getDirection());

    // Test with obstacle
    e.clearBoard();
    e.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    e.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("North");
    e.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }

  @Test
  public void moveSouthWtOrWotObs() throws EnvironmentException {
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    e.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("South");
    e.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face South", "South", lifeForm.getDirection());

    // Test with obstacle
    e.clearBoard();
    e.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    e.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("South");
    e.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }

  @Test
  public void moveWestWtOrWotObs() throws EnvironmentException {
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    e.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("West");
    e.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face West", "West", lifeForm.getDirection());

    // Test with obstacle
    e.clearBoard();
    e.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    e.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("West");
    e.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }

  @Test
  public void moveEastWtOrWotObs() throws EnvironmentException {
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    e.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("East");
    e.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face East", "East", lifeForm.getDirection());

    // Test with obstacle
    e.clearBoard();
    e.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    e.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("East");
    e.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }


  @Test
  public void moveLifeFormIfNearAtFourDifferentEdges() throws EnvironmentException {
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test North edge
    e.addLifeForm(lifeForm, 0, 2);
    lifeForm.setDirection("North");
    e.moveLifeForm(lifeForm);
    assertEquals(0, lifeForm.getRow());
    assertEquals(2, lifeForm.getCol());

    // Test South edge
    e.clearBoard();
    e.addLifeForm(lifeForm, 4, 2);
    lifeForm.setDirection("South");
    e.moveLifeForm(lifeForm);
    assertEquals(4, lifeForm.getRow());
    assertEquals(2, lifeForm.getCol());

    // Test East edge
    e.clearBoard();
    e.addLifeForm(lifeForm, 2, 4);
    lifeForm.setDirection("East");
    e.moveLifeForm(lifeForm);
    assertEquals(2, lifeForm.getRow());
    assertEquals(4, lifeForm.getCol());

    // Test West edge
    e.clearBoard();
    e.addLifeForm(lifeForm, 2, 0);
    lifeForm.setDirection("West");
    e.moveLifeForm(lifeForm);
    assertEquals(2, lifeForm.getRow());
    assertEquals(0, lifeForm.getCol());
  }
}
