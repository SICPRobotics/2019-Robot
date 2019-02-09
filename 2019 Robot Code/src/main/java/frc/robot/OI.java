package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI 
{
  XboxController xbox = new XboxController(RobotMap.k_xbox);

  Button buttonA = new JoystickButton(xbox, 1); 
  Button buttonB = new JoystickButton(xbox, 2); 
  Button buttonX = new JoystickButton(xbox, 3); 
  Button buttonY = new JoystickButton(xbox, 4); 
  Button buttonLB = new JoystickButton(xbox, 5); //open
  Button buttonRB = new JoystickButton(xbox, 6); //open
  Button buttonBack = new JoystickButton(xbox, 7); //open
  Button buttonStart = new JoystickButton(xbox, 8); //open
  Button leftThumb = new JoystickButton(xbox, 9); //open
  Button rightThumb = new JoystickButton(xbox, 10); //open
 
  Button j1 = new JoystickButton(Robot.j1, 1);

  public OI()
  {
    buttonA.whenPressed(new MoveElevator(RobotMap.k_cargoShip));
    buttonB.whenPressed(new MoveElevator(RobotMap.k_midHatch));
    buttonX.whenPressed(new ToggleBeak());
    buttonY.whenPressed(new MoveElevator(RobotMap.k_topHatch));

    j1.whenPressed(new StartVisionPID());
    j1.whenReleased(new DisableVisionPID());
  }

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
