
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI 
{
  public static XboxController xbox = new XboxController(1);

  Button buttonX = new JoystickButton(xbox, 1); 
  Button buttonA = new JoystickButton(xbox, 2); 
  Button buttonB = new JoystickButton(xbox, 3); //open
  Button buttonY = new JoystickButton(xbox, 4); 
  Button buttonLB = new JoystickButton(xbox, 5); 
  Button buttonRB = new JoystickButton(xbox, 6); 
  Button buttonBack = new JoystickButton(xbox, 7); 
  Button buttonStart = new JoystickButton(xbox, 8); //open
  Button leftThumb = new JoystickButton(xbox, 9); //open
  Button rightThumb = new JoystickButton(xbox, 10); //open
  XboxTrig leftTrig = new XboxTrig(true);
  XboxTrig rightTrig = new XboxTrig(false);

  Button j1 = new JoystickButton(Robot.j1, 1);
  Button j2 = new JoystickButton(Robot.j1, 2);
  Button j3 = new JoystickButton(Robot.j1, 3);
  Button j4 = new JoystickButton(Robot.j1, 4);

  public OI()
  {
    buttonA.whenPressed(new GoToHeight(inchesToElevatorHeight(3.5714))); //100000
    //low: 16.5, 19

    //buttonB.whenPressed(new GoToHeight(779204));
    buttonB.whenPressed(new GoToHeight(inchesToElevatorHeight(30.8287))); //863204
    //middle: 44, 47

    //buttonY.whenPressed(new GoToHeight(1292000));
    buttonY.whenPressed(new GoToHeight(inchesToElevatorHeight(48.8214))); //1367000
    //high:

    //300000));//1314461));
    
    buttonBack.whileHeld(new DriveElevator(-.3));
    buttonStart.whileHeld(new DriveElevator(.3));


    buttonLB.whenPressed(new ToggleBeak());
    buttonRB.whenPressed(new Calibrate());
    rightTrig.whenActive(new HoldParallelDown(true));
    leftTrig.whenActive(new HoldParallelDown(false));

    j2.whenPressed(new Invert());

  }

  public int inchesToElevatorHeight(double inches) {
    return (int) (inches * 28000);
  }
}
