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

  @Before
  public void resetEnv() {
    //reset the singleton
    Environment.e = null;
  }

  @Test
  public void testEnvironmentInit() throws EnvironmentException {
    Environment env = Environment.getEnvironment(1,1);
    assertNull(env.getLifeForm(0,0));

    Environment env2 = Environment.getEnvironment(1,1);
    assertEquals(env, env2);
  }


  //Lab 5 tests
  @Test
  public void testAddWeaponToLocation() throws EnvironmentException {
    Weapon w = new Pistol();
    Environment env = Environment.getEnvironment(1,1);

    env.addWeapon(w, 0,0);

    assertEquals(w, env.getWeapons(0,0)[0]);
  }

  @Test
  public void testRemoveWeaponToLocation() throws EnvironmentException {
    Weapon w = new Pistol();
    Environment env = Environment.getEnvironment(1,1);

    env.addWeapon(w, 0,0);

    env.removeWeapon(w,0,0);

    assertNull(env.getWeapons(0,0)[0]);
  }

  @Test
  public void testDistSameRow() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);

    assertEquals(15.0, env.getDistance(0, 0, 0, 3), 0.01);
  }

  @Test
  public void testDistSameCol() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);

    assertEquals(20.0, env.getDistance(4, 0, 0, 0), 0.01);
  }

  @Test
  public void testDistDifRowCol() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);

    assertEquals(14.14, env.getDistance(4, 1, 2, 3), 0.01);
  }



  @Test
  public void testEnvironmentAddLf() throws EnvironmentException {
    Environment env = Environment.getEnvironment(2,3);
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = env.addLifeForm(lf, 0, 1);
    assertTrue(success);
    assertEquals(lf, env.getLifeForm(0,1));
  }


  @Test
  public void testEnvBorderCaseA() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = env.addLifeForm(lf, 0, 0);
    assertTrue(success);
    assertEquals(lf, env.getLifeForm(0,0));
  }

  @Test
  public void testEnvBorderCaseB() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = env.addLifeForm(lf, 4, 4);
    assertTrue(success);
    assertEquals(lf, env.getLifeForm(4,4));
  }


  @Test
  public void environmentRemoveLifeForm() throws EnvironmentException {
    Environment env = Environment.getEnvironment(2,3);
    LifeForm lf = new MockLifeForm("Bob", 40);

    env.addLifeForm(lf, 0, 1);
    env.removeLifeForm(0,1);

    assertNull(env.getLifeForm(0,1));
  }

  @Test
  public void moveNorthWtOrWotObs() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    env.clearBoard();
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    env.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("North");
    env.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face North", "North", lifeForm.getDirection());

    // Test with obstacle
    env.clearBoard();
    env.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    env.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("North");
    env.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }

  @Test
  public void moveSouthWtOrWotObs() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    env.clearBoard();
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    env.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("South");
    env.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face South", "South", lifeForm.getDirection());

    // Test with obstacle
    env.clearBoard();
    env.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    env.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("South");
    env.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }

  @Test
  public void moveWestWtOrWotObs() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    env.clearBoard();
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    env.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("West");
    env.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face West", "West", lifeForm.getDirection());

    // Test with obstacle
    env.clearBoard();
    env.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    env.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("West");
    env.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }

  @Test
  public void moveEastWtOrWotObs() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    env.clearBoard();
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test without obstacles
    env.addLifeForm(lifeForm, 1, 1);
    lifeForm.setDirection("East");
    env.moveLifeForm(lifeForm);
    assertEquals("LifeForm should face East", "East", lifeForm.getDirection());

    // Test with obstacle
    env.clearBoard();
    env.addLifeForm(lifeForm, 1, 1);
    LifeForm obstacle = new LifeForm("Block", 40);
    env.addLifeForm(obstacle, 0, 1);
    lifeForm.setDirection("East");
    env.moveLifeForm(lifeForm);
    assertEquals(1, lifeForm.getRow());
    assertEquals(1, lifeForm.getCol());
  }


  @Test
  public void moveLifeFormIfNearAtFourDifferentEdges() throws EnvironmentException {
    Environment env = Environment.getEnvironment(5,5);
    env.clearBoard();
    LifeForm lifeForm = new LifeForm("Bob", 40);

    // Test North edge
    env.addLifeForm(lifeForm, 0, 2);
    lifeForm.setDirection("North");
    env.moveLifeForm(lifeForm);
    assertEquals(0, lifeForm.getRow());
    assertEquals(2, lifeForm.getCol());

    // Test South edge
    env.clearBoard();
    env.addLifeForm(lifeForm, 4, 2);
    lifeForm.setDirection("South");
    env.moveLifeForm(lifeForm);
    assertEquals(4, lifeForm.getRow());
    assertEquals(2, lifeForm.getCol());

    // Test East edge
    env.clearBoard();
    env.addLifeForm(lifeForm, 2, 4);
    lifeForm.setDirection("East");
    env.moveLifeForm(lifeForm);
    assertEquals(2, lifeForm.getRow());
    assertEquals(4, lifeForm.getCol());

    // Test West edge
    env.clearBoard();
    env.addLifeForm(lifeForm, 2, 0);
    lifeForm.setDirection("West");
    env.moveLifeForm(lifeForm);
    assertEquals(2, lifeForm.getRow());
    assertEquals(0, lifeForm.getCol());
  }
}
