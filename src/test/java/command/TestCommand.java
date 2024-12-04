package command;

import command.*;
import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import weapon.PlasmaCannon;
import weapon.Weapon;
import weapon.ChainGun;

public class TestCommand {

  private Environment environment;
  private LifeForm lifeForm;
  private Weapon chainGun;


  @Before
  public void setUp() throws EnvironmentException {
    environment = Environment.getEnvironment(5, 5);
    environment.clearBoard();
    lifeForm = new LifeForm("Bob", 40);
    chainGun = new ChainGun();
    environment.addLifeForm(lifeForm, 0, 0);
    environment.addWeapon(chainGun, 0, 1);
  }

  @Test
  public void testMoveLifeForm() {
    environment.addLifeForm(lifeForm, 1, 0);
    MoveCommand moveCommand = new MoveCommand(environment);
    environment.moveLifeForm(lifeForm);
    moveCommand.execute();
    assertEquals(1, lifeForm.getRow());
    assertEquals(0, lifeForm.getCol());
  }


  @Test
  public void testReloadPlayersWeapon() {
    lifeForm.pickUpWeapon(chainGun);
    int initialAmmo = chainGun.getCurrentAmmo();
    ReloadCommand reloadCommand = new ReloadCommand(environment);
    reloadCommand.execute();
    assertEquals("Ammo should have been reloaded", initialAmmo, chainGun.getCurrentAmmo());
  }

  @Test
  public void testTurnNorthCommand() {
    TurnNorthCommand turnNorthCommand = new TurnNorthCommand(environment);
    turnNorthCommand.execute();
    assertEquals("LifeForm should face North", "North", lifeForm.getDirection());
  }

  @Test
  public void testTurnSouthCommand() {
    TurnSouthCommand turnSouthCommand = new TurnSouthCommand(environment);
    turnSouthCommand.execute();
    assertEquals("LifeForm should face South", "South", lifeForm.getDirection());
  }

  @Test
  public void testTurnEastCommand() {
    TurnEastCommand turnEastCommand = new TurnEastCommand(environment);
    turnEastCommand.execute();
    assertEquals("LifeForm should face East", "East", lifeForm.getDirection());
  }

  @Test
  public void testTurnWestCommand() {
    TurnWestCommand turnWestCommand = new TurnWestCommand(environment);
    turnWestCommand.execute();
    assertEquals("LifeForm should face West", "West", lifeForm.getDirection());
  }

  @Test
  public void testDropWpnWSpace() {
    environment.addWeapon(chainGun, 0, 1);
    lifeForm.pickUpWeapon(chainGun);
    DropCommand dropCommand = new DropCommand(environment);
    dropCommand.execute();
    assertNull("LifeForm should no longer have the weapon", lifeForm.getWeapon());
  }

  @Test
  public void testDropWpnWNoSpace() {
    environment.removeWeapon(chainGun, 0, 1);
    lifeForm.pickUpWeapon(chainGun);
    DropCommand dropCommand = new DropCommand(environment);
    dropCommand.execute();
    assertNull("Weapon should not be in the environment since there is no space", lifeForm.getWeapon());
  }

  @Test
  public void testAcquireWpnW1n0Available() {
    environment.addWeapon(chainGun, 0, 1);
    lifeForm.pickUpWeapon(chainGun);
    AcquireCommand acquireCommand = new AcquireCommand(environment);
    acquireCommand.execute();
    assertEquals("LifeForm should have acquired the ChainGun", chainGun, lifeForm.getWeapon());
  }

  @Test
  public void testAcquireWWinAvailableBAllEpd() {
    Weapon plasmaCannon = new PlasmaCannon();
    environment.addWeapon(plasmaCannon, 0, 2);
    lifeForm.pickUpWeapon(chainGun);
    AcquireCommand acquireCommand = new AcquireCommand(environment);
    acquireCommand.execute();
    assertEquals("LifeForm should still have the ChainGun", chainGun, lifeForm.getWeapon());
  }
}