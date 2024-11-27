package environment;

import lifeform.LifeForm;

public class CellInfo {
  int r, c;
  LifeForm lf;
  boolean w1, w2;

  public CellInfo(int row, int col, LifeForm life, boolean weapon1, boolean weapon2) {
    r = row;
    c = col;
    lf = life;
    w1 = weapon1;
    w2 = weapon2;
  }

  public int getRow() {
    return r;
  }

  public int getCol() {
    return c;
  }

  public boolean hasLife() {
    return lf != null;
  }

  public boolean hasWeapon1() {
    return w1;
  }

  public boolean hasWeapon2() {
    return w2;
  }
}