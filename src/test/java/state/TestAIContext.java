package state;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.MockLifeForm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestAIContext {

  private static Environment e;

  @Before
  public void init() throws EnvironmentException {
    e = Environment.getEnvironment(12, 12);
    e.clearBoard();
  }

  @Test
  public void testChangeActiveState() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    AiContext context = new AiContext(l, e);
    context.setCurrentState(context.getDeadState());
    assertEquals(context.getCurrentState(), context.getDeadState());
  }

  @Test
  public void testCanGetState() {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    AiContext context = new AiContext(l, e);
    assertNotNull(context.getCurrentState());
  }
}
