package lifeform;

public class MockLifeForm extends LifeForm {

  public MockLifeForm(String name, int points) {
    this(name, points, 0);
    maxSpeed = 5;
  }

  public MockLifeForm(String name, int points, int attackDmg) {
    super(name, points, attackDmg);
  }

  public void setCurrentLifePoints(int lp) {
    currentLifePoints = lp;
  }
}
