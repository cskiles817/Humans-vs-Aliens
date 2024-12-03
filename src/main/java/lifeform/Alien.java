package lifeform;

import exceptions.RecoveryRateException;
import gameplay.TimerObserver;
import recovery.RecoveryBehavior;

public class Alien extends LifeForm implements TimerObserver {
  private int recoverRate;
  private RecoveryBehavior rb;
  public int myTime;
  public int count;

  /**
   * Sets an alien with a name and max lifepoints
   *
   * @param name
   * @param maxHitPoints
   */
  public Alien(String name, int maxHitPoints) {
    super(name, maxHitPoints, 10);
    maxSpeed = 2;
  }

  /**
   * Create an instance
   *
   * @param name         the name of the life for
   * @param maxHitPoints the current starting life points of the life form
   * @param behavior     the behavior class
   */
  public Alien(String name, int maxHitPoints, RecoveryBehavior behavior) {
    super(name, maxHitPoints, 10);
    rb = behavior;
    maxSpeed = 2;
  }

  /**
   * Create an instance
   *
   * @param name         the name of the life for
   * @param maxHitPoints the current starting life points of the life form
   * @param recoveryRate recovery rate
   */
  public Alien(String name, int maxHitPoints, RecoveryBehavior behavior, int recoveryRate)
          throws RecoveryRateException {
    super(name, maxHitPoints, 10);
    recoverRate = recoveryRate;
    rb = behavior;
    count = 1;
    maxSpeed = 2;

    if (recoverRate < 0) {
      throw new RecoveryRateException("Recovery Rate cannot be less than zero");
    }
  }

  /**
   * Gets the Alien recovery rate
   * @return rate of recovery
   */
  public int getRecoveryRate() {
    return recoverRate;
  } //maxHitP + recoverRate

  /**
   * controls the alien recovery
   */
  protected void recover() {
    int newLife = rb.calculateRecovery(currentLifePoints, maxLifePoints);
    currentLifePoints = newLife;
  }

  /**
   * Update the time
   *
   * @param time adds time
   */
  public void updateTime(int time) {

    myTime = time;

    if ((currentLifePoints < maxLifePoints) && ((recoverRate + 1) % count == 0)) {
      recover();
      count += 1;
    } else if ((currentLifePoints < maxLifePoints) && (count > 1)) {
      count += 1;
    } else if (currentLifePoints < maxLifePoints) {
      recover();
    } else
    {
      count = 1;
    }
  }
}


