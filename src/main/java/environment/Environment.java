package environment;

import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import gameplay.EnvironmentObserver;
import lifeform.LifeForm;
import weapon.Weapon;

import java.util.ArrayList;
import java.util.List;


public class Environment {
  static Environment e;

  public static Cell[][] cells;

  public int focusRow = 0;
  public int focusCol = 0;


  private List<EnvironmentObserver> observers = new ArrayList<>();

  public void addObserver(EnvironmentObserver observer) {
    observers.add(observer);
  }

  private void notifyObservers(int row, int col, LifeForm lifeform,
                               Weapon weapon1, Weapon weapon2) {
    observers.forEach(obs -> obs.updateCell(row, col, lifeform, weapon1, weapon2));
  }

  public void setLifeForm(int row, int col, LifeForm lf) {
    notifyObservers(row, col, lf, getWeapons(row, col)[0], getWeapons(row, col)[1]);
  }



  /**
   * Adds a Lifeform to the cell
   *
   * @return if the Lifeform has been successfully added to the cell
   */

  public boolean addLifeForm(LifeForm entity, int row, int col) {
    boolean added = cells[row][col].addLifeForm(entity);
    if (added) {
      entity.setLocation(row, col);
    }
    return added;
  }


  public boolean addWeapon(Weapon weapon, int row, int col) {
    return cells[row][col].addWeapon(weapon);
  }

  /**
   * Remove all weapons and lifeform from cell
   */
  public void clearBoard() {
    for (int r = 0; r < cells.length; r++) {
      for (int c = 0; c < cells[0].length; c++) {
        cells[r][c].removeLifeForm();
        cells[r][c].removeWeapon(cells[r][c].getWeapon1());
        cells[r][c].removeWeapon(cells[r][c].getWeapon2());
      }
    }
  }


  /**
   * Creates a Enviroment with provided rows and cols of the Cell class
   */
  public Environment(int row, int col) {
    cells = new Cell[row][col];

    for (int r = 0; r < row; r++) {
      for (int c = 0; c < col; c++) {
        cells[r][c] = new Cell();
      }
    }

  }

  /**
   * Singleton of the current Environment
   *
   * @param row size of row
   * @param col size of col
   * @return current Environment
   * @throws EnvironmentException
   */
  public static Environment getEnvironment(int row, int col) throws EnvironmentException {
    if (e == null) {
      e = new Environment(row, col);
    }

    if (row > cells.length || col > cells[0].length) {
      throw new EnvironmentException("Row or Column Out of Bounds");
    }

    return e;
  }

  public int getNumCols() {
    return cells[0].length;
  }

  public int getNumRows() {
    return cells.length;
  }

  public Weapon[] getWeapons(int row, int col) {
    return new Weapon[]{cells[row][col].getWeapon1(), cells[row][col].getWeapon2()};
  }

  public void removeWeapon(Weapon weapon, int row, int col) {
    cells[row][col].removeWeapon(weapon);
  }


  /**
   * @return the LifeForm in this Cell.
   */

  public LifeForm getLifeForm(int row, int col) {
    if (cells[row][col] == null) {
      cells[row][col] = null;
      return null;
    } else {
      return cells[row][col].getLifeForm();
    }
  }

  /**
   * Removes the LifeForm in this Cell.
   */

  public void removeLifeForm(int row, int col) {
    if (cells[row][col] != null) {
      cells[row][col].removeLifeForm();
    }

  }

  /**
   * gets distance between two pairs of rows and columns
   * @param row1
   * @param col1
   * @param row2
   * @param col2
   * @return
   * @throws EnvironmentException
   */
  public double getDistance(int row1, int col1, int row2, int col2) throws EnvironmentException {
    if (row1 < 0
            || col1 < 0
            || row1 >= cells.length
            || col1 >= cells[0].length
            || row2 < 0
            || col2 < 0
            || row2 >= cells.length
            || col2 >= cells[0].length) {
      throw new EnvironmentException("Row or Column Out of Bounds");
    }
    int x = col2 - col1;
    int y = row2 - row1;
    return (Math.sqrt(x * x + y * y) * 5.0);
  }

