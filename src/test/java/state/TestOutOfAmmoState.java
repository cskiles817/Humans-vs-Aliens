//package state;
//
//import environment.Environment;
//import org.junit.Test;
//import weapon.MockWeapon;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class TestOutOfAmmoState
//{
//  Environment e = new Environment(12, 12);
//  MockWeapon weapon = new MockWeapon();
//
//
//  @Test
//  public void testInitialization()
//  {
//
//    AIContext context = new AIContext(1, e);
//    OutOfAmmoState state = new OutOfAmmoState(context);
//    context.setCurrentState(state);
//    assertEquals(state, context.getCurrentState());
//  }
//
//  @Test
//  public void testReload()
//  {
//    OutOfAmmoState state = new OutOfAmmoState();
//    AIContext context = new AIContext(1, e);
//    context.setCurrentState(state);
//    weapon.reload();
//    state.handle(context, weapon);
//    assertTrue(context.getCurrentState() instanceof ReadyState);
//  }
//
//  @Test
//  public void testMoveToCorrectState()
//  {
//    OutOfAmmoState state = new OutOfAmmoState();
//    AIContext context = new AIContext(1, e);
//    context.setCurrentState(state);
//    weapon.reload();
//    state.handle(context, weapon);
//    assertTrue(context.getCurrentState() instanceof ReadyState);
//    weapon.useAmmo();
//    state.handle(context, weapon);
//    assertTrue(context.getCurrentState() instanceof OutOfAmmoState);
//  }
//
//  @Test
//  public void testIfDead()
//  {
//    MockLifeForm l = new MockLifeForm("Mock", 1);
//    e.addLifeForm(l, 1, 1);
//    AIContext context = new AIContext(1, e);
//    l.setCurrentLifePoints(0);
//    context.execute();
//    assertEquals(context.getDeadState(), context.getCurrentState());
//  }
//}
