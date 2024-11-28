package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAIContext {

  private static final Environment e;

  static {
    try {
      e = Environment.getEnvironment(12, 12);
    } catch (EnvironmentException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Before
  public void init() {
    e.clearBoard();
  }

  @Test
  public void testChangeActiveState() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    AIContext context = new AIContext(l, e);
    context.setCurrentState(context.getDeadState());
    assertEquals(context.getCurrentState(), context.getDeadState());
  }

  @Test
  public void testCanGetState() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    AIContext context = new AIContext(l, e);
    assertNotNull(context.getCurrentState());
  }
}