  /**
   * gets distance between two lifeforms
   * @param a
   * @param b
   * @return
   * @throws EnvironmentException
   */
  public double getDistance(LifeForm a, LifeForm b) throws EnvironmentException {
    if (a.getRow() == -1
            || a.getCol() == -1
            || b.getRow() == -1
            || b.getCol() == -1) {
      throw new EnvironmentException("LifeForm location not set");
    }

    return getDistance(a.getRow(), a.getCol(), b.getRow(), b.getCol());
  }

  /**
   * Lifeform wants to move locations in direction
   * @param entity Lifeform
   * @return
   */
  public boolean moveLifeForm(LifeForm entity) {
    int row = entity.getRow();
    int col = entity.getCol();

    if (row < 0 || col < 0 || row >= getNumRows() || col >= getNumCols()) {
      System.out.println("Target position is out of bounds.");
      return false;
    }

    int targetRow = entity.getRow();
    int targetCol = entity.getCol();
    int speed = entity.getMaxSpeed();

    switch (entity.getDirection()) {
      case "North":
        targetRow -= speed;
        break;
      case "South":
        targetRow += speed;
        break;
      case "East":
        targetCol += speed;
        break;
      case "West":
        targetCol -= speed;
        break;
      default:
        System.out.println("Unknown direction. Cannot move.");
        return false;
    }

    if (targetRow < 0 || targetCol < 0 || targetRow >= getNumRows() || targetCol >= getNumCols()) {
      System.out.println("Movement goes out of bounds.");
      return false;
    }

    if (cells[targetRow][targetCol].isOccupied()) {
      System.out.println(
              "Target cell is occupied by another LifeForm.");
      return false;
    }

    int currentRow = entity.getRow();
    int currentCol = entity.getCol();
    if (currentRow != -1 && currentCol != -1) {
      cells[currentRow][currentCol].removeLifeForm();
    }

    cells[targetRow][targetCol].addLifeForm(entity);
    entity.setLocation(targetRow, targetCol);
    System.out.println(entity.getName() + " moved to ("
            + targetRow + ", "
            + targetCol + ") facing "
            + entity.getDirection());

    notifyObservers(row, col, null, getWeapons(row,col)[0], getWeapons(row,col)[1]);
    notifyObservers(targetRow, targetCol, entity,
            getWeapons(targetRow,targetCol)[0],
            getWeapons(targetRow,targetCol)[1]);

    return true;
  }

  /**
   * See what lifeForm a lifeForm is targeting
   * @param lf Current Lifeform
   * @return Lifeform or null
   */
  public LifeForm getTargetedByLifeForm(LifeForm lf) {
    if (lf.getDirection() == "North") {
      for (int r = lf.getRow() - 1; r >= 0; r--) {
        if (getLifeForm(r, lf.getCol()) != null) {
          return getLifeForm(r, lf.getCol());
        }
      }
    } else if (lf.getDirection() == "South") {
      for (int r = lf.getRow() + 1; r < cells.length; r++) {
        if (getLifeForm(r, lf.getCol()) != null) {
          return getLifeForm(r, lf.getCol());
        }
      }
    } else if (lf.getDirection() == "East") {
      for (int c = lf.getCol() + 1; c < cells[0].length; c++) {
        if (getLifeForm(lf.getRow(), c) != null) {
          return getLifeForm(lf.getRow(), c);
        }
      }
    } else if (lf.getDirection() == "West") {
      for (int c = lf.getCol() - 1; c >= 0; c--) {
        if (getLifeForm(lf.getRow(), c) != null) {
          return getLifeForm(lf.getRow(), c);
        }
      }
    }

    return null;
  }

  public int getCol() {
    return focusCol;
  }
}