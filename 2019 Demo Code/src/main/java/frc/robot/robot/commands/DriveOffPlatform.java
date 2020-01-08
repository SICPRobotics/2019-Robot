package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveOffPlatform extends Command 
{
  Timer timer = new Timer();

  public DriveOffPlatform() 
  {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("DriveOffPlatform init");
    timer.start();
  }

  @Override
  protected void execute() 
  {
    if (timer.get() < 1)
      Robot.driveTrain.drive(-1);
    else if (timer.get() <2 )
      Robot.driveTrain.drive(0);
    else
      Robot.driveTrain.drive(.4);
  }

  @Override
  protected boolean isFinished() 
  {
    System.out.println("timer: " + timer.get());
    if (timer.get() > 4)
      return true;
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("DriveOffPlatform end");
  }

  @Override
  protected void interrupted() 
  {
  }
}
