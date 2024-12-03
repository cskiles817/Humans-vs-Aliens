package state;

import environment.Environment;
import exceptions.EnvironmentException;
import gameplay.SimpleTimer;
import gameplay.Simulator;
import lifeform.Human;
import lifeform.LifeForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSimulator {
  private static Environment e;
  private static SimpleTimer t = new SimpleTimer();

  static {
    try {
      e = Environment.getEnvironment(5, 5);
    } catch (EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Before
  public void clearBoard() {
    e.clearBoard();
  }

  @Test
  public void testPopulatesBoard(){
    Simulator s = new Simulator(e, t, 5, 5);
    int humanCount = 0;
    int alienCount = 0;
    int weaponCount = 0;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        LifeForm l = e.getLifeForm(r, c);
        if (l != null) {
          if (l instanceof Human) {
            humanCount++;
          }
          else {
            alienCount++;
          }
        }
        if (e.getWeapons(r, c)[0] != null) {
          weaponCount++;
        }
        if (e.getWeapons(r, c)[1] != null) {
          weaponCount++;
        }
      }
    }
    assertEquals(10, humanCount + alienCount);
    assertEquals(5, humanCount);
    assertEquals(5, alienCount);
    assertEquals(10, weaponCount);
  }

  @Test
  public void testTimeUpdatesAIContext() {
    Simulator s = new Simulator(e, t, 5, 5);

    s.updateTime(0);

  }
}
