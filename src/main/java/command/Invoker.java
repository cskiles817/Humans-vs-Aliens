package command;

import exceptions.EnvironmentException;
import exceptions.WeaponException;

public class Invoker {
  private Command reloadCommand;
  private Command moveCommand;
  private Command turnNorthCommand;
  private Command turnSouthCommand;
  private Command turnEastCommand;
  private Command turnWestCommand;
  private Command attackCommand;
  private Command dropCommand;
  private Command acquireCommand;

  public void setReloadCommand(Command reloadCommand) {
    this.reloadCommand = reloadCommand;
  }

  public void setMoveCommand(Command moveCommand) {
    this.moveCommand = moveCommand;
  }

  public void setTurnNorthCommand(Command turnNorthCommand) {
    this.turnNorthCommand = turnNorthCommand;
  }

  public void setTurnSouthCommand(Command turnSouthCommand) {
    this.turnSouthCommand = turnSouthCommand;
  }

  public void setTurnEastCommand(Command turnEastCommand) {
    this.turnEastCommand = turnEastCommand;
  }

  public void setTurnWestCommand(Command turnWestCommand) {
    this.turnWestCommand = turnWestCommand;
  }

  public void setAttackCommand(Command attackCommand) {
    this.attackCommand = attackCommand;
  }

  public void setDropCommand(Command dropCommand) {
    this.dropCommand = dropCommand;
  }

  public void setAcquireCommand(Command acquireCommand) {
    this.acquireCommand = acquireCommand;
  }

  /**
   * Reload weapon
   */
  public void reload() {
    if (reloadCommand != null) {
      reloadCommand.execute();
    }
  }

  /**
   * Move to facing Direction
   */
  public void move() {
    if (moveCommand != null) {
      moveCommand.execute();
    }
  }

  /**
   * Change direction to North
   */
  public void turnNorth() {
    if (turnNorthCommand != null) {
      turnNorthCommand.execute();
    }
  }

  /**
   * Change direction to South
   */
  public void turnSouth() {
    if (turnSouthCommand != null) {
      turnSouthCommand.execute();
    }
  }

  /**
   * Change direction to East
   */
  public void turnEast() {
    if (turnEastCommand != null) {
      turnEastCommand.execute();
    }
  }

  /**
   * Change direction to West
   */
  public void turnWest() {
    if (turnWestCommand != null) {
      turnWestCommand.execute();
    }
  }

  /**
   * Attack targeted LifeForm
   */
  public void attack() {
    if (attackCommand != null) {
      attackCommand.execute();
    }
  }

  /**
   * Drop held Weapon
   */
  public void drop() {
    if (dropCommand != null) {
      dropCommand.execute();
    }
  }

  /**
   * Pick up weapon
   */
  public void acquire() {
    if (acquireCommand != null) {
      acquireCommand.execute();
    }
  }
}
