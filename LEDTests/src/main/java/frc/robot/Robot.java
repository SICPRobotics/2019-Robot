package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot 
{  
  Joystick j;
  DigitalInput leds;

  @Override
  public void robotInit() 
  {
    j = new Joystick(0);
    leds = new DigitalInput(0);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopPeriodic() 
  {
    System.out.println(j.getRawAxis(1));
    //leds.set(j.getRawAxis(1));
  }

  @Override
  public void testPeriodic() {
  }
}
