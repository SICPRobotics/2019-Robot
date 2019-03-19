package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Calibrate extends Command 
{
  Timer timer = new Timer();
  double lastReading, newReading;
  int nextSec = 1;
 
  public Calibrate() 
  {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("Calibrate init");
    timer.start();
    lastReading = Robot.elevator.elevatorHeight();
    nextSec = 1;
  }

  @Override
  protected void execute() 
  {
    Robot.elevator.slowDrive(.075); //.082
  }

  @Override
  protected boolean isFinished() 
  {
    if (timer.get() > 10)
      return true;
    else if (timer.get() > nextSec)
    {
      newReading = Robot.elevator.elevatorHeight();
      if (newReading - lastReading < 2000)
        return true;
      lastReading = newReading;
      nextSec++;
    }
    return false;
  }

  @Override
  protected void end() 
  {
    Robot.elevator.setEncPosition(0);
    System.out.println("Calibrate end" + Robot.elevator.elevatorHeight());
    Robot.elevator.slowDrive(0);
    
  }

  @Override
  protected void interrupted() {
  }
}
