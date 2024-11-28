package state;

import org.junit.Test;

public class TestOutOfAmmoState
{

  @Test
  public void testInitialization()
  {

  }

  @Test
  public void testReload()
  {

  }

  @Test
  public void testMoveToCorrectState()
  {

  }

  @Test
  public void testIfDead()
  {
    MockLifeForm l = new MockLifeForm("Mock", 1);
    e.addLifeForm(l, 1, 1);
    AIContext context = new AIContext(1, e);
    l.setCurrentLifePoints(0);
    context.execute();
    assertEquals(context.getDeadState(), context.getCurrentState());
  }
}
