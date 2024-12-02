package environment;

import lifeform.LifeForm;

public class CellInfo {
  int rows;
  int column;
  LifeForm lf;
  static boolean w1;
  static boolean w2;

  /**
   *
   * @param row of cell
   * @param col of cell
   * @param life of lifeform
   * @param weapon1 first acquired weapon
   * @param weapon2 second acquired weapon
   */
  public CellInfo(int row, int col, LifeForm life, boolean weapon1, boolean weapon2) {
    rows = row;
    column = col;
    lf = life;
    w1 = weapon1;
    w2 = weapon2;
  }

  public int getRows() {
    return rows;
  }

  public int getColumn() {
    return column;
  }

  public boolean hasLife() {
    return lf != null;
  }

  public static boolean hasWeapon1() {
    return w1;
  }

  public static boolean hasWeapon2() {
    return w2;
  }
}