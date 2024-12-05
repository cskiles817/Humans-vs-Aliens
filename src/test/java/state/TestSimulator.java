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


public class TestSimulator {

  private static Environment e;

  @Before
  public void setUp() throws EnvironmentException {
    e = Environment.getEnvironment(12, 12);
    e.clearBoard();
  }

  SimpleTimer t = new SimpleTimer(1000);

  @Test
  public void testPopulatesBoard() {
    e.clearBoard();
    new Simulator(e, t, 10, 10);
    //Simulator.main(null);
    int humanCount = 0;
    int alienCount = 0;
    int weaponCount = 0;
    for (int r = 0; r < 12; r++) {
      for (int c = 0; c < 12; c++) {
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

    assertEquals(10, humanCount);
    assertEquals(10, alienCount);
    assertEquals(20, humanCount + alienCount);
    assertEquals(20, weaponCount);
  }

  //@Test
  public void testTimeUpdatesAIContext() {
    Simulator s = new Simulator(e, t, 5, 5);

    s.updateTime(0);

  }
}
