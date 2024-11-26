package command;

import environment.Environment;

import java.awt.Dimension;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;


public class InvokerBuilder {

  /**
   * init Commands with Button display
   * @param environment
   * @return
   */
  public Invoker loadCommands(Environment environment) {

    Invoker invoker = new Invoker();

    invoker.setReloadCommand(new ReloadCommand(environment));
    invoker.setMoveCommand(new MoveCommand(environment));
    invoker.setTurnNorthCommand(new TurnNorthCommand(environment));
    invoker.setTurnSouthCommand(new TurnSouthCommand(environment));
    invoker.setTurnEastCommand(new TurnEastCommand(environment));
    invoker.setTurnWestCommand(new TurnWestCommand(environment));
    invoker.setAttackCommand(new AttackCommand(environment));
    invoker.setDropCommand(new DropCommand(environment));
    invoker.setAcquireCommand(new AcquireCommand(environment));


    JFrame frame = new JFrame("Command GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 300);
    frame.setLayout(new GridLayout(1, 2));

    JButton reloadButton = new JButton("Reload");
    reloadButton.addActionListener(e -> invoker.reload());

    JButton attackButton = new JButton("Attack");
    attackButton.addActionListener(e -> invoker.attack());

    JButton acquireButton = new JButton("Acquire");
    acquireButton.addActionListener(e -> invoker.acquire());

    JButton dropButton = new JButton("Drop");
    dropButton.addActionListener(e -> invoker.drop());

    JButton moveButton = new JButton("Move");
    moveButton.addActionListener(e -> invoker.move());

    JButton turnNorthButton = new JButton("Turn North");
    turnNorthButton.addActionListener(e -> invoker.turnNorth());

    JButton turnSouthButton = new JButton("Turn South");
    turnSouthButton.addActionListener(e -> invoker.turnSouth());

    JButton turnEastButton = new JButton("Turn East");
    turnEastButton.addActionListener(e -> invoker.turnEast());

    JButton turnWestButton = new JButton("Turn West");
    turnWestButton.addActionListener(e -> invoker.turnWest());

    JPanel movementPanel = new JPanel(new GridLayout(3, 3));
    movementPanel.add(new JLabel());
    movementPanel.add(turnNorthButton);
    movementPanel.add(new JLabel());
    movementPanel.add(turnWestButton);
    movementPanel.add(moveButton);
    movementPanel.add(turnEastButton);
    movementPanel.add(new JLabel());
    movementPanel.add(turnSouthButton);
    movementPanel.add(new JLabel());


    JPanel controlsPanel = new JPanel();
    controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

    attackButton.setPreferredSize(new Dimension(150, 50));
    JPanel acquireDropPanel = new JPanel(new GridLayout(1, 2));

    attackButton.setPreferredSize(new Dimension(150, 50));
    JPanel usePanel = new JPanel(new GridLayout(1, 2));

    acquireDropPanel.add(acquireButton);
    acquireDropPanel.add(dropButton);

    usePanel.add(attackButton);
    usePanel.add(reloadButton);

    controlsPanel.add(Box.createVerticalStrut(10));
    controlsPanel.add(Box.createVerticalStrut(10));
    controlsPanel.add(acquireDropPanel);
    controlsPanel.add(usePanel);

    frame.add(movementPanel);
    frame.add(controlsPanel);
    frame.setVisible(true);

    return invoker;
  }

}

