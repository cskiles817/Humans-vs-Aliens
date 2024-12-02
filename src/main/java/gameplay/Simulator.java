package gameplay;

import environment.Environment;

public class Simulator implements TimerObserver {
  private Environment env;
  private SimpleTimer timer;
  private int numHumans;
  private int numAliens;

  Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) {
    this.env = e;
    this.timer = timer;
    this.numHumans = numHumans;
    this.numAliens = numAliens;
  }

  public static void main(String[] args) {

  }

  @Override
  public void updateTime(int time) {

  }
}
