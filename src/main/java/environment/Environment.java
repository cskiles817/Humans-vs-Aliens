package environment;

import exceptions.EnvironmentException;
import gameplay.EnvironmentObserver;
import lifeform.LifeForm;
import weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Environment {
  private static Environment e;

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
   * Creates an Environment with provided rows and cols of the Cell class
   */
  private Environment(int row, int col) {
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

//    if (row > cells.length || col > cells[0].length) {
//      throw new EnvironmentException("Row or Column Out of Bounds");
//    }

    return e;
  }

  public int getNumCols() {
    return cells[0].length;
  }

  public int getNumRows() {
    return cells.length;
  }

  public Weapon[] getWeapons(int row, int col) {
    if (isValidCell(row, col)) {
      return new Weapon[]{cells[row][col].getWeapon1(), cells[row][col].getWeapon2()};
    } else {
      return null;
    }
  }

  /**
   * @param row of weapon
   * @param col of weapon
   * @return weapon
   */
  public boolean hasWeapon(int row, int col) {
    Weapon w1;
    Weapon w2;
    w1 = getWeapons(row, col)[0];
    w2 = getWeapons(row, col)[1];
    return w1 != null || w2 != null;
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
   *
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
   *
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
   *
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

    int speed = entity.getMaxSpeed();
    //System.out.println(entity.getName() + " speed = " + speed);
    int targetRow = row;
    int targetCol = col;
    switch (entity.getDirection()) {
      case "North":
        targetRow -= speed;
        if (targetRow <= 0) {
          targetRow = 0;
        }
        while (!isValidCell(targetRow, targetCol) || cells[targetRow][targetCol].isOccupied() && targetRow != row) {
          targetRow++;
        }
        break;
      case "South":
        targetRow += speed;
        if (targetRow > getNumRows() - 1) {
          targetRow = getNumRows() - 1;
        }
        while (!isValidCell(targetRow, targetCol) || cells[targetRow][targetCol].isOccupied() && targetRow != row) {
          targetRow--;
        }
        break;
      case "East":
        targetCol += speed;
        if (targetCol > getNumCols() - 1) {
          targetCol = getNumCols() - 1;
        }
        while (!isValidCell(targetRow, targetCol) || cells[targetRow][targetCol].isOccupied() && targetCol != col) {
          targetCol++;
        }

        break;
      case "West":
        targetCol -= speed;
        if (targetCol <= 0) {
          targetCol = 0;
        }
        while (!isValidCell(targetRow, targetCol) || cells[targetRow][targetCol].isOccupied() && targetCol != col) {
          targetCol--;
        }
        break;
      default:
        System.out.println("Unknown direction. Cannot move.");
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

    notifyObservers(row, col, null, getWeapons(row, col)[0], getWeapons(row, col)[1]);
    notifyObservers(targetRow, targetCol, entity,
            getWeapons(targetRow, targetCol)[0],
            getWeapons(targetRow, targetCol)[1]);

    return true;
  }

  /**
   * See what lifeForm a lifeForm is targeting
   *
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

  /**
   * Returns info about a random cell
   */
  public Object[] getRandomCell() {
    Object[] ob = new Object[4];
    int randRow = (int) (Math.random() * 11);
    int randCol = (int) (Math.random() * 11);
    int numWeapons = cells[randRow][randCol].getWeaponsCount();
    boolean hasLifeForm = (cells[randRow][randCol].getLifeForm() != null);
    ob[0] = hasLifeForm;
    ob[1] = numWeapons;
    ob[2] = randRow;
    ob[3] = randCol;
    return ob;
  }

  boolean isValidCell(int row, int col) {
    return (row >= 0 && row < cells.length && col >= 0 && col < cells[0].length);
  }

}