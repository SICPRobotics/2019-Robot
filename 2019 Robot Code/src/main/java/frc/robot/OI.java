package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.*;

public class OI 
{
  public static XboxController xbox = new XboxController(1);

  Button buttonA = new JoystickButton(xbox, 1); 
  Button buttonB = new JoystickButton(xbox, 2); 
  Button buttonX = new JoystickButton(xbox, 3); 
  Button buttonY = new JoystickButton(xbox, 4); 
  Button buttonLB = new JoystickButton(xbox, 5); 
  Button buttonRB = new JoystickButton(xbox, 6); 
  Button buttonBack = new JoystickButton(xbox, 7); 
  Button buttonStart = new JoystickButton(xbox, 8); //open
  Button leftThumb = new JoystickButton(xbox, 9); //open
  Button rightThumb = new JoystickButton(xbox, 10); //open
  //Trigger trig = new Trigger();
  XboxTrig leftTrig = new XboxTrig(true);
  XboxTrig rightTrig = new XboxTrig(false);

  Button j1 = new JoystickButton(Robot.j1, 1);
  Button j2 = new JoystickButton(Robot.j1, 2);
  Button j3 = new JoystickButton(Robot.j1, 3);
  Button j4 = new JoystickButton(Robot.j1, 4);

  public OI()
  {
    buttonA.whenPressed(new GoToHeight(0)); //191413
    buttonB.whenPressed(new GoToHeight(762204));
    buttonX.whileHeld(new DriveElevator(.3));
    buttonY.whenPressed(new GoToHeight(1280461));
    
    buttonLB.whenPressed(new ToggleBeak());
    //buttonRB.whenPressed(new MoveParallelogramUp());
    buttonBack.whenPressed(new Calibrate());
    //buttonStart.whenPressed(new HoldParallelDown(true));
    rightTrig.whenActive(new HoldParallelDown(true));
    //right trigger: move parallelogram down while held

    //j1.whenPressed(new StartVisionPID());
    //j1.whenReleased(new DisableVisionPID());

    j2.whenPressed(new Calibrate());
    j3.whileHeld(new DriveElevator(.4));
    j4.whileHeld(new DriveElevator(-.4));

  }

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it onceb
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
